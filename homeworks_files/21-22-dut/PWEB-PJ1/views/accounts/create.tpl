<?php
require "./views/common/commonHead.tpl";
require "./views/common/navbarVisiteur.tpl";
?>

<main class="container">
    <h1>S'inscrire</h1><br>

    <?php
    if ($errors) {
        echo '<ul class="browser-default">';
        foreach ($errors as $error) {
            echo "<li>$error</li>";
        }
        echo '</ul>';
    } ?>

    <div class="row">
        <form class="col s12 l8 push-l2" action="index.php?page=accounts&action=create" method="post">
            <p>Complétez les informations à propos du responsable de l'entreprise et de la méthode de connection :
            <p>
            <div class="row">
                <div class="input-field col s12 m6">
                    <label for="nom">Nom du représentant :</label>
                    <input type="text" id="nom" name="nom">
                </div>
                <div class="input-field col s12 m6">
                    <label for="pseudo">Identifiant :</label>
                    <input type="text" id="pseudo" name="pseudo">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <label for="mdp">Mot de passe :</label>
                    <input type="password" id="mdp" name="mdp">
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <label for="email">E-mail de notification :</label>
                    <input type="email" id="email" name="email">
                </div>
            </div>
            <p>
                Parlez-nous de votre entreprise :
            <p>
            <div class="input-field">
                <label for="nomE">Nom d'entreprise :</label>
                <input type="text" id="nomE" name="nomE">
            </div>
            <label for="adresseE">Adresse d'entreprise :</label>
            <input type="text" id="adresseE" name="adresseE">
    </div>
    <button type="submit" class="btn waves-effect waves-light">
        Envoyer <i class="material-icons right">send</i>
    </button>
    <a href="?page=accounts&action=connect">Vous possedez déjà un compte ?</a>
    </form>
    </div>
</main>
<?php
require "./views/common/commonFoot.tpl";
?>