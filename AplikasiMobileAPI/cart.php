<?php
               
               
               
               
               require_once 'koneksi.php'; 

                $idproduk = $_POST['idproduk'];
                $ui = $_POST['idAcount'];
                $quantitynumber = $_POST['quantitynumber'];
				$cek = mysqli_query($conn,"select * from cart where userid='$ui' and status='Cart'");
				$liat = mysqli_num_rows($cek);
				$f = mysqli_fetch_array($cek);
				$orid = $f['orderid'];
                $response = array();
				
				//kalo ternyata udeh ada order id nya
				if($liat>0){
							
							//cek barang serupa
							$cekbrg = mysqli_query($conn,"select * from detailorder where idproduk='$idproduk' and orderid='$orid'");
							$liatlg = mysqli_num_rows($cekbrg);
							$brpbanyak = mysqli_fetch_array($cekbrg);
							$jmlh = $brpbanyak['qty'];
							
							//kalo ternyata barangnya ud ada
							if($liatlg>0){
								$baru = $jmlh + $quantitynumber;
								
								$updateaja = mysqli_query($conn,"update detailorder set qty='$baru' where orderid='$orid' and idproduk='$idproduk'");
								
								if($updateaja){
									array_push($response, array(
                                        'status' => 'OK'
                                    ));
								} else {
									array_push($response, array(
                                        'status' => 'FAILED1'
                                    ));
								}
								
							} else {
							
							$tambahdata = mysqli_query($conn,"insert into detailorder (orderid,idproduk,qty) values('$orid','$idproduk','$quantitynumber')");
							if ($tambahdata){
                                array_push($response, array(
                                    'status' => 'OK'
                                ));
							} else { 
                                array_push($response, array(
                                'status' => 'FAILED2'
                            ));
							}
							};
				} else {
					
					//kalo belom ada order id nya
						$oi = crypt(rand(22,999),time());
						
						$bikincart = mysqli_query($conn,"insert into cart (orderid, userid) values('$oi','$ui')");
						
						if($bikincart){
							$tambahuser = mysqli_query($conn,"insert into detailorder (orderid,idproduk,qty) values('$oi','$idproduk','$quantitynumber')");
							if ($tambahuser){
                                array_push($response, array(
                                    'status' => 'OK'
                                ));
							} else { 
                                array_push($response, array(
                                'status' => 'FAILED3'
                            ));
							}
						} else {
							array_push($response, array(
                                'status' => 'FAILED4'
                            ));
						}
                    }
                echo json_encode(array("server_response" => $response));
                mysqli_close($conn);




?>