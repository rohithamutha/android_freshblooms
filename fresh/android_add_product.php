<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

include 'config.php';

$RequestMethod = $_SERVER["REQUEST_METHOD"];

if ($RequestMethod == "POST") {
    try {
        $flowername = htmlspecialchars($_POST['flowername']);
        $price = htmlspecialchars($_POST['price']);
        $description = htmlspecialchars($_POST['description']);
        $offer = htmlspecialchars($_POST['offer']);
        $Delivery = htmlspecialchars($_POST['delivery']);
        $Seasonal_Flower = htmlspecialchars($_POST['seasonal_flower']);
        $Category = htmlspecialchars($_POST['category']);

        $imageNames = ''; // Store comma-separated image names
        $targetDir = "../images/";

        if (!empty($_FILES['img']['name']) && is_array($_FILES['img']['tmp_name'])) {
            foreach ($_FILES['img']['tmp_name'] as $key => $tmp_name) {
                $fileName = basename($_FILES['img']['name'][$key]);
                $targetFilePath = $targetDir . $fileName;

                // Rename file if it already exists
                if (file_exists($targetFilePath)) {
                    $fileName = time() . '_' . $fileName;
                    $targetFilePath = $targetDir . $fileName;
                }

                if (move_uploaded_file($tmp_name, $targetFilePath)) {
                    $imageNames .= ($imageNames != '' ? ',' : '') . $fileName;
                } else {
                    throw new Exception("Error uploading file: " . $fileName);
                }
            }
        } else {
            throw new Exception("No images received.");
        }

        // Prepare SQL insert
        $sql = "INSERT INTO flowersales (flowername, category, offer, delivary, image, description, seasonal_flowers, price)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ssssssss", $flowername, $Category, $offer, $Delivery, $imageNames, $description, $Seasonal_Flower, $price);

        if ($stmt->execute()) {
            echo json_encode([
                'status' => true,
                'message' => 'Product added successfully'
            ]);
        } else {
            throw new Exception("Database error: " . $stmt->error);
        }

        $stmt->close();
        $conn->close();

    } catch (Exception $e) {
        http_response_code(500);
        echo json_encode([
            'status' => false,
            'message' => 'Server error: ' . $e->getMessage()
        ]);
    }
} else {
    http_response_code(405);
    echo json_encode([
        'status' => false,
        'message' => $RequestMethod . ' method not allowed'
    ]);
}
?>
