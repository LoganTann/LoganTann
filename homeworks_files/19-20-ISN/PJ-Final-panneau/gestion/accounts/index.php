<?php
  include '../functions.php';
 ?>
<!DOCTYPE html>
<html lang="fr" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Liste des comptes</title>
    <link rel="stylesheet" href="../style.css">
  </head>
  <body>
    <p id="gestionNav">
		<a href="../">Espace de gestion</a> >
		<a href="./">Liste des comptes</a> >
    </p>
    <div id="espaceErreur">
    	<h1>Gestion des comptes</h1>
    </div>

	<a class="btn_register" href="create.php">Créer un nouveau compte</a>

	<h3>Modifier un compte : </h3>
    <?php
	  $accountsList = getAllAccountsNames($db);
	  echo "<ul>";
	  foreach ($accountsList as $account_id => $account_name) {
		echo "<li><a href='edit.php?card_id=$account_id'>$account_name</a></li>";
	  }
	  echo "</ul>";
	 ?>

  </body>
</html>
