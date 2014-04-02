<?php require_once "../config.php"; ?>
<!DOCTYPE html>
<html>
     <link rel="stylesheet" href="../resources/css/styles.css">
    <body >
        <?php include("../header.php"); ?>
        <form  method="post" action="response.php">
            <table class="simple-table" style="width: 500px;height: 300px; ">
                <tr>
                    <th >Название книги: </th>
                    <td ><input name="name" class="text-field"><td>                
                </tr>
                <tr>
                    <th>Тематика: </th>
                    <td><input name="subject" class="text-field"><td>                
                </tr>
                <tr>
                    <th>ФИО авторов: </th>
                    <td><input name="author" class="text-field"><td>                
                </tr>
                <tr>
                    <th>Год издания: </th>
                    <td><input name="year" class="text-field"><td>                
                </tr>
                <tr>
                    <th>Издательство: </th>
                    <td><input name="publisher" class="text-field"><td>                
                </tr>
            </table>
            <input class="button-normal" style="margin-top:  20px;" type="submit" value ="Send">
        </form>
        <?php include("../footer.php"); ?>
    </body>
</html>
