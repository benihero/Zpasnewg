<?php
require_once("koneksi.php");
if($_SERVER['REQUEST_METHOD']=='POST'){

    $search = $_POST['cari'];
    $sql = "SELECT * FROM spkinput where Aktifitas = '$search'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){
        array_push($result,array('id'=>$row[0],'SPKName'=>$row[1],'Aktifitas'=>$row[2],'NamaTk'=>$row[3],'KIT'=>$row[4],'HKO'=>$row[5],'Hasil'=>$row[6],'Great'=>$row[9],'TanggalRealisasi'=>$row[8]));

    }
    echo json_encode(array("value"=>1,"resultSPK"=>$result));
    mysqli_close($koneksi);
}

?>