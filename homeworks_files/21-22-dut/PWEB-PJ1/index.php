<?php

session_start();

$page = "home";
$action = "index";

if (isset($_GET["page"])) {
    $page = $_GET["page"];
}
if (isset($_GET["action"])) {
    $action = $_GET["action"];
}


require("./controllers/$page.php");
$action();

// TODO : recap de la commande + paiement
// TODO : disponibilités des voitures (genre tu peux pas spammer 'add')
// TODO : gerer la flotte
// TODO : facture
// TODO : tunnel de commande : Panier->Recap->Paiement