<?php
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    include('config.php');

    $id = isset($_GET['id']) ? intval($_GET['id']) : 0;

    if ($id === 0) {
        echo json_encode(["success" => false, "message" => "Invalid ID."]);
        exit();
    }

    $sql = "DELETE FROM flowersales WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("i", $id);

    if ($stmt->execute()) {
        echo json_encode(["success" => true, "message" => "Flower deleted successfully."]);
    } else {
        echo json_encode(["success" => false, "message" => "Error deleting flower: " . $stmt->error]);
    }

    $stmt->close();
    $conn->close();
} else {
    echo json_encode(["success" => false, "message" => "Invalid request method."]);
}
?>
