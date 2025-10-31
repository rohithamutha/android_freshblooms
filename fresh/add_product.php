<?php
// header('Access-Control-Allow-Origin: *');
// header('Content-Type: application/json');
// header("Access-Control-Allow-Methods: POST");
// header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

// Include the database connection file
include 'config.php';

$RequestMethod = $_SERVER["REQUEST_METHOD"];

if ($RequestMethod == "POST") {
    try {

        $flowername = htmlspecialchars($_POST['flowername']);
        $price = htmlspecialchars($_POST['price']);
        $description = htmlspecialchars($_POST['description']);
        $offer = htmlspecialchars($_POST['offer']);
        $Delivery = htmlspecialchars($_POST['Delivery']);
        $Seasonal_Flower = htmlspecialchars($_POST['Seasonal_Flower']);
        $Category = htmlspecialchars($_POST['Category']);


        // Initialize imageNames variable
        $imageNames = '';

        // Check if image files are uploaded
        if (!empty($_FILES['img']) && is_array($_FILES['img']['tmp_name'])) {
            // Directory to store uploaded images
            $targetDir = "../images/";

            // Loop through each uploaded file
            foreach ($_FILES['img']['tmp_name'] as $key => $tmp_name) {
                // Get the original file name
                $fileName = basename($_FILES['img']['name'][$key]);
                $targetFilePath = $targetDir . $fileName;

                // Check if file already exists
                if (file_exists($targetFilePath)) {
                    // Handle file name collision or take appropriate action
                    // You can rename the file or skip uploading it
                    $fileName = time() . '_' . $fileName;
                    $targetFilePath = $targetDir . $fileName;
                }

                // Upload file to the server
                if (move_uploaded_file($_FILES['img']['tmp_name'][$key], $targetFilePath)) {
                    // Append file name to the list
                    if (!empty($imageNames)) {
                        $imageNames .= ',';
                    }
                    $imageNames .= $fileName;
                } else {
                    throw new Exception("Error uploading file " . $_FILES['img']['name'][$key]);
                }
            }
        }

        // Insert image names and notification content into the database
        $sql = "INSERT INTO flowersales (flowername,category,offer,delivary,image,description,seasonal_flowers,price) VALUES (?, ?, ?, ?,?,?,?,?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ssssssss", $flowername, $Category,$offer, $Delivery, $fileName,$description,$Seasonal_Flower,$price);

        if ($stmt->execute()) {
            $response = array(
                'status' => 200,
                'message' => 'Product Added successfully',
            );
            echo "<script type='text/javascript'>alert('Added Product');window.location.href='../admin/add_product.php';</script>";
            exit;
        } else {
            throw new Exception("Error: " . $sql . "<br>" . $conn->error);
        }

        $stmt->close();
        $conn->close();

        // http_response_code(200);
        // echo json_encode($response);
        

    } catch (Exception $e) {
        $response = array(
            'status' => 500,
            'message' => 'Server Error: ' . $e->getMessage()
        );

        http_response_code(500);
        echo json_encode($response);
    }
} else {
    $response = array(
        'status' => 405,
        'message' => $RequestMethod . ' Method Not Allowed'
    );

    http_response_code(405);
    echo json_encode($response);
}
?>