<?php
include("config.php");

$user_id = $_POST['user_id'];

$sql = "SELECT c.cid, c.product_id, c.quantity, f.flowername, f.price, f.image
        FROM cart c
        JOIN flowersales f ON c.product_id = f.id
        WHERE c.user_id = ?";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $user_id);
$stmt->execute();
$result = $stmt->get_result();

$cartItems = [];

while ($row = $result->fetch_assoc()) {
    $cartItems[] = $row;
}

echo json_encode(['status' => 'success', 'cart' => $cartItems]);
?>
