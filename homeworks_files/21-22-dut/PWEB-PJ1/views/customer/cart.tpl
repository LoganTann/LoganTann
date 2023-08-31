<?php
if (empty($_SESSION['loggedin'])) {
    header("Location : index.php?page=accounts&action=connect&msg=shouldLogin");
    exit;
}

require './views/common/commonHead.tpl';
require "./views/common/navbarSub.tpl";

?>
<main class="container">
    <?php if (isset($car)) { ?>
        <div class="row">
            <form name="dates" action="?page=customer&action=addToCart&id=<?= $car['id'] ?>" method="post">
                <div class="col s12 m6">
                    <ul class="collection">
                        <li class="collection-item avatar">
                            <?php echo '<img src="' . $car['photo'] . '" alt="" class="circle">';
                            echo '<span class="title"><b>' . $car['type'] . '</b></span>'; ?>
                            <p><?php echo 'Tarif journalier : ' . $car['prix'] . '€'; ?> <br>
                                Dates selectionnées :<br>
                                du <input name="DateD" type="input-field inline" class="datepicker" value='<?= isset($_POST['DateD']) ? $_POST['DateD'] : '' ?>'><br>
                                au <input id="DateF" name="DateF" type="input-field inline" class="datepicker" value='<?= isset($_POST['DateF']) ? $_POST['DateF'] : '' ?>'>

                                <a href="#!" onclick="document.getElementById('DateF').value = ''">&lt;réinitialiser&gt;</a>
                            </p>
                        </li>
                    </ul>
                </div>
                <div class="col m12">
                    <input type="submit" value="Suivant" class="waves-effect waves-light btn-large" />
                </div>
                <?php echo $error ?>
            </form>
        </div>
    <?php } else { ?>
        Panier vide :)
    <?php } ?>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            var elems = document.querySelectorAll('.datepicker');
            var instances = M.Datepicker.init(elems, {
                // specify options here
            });
        });
    </script>
</main>
<?php
require './views/common/commonFoot.tpl';
?>