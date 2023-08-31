<?php
require "./views/common/commonHead.tpl";
require "./views/common/navbarVisiteur.tpl";
?>

<main class="container">
    <h3>Accédez dès maintenant à votre espace entreprise</h3>

    <?php if (isset($boxMessage) && $boxMessage !== "") : ?>
        <div class="card-panel <?= $boxColor ?> lighten-1">
            <?= $boxMessage ?>
        </div>
    <?php endif; ?>

    <div class="login">
        <div class="form yellow darken-3">
            <form class="login-form" action="index.php?page=accounts&action=connect" method="post">
                <span class="material-icons">lock</span>
                <input class="white" style="border-radius: 5px;" type="text" placeholder="pseudo" name="pseudo" required />
                <input class="white" style="border-radius: 5px;" type="password" placeholder="mot de passe" name="mdp" required />
                <button class="waves-effect" type="submit">connexion</button>
            </form>
            <p>Vous n'avez-pas de compte ?
                <a href="?page=accounts&action=create" style="font-size: 14px">Inscrivez-vous !</a>
            </p>
        </div>
    </div>
</main>

<?php
require "./views/common/commonFoot.tpl";
?>