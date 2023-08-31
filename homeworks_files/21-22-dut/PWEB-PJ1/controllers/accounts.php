<?php
function create()
{
	$errors = false;
	if (count($_POST) > 0) {
		$user_info = array(
			"nom" => $_POST["nom"],
			"pseudo" => $_POST["pseudo"],
			"mdp" => password_hash($_POST["mdp"], PASSWORD_DEFAULT),
			"email" => $_POST["email"],
			"nomE" => $_POST["nomE"],
			"adresseE" => $_POST["adresseE"]
		);
		require("./model/clientBD.php");
		$errors = valid_registration($user_info);
		if (count($errors) <= 0 && ($id_user = new_user($user_info)) >= 0) {
			$_SESSION['user_info'] = $user_info;
			$_SESSION['user_info']['id'] = $id_user;
			$_SESSION['loggedin'] = true;
			header("Location: index.php");
			return;
		}
		$errors[] = "Echec de l'inscription. Si aucune autre erreur n'est affichée, c'est probablement une erreur de la base de données.";
	}
	require("./views/accounts/create.tpl");
}

function logout()
{
	unset($_SESSION['loggedin']);
	// redirect
	header("Location: index.php?page=accounts&action=connect&msg=disconnected");
}

function connect()
{
	$boxColor = "red";
	$boxMessage = "";
	if (isset($_GET['msg'])) {
		switch ($_GET['msg']) {
			case "shouldLogin":
				$boxColor = "orange";
				$boxMessage = "Veuillez vous connecter au préalable pour accéder à cette page";
				break;
			case "disconnected":
				$boxColor = "green";
				$boxMessage = "Vous avez été déconnecté.";
				break;
		}
	}
	if (count($_POST) > 0) {
		require("./model/clientBD.php");
		if (empty($_POST["pseudo"]) || empty($_POST["mdp"])) {
			$boxMessage = "Veuillez remplir tous les champs.";
		} else if (verif_bd($_POST["pseudo"], $_POST["mdp"], $user_info)) {
			$_SESSION['user_info'] = $user_info;
			$_SESSION['loggedin'] = true;
			header("Location: index.php");
			return;
		} else {
			$boxMessage = "identifiant ou mot de passe incorrect";
		}
	}
	require "./views/accounts/connect.tpl";
}

function getRentalCars()
{
	require("model/cars.php");
	$Cars = getCarsBD($_SESSION['user_info']['id']);
	require "views/home/getRentalCars.tpl";
}

function getNb()
{
	require('./model/clientBD.php');
	$count = getNbCars($_SESSION['user_info']['id']);
	return ($count['nb']);
}


function getBill()
{
	require('./FPDF/fpdf.php');

	class PDF extends FPDF
	{
		// En-tête
		function Header()
		{
			//red part
			$this->SetX(5);
			$this->SetY(5);

			$this->SetDrawColor(255, 0, 0);
			$this->SetFillColor(255, 0, 0);
			$this->SetTextColor(255, 0, 0);

			$this->Cell(190, 9, "", 1, 1, 'C', true);

			//grey part
			$this->SetX(5);
			$this->SetY(14);

			$this->SetDrawColor(247, 243, 243);
			$this->SetFillColor(247, 243, 243);
			$this->SetTextColor(247, 243, 243);

			$this->Cell(190, 50, "", 1, 1, 'C', true);


			// Logo
			$this->Image('./views/static/logo.png', 15, 25, 50);

			$this->SetXY(140, 25);

			$this->SetTextColor(127, 122, 122);
			$this->SetFont('Times', 'B', 15);
			$this->SetFontSize(15);

			$this->Cell(60, 10, 'FACTURE', 1, 0, 'C');

			$this->SetXY(140, 33);
			$this->SetFont('Times', '', 15);

			$today = date("m.d.y");
			$this->Cell(60, 10, 'Date : ' . $today, 1, 0, 'C');

			$this->SetXY(153, 41);

			$this->SetFillColor(0, 0, 0);
			$this->Cell(34, 0.4, '', 1, 0, 'C', true);

			$this->SetXY(140, 42);
			$this->SetFont('Times', '', 15);

			$this->Cell(60, 10, 'No. de facture : ' . $_SESSION['facture']['id'], 1, 0, 'C');

			$this->SetXY(151, 50);

			$this->SetFillColor(0, 0, 0);
			$this->Cell(38, 0.4, '', 1, 0, 'C', true);
			$this->Ln();
			$this->Cell(0, 20, '', 0, 1);
		}

		function EnterpriseInfos($info_to_fill, $user_info)
		{
			$this->SetFillColor(247, 243, 243);
			$this->SetTextColor(127, 122, 122);
			$this->SetLineWidth(.3);
			$this->SetXY(40, 70);
			$w = array(35, 35, 35, 35, 35);
			for ($i = 0; $i < count($info_to_fill); $i++) {
				$this->SetX(40);
				$this->Cell($w[$i], 12, $info_to_fill[$i], 0, 1, 'C', true);
			}
			$this->SetFillColor(127, 122, 122);
			$this->SetXY(42, 80);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(42, 92);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(42, 104);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(42, 116);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(42, 128);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetFillColor(247, 243, 243);
			$this->SetXY(86, 70);
			$this->Cell(80, 12, $user_info['id'], 0, 1, 'C', true);
			$this->SetX(86);
			$this->Cell(80, 12, $user_info['nom'], 0, 1, 'C', true);
			$this->SetX(86);
			$this->Cell(80, 12, $user_info['email'], 0, 1, 'C', true);
			$this->SetX(86);
			$this->Cell(80, 12, $user_info['nomE'], 0, 1, 'C', true);
			$this->SetX(86);
			$this->Cell(80, 12, $user_info['adresseE'], 0, 1, 'C', true);

			$this->SetFillColor(127, 122, 122);
			$this->SetXY(88, 80);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(88, 92);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(88, 104);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(88, 116);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(88, 128);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);
		}

		function RentedVehicule($header, $InfoCar, $InfoFacture)
		{
			$this->SetFillColor(247, 243, 243);
			$this->SetTextColor(127, 122, 122);
			$this->SetLineWidth(.3);
			$this->SetXY(84, 145);
			$this->Cell(40, 10, "Voiture a louer : ", 1, 0, 'C', false);
			$this->SetXY(10, 160);
			$w = array(35, 35, 35, 35, 35);
			for ($i = 0; $i < count($header); $i++) {
				$this->SetX(10);
				$this->Cell($w[$i], 12, $header[$i], 0, 1, 'C', true);
			}
			$this->SetFillColor(127, 122, 122);
			$this->SetXY(12, 170);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(12, 182);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(12, 194);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(12, 206);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(12, 218);
			$this->Cell(31, 0.1, '', 0, 0, 'C', true);

			$this->SetFillColor(247, 243, 243);
			$this->SetXY(56, 160);
			$this->Cell(80, 12, $InfoCar['id'], 0, 1, 'C', true);
			$this->SetX(56);
			$this->Cell(80, 12, $InfoCar['type'], 0, 1, 'C', true);
			$this->SetX(56);
			$this->Cell(80, 12, $InfoCar['prix'] . " EUR", 0, 1, 'C', true);
			$this->SetX(56);
			$this->Cell(80, 12, $InfoFacture['dateD'], 0, 1, 'C', true);
			$this->SetX(56);
			$this->Cell(80, 12, $InfoFacture['dateF'], 0, 1, 'C', true);

			$this->SetFillColor(127, 122, 122);
			$this->SetXY(58, 170);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(58, 182);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(58, 194);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(58, 206);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);

			$this->SetXY(58, 218);
			$this->Cell(76, 0.1, '', 0, 0, 'C', true);


			$this->Image($InfoCar['photo'], 145, 174, 50);
			$this->SetXY(10, 225);
			$this->SetTextColor(255, 0, 0);
			if (getNb() >= 10) {
				$this->Cell(0, 10, 'TOTAL A PAYER (AVEC REDUCTION 10%)* :         ' . $InfoFacture['valeur'] * 0.9 . ' EUR', 1, 1, 'T');
			} else {
				$this->Cell(0, 10, 'TOTAL A PAYER (SANS REDUCTION 10%)* :         ' . $InfoFacture['valeur'] . ' EUR', 1, 1, 'T');
			}
		}

		// Pied de page
		function Footer()
		{
			$this->SetY(-25);
			$this->SetFontSize(12);
			$this->SetTextColor(0, 0, 0);
			$this->Cell(10, 10, '* Beneficiez d\'une reduction de 10% en louant au moins 10 voitures.', 0, 0);

			$this->SetY(-15);
			$this->SetDrawColor(255, 0, 0);
			$this->SetFillColor(255, 0, 0);
			$this->SetTextColor(255, 255, 255);

			$this->SetFont('Times', 'B', 15);
			$this->Cell(190, 9, 'Page ' . $this->PageNo() . '/{nb}', 1, 1, 'C', true);
		}
	}

	$pdf = new PDF();
	$pdf->AliasNbPages();
	$pdf->AddPage();
	$header = array('Identifiant :', 'Nom :', 'Prix/jour :', 'Date debut', 'Date fin');

	require("./model/cars.php");
    require("./model/factureBD.php");
	$InfoCar = getCarBD($_GET['id']);
    $InfoFacture = getFacture($InfoCar['id'],$_SESSION['user_info']['id']);
    $_SESSION['facture']['id'] = $InfoFacture['id'];
    //var_dump($InfoFacture);
	$info_to_fill = array('Identifiant :', 'Nom :', 'E-mail :', 'Entreprise :', 'Adresse :');
	$user_info = $_SESSION['user_info'];
	$pdf->EnterpriseInfos($info_to_fill, $user_info);
	$pdf->RentedVehicule($header, $InfoCar, $InfoFacture);
	$pdf->SetTitle('FACTURE_PARISDESCARZ_' . $user_info['nom'] . '_' . $InfoCar['type']);
	$pdf->Output();
}
