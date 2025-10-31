<?php
header('Content-Type: application/json');
include('config.php');

$cid = isset($_GET['cid']) ? intval($_GET['cid']) : 0;
$status = isset($_GET['status']) ? $_GET['status'] : '';

$response = [];

if ($cid > 0 && $status !== '') {
    // Prepare and execute the update query
    $query = "UPDATE cart SET status = ? WHERE cid = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("si", $status, $cid);

    if ($stmt->execute()) {
        $response['success'] = true;
        $response['message'] = "Cart status updated successfully.";
    } else {
        $response['success'] = false;
        $response['message'] = "Failed to update cart status.";
    }

    $stmt->close();
} else {
    $response['success'] = false;
    $response['message'] = "Invalid parameters.";
}

echo json_encode($response);
?>
