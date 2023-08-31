<?php
include 'functions.php';

// THIS FILE'S FUNCTIONS =====
function checkConnection($isNotAdmin) {
	$adminPassword = "LAPATATE";
	if ($isNotAdmin) {
		if (empty($_POST["password"])) {
			return "";
		} elseif ($_POST["password"] != $adminPassword) {
			return "wrongPassword";
		}
		$_SESSION["admin"] = true;
	}

	return "ok";
}
function printTemplate_Login() {
	echo form(
		p("Entrez le mot de passe d'administration : ").
		input("password", "password").
		p(br()).
		input("Connect", "submit", "Cliquez ici pour vous connecter")
	);
}
function printTemplate_Index() {
	echo p(a("accounts/", "Gestion des comptes :"));
	echo ul(
		a("accounts/", "Liste des comptes"),
		a("accounts/create.php", "Ajouter un compte")
	);

	echo p(a("news/", "Gestion des articles :"));
	echo ul(
		a("news/", "Liste des articles; ajouter un article"),
	);
}
function print_goodTemplate($status) {
	if ($status == "ok") {
		printTemplate_Index();
	} else {
		printTemplate_Login();
	}
}

// MAIN CODE =====

$connectionStatus = checkConnection($isNotAdmin);
$title = "Merci de s'identifier pour accéder au panneau d'administration";
$erreur = "";

if ($connectionStatus == "ok") {
	$title = "Espace administration du panneau";
} elseif ($connectionStatus == "wrongPassword") {
	$erreur = "Mot de passe incorrect !!";
}
?>
<!DOCTYPE html>
<html lang="fr" dir="ltr">
	<head>
		<meta charset="utf-8">
		<title><?php echo $title; ?></title>
		<link rel="stylesheet" href="style.css">
	</head>
	<body>
		<p id="gestionNav">
			<a href="index.php"><?php echo $title; ?></a>
		</p>
		<div id="espaceErreur">
			<h1><?php echo $title; ?></h1>
			<p><?php echo $erreur; ?></p>
		</div>
		<div class="biggerText">
			<?php print_goodTemplate($connectionStatus); ?>
		</div>
	</body>
</html>
