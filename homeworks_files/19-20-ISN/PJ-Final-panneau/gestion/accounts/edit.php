<?php
require '../functions.php';
abortIfNotAdmin();

function traitement($db) {
	if (isset($_GET['card_id'])){
		$cardid = $_GET['card_id'];
	} else {
		return "Erreur : aucun identifiant renseigné en paramètre GET !";
	}
	if (empty($_POST["bouton"])) {
		return "";
	}
	if (empty($_POST["name"])) {
		// Si le nom est vide, faire :
		return "Erreur : pas de nom !";
	}
	if (empty($_POST["birthday"])) {
		// Si l'anniversaire est vide, faire :
		return "Erreur : Pas de date d'anniversaire valide !";
	}

	if (empty($_POST["status"])) {
		$status = "Élève";
		$isStudent = 1;
	} else {
		$status = "Professeur";
		$isStudent = 0;
	}
	$is_here = empty($_POST["isHere"]) ? 0 : 1;
	$valeurRetour = "Nom envoyé : <i>".$_POST["name"]."</i>";
	$valeurRetour .= " Date d'anniv: <i>".$_POST["birthday"]."</i>";
	$valeurRetour .= " Statut : <i>".$status."</i>";

	$birthday = $_POST['birthday'];
	$name = $_POST['name'];

	editInfos($db, $name, $birthday, $isStudent, $is_here, $cardid);

	return $valeurRetour;
}

function template_editForm($db) {
	$cardid = $_GET['card_id'];
	$account_query = $db->query("SELECT * FROM accounts WHERE card_id=$cardid");
	$account_infos = $account_query->fetch();

	$input_name = input("name", "text", $account_infos['name']);
	$input_bday = input("birthday", "date", $account_infos['birthday']);
	$input_isStudent = '
		<select name="status">
		<option value="" >Élève</option>
		<option value="true" >Professeur</option>
		</select>';
	$input_isHere = '<div>
		<input type="checkbox" name="isHere" value="1">
		<label for="isHere">Est présent aujourd\'hui</label>
	</div>';

	if ($account_infos['is_student']) {
		// Ajoute l'attribut selected à l'option Élève
		$input_isStudent = str_replace(">É", "selected >É", $input_isStudent);
	} else {
		// Ajoute l'attribut selected à l'option Professeur
		$input_isStudent = str_replace(">P", "selected >P", $input_isStudent);
	}
	if (!empty($account_infos['is_here'])) {
		// Ajoute l'attribut checked au checkbox
		$input_isHere = str_replace("input", "input checked", $input_isHere);
	}

	echo form("<table> <tbody>
	  <tr>
	    <td>
			$input_name <br>
			$input_bday <br>
			$input_isStudent <br>
			$input_isHere
	    </td>
	    <td id='formValidation'>
			<input type='submit' name='bouton' value='Modifier le compte' id='submitBtn'>
	    </td>
	  </tr>
	</tbody> </table>", "?card_id=$cardid");
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
		<a href="./">Gestion des comptes</a> >
		<a href="?">Formulaire de modification de comptes</a>
	</p>
	<div id="espaceErreur">
		<h1>Formulaire de modification de comptes</h1>
		<?php echo traitement($db); ?>
	</div>
	<div>
		<?php
		if (isset($_GET['card_id'])) {
			template_editForm($db);
		}
		?>
	</div>
</body>
</html>
