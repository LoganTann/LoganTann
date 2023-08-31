<?php

/**
 * Page d'accueil d'administration.
 * Route : ?page=admin&action=index
 */
function index() {
    $data = [
        "connected" => false,
        "box-color" => "orange",
        "box-message" => empty($_SESSION['adminConnected']) ? "Pour accéder à l'espace administration, une identification est (logiquement) nécessaire !<br>
            Hint de mot de passe : c'est la pire prononciation de &laquo;json&raquo; !" : ""
    ];
    // Traitement du mot de passe, si envoyé
    if (isset($_POST['password'])) {
        $password_hashed = '$2y$10$xLI4nqFTEzLvmlW9xS5jWeYVWZxVpORPqjUSVA.DBQ/3/1fMW65GG';
        if (password_verify($_POST['password'], $password_hashed)) {
            $_SESSION['adminConnected'] = true;
            $_SESSION['loggedin'] = false;
        } else {
            $data['box-color'] = "red";
            $data['box-message'] = 'Mot de passe incorrect. C\'était <span style="font-family: monospace">jizon</span>.';
        }
    }
    // passage d'une variable à la vue
    if (isset($_GET['customMsg'])) {
        if ($_GET['customMsg'] == 'logout_OK') {
            $data['box-color'] = "green";
            $data['box-message'] = 'Déconnection effectuée avec succès';
        }
    }
    // passage d'une variable à la vue
    if (isset($_SESSION['adminConnected'])) {
        $data['connected'] = true;
        // récupréation des voitures à afficher
        require("model/cars.php");
        $Cars = getCarsBD();

        // gestion de l'évènement "supprimer une voiture".
        if (isset($_POST["event_carRemove"])) {
            if (isset($_POST["carId"]) && deleteCar($_POST["carId"])) {
                $data['box-color'] = "green";
                $data['box-message'] = "Voiture ID='". $_POST["carId"]."' supprimée";
            } else {
                $data['box-color'] = "red";
                $data['box-message'] = "Erreur interne lors de la suppression";
            }
        }
    }    
    require("./views/admin/dashboard.tpl");
}

/**
 * Permet de se déconnecter
 * Route : ?page=admin&action=logout
 */
function logout() {
    unset($_SESSION['adminConnected']);
    header('Location: ?page=admin&action=index&customMsg=logout_OK');
}

/**
 * Page de gestion des voitures.
 * Permet d'ajouter une nouvelle voiture, ou de mettre une voiture en rupture de stock
 * Route: ?page=admin&action=manageCars
 */
function manageCar() {
    // données qui seront envoyées à la vue.
    $data = ["msgs" => [], "boxGreen"=>false];
    require("./model/cars.php");
    // On reçois l'évènement que l'admin veut ajouter une voiture dans la bdd.
    if (isset($_POST["event_carAdd"])) {
        // récupération des données du formulaire
        $carType = $_POST["carType"];
        $carPrice = $_POST["carPrice"];
        $carCaract = json_encode([
            "typeEnergie" => $_POST["carCaract_typeEnergie"],
            "nbPlaces" => intval($_POST["carCaract_nombreDePlaces"]),
            "automatique" => !isset($_POST["carCaract_automatique"])
        ]);
        $carPhoto = $_POST["carPhoto"];
        $carEtatL = $_POST["carEtatL"];
        
        // traitement d'upload de fichiers
        $target_file = "./writeable/$carPhoto";
        if (move_uploaded_file($_FILES["carPhoto__file"]["tmp_name"], $target_file)) {
            if (addCar($carType, $carPrice, $carCaract, $target_file, $carEtatL)) {
                $data["msgs"][] = "Requête exécutée avec succès";
                $data["boxGreen"] = true;
            } else {
                $data["msgs"][] = "Problème SQL !";
            }
        } else {
            $data["msgs"][] = "Echec... Avez-vous spécifié une image ?";
        }
    }
    require("./views/admin/manageCar.tpl");
}
