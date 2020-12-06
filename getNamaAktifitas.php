<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='GET'){
    
   
    $sql = "SELECT AktifitasName FROM aktifitas ";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('AktifitasName'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"resultAktifitas"=>$result));
  mysqli_close($koneksi);

}


?>
