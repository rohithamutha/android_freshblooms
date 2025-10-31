<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST'); // or DELETE if using RESTful practice
header('Access-Control-Allow-Headers: Content-Type');

include('config.php');

// Read raw JSON input
$data = json_decode(file_get_contents("php://input"), true);

// Check if 'cid' is present
if (isset($data['cid'])) {
    $cid = intval($data['cid']);

    // Delete the item from the cart using prepared statement
    $query = "DELETE FROM cart WHERE cid = ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $cid);

    if ($stmt->execute()) {
        echo json_encode([
            "status" => "success",
            "message" => "Item deleted successfully"
        ]);
    } else {
        echo json_encode([
            "status" => "error",
            "message" => "Failed to delete item: " . $stmt->error
        ]);
    }

    $stmt->close();
    $conn->close();
} else {
    echo json_encode([
        "status" => "error",
        "message" => "Missing cart ID (cid)"
    ]);
}
?>
