<?php

$user_name = "clickmoney";
$password = "barsoft0244";
$database = "clickmoney";
$server = "db3.ho.ua";

$db_handle = mysql_connect($server, $user_name, $password);
$db_found = mysql_select_db($database, $db_handle);
?>
