<?php
header('Content-Type: application/json');  // Tell Android it's JSON
include("config.php");

$id = isset($_POST['id']) ? intval($_POST['id']) : 0;

$response = [];

if ($id > 0) {
    $sql = "SELECT * FROM order_request WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param('i', $id);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $details = $result->fetch_assoc();
        $response['success'] = true;
        $response['data'] = $details;
    } else {
        $response['success'] = false;
        $response['message'] = "Order not found";
    }

    $stmt->close();
} else {
    $response['success'] = false;
    $response['message'] = "Invalid or missing ID";
}

echo json_encode($response);
?>
