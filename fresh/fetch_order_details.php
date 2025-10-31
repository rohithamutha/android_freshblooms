<?php
include("config.php");

$id = $_POST['id'];

$sql = "SELECT * FROM order_request WHERE id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param('i', $id);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
  $details = $result->fetch_assoc();
  echo json_encode($details);
} else {
  echo json_encode(['error' => 'Order not found']);
}
?>
