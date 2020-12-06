<?php
require_once("koneksi.php");
if($_SERVER['REQUEST_METHOD']=='GET'){

    // $search = $_POST['getspk'];
    $sql = "SELECT * FROM spkinput";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){
        array_push($result,array('SPKName'=>$row[1],'Aktifitas'=>$row[2],'NamaTk'=>$row[3],'KIT'=>$row[4],'HKO'=>$row[5],'Hasil'=>$row[6],'Great'=>$row[9]));

    }
    echo json_encode(array("value"=>1,"resultSPK"=>$result));
    mysqli_close($koneksi);
}

?>