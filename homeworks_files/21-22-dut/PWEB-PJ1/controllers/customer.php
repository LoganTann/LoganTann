<?php

function addToCart(){
    $error = "";

    require("./model/cars.php");
    if(isset($_GET['id'])){
        $_SESSION['cart'] = $_GET['id'];
    }
    $Cars = getCarsBD('disponible');
    $car = getCarBD($_SESSION['cart']);
    //echo "ici";
    if(count($_POST) > 0){
        $dates = array(
            "Debut" => $_POST['DateD'],
            "Fin" => $_POST['DateF']
        );
        $_SESSION['dates'] = $dates;
        if(!checkDates($dates)){
            // var_dump( $dates);
            $error = 'Les dates ne sont pas valides, veuillez ressayer';
        } else {
            $_SESSION['dates']['Fin'] = $dates['Fin'];
            $nexturl = "index.php?page=customer&action=recap";
            header ("Location:" . $nexturl);
        }
    }
    require('./views/customer/cart.tpl');
}

function recap(){
    require("./model/cars.php");
    $car = getCarBD($_SESSION['cart']);
    require('./views/customer/recap.tpl');
}

function checkDates(&$dates){
    if($dates['Fin'] == ""){
        // echo "pas de date de fin";
        $dates['Fin'] =  date("M t, Y", strtotime($dates['Debut']));
        return strtotime($dates['Debut']) >= time();
    } else {
        return strtotime($dates['Debut']) >= time() && (strtotime($dates['Debut']) <= strtotime($dates['Fin']) && strtotime($dates['Debut']) >= time());
    }
}

function rent(){
    require("./model/cars.php");
    require("./model/factureBD.php");
    changeState($_SESSION['user_info']['id'], $_SESSION['cart']);
    $facture_info = array(
        "ide" => $_SESSION['user_info']['id'],
        "idv" => $_SESSION['cart'],
        "dateD" => date("Y-m-d",strtotime($_SESSION['dates']['Debut'])),
        "dateF" => date("Y-m-d",strtotime($_SESSION['dates']['Fin'])),
        "valeur" => $_SESSION['facture']['valeur'],
        "etatR" => 0
    );

    if ($idfacture = creerFacture($facture_info) >=0){
        $_SESSION['facture']['id'] = $idfacture;
        $nexturl = "index.php?page=accounts&action=getRentalCars";
        header ("Location:" . $nexturl);
        return;
    }
    require ('./views/customer/cart.tpl');
}