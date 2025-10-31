<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

include('config.php');

// Read JSON input from Android app
$data = json_decode(file_get_contents("php://input"), true);

// Default values
$category = isset($data['category']) ? $data['category'] : 'flowers';
$max_price = isset($data['max_price']) ? intval($data['max_price']) : 500;

// Override max_price for "design" category
if ($category === "design") {
    $max_price = 100000;
}

$response = [];

// Use seasonal filter for flowers category
if ($category === "flowers") {
    $query = "
        WITH ranked_flowers AS (
            SELECT *,
                   ROW_NUMBER() OVER (PARTITION BY seasonal_flowers ORDER BY id) AS row_num
            FROM flowersales
            WHERE category = ? AND price <= ?
        )
        SELECT * FROM ranked_flowers WHERE row_num = 1
    ";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("si", $category, $max_price);
} else {
    $query = "SELECT * FROM flowersales WHERE category = ? AND price <= ?";
    $stmt = $conn->prepare($query);
    $stmt->bind_param("si", $category, $max_price);
}

$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $description = $row['description'];
        $shortDescription = strlen($description) > 80
            ? substr($description, 0, 80) . '...'
            : $description;

        $response[] = [
            "id" => $row['id'],
            "flowername" => $row['flowername'],
            "image" => $row['image'],
            "price" => $row['price'],
            "seasonal_flowers" => $row['seasonal_flowers'],
            "description" => $description,
            "short_description" => $shortDescription,
            "category" => $row['category']
        ];
    }

    echo json_encode([
        "success" => true,
        "data" => $response
    ]);
} else {
    echo json_encode([
        "success" => false,
        "message" => "No items available for the selected filters."
    ]);
}

$stmt->close();
$conn->close();
?>
