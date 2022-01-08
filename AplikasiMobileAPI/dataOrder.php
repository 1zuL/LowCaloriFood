<?php
require_once 'koneksi.php';
    $uid = $_POST['idAcount'];
	$caricart = mysqli_query($conn,"select * from cart where userid='$uid' and status='Cart'");
	$fetc = mysqli_fetch_array($caricart);
	
	$orderidd = $fetc['orderid'];
	
	$itungtrans = mysqli_query($conn,"select count(orderid) as jumlahtrans from cart where userid='$uid' and status!='Cart'");
	$itungtrans2 = mysqli_fetch_assoc($itungtrans);
	$itungtrans3 = $itungtrans2['jumlahtrans'];
	$brg=mysqli_query($conn,"SELECT DISTINCT(idcart), c.orderid, tglorder, status from cart c, detailorder d where c.userid='$uid' and d.orderid=c.orderid and status!='Cart' order by tglorder DESC");
	$response["data"] = array();
    $ongkir = 10000;
	while($ambil=mysqli_fetch_object($brg)){
        $b['orderid'] = $ambil->orderid;
        $b['tglorder'] = $ambil->tglorder;
		$ordid = $b['orderid'];
		$result1 = mysqli_query($conn,"SELECT SUM(qty*hargaafter)+$ongkir AS count FROM detailorder d, produk p where d.orderid='$ordid' and p.idproduk=d.idproduk order by d.idproduk ASC");
		$cekrow = mysqli_num_rows($result1);
		$row1 = mysqli_fetch_assoc($result1);
		$count = $row1['count'];
        $b['count'] = $count;
        $b['status'] = $ambil->status;
        array_push($response["data"],$b);
    }
    echo json_encode($response);
    mysqli_close($conn);

?>