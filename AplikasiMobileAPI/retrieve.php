<?php
require("koneksi.php");
$perintah = "SELECT * FROM produk order by idproduk desc LIMIT 6";
$eksekusi = mysqli_query($conn, $perintah);
$cek = mysqli_affected_rows($conn);


if($cek>0){
    $response["kode"] = 1;
    $response["pesan"] = "Data Tersedia";
    $response["data"] = array();

    while($ambil = mysqli_fetch_object($eksekusi)){
        $F["idproduk"] = $ambil->idproduk;
        $F["namaproduk"] = $ambil->namaproduk;
        $F["gambar"] = $ambil->gambar;
        $F["deskripsi"] =  $ambil->deskripsi;
        $F["rate"] = $ambil->rate;
        $F["hargaafter"] = $ambil->hargaafter;
        

        array_push($response["data"],$F);
    }
}
else{
    $response["kode"] = 0;
    $response["pesan"] = "data tidak tersedia";
}
echo json_encode($response);
mysqli_close($conn);
?>