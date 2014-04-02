<?php
require_once "../config.php";
require_once "../mysql-connect.php";
?>
<html>
    <link rel="stylesheet" href="../resources/css/styles.css"> 
    <body>
        <?php include("../header.php"); ?>

        <table class = "simple-table"  style="width: 500px;">
            <?php
            $html = file_get_contents("http://finance.i.ua/fuel/");


            $dom = new DOMdocument();
            @$dom->loadHTML($html);
            $xpath = new DOMXPath($dom);

            $elements = $xpath->query("//*");

            foreach ($elements as $element) {
                if ($element->nodeName == 'table') {
                    $nodes = $element->childNodes;

                    foreach ($nodes as $node) {
                        echo '<tr>';
                        echo DOMinnerHTML($node);
                        echo '</tr>';
                    }
                }
            }

            function DOMinnerHTML(DOMNode $element) {
                $innerHTML = "";
                $children = $element->childNodes;

                foreach ($children as $child) {
                    $innerHTML .= $element->ownerDocument->saveHTML($child);
                }

                return $innerHTML;
            }
            ?>
        </table>
      
    </body>
</html>