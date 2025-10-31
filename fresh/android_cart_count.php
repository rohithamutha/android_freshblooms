<?php
header('Content-Type: application/json');

include 'config.php'; // Database connection

// Support GET or POST (you can restrict to POST if preferred)
$userId = $_GET['user_id'] ?? $_POST['user_id'] ?? null;

if (!$userId) {
    echo json_encode([
        "status" => "error",
        "message" => "Missing user_id"
    ]);
    exit;
}

try {
    $query = "SELECT COUNT(product_id) AS cart_count FROM cart WHERE user_id = ? AND status = 'added'";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $userId);
    $stmt->execute();
    $result = $stmt->get_result();
    $row = $result->fetch_assoc();
    
    $cartCount = $row['cart_count'] ?? 0;

    echo json_encode([
        "status" => "success",
        "cart_count" => $cartCount
    ]);

    $stmt->close();
    $conn->close();
} catch (Exception $e) {
    echo json_encode([
        "status" => "error",
        "message" => "An error occurred: " . $e->getMessage()
    ]);
}
?>
