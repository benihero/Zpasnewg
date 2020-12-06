<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $search = $_POST['getKIT'];
    $sql = "SELECT KIT FROM tk where Nama = '$search'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('KIT'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"resultKIT"=>$result));
  mysqli_close($koneksi);

}


?>
