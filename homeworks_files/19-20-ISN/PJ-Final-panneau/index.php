<?php
require 'gestion/functions.php';

$delay = 8;
header("refresh: $delay");

$articlesFileList = glob("articles/[[:digit:]]#*.html");
$max = count($articlesFileList);
natsort($articlesFileList); // pas forcément utile... mais bon.

$article_id = &$_SESSION['idOfArticleToDisplay'];
// Passe la variable par référence : tout changement dans $article_id changera
//   la variable $_SESSION['idOfArticleToDisplay']
//   Bon à savoir : passer la référence d'une variable indéfinie va la créer
//   automatiquement avec comme valeur NULL.

if (!is_numeric($article_id)) { // valeur par défaut
	$article_id = 0;
}

if ($article_id < $max - 1) {
	$article_id++;
} else {
	$article_id = 0;
}
$articlePath = $articlesFileList[$article_id];

$articleContent = file_get_contents($articlePath);
$article_generated = str_replace("\n", "<br>", $articleContent);

function displayArticlesTitle($currentArticleId){
	for ($i=0; $i < 4; $i++) {
		$iplus1 = $i + 1;
		$articleName = getArticleNameById($i);
		if ($articleName === false) {
			echo "<div class='titre news_$iplus1 empty'>-</div>";
			continue;
			// Passe à l'itération suivante sans tenir compte des instructions
			// qui suivent.
		} //// c'est comme un else {...} sans indentation dans notre cas

		if ($currentArticleId == $i) {
			echo "<div class='titre news_$iplus1 selected'>";
		} else {
			echo "<div class='titre news_$iplus1'>";
		}
		echo $articleName, "</div>";
	}
}
 ?>

<!DOCTYPE html>
<html lang="fr" dir="ltr">
<head>
	<meta charset="utf-8">
	<title>L'affichage du panneau</title>
	<link rel="stylesheet" href="style.css">
</head>
<body id="panelContainer">
	<div class="jour">
		<?php echo displayCurrentDay(); ?>
	</div>
	<div class="contenu_news">
		<?php echo $article_generated;?>
	</div>

	<div class="heure">
		<?php echo displayCurrentTime(); ?>
	</div>
	<?php displayArticlesTitle($article_id); ?>
</body>
</html>
