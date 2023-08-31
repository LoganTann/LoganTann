<?php
/* FUNCTIONS.PHP
 * utilitaires automatisant certaines tâches. à importer avec include.
*/

// Création d'une session.
session_start();

// Détermine si la session actuelle n'est pas connectée en tant qu'admin (automatique)
$isNotAdmin = empty($_SESSION["admin"]);

// Connection à la base de donnée (automatique)
try {
	$dbID = 'root';
	$dbPassword = '';
	$db = new PDO('mysql:host=localhost;dbname=panneau;charset=utf8', $dbID, $dbPassword, [PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION]);
}
catch(Exception $e) { die('Erreur : '.$e->getMessage()); }

// FONCTIONS :

function abortIfNotAdmin() {
	// Pas possible d'utiliser $isNotAdmin pour cause de pb de portée de variable
	if (empty($_SESSION["admin"])) {
		echo '<!DOCTYPE html> <html> <head> <meta charset="utf-8"> <title>Forbidden access</title> </head> <body>';

		echo `<h2>Forbidden Access</h2>`;
		echo `<p>Vous n'avez pas les droits pour accéder à cette page. Merci de <a href='/panneau/gestion/'>vous connecter</a>.</p>`;

		echo `</body> </html>`;
		// Quitte le script
		die();
	}
}

function editInfos($db, $name, $birthday, $is_student, $is_here, $id) {
	/* Modifie les informations de $id : nom, anniv, est étudiant, est présent*/
	$accountsList = getAllAccountsNames($db);

	if (!empty($accountsList[$id])) {
		$reponse = $db->query("
			UPDATE `accounts`
			SET name ='$name', birthday = '$birthday',
				is_student = '$is_student', is_here = '$is_here'
			WHERE card_id = '$id'");

		return 0;
	} else {
		return 1;
	}
}
function createAccount($db, $name, $birthday, $is_student, $is_here = 1) {
	$reponse = $db->query("
		INSERT INTO `accounts`
			(`card_id`, `name`, `birthday`, `is_student`, `is_here`)
		VALUES (NULL, '$name', '$birthday', '$is_student', '$is_here')");
	$retval = "FAIT !";

	return $retval;
}

function getInfos($db, $id) {
	/* Renvoie sous forme de liste les informations de $id*/
	$reponse = $db->query("SELECT * FROM `accounts` WHERE card_id=$id");

	return $reponse->fetch();
}
function getAllAccountsNames($db) {
	/*	Renvoie un tableau associatif qui contient en clé l'id de la personne et
		en contenu le nom de la personne.*/
	$reponse = $db->query('SELECT card_id, name FROM `accounts`');

	while ($account = $reponse->fetch()) {
		$account_id = $account['card_id'];
		$retval[$account_id] = $account['name'];
	}

	$reponse->closeCursor();

	return $retval;
}

// News manager functions

function extractArticleIdAndNames($path, $rootPath = '..\\/..\\/') {
	// description : à partir d'une addresse relative d'article, extrait
	// l'identifiant et le nom
	// explication du regex :
	// ../../articles/ [obtenir le chiffre] # [obtenir la string] . html
	$regex_pattern_full = '~'.$rootPath;
	$regex_pattern_full .= 'articles\\/';
	$regex_pattern_full .= '(\\d+)'; // [obtenir un chiffre]
	$regex_pattern_full .= '#';
	$regex_pattern_full .= '([^.]+)'; // [obtenir une chaîne de caractères]
	$regex_pattern_full .= '.html~';

	if (preg_match($regex_pattern_full, $path, $matches)) {
		return [$matches[1], $matches[2]];
	} else {
		return [0, "*invalid*"];
	}
}
function getArticleFilenameById($id, $rootPath = ".") {
	$id_matches = glob("$rootPath/articles/$id#*.html");
	if (!empty($id_matches[0])) {
		return $id_matches[0];
	} else {
		return false;
	}
}
function getArticleNameById($id, $rootPath = ".") {
	$path = getArticleFilenameById($id, $rootPath);
	if ($path===false) {
		return false;
	}
	list($id, $name) = extractArticleIdAndNames($path, '');
	if ($name == "*invalid*") {
		return false;
	} else {
		return $name;
	}
}
function isNotEmpty(&$var) {
	// fait !empty() mais exclus la valeur 0
	if (empty($var)) {
		return $var === '0';
	} else {
		return true;
	}
}

// HTML shortcuts (make better code)
function p($content) {
	return "<p> $content </p>";
}
function br() {
	return "<br>";
}
function a($href, $content = "") {
	if ($content == "") {
		$content = $href;
	}

	return "<a href='$href'> $content </a>";
}
function ul(...$listItems) { // ... = arguments en nombre infini dans une liste
	$retval = "<ul>";
	foreach ($listItems as $item) {
		$retval .= "<li>$item</li>";
	}
	$retval .= "</ul>";

	return $retval;
}
function form($content, $action = "?", $method = "POST") {
	return "<form action='$action' method='$method'> $content </form>";
}
function input($name, $type = "text", $value = "") {
	return "<input type='$type' name='$name' value='$value' />";
}

// Display Panel functions
function displayAbsentTeacher($db) {
	$getabsent = $db->query("
		SELECT name FROM accounts
		WHERE is_student = 0 AND is_here = 0");
	echo "<ul><li>professeurs absents :</li><ul>";
	while ($absent = $getabsent->fetch()) {
		echo '<li>'.$absent['name'].'</li>';
	}
	echo "</ul></ul>";
}
function displayTodaysBirthday($db) {
	/* Affiche les anniversaires du jour */
	$currentDay = date("m-d"); // month - day
	$getBirthdays = $db->query("SELECT * FROM `accounts`
		 						WHERE birthday LIKE '%$currentDay' ");
	$numberOfMatches = $getBirthdays->rowCount();

	if ($numberOfMatches <= 0) {
		echo "<em>Aucun anniversaire aujourd'hui</em>";

		return 0; // Pas la peine d'aller plus loin
	} elseif ($numberOfMatches == 1) {
		echo "<p>1 anniversaire aujourd'hui</p>";
	} else {
		echo "<p>$numberOfMatches anniversaires aujourd'hui</p>";
	}

	echo "<ul>";
	while ($person_s_birthday = $getBirthdays->fetch()) {
		$name = $person_s_birthday["name"];
		$age = calculateAge($person_s_birthday["birthday"]);
		$is_teacher = empty($person_s_birthday["is_student"]) ? "(est prof)" : "";
		echo "<li>$name ($age ans) $is_teacher</li>";
	}
	echo "</ul>";

	return 0;
}
function displayCurrentDay() {
	$dayNum = date("j");
	if ($dayNum == 1) {
		$dayNum = "1<sup>er</sup>";
	}

	echo getFrenchDay(), " ", $dayNum; // ex : Mercredi 06
	echo " ", getFrenchMonth(), " ", date("Y"); // ex : Mai 2020
}
function displayCurrentTime() {
	echo date("H:i");
}

function calculateAge($birthdate) {
	/* Récupère une date sous la forme Y-m-d et calcule la différence d'année
	   avec l'année actuelle (age en assumant que l'anniv est passé ou aujd)*/
	$age = 0;
	$currentYear = date("Y");
	$yearOfBirth = substr($birthdate, 0, 4);
	if ($yearOfBirth > 1) { // c'est un chiffre
		$age = $currentYear - $yearOfBirth;
	}

	return $age;
}
function getFrenchDay() {
	/* Récupère le jour de la semaine en français (retourne lundi, mardi...)
	*/
	$currentDay = date("N");
	// date("N") -> Représentation numérique ISO-8601 du jour de la semaine
	// 1 (pour Lundi) à 7 (pour Dimanche)
	$translation = [
		"-arrays starts at 0-",
		"Lundi",
		"Mardi",
		"Mercredi",
		"Jeudi",
		"Vendredi",
		"Samedi",
		"Dimanche",
	];

	return $translation[$currentDay];
}
function getFrenchMonth() {
	/* Idem que getFrenchDay() mais pour les mois
	*/
	$currentMonth = date("n");
	// date("n") -> Mois sans les zéros initiaux. Retour : 1 à 12
	$translation = [
		"-arrays starts at 0-",
		"Janvier",
		"Février",
		"Mars",
		"Avril",
		"Mai",
		"Juin",
		"Juillet",
		"Août",
		"Septembre",
		"Octobre",
		"Novembre",
		"Décembre",
	];

	return $translation[$currentMonth];
}
