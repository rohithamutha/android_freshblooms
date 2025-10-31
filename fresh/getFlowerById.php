<?php
include("config.php");

header("Content-Type: application/json");
$data = json_decode(file_get_contents("php://input"));

$id = $data->id;

$query = "SELECT * FROM flowersales WHERE id = '$id'";
$result = mysqli_query($conn, $query);

if ($row = mysqli_fetch_assoc($result)) {
    echo json_encode([
        "success" => true,
        "data" => $row
    ]);
} else {
    echo json_encode([
        "success" => false,
        "message" => "Flower not found"
    ]);
}
?>
