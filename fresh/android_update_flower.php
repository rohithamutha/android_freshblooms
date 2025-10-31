<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include('config.php');

    $response = ['success' => false];

    $id = isset($_POST['id']) ? intval($_POST['id']) : 0;
    if ($id === 0) {
        $response['message'] = "Invalid ID";
        echo json_encode($response);
        exit();
    }

    $flowername = $_POST['flowername'] ?? '';
    $category = $_POST['Category'] ?? '';
    $price = $_POST['Price'] ?? '';
    $offer = $_POST['Offer'] ?? '';
    $delivery = $_POST['Delivery'] ?? '';
    $seasonal_flower = $_POST['Seasonal_Flower'] ?? '';
    $description = $_POST['Description'] ?? '';
    $image = isset($_FILES['image']['name']) ? $_FILES['image']['name'] : '';

    $image_uploaded = false;
    $image_target_dir = "../images/";
    $image_target_file = $image_target_dir . basename($image);

    // Handle image upload
    if (!empty($image)) {
        $allowed_extensions = ['jpg', 'jpeg', 'png', 'gif'];
        $image_extension = strtolower(pathinfo($image_target_file, PATHINFO_EXTENSION));

        if (!in_array($image_extension, $allowed_extensions)) {
            $response['message'] = "Invalid image format. Only JPG, JPEG, PNG, and GIF are allowed.";
            echo json_encode($response);
            exit();
        }

        if (!move_uploaded_file($_FILES['image']['tmp_name'], $image_target_file)) {
            $response['message'] = "Image upload failed.";
            echo json_encode($response);
            exit();
        }

        $image_uploaded = true;
    }

    // Prepare SQL query
    if ($image_uploaded) {
        $sql = "UPDATE flowersales 
                SET flowername = ?, category = ?, image = ?, price = ?, offer = ?, delivary = ?, seasonal_flowers = ?, description = ? 
                WHERE id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ssssssssi", $flowername, $category, $image, $price, $offer, $delivery, $seasonal_flower, $description, $id);
    } else {
        $sql = "UPDATE flowersales 
                SET flowername = ?, category = ?, price = ?, offer = ?, delivary = ?, seasonal_flowers = ?, description = ? 
                WHERE id = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("sssssssi", $flowername, $category, $price, $offer, $delivery, $seasonal_flower, $description, $id);
    }

    // Execute query
    if ($stmt->execute()) {
        $response['success'] = true;
        $response['message'] = "Product updated successfully.";
    } else {
        $response['message'] = "Update failed: " . $stmt->error;
    }

    $stmt->close();
    $conn->close();
    echo json_encode($response);
} else {
    echo json_encode([
        "success" => false,
        "message" => "Invalid request method."
    ]);
    exit();
}
?>
