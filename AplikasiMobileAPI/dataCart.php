<?php
require_once 'koneksi.php';
$uid = $_POST['idAcount'];
$caricart = mysqli_query($conn,"select * from cart where userid='$uid' and status='Cart'");
$fetc = mysqli_fetch_array($caricart);
$orderidd = $fetc['orderid'];
$itungtrans = mysqli_query($conn,"select count(detailid) as jumlahtrans from detailorder where orderid='$orderidd'");
$itungtrans2 = mysqli_fetch_assoc($itungtrans);
$itungtrans3 = $itungtrans2['jumlahtrans'];
$brg=mysqli_query($conn,"SELECT * from detailorder d, produk p where orderid='$orderidd' and d.idproduk=p.idproduk order by d.idproduk ASC");
$response["data"] = array();
						
while($ambil = mysqli_fetch_object($brg)){
    $b['idproduk'] = $ambil->idproduk;
    $b['namaproduk'] = $ambil->namaproduk;
    $b['qty'] = $ambil->qty;
    $b['gambar'] = $ambil->gambar;
    $b['hargaafter'] = $ambil->hargaafter;
    $no=1;
	$subtotal = 10000;
    $hrg = $b['hargaafter'];
	$qtyy = $b['qty'];
	$totalharga = $hrg * $qtyy;
	$subtotal += $totalharga;
    $b['hargatotal'] = $subtotal;
    $b['orderidd'] = $orderidd;
    array_push($response["data"],$b);
}
echo json_encode($response);
mysqli_close($conn);


?>