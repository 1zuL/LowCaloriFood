<?php
require_once "koneksi.php";
$email = mysqli_real_escape_string($conn,$_POST['email']);
$pass = mysqli_real_escape_string($conn,$_POST['password']);
$queryuser = mysqli_query($conn,"SELECT * FROM login WHERE email='$email'");
$cariuser = mysqli_fetch_assoc($queryuser);
$response = array();
$response['login'] = array();
    if( password_verify($pass, $cariuser['password']) ) {
        
        $response2['id'] = $cariuser['userid'];
        $response2['namalengkap'] = $cariuser['namalengkap'];
        array_push($response['login'], $response2);

        $response['server_response'] = "1";
        echo json_encode($response);
    }else {
        $response["server_response"] = "0";
        echo json_encode($response);
    }	
    
    mysqli_close($conn);


?>