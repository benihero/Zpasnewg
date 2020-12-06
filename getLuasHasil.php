<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $Aktifitas = $_POST['AktifitasLuas'];
    $sql = "SELECT Lhasil FROM aktifitas where AktifitasName = '$Aktifitas'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('Lhasil'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"resultAktifitas"=>$result));
  mysqli_close($koneksi);

}


?>
