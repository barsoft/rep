<?php
require_once "../config.php";
require_once "../mysql-connect.php";
?>
<!DOCTYPE html>
<html>
   <link rel="stylesheet" href="../resources/css/styles.css">
    <body >
        <?php include("../header.php"); ?>
        <form  method="post" action="insert-form.php">
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

                    $query = "SELECT * FROM book WHERE name LIKE '%" . $_POST['name'] . "%' AND
                                 subject LIKE '%" . $_POST['subject'] . "%' AND
                                 author LIKE '%" . $_POST['author'] . "%' AND
                                 year LIKE '%" . $_POST['year'] . "%'  AND
                                 publisher LIKE '%" . $_POST['publisher'] . "%'";
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
                                              <a href="update-form.php?id=' . $id . '
                                                  &name=' . $name . '
                                                  &subject=' . $subject . '
                                                  &author=' . $author . '
                                                  &year=' . $year . '
                                                  &publisher=' . $publisher . ' "
                                                  >Update</a>
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
            <input class="button-normal" style="margin-top: 20px;
                   " type="submit" value ="Filter" onclick="window.location.replace('filter-form');">
        </form>
        <?php include("../footer.php"); ?>
    </body>
</html>
