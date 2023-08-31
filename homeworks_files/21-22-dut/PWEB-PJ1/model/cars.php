<?php
function description($id)
{
	require("model/connectBD.php");
	$sql = "SELECT v.id, type, prix, caract, photo, etatL FROM vehicule v";
	try {
		$commande = $pdo->prepare($sql);
		$bool = $commande->execute();
		if (!$bool) {
			return "ERROR";
		}
		$descriptif = $commande->fetch();
	} catch (PDOException $e) {
		echo utf8_encode("Echec de select : " . $e->getMessage() . "\n");
		die();
	}
	return $descriptif;
}

/**
 * Retourne les infos de la voiture
 * @param null $etatL
 * @return array|mixed|void Les infos de la voiture
 */
function getCarsBD($etatL = null)
{
	require('model/connectBD.php');
	$sql = "SELECT id, type, prix, photo, etatL, caract FROM vehicule";
	if (!is_null($etatL)) {
		$sql .= " WHERE etatL=:etatL";
	}

	try {
		$commande = $pdo->prepare($sql);
		if (!is_null($etatL)) {
			$commande->bindParam(':etatL', $etatL);
		}
		if ($commande->execute()) {
			return $commande->fetchAll();
		}
	} catch (PDOException $e) {
		echo utf8_encode("Echec de select : " . $e->getMessage() . "\n");
		die(); // On arrête tout.
	}
	return [];
}


function getCarBD($id){
    require('model/connectBD.php');
    $sql = "SELECT id, type, prix, photo, etatL, caract FROM vehicule WHERE id=:id";
    try {
        $commande = $pdo->prepare($sql);
        $commande->bindParam(':id',$id);
        if ($commande->execute()){
            return $commande->fetch();
        }
    } catch (PDOException $e) {
        echo utf8_encode("Echec de select : " . $e->getMessage() . "\n");
        die(); // On arrête tout.
    }
    return [];
}

function getRentalCarsBD($idu)
{
	require('model/connectBD.php');
	$sql = "SELECT type, prix, photo, etatL, caract FROM vehicule WHERE etatL=:idu";
	try {
		$commande = $pdo->prepare($sql);
		$commande->bindParam(':idu', $idu);
		if ($commande->execute()) {
			return $commande->fetchAll();
		}
	} catch (PDOException $e) {
		echo utf8_encode("Echec de select : " . $e->getMessage() . "\n");
		die(); // On arrête tout.
	}
	return [];
}


function addCar($carType, $carPrice, $carCaract, $target_file, $carEtatL)
{
	require('model/connectBD.php');
	$sql = "INSERT INTO `vehicule` (`id`, `type`, `prix`, `caract`, `photo`, `etatL`) VALUES (NULL, :type, :price, :caract, :image, :etatl)";
	$req = $pdo->prepare($sql);
	$req->bindParam(':type', $carType);
	$req->bindParam(':price', $carPrice);
	$req->bindParam(':caract', $carCaract);
	$req->bindParam(':image', $target_file);
	$req->bindParam(':etatl', $carEtatL);
	return $req->execute();
}

function deleteCar($carId)
{
	require('model/connectBD.php');
	$sql = "DELETE FROM `vehicule` WHERE id = :id";
	$req = $pdo->prepare($sql);
	$req->bindParam(':id', $carId);
	return $req->execute();
}

function changeState($state, $id)
{
    require('model/connectBD.php');
    $sql = "UPDATE `vehicule` SET etatL = :state WHERE id = :id";
    $req = $pdo->prepare($sql);
    $req->bindParam(':state',$state);
    $req->bindParam(':id',$id);
    return $req->execute();
}
