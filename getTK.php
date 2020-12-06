<?php

require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $search = $_POST['getTK'];
    $sql = "SELECT Nama FROM tk where mandora = '$search'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('Nama'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"resultTK"=>$result));
  mysqli_close($koneksi);

}


?>
