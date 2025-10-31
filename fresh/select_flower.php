<?php
include 'config.php'; // Include your database connection

if (isset($_GET['id'])) {
    $flowerId = intval($_GET['id']);
    
    try {
        $query = "SELECT * FROM flowersales WHERE id = ?";
        $stmt = $conn->prepare($query);
        $stmt->bind_param('i', $flowerId);
        $stmt->execute();
        $result = $stmt->get_result();

        if ($result->num_rows > 0) {
            $flower = $result->fetch_assoc();
            echo json_encode(['success' => true, 'data' => $flower]);
        } else {
            echo json_encode(['success' => false, 'message' => 'Flower not found']);
        }
    } catch (Exception $e) {
        error_log($e->getMessage());
        echo json_encode(['success' => false, 'message' => 'An error occurred']);
    }
} else {
    echo json_encode(['success' => false, 'message' => 'Invalid ID']);
}
?>
