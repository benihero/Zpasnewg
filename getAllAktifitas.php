<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $search = $_POST['getAllAktifitas'];
    $sql = "SELECT * FROM aktifitas where SPKName = '$search'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('AktifitasName'=>$row[1],'SatuanHasil'=>$row[3],'lokasi'=>$row[4],'Status'=>$row[5],'Lhasil'=>$row[6],'Thasil'=>$row[7],'JumlahTK'=>$row[8],));
    }
  echo json_encode(array("value"=>1,"resultAllAktifitas"=>$result));
  mysqli_close($koneksi);


}

?>
