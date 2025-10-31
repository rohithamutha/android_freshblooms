<?php
include('config.php');

// Get the JSON data from the request
$data = json_decode(file_get_contents('php://input'), true);

$response = ['success' => false]; // Default response

if (!empty($data)) {
    foreach ($data as $item) {
        $cid = $item['cid'];
        $quantity = $item['quantity'];
        $total = $item['total'];

        // Update the cart table with new quantity and total
        $query = "UPDATE cart SET quantity = $quantity WHERE cid = $cid";
        if ($conn->query($query) === TRUE) {
            $response['success'] = true;
        } else {
            $response['success'] = false;
            $response['error'] = $conn->error;
            break;
        }
    }
}

// Send the response back as JSON
echo json_encode($response);
?>
