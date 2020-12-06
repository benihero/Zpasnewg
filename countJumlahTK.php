<?php
require_once('koneksi.php');

if($_SERVER['REQUEST_METHOD']=='POST'){

    $AktifitasTK = $_POST["AktifitasTK"];
    $sql = "SELECT COUNT(Aktifitas) AS jumlah FROM spkinput where Aktifitas = '$AktifitasTK'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){

        array_push($result, array('jumlah'=>$row[0]));
    }
  echo json_encode(array("value"=>1,"JmlhTK"=>$result));
  mysqli_close($koneksi);

}


?>
