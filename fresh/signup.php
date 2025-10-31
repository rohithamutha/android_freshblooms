<?php
include 'config.php';
	$username = $_POST['name'];
    $Email = $_POST['email'];
    $Password = $_POST['Password'];
    $Phone_no = $_POST['phone_no'];
    $sql = "INSERT INTO userdeatils (name, email, Password,phone_no) VALUES('$username','$Email','$Password','$Phone_no')";
if ($conn->query($sql) === TRUE) {
   echo "<script type='text/javascript'>alert('Signup Successfully');window.location.href='../login.php';</script>";
  } else {
  echo "Error: " . $sql . "<br>" . $conn->error;
}
$conn->close();
?>