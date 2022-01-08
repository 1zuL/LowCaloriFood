<?php
   
               require_once 'koneksi.php'; 

               $uid = $_POST['userid'];
               $caricart = mysqli_query($conn,"select * from cart where userid='$uid' and status='Cart'");
               $fetc = mysqli_fetch_array($caricart);
               $orderidd = $fetc['orderid'];
               $response = array();

               $kode = $_POST['idproduknya'];
               $q2 = mysqli_query($conn, "delete from detailorder where idproduk='$kode' and orderid='$orderidd'");
               if($q2){
                array_push($response, array(
                    'status' => 'OK'
                ));
               } else {
                array_push($response, array(
                    'status' => 'FAILED1'
                ));
               }
               echo json_encode(array("server_response" => $response));
               mysqli_close($conn);

               ?>