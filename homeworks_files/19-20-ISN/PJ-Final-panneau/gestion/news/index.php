<?php
include '../functions.php';

abortIfNotAdmin();

function generateContent() {
	$articlesFileList = glob("../../articles/[[:digit:]]#*.html");
	natsort($articlesFileList);

	template_newsList($articlesFileList);
	template_createNews($articlesFileList);

	return 0;
}

function template_createNews($articlesFileList) {
	$defaultValue = "Information numéro ".(count($articlesFileList) + 1);

	echo "<h2>Créer un nouvel article</h2>";
	echo form(
		"<label for='articleName'>Nom de l'article</label>"
			.input("articleName", "text", $defaultValue)
			.input("submit", "submit", "Créer un nouvel article"),
		"create.php"
	);

	return 0;
}
function template_newsList($articlesFileList) {
	echo "<h2>Liste des articles à éditer</h2>";
	if (count($articlesFileList) == 0) {
		echo "<p><em>Aucun article encore créé...</em></p>";

		return 1;
	}

	foreach ($articlesFileList as $i => $path) {
		// TODO: Utiliser un tableau, c'est + joli

		list($article_id, $article_name) = extractArticleIdAndNames($path);

		echo "<a class=\"fichier\" href='edit.php?articleId=$article_id'>$article_name</a><br>";
	}

	return 0;
}

 ?>
<!DOCTYPE html>
<html lang="fr" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>Espace de création de comptes</title>
	<link rel="stylesheet" href="../style.css">
</head>
<body>
	<p id="gestionNav">
		<a href="../">Espace de gestion</a> >
		<a href="?">Liste des articles</a>
	</p>
	<div id="espaceErreur">
		<h1>Liste des articles à éditer</h1>
	</div>
	<div id="ListeFichier">
		<?php generateContent(); ?>
	</div>
</body>
</html>
