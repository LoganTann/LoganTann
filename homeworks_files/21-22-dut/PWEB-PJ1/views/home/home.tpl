<?php
require './views/common/commonHead.tpl';
?>

<?php
if (empty($_SESSION['loggedin'])) {
    require("./views/common/navbarVisiteur.tpl");
} else {
    require("./views/common/navbarSub.tpl");
}

?>

<header class="container homeHead white-text">
    <h3><b>Bienvenue sur Paris Descar-z</b></h3>
    <h4>Votre plateforme Business To Business parisienne de location</h4>
</header>

<main class="white homeMain">
    <div class="container">
        <?php if (isset($Cars)) { ?>
            <h4>Notre catalogue : </h4>
            <p class="taller"><a href="?page=vehicle&action=getCars" class="yellow-text text-darken-2">Consulter uniquement les voitures disponibles</a></p>
            <div class="row">
                <?php foreach ($Cars as $car) : ?>
                    <div class="col s12 m6 xl4">
                        <?php require("./views/home/card.tpl"); ?>
                    </div>
                <?php endforeach; ?>
            </div>
        <?php
        } else {
            echo 'Toutes nos voitures ont été réservées, désolé.';
        }
        ?>
    </div>
</main>

<style>
    /* style propre à la page d'accueil */

    body {
        background-image: url("./views/static/background.png");
        background-size: contain;
    }

    footer {
        background: white;
    }

    nav.bg-custom-2 {
        background: rgba(255, 255, 255, 0.25);
        box-shadow: 0 8px 32px 0 rgba(31, 38, 135, 0.37);
        border: 1px solid rgba(255, 255, 255, 0.18);
    }

    @media screen and (max-width: 800px) {
        body {
            background-size: auto 100vh;
            background-position: center 0;
        }

        .homeHead {
            height: 70%;
            aspect-ratio: initial;
        }
    }
</style>

<?php
require './views/common/commonFoot.tpl';
?>