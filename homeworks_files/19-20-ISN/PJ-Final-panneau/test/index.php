<?php

require '../gestion/functions.php';

echo "displayTodaysBirthday(\$db) : ";
displayTodaysBirthday($db);
echo "<br>displayCurrentDay() : ";
displayCurrentDay();
echo "<br>displayCurrentTime() : ";
displayCurrentTime();
echo "<br>displayAbsentTeacher() : ";
displayAbsentTeacher($db);
echo "<br>displayArticle() : <br>";
displayArticle("..", 3);
