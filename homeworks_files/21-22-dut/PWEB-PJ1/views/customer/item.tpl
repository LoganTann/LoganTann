<ul class="collection">
    <li class="collection-item avatar">
        <?php echo '<img src="'.$item['photo'].'" alt="" class="circle">';
              echo '<span class="title"><b>'.$item['type'].'</b></span>'; ?>
        <p><?php echo 'Tarif journalier : '. $item['prix'].'€';?> <br>
            Dates selectionnées : du <input name="<?php echo('DateD'.$i);?>" type="input-field inline" class="datepicker">
            au <input name="<?php echo('DateF'.$i);?>" type="input-field inline" class="datepicker">

        </p>
    </li>
</ul>