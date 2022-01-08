<?php
require_once 'koneksi.php';
        $idorder = $_POST['idorder']; 
        $userid = $_POST['userid'];  
		$veriforderid = mysqli_query($conn,"select * from cart where orderid='$idorder'");
		$fetch = mysqli_fetch_array($veriforderid);
		$liat = mysqli_num_rows($veriforderid);
		$response = array();
		if($fetch>0){
		$nama = $_POST['nama'];
		$metode = $_POST['metode'];
		$tanggal = date('Y-m-d');
			  
		$kon = mysqli_query($conn,"insert into konfirmasi (orderid, userid, payment, namarekening, tglbayar) 
		values('$idorder','$userid','$metode','$nama','$tanggal')");
		if ($kon){
		
		$up = mysqli_query($conn,"update cart set status='Confirmed' where orderid='$idorder'");
		array_push($response, array(
            'status' => 'OK'
        ));
		} else { 
            array_push($response, array(
            'status' => 'FAILED1'
        ));
		}
		} else {
            array_push($response, array(
                'status' => 'FAILED1'
            ));
		}
        echo json_encode(array("server_response" => $response));
    mysqli_close($conn);
		
?>