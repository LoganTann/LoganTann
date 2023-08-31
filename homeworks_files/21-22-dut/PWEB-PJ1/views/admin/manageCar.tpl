<?php
require './views/common/commonHead.tpl';
require './views/admin/navbarAdmin.tpl';
?>

<div class="container">
    <?php
        if (isset($data["msgs"]) && is_array($data["msgs"]) && count($data["msgs"]) > 0) {
            $bgColor = $data["boxGreen"] ? "green lighten-3" : "red lighten-1" ;
            echo "<div class='$bgColor' style='padding: 1em'>";
            foreach ($data["msgs"] as $elem) {
                echo $elem, " <br>";
            }
            echo "</div>";
        }
    ?>

    <h3>Ajouter une voiture</h3>

    <form enctype="multipart/form-data" action="?page=admin&action=manageCar" method="POST">
        <div class="row">
            <div class="input-field col s6">
                <input type="text" name="carType" id="carPrice"/>
                <label for="carType">Nom de la voiture</label>
            </div>
            <div class="input-field col s6">
                <input type="number" name="carPrice" id="carPrice" style="width: 90%"/> €
                <label for="carPrice">Prix de la voiture</label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s12 m6">
                <select name="carCaract_typeEnergie">
                    <option value="Diesel" selected>Diesel</option>
                    <option value="Essence">Essence</option>
                    <option value="Electrique">Electrique</option>
                    <option value="Hybride">Hybride</option>
                    <option value="GPL">GPL</option>
                    <option value="SP-95">SP-95</option>
                    <option value="SP-98">SP-98</option>
                    <option value="Nucleaire">Nucléaire</option>
                </select>
                <label>Type d'énergie</label>
            </div>
            <div class="col s3">
                <label for="nbPlace">Nombre de places</label>
                <input id="nbPlace" name="carCaract_nombreDePlaces" type="number" placeholder="4" min="1" max="10" />
            </div>
            <div class="col s3 switch">
                Boite de vitesse<br>
                <label>
                    Automatique
                    <input type="checkbox" checked name="carCaract_automatique">
                    <span class="lever"></span>
                    Manuelle
                </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12 m6 file-field">
                <div class="btn">
                    <span>Photo</span>
                    <input type="file" name="carPhoto__file"> 
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" name="carPhoto" type="text">
                </div>
            </div>

            
            <div class="input-field col s12 m6">
                <select name="carEtatL">
                    <option value="disponible" selected>Disponible</option>
                    <option value="en_revision">En révision</option>
                </select>
                <label>Etat</label>
            </div>
        </div>
        <input type="submit" name="event_carAdd" class="btn"/>
    </form>

    <script>
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('select');
        var instances = M.FormSelect.init(elems);
    });
    </script>
</div>
<?php
require './views/common/commonFoot.tpl';
?>