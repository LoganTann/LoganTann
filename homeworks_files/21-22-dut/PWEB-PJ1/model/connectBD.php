<?php

$hostname = "localhost";	
$base= "voiturebd";
$loginBD= "root";	
$passBD="";

try {
	$dsn = "mysql:server=$hostname ; dbname=$base";
	$pdo = new PDO ($dsn, $loginBD, $passBD, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
}
catch (PDOException $e) {
	echo utf8_encode("Echec de connexion : " . $e->getMessage() . "\n");
	die();
}

?>