<?php
function valid_registration($user_info) {
	$errors = array();
	if (!preg_match("/^[\w\- ]+$/", $user_info["nom"])) {
		$errors[] = "Votre nom de famille ne doit pas contenir de caractères spéciaux !";
	}
	if (!preg_match("/^[\w\- ]+$/", $user_info["pseudo"])) {
		$errors[] = "Votre pseudo ne doit pas contenir de caractères spéciaux !";
	}
	if (strlen($user_info["mdp"]) < 8) {
		$errors[] = "Entrez un mot de passe d'au moins 8 caractères !";
	}
	if (!filter_var($user_info["email"], FILTER_VALIDATE_EMAIL)) {
		$errors[] = "Entrez un email sous la forme user@host.domain !";
	}
	return $errors;
}

function new_user($user_info) {
	require('model/connectBD.php');
	$sql  = "INSERT INTO `client` (`nom`, `pseudo`, `mdp`, `email`, `nomE`, `adresseE`) VALUES (:nom, :pseudo, :mdp, :email, :nomE, :adresseE)";
	try {
		$commande = $pdo->prepare($sql);
		$commande->bindParam(':nom', 		$user_info["nom"]);
		$commande->bindParam(':pseudo',		$user_info["pseudo"]);
		$commande->bindParam(':mdp', 		$user_info["mdp"]);
		$commande->bindParam(':email',	 	$user_info["email"]);
		$commande->bindParam(':nomE', 		$user_info["nomE"]);
		$commande->bindParam(':adresseE', 	$user_info["adresseE"]);
		if ($commande->execute()) {
			return $pdo->lastInsertId("id");
		}
	}
	catch (PDOException $e) {
		echo utf8_encode("Echec de insert : " . $e->getMessage() . "\n");
		die();
	}
	return -1;
}

function verif_bd($pseudo,$mdp,&$user_info) {
	require('model/connectBD.php');
	$sql="SELECT * FROM `client` WHERE pseudo=:pseudo";
	try {
		$commande = $pdo->prepare($sql);
		$commande->bindParam(':pseudo', $pseudo);
		// $commande->bindParam(':mdp', $mdp);
		$bool = $commande->execute();
		if ($bool) {
			$resultat = $commande->fetchAll(PDO::FETCH_ASSOC);
		}
	}
	catch (PDOException $e) {
		echo utf8_encode("Echec de select : " . $e->getMessage() . "\n");
		die();
	}
	if (count($resultat) == 0) {
		$user_info=array();
		return false; 
	}
	else {
        if(password_verify($mdp,$resultat[0]['mdp'])) {
		$user_info = $resultat[0];
        return true;} else {
            return false;
        }
	}
}

function getNbCars($id){
    require('model/connectBD.php');
    $sql = "SELECT count(*) AS nb FROM `vehicule` WHERE etatL=:id";
    try {
        $commande = $pdo->prepare($sql);
        $commande->bindParam(':id', $id);
        if ($commande->execute()) {
            $resultat = $commande->fetchALL(PDO::FETCH_ASSOC);
        }
    }
    catch (PDOException $e) {
        echo utf8_encode("Echec de select : " . $e->getMessage() . "\n");
        die();
    }
    if (count($resultat) == 0) {
        return false;
    }
    else {
        return $resultat[0];
    }
}
?>