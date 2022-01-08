<?php
require_once 'koneksi.php';

$listpembayaran = mysqli_query($conn,"select * from pembayaran ");
$response["data"] = array();
while($ambil = mysqli_fetch_object($listpembayaran)){
$b['metode'] = $ambil->metode;
$b['norek'] = $ambil->norek;
array_push($response["data"],$b);
}
echo json_encode($response);
mysqli_close($conn);

?>