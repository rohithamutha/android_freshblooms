<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

include('config.php');

// Read JSON input
$data = json_decode(file_get_contents("php://input"), true);

// Check if 'season' (used for design type) is provided
$season = isset($data['season']) ? strtolower(trim($data['season'])) : null;

if (!$season) {
    echo json_encode([
        "success" => false,
        "message" => "Design type is required (e.g., birthday, wedding)"
    ]);
    exit;
}

// Escape user input
$season_safe = mysqli_real_escape_string($conn, $season);

// Query: match category 'design' and seasonal_flowers = season (case-insensitive)
$query = "SELECT id, flowername, category, image, price, stock_level, offer, delivary, seasonal_flowers, description 
          FROM flowersales 
          WHERE category = 'design' AND LOWER(TRIM(seasonal_flowers)) = LOWER('$season_safe')";

$result = mysqli_query($conn, $query);

if (!$result) {
    echo json_encode([
        "success" => false,
        "message" => "Query error: " . mysqli_error($conn)
    ]);
    exit;
}

$response = [];

if (mysqli_num_rows($result) > 0) {
    while ($row = mysqli_fetch_assoc($result)) {
        $row['short_description'] = strlen($row['description']) > 80
            ? substr($row['description'], 0, 80) . '...'
            : $row['description'];
        $response[] = $row;
    }

    echo json_encode([
        "success" => true,
        "data" => $response
    ]);
} else {
    echo json_encode([
        "success" => false,
        "message" => "No design found for: $season"
    ]);
}

mysqli_close($conn);
?>
