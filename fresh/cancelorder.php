<?php
// cancel_order.php
header('Content-Type: application/json');

// Get the POST data
$data = json_decode(file_get_contents('php://input'), true);

if (isset($data['product_id'])) {
    $product_id = $data['product_id'];

    // Database connection
    include('config.php');

    // Update the order status in the cart table
    $sql = "UPDATE cart SET status='cancelled' WHERE cid=?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $product_id);

    if ($stmt->execute()) {
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['failed' => false, 'error' => 'Failed to update order status']);
    }

    $stmt->close();
    $conn->close();
} else {
    echo json_encode(['error' => false, 'error' => 'Invalid request']);
}
?>
