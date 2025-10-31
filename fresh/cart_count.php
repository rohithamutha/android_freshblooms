<?php
session_start();
header('Content-Type: application/json'); // Ensure JSON response

include 'config.php'; // Database connection

$userId = $_SESSION["id"]; // Get the user ID from session

try {
    // Fetch cart count
    $query = "SELECT count(product_id) AS cart_count FROM cart WHERE user_id = ? AND status = 'added';";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("s", $userId);
    $stmt->execute();
    $result = $stmt->get_result();
    $row = $result->fetch_assoc();
    
    $cartCount = $row['cart_count'] ?? 0; // Default to 0 if no rows found

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
