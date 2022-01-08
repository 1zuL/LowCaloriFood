<?php
require_once 'koneksi.php';

if($conn){
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = password_hash($_POST['password'], PASSWORD_DEFAULT); 
    $mobile = $_POST['mobile'];
    $alamat = $_POST['alamat'];
    $response = array();
    

    $insert = "INSERT INTO login(namalengkap, email,notelp,password, alamat) VALUES('$name','$email','$mobile','$password','$alamat')";

    if($name !="" && $email != "" && $password != "" && $mobile != ""){
        $result = mysqli_query($conn,$insert);
        $response = array();
        if($result){
            array_push($response, array(
            'status' => 'OK'
        ));
        }else{
            array_push($response, array(
            'status' => 'FAILED1'
        ));
        }
    }else {
        array_push($response, array(
            'status' => 'FAILED2'
        ));
    }
}else{
    array_push($response, array(
        'status' => 'FAILED3'
    ));
}

echo json_encode(array("server_response" => $response));
mysqli_close($conn);
?>