<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    include('config.php');

    $id = isset($_POST['id']) ? intval($_POST['id']) : 0;
    if ($id === 0) {
        echo "<script>alert('Invalid ID');window.history.back();</script>";
        exit();
    }

    $id = $_POST['id'];
    $flowername = $_POST['flowername'];
    $category = $_POST['Category'];
    $price = $_POST['Price'];
    $offer = $_POST['Offer'];
    $delivery = $_POST['Delivery'];
    $seasonal_flower = $_POST['Seasonal_Flower'];
    $description = $_POST['Description'];

    // Handle image upload
    $image = isset($_FILES['image']['name']) ? $_FILES['image']['name'] : '';
    $image_target_dir = "../images/";
    $image_target_file = $image_target_dir . basename($image);
    $image_uploaded = false;

    if (!empty($image)) {
        $allowed_extensions = ['jpg', 'jpeg', 'png', 'gif'];
        $image_extension = strtolower(pathinfo($image_target_file, PATHINFO_EXTENSION));

        if (in_array($image_extension, $allowed_extensions)) {
            if (move_uploaded_file($_FILES['image']['tmp_name'], $image_target_file)) {
                $image_uploaded = true;
            } else {
                echo "<script>alert('Image upload failed.');window.history.back();</script>";
                exit();
            }
        } else {
            echo "<script>alert('Invalid image format. Only JPG, JPEG, PNG, and GIF are allowed.');window.history.back();</script>";
            exit();
        }
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
        echo "<script>alert('Updated successfully.');window.location.href='../admin/manage_product.php';</script>";
    } else {
        echo "<script>alert('Error: " . htmlspecialchars($stmt->error) . "');window.history.back();</script>";
    }

    $stmt->close();
    $conn->close();
} else {
    echo "<script>alert('Invalid request method.');window.history.back();</script>";
    exit();
}
?>
