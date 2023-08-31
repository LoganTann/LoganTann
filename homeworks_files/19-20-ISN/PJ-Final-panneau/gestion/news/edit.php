<?php
/* Espace de modification / création d'articles */

// init
include '../functions.php';
abortIfNotAdmin();

// var init
$errors = "";
$fileContent = false;

// Force à utiliser les sessions plutôt que GET
$set_new_id_using_GET = isNotEmpty($_GET['articleId']);
if ($set_new_id_using_GET){
	$_SESSION['articleId'] = $_GET["articleId"];

	echo "<script>window.location = window.location.pathname</script>";
	echo "<noscript>Js désactivé. <a href='edit.php'>Cliquez ici pour être redirigé.</a></noscript>";
	exit;
}

// récupère le fichier à éditer
$got_an_id = isNotEmpty($_SESSION['articleId']);
if ($got_an_id){
	$article_id = $_SESSION['articleId'];
	$fileToEdit = getArticleFilenameById($article_id, "../..");
} else {
	$errors = "Erreur : L'article que vous tentez d'éditer n'existe pas, ou plus, ou alors aucun article à éditer n'a été spécifié.<br>";
	$fileToEdit = false;
}

/* Sauvegarde éventuelle */
$user_have_clicked_save_btn = isNotEmpty($_POST["bouton"]);
$does_have_valid_file_to_edit = isNotEmpty($fileToEdit);
if ($user_have_clicked_save_btn) {

	$articleContent = $_POST["articleContent"];

	if (empty($articleContent)) {
		$errors .= "ATTENTION : texte vide ! Si vous souhaitez supprimer l'article, merci de le faire via l'espace de gestion. <br>";

	} elseif ($does_have_valid_file_to_edit) {
		$fileContent = $articleContent; // (sera utilisé par la suite)

		if (file_put_contents($fileToEdit, $fileContent)) {
			$errors .= "Texte modifié avec succès !<br>";
			$errors .= "<a href='../' target='_blank'>Prévisualiser</a>";
		} else {
			$errors .= "Erreur : Impossible de sauvegarder le fichier (problème de permissions de fichier en écriture dans le disque ?). Se référer à l'erreur ci-dessus.<br>";
		}
	}
}

/* Obtention du contenu si vide*/
if ($does_have_valid_file_to_edit) {
	list($id, $real_file_name) = extractArticleIdAndNames($fileToEdit);
	$title = "Modification de « $real_file_name »";
	if ($fileContent === false) {
		$fileContent = file_get_contents($fileToEdit);
	}
}

function generateContent($fileContent) {
	if ($fileContent === false) {
		echo p("Erreur : le fichier que vous tentez de modifier n'existe pas, vous ne pouvez pas l'éditer ou bien une autre erreur est survenue. Si tel est le cas, celle-ci est marquée plus haut.");
	} else {
		echo form("
		<input type='submit' name='bouton' value='envoyer' id='submitBtn'><br>
			<textarea name='articleContent' rows='8' cols='80'>$fileContent</textarea>
			");
	}
}
?>
<!DOCTYPE html>
<html lang="fr" dir="ltr">
<head>
	<meta charset="utf-8">
	<title><?php echo empty($title) ? "Erreur" : $title; ?></title>
	<link rel="stylesheet" href="../style.css">
	<style>
	textarea {
		height: 70vh; /* 70 view (écran) height*/
		margin-top: 20px;
	}
	</style>
</head>
<body>
	<p id="gestionNav">
		<a href="../">Espace de gestion</a> >
		<a href="./">Liste des articles</a> >
		<a href="?"><?php echo empty($title) ? "Erreur" : $title; ?></a>
	</p>
	<div id="espaceErreur">
		<?php
		echo "<h1>", empty($title) ? "Erreur" : $title,"</h1>";
		echo empty($errors) ? "" : $errors;
		?>
	</div>
	<main>
		<?php echo generateContent($fileContent); ?>
	</main>
</body>
</html>
