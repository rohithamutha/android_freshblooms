<?php
include("config.php");
header("Content-Type: application/json");

$data = json_decode(file_get_contents("php://input"));
$season = $data->season;

$query = "SELECT id, flowername, image, price, seasonal_flowers FROM flowersales WHERE seasonal_flowers = '$season'";
$result = mysqli_query($conn, $query);

$flowers = [];
while ($row = mysqli_fetch_assoc($result)) {
    $flowers[] = $row;
}

echo json_encode([
    "success" => true,
    "data" => $flowers
]);
?>
