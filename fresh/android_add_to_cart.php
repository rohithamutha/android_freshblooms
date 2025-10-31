<?php
header('Content-Type: application/json'); 
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

include 'config.php';

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Get user ID from POST instead of session
    $userId = isset($_POST['user_id']) ? trim($_POST['user_id']) : null;
    $productId = isset($_POST['product_id']) ? trim($_POST['product_id']) : null;
    $status = isset($_POST['status']) ? trim($_POST['status']) : null;
    $quantity = isset($_POST['quantity']) ? intval($_POST['quantity']) : 1;

    // Field validation
    if (empty($userId) || empty($productId) || empty($status) || $quantity <= 0) {
        echo json_encode([
            "status" => "error",
            "message" => "All fields are required and quantity must be greater than 0."
        ]);
        exit;
    }

    try {
        // Check if product already exists in cart
        $checkQuery = "SELECT quantity FROM cart WHERE user_id = ? AND product_id = ? AND status = ?";
        $checkStmt = $conn->prepare($checkQuery);
        $checkStmt->bind_param("sss", $userId, $productId, $status);
        $checkStmt->execute();
        $result = $checkStmt->get_result();

        if ($result->num_rows > 0) {
            // Product exists - update quantity
            $row = $result->fetch_assoc();
            $newQuantity = $row['quantity'] + $quantity;

            $updateQuery = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ? AND status = ?";
            $updateStmt = $conn->prepare($updateQuery);
            $updateStmt->bind_param("isss", $newQuantity, $userId, $productId, $status);

            if ($updateStmt->execute()) {
                echo json_encode([
                    "status" => "success",
                    "message" => "Cart updated successfully."
                ]);
            } else {
                echo json_encode([
                    "status" => "error",
                    "message" => "Failed to update cart: " . $updateStmt->error
                ]);
            }

            $updateStmt->close();
        } else {
            // Insert new product in cart
            $insertQuery = "INSERT INTO cart (user_id, product_id, quantity, status) VALUES (?, ?, ?, ?)";
            $insertStmt = $conn->prepare($insertQuery);
            $insertStmt->bind_param("ssss", $userId, $productId, $quantity, $status);

            if ($insertStmt->execute()) {
                echo json_encode([
                    "status" => "success",
                    "message" => "Product added to cart successfully."
                ]);
            } else {
                echo json_encode([
                    "status" => "error",
                    "message" => "Failed to add product to cart: " . $insertStmt->error
                ]);
            }

            $insertStmt->close();
        }

        $checkStmt->close();
        $conn->close();
    } catch (Exception $e) {
        echo json_encode([
            "status" => "error",
            "message" => "An error occurred: " . $e->getMessage()
        ]);
    }
} else {
    echo json_encode([
        "status" => "error",
        "message" => "Invalid request method."
    ]);
}
?>
