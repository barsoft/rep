<?php

require_once "../config.php";
require_once "../mysql-connect.php";

if ($db_found) {

    $SQL = "UPDATE book  SET  name='" . $_POST['name'] . "',subject=' " 
            . $_POST['subject'] . "',author= '" . $_POST['author'] . "',year= '"
            . $_POST['year'] . "',publisher= '" . $_POST['publisher'] . "'
            WHERE id='" . $_POST['id'] . "'";

    $result = mysql_query($SQL)
        or die("Invalid query: " . mysql_error());
    mysql_close($db_handle);

        header('Location: index.php');
    
} else {

    print "Database NOT Found ";
    mysql_close($db_handle);
}
?>