<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

include("config.php");

$sql = "SELECT * FROM order_request";
$result = $conn->query($sql);

$response = [];

if ($result->num_rows > 0) {
    $orders = [];

    while ($row = $result->fetch_assoc()) {
        $orders[] = [
            'id' => $row['id'],
            'name' => $row['name'],
            'mobile_no' => $row['mobile_no']
        ];
    }

    $response['success'] = true;
    $response['data'] = $orders;
} else {
    $response['success'] = false;
    $response['message'] = 'No orders found.';
}

echo json_encode($response);
?>
