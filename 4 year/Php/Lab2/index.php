<?php
require_once "../config.php";
require_once "../mysql-connect.php";
?>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="../resources/css/styles.css">
    <body >
        <?php include("../header.php"); ?>
        <form  method="post" action="update-form.php">

            <table class = "simple-table" style = "width: 500px; ">
                <tr>
                    <th style="height: 10px;">Name</th>             
                    <th>Subject</th>           
                    <th>Author</th>           
                    <th>Year </th>           
                    <th>Publisher </th>        
                </tr>
                <?php
                if ($db_found) {
                    $query = "SELECT * FROM book";
                    $result = mysql_query($query);
                    $num = mysql_numrows($result);
                    mysql_close($db_handle);
                    $i = 0;
                    while ($i < $num) {
                        $id = mysql_result($result, $i, "id");
                        $name = mysql_result($result, $i, "name");
                        $subject = mysql_result($result, $i, "subject");
                        $author = mysql_result($result, $i, "author");
                        $year = mysql_result($result, $i, "year");
                        $publisher = mysql_result($result, $i, "publisher");
                        echo ' 
                            <tr style="height: 10px;">
                                 <td>' . $name . '</td>             
                                 <td>' . $subject . ' </td>           
                                 <td>' . $author . ' </td>           
                                 <td>' . $year . ' </td>           
                                 <td>' . $publisher . ' </td>                 
                                  <td> 
                                 <input class="button-small" style="" value ="Update" type="submit" name="update">
                                  </td>                  
                            </tr>'
                        ;
                        $i++;
                    };
                } else {
                    print "Database NOT Found ";
                    mysql_close($db_handle);
                }
                ?>

            </table>
            <table >
                <tr>
                    <td>
                        <input class="button-normal" style="margin-top: 20px;" value ="Filter" name="filter" onclick='window.location.replace("filter-form.php");'>
                    </td>
                    <td>
                        <input class="button-normal" style="margin-top: 20px;"  value ="Insert"  name="insert" onclick='window.location.replace("insert-form.php");'>
                    </td>
                </tr>
            </table>
        </form>
        <?php include("../footer.php"); ?>
    </body>
</html>
