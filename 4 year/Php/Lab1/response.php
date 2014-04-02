<?php require_once "../config.php"; ?>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="../resources/css/styles.css">
    <body >
        <?php
        if (empty($_POST)) {
            header('Location: index.php');
        }
        include("../header.php");
        ?>
        <table class="simple-table" style="width: 500px;height: 300px; ">
            <tr >
                <th style="width: 40%;">Название книги: </th> 
                <td><?= $_POST['name'] ?></td>
            </tr>
            <tr>
                <th>Тематика: </th>             
                <td><?= $_POST['subject'] ?></td>
            </tr>
            <tr>
                <th>ФИО авторов: </th>    
                <td><?= $_POST['author'] ?></td>
            </tr>
            <tr>
                <th>Год издания: </th>    
                <td><?= $_POST['year'] ?></td>
            </tr>
            <tr>
                <th>Издательство: </th>   
                <td><?= $_POST['publisher'] ?></td>
            </tr>
        </table>
        <input class="button-normal" style="margin-top:  20px;" type="submit" value ="Back" onclick="window.location.replace('index.php');">
        <?php include("../footer.php"); ?>
    </body>
</html>
