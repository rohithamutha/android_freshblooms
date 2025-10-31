<?php
include('config.php');

$cid = $_GET['cid'];
$status = $_GET['status'];
        

// Update the cart table with new quantity and total
$query = "UPDATE cart SET status = '$status' WHERE cid = $cid";

if ($conn->query($query) === TRUE) {
        echo "<script type='text/javascript'>alert('Success');window.location.href='../admin/index.php';</script>";

    } else {
        
        echo "<script type='text/javascript'>alert('Something Went Wrong');window.location.href='../admin/index.php';</script>";   
        
    }
    


?>
