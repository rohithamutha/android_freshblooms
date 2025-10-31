<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

include('config.php');

// Read JSON input
$data = json_decode(file_get_contents("php://input"), true);

// Get and sanitize season
$season = isset($data['season']) ? strtolower(trim($data['season'])) : null;

if (!$season) {
    echo json_encode([
        "success" => false,
        "message" => "Season is required (e.g., summer, winter, allseasons, etc.)"
    ]);
    exit;
}

// Escape for safety
$season_safe = mysqli_real_escape_string($conn, $season);

// SQL: make sure comparison is case- and whitespace-insensitive
$query = "SELECT id, flowername, category, image, price, stock_level, offer, delivary, seasonal_flowers, description 
          FROM flowersales 
          WHERE LOWER(TRIM(seasonal_flowers)) = LOWER(TRIM('$season_safe'))";

$result = mysqli_query($conn, $query);

if (!$result) {
    echo json_encode([
        "success" => false,
        "message" => "Query failed: " . mysqli_error($conn)
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
        "message" => "No flowers found for season: $season"
    ]);
}

mysqli_close($conn);
?>
