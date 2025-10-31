<?php
header('Content-Type: application/json');
include('config.php');

$query = "
  SELECT 
    c.cid, c.quantity, c.status, f.price, f.flowername, f.image, 
    ud.firstname, ud.mobile
  FROM cart c
  JOIN flowersales f ON c.product_id = f.id
  JOIN finalpayment ud ON c.user_id = ud.user_id
  WHERE c.status IN ('Delivering', 'Delivered', 'cancelled')
  ORDER BY FIELD(c.status, 'Delivering', 'Delivered', 'cancelled')";

$result = $conn->query($query);
$response = [];

if ($result && $result->num_rows > 0) {
  while ($row = $result->fetch_assoc()) {
    $row['total'] = $row['price'] * $row['quantity'];
    $response[] = $row;
  }
}

echo json_encode(['status' => 'success', 'orders' => $response]);
