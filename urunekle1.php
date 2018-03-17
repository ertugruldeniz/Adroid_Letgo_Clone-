<?php
$host="94.73.170.241"; //baðlanýlacak veri tabanýnýn IP adresi
$user="android20";
$password="AXxz51T4";
$db="android20";


$baslik=$_GET['baslik'];
$konum=$_GET['konum'];
$fiyat=$_GET['fiyat'];
$resim=$_GET['resim'];


$baglan=mysql_connect($host,$user,$password);

mysql_select_db($db,$baglan);//veri tabanina baglanildi

$sql="insert into EVBUL(BASLIK,KONUM,FIYAT,RESIM) values ('$baslik','$konum','$fiyat','$resim')";

$islem=mysql_query($sql);

if($islem)
echo "İslem basarili";
else
echo "İslem basarisiz";
?>