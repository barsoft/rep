<?php

require_once "../config.php";
require_once "../mysql-connect.php";

if ($db_found) {

    $SQL = "INSERT INTO book (name, subject, author,year,publisher) 
    VALUES ('" . $_POST['name'] . "',' " . $_POST['subject'] . "', '" . $_POST['author'] . "','"
            . $_POST['year'] . "','" . $_POST['publisher'] . "')";

    $result = mysql_query($SQL);



    mysql_close($db_handle);

    header('Location: index.php');
} else {

    print "Database NOT Found ";
    mysql_close($db_handle);
}
?>