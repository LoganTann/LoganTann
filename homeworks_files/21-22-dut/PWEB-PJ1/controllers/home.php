<?php

function index() {
    if (!isset($_SESSION['loggedin'])) {
        $_SESSION['loggedin'] = false;
        $_SESSION['cart'] = array();
    }
    require ("model/cars.php");
    $Cars = getCarsBD();
    require("./views/home/home.tpl");
}