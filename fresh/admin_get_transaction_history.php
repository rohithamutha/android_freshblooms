<?php
header('Content-Type: application/json');
include('config.php');

$query = "SELECT payment_id, created_at, total_amount FROM finalpayment ORDER BY created_at DESC";
$result = $conn->query($query);
$response = [];

if ($result && $result->num_rows > 0) {
  while ($row = $result->fetch_assoc()) {
    $date = new DateTime($row['created_at']);
    $row['formatted_date'] = $date->format('D, j M');
    $response[] = $row;
  }
}

echo json_encode(['status' => 'success', 'transactions' => $response]);
