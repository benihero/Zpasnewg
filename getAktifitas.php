<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $search = $_POST['getAktifitas'];
    $sql = "SELECT AktifitasName FROM aktifitas where SPKName = '$search'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('AktifitasName'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"resultAktifitas"=>$result));
  mysqli_close($koneksi);

}


?>
