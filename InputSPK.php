<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

$response = array();

$aktifitas = $_POST['aktifitas'];
$namaTK = $_POST['namaTK'];
$SPKName = $_POST['SPKName'];
$KIT = $_POST['KIT'];
$HKO = $_POST['HKO'];
$hasil = $_POST['hasil'];
$JamKerja = $_POST['JamKerja'];
$Keterangan = $_POST['Keterangan'];
$great = $_POST['great'];
$tanggalRealisasi = $_POST['tanggalReal'];

require_once('koneksi.php');
$sql = "INSERT INTO spkinput (SPKName,Aktifitas,NamaTk,KIT,HKO,Hasil,JamKerja,Keterangan,Great,TanggalRealisasi) VALUES ('$SPKName','$aktifitas','$namaTK','$KIT','$HKO','$hasil','$JamKerja','$Keterangan','$great','$tanggalRealisasi')";


if(mysqli_query($koneksi,$sql)){

    $response["value"] = 1;
    $response["message"] = "Data Berhasil Dimasukan";
    echo json_encode($response);
  
  }else{
    $response["value"] = 0;
    $response["message"] = "Data Tidak Berhasil dimasukan";
    echo json_encode($response);
  }
  mysqli_close($koneksi);
  
  } else {
    $response["value"] = 0;
    $response["message"] = "oops! Coba lagi!";
    echo json_encode($response);
  }

?>