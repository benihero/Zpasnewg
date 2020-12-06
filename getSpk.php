<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='GET'){

    $sql = "SELECT DISTINCT SPKName FROM spk";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('SPKName'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"result"=>$result));
  mysqli_close($koneksi);

}


?>