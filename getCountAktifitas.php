<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $aktifitas = $_POST['Aktifitas'];
    $sql = "SELECT Hasil FROM spkinput where Aktifitas = '$aktifitas'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('Hasil'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"resultAktifitass"=>$result));
  mysqli_close($koneksi);

}


?>
