<?php

require_once "koneksi.php";
$orderidd = $_POST['orderidd'];
$response = array();
$q3 = mysqli_query($conn, "update cart set status='Payment' where orderid='$orderidd'");
	if($q3){
		array_push($response, array(
            'status' => 'OK'
        ));
	} else {
		array_push($response, array(
            'status' => 'FAILED'
        ));
	}
    echo json_encode(array("server_response" => $response));
    mysqli_close($conn);
    ?>