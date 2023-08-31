<nav class="navbar navbar-dark bg-custom-2 navbarSub">
    <div class="nav-wrapper">
        <a href="?" class="brand-logo left"><img class="img-format" src="./views/static/logo.png"/></a>
        <ul id="nav-mobile" class="right">
            <li><a href="?page=accounts&action=getRentalCars">Gérer sa flotte</a></li>
            <li><a href="?page=vehicle&action=getCars">Louer un véhicule</a></li>
            <li><a href="#modal1" class="modal-trigger">Se déconnecter</a></li>
        </ul>
    </div>
</nav>

<div id="modal1" class="modal" style="width: 33% !important;">
    <div class="modal-content">
        <h4>Êtes vous sûr de vouloir quitter ?</h4>
    </div>
    <div class="modal-footer">
        <a href="#!" class="modal-close orange btn-flat white-text">Annuler</a>
        <a href="?page=accounts&action=logout" class="modal-close green lighten1 btn-flat white-text">Déconnexion</a>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.modal');
        var instances = M.Modal.init(elems, {});
    });
</script>