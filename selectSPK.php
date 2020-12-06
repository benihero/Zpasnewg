<?php
require_once("koneksi.php");
if($_SERVER['REQUEST_METHOD']=='POST'){

    $search = $_POST['getspk'];
    $sql = "SELECT * FROM spk where SPKName = '$search'";
    $res = mysqli_query($koneksi,$sql);
    $result = array();

    while($row = mysqli_fetch_array($res)){
        array_push($result,array('SPKName'=>$row[0],'shift'=>$row[1],'TanggalSPK'=>$row[2],'TanggalReal'=>$row[3],'kawil'=>$row[4],'mandora'=>$row[5],'PJ'=>$row[6]));

    }
    echo json_encode(array("value"=>1,"result"=>$result));
    mysqli_close($koneksi);
}

?>