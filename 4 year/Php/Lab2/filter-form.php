<?php require_once "../config.php"; ?>
<!DOCTYPE html>
<html>
     <link rel="stylesheet" href="../resources/css/styles.css">
    <body >
        <?php include("../header.php"); ?>
        <form  method="post" action="filter.php">
            <?php include("data-form.php"); ?>
            <input class="button-normal" style="margin-top:  20px;" type="submit" value ="Filter">
        </form>
        <?php include("../footer.php"); ?>
    </body>
</html>
