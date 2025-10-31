<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

include('config.php');

// Get POST category from Android (form or JSON)
$category = $_POST['category'] ?? null;

// If category not sent in POST, try to decode raw JSON
if (!$category) {
    $json = json_decode(file_get_contents("php://input"), true);
    $category = $json['category'] ?? 'flowers'; // default fallback
}

$response = [];

$query = "SELECT * FROM flowersales WHERE category = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param('s', $category);
$stmt->execute();
$result = $stmt->get_result();

$items = [];

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $description = $row['description'];
        $shortDescription = strlen($description) > 80 
            ? substr($description, 0, 80) . '...'
            : $description;

        $items[] = [
            'id' => $row['id'],
            'flowername' => $row['flowername'],
            'image' => $row['image'],
            'price' => $row['price'],
            'seasonal_flowers' => $row['seasonal_flowers'],
            'description' => $description,
            'short_description' => $shortDescription,
            'category' => $row['category']
        ];
    }

    echo json_encode([
        'success' => true,
        'data' => $items
    ]);
} else {
    echo json_encode([
        'success' => false,
        'message' => 'No items found in this category.'
    ]);
}

$conn->close();
?>
