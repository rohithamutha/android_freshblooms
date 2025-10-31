<?php
include('config.php');
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Content-Type');

// Get the JSON POST body
$data = json_decode(file_get_contents("php://input"), true);

// Extract data
$razorpay_payment_id = $data['razorpay_payment_id'] ?? '';
$total_amount = $data['total_amount'] ?? '';
$user_id = $data['user_id'] ?? '';
$product_details = $data['product_details'] ?? '';
$firstname = $data['firstname'] ?? '';
$lastname = $data['lastname'] ?? '';
$address = $data['address'] ?? '';
$city = $data['city'] ?? '';
$country = $data['country'] ?? '';
$pincode = $data['pincode'] ?? '';
$mobile = $data['mobile'] ?? '';
$email = $data['email'] ?? '';

// Validate payment ID
if (!empty($razorpay_payment_id)) {
    $payment_status = "Success";

    // Step 1: Update cart status
    $update_stmt = $conn->prepare("UPDATE cart SET status = 'Delivering' WHERE user_id = ? AND status = 'added'");
    $update_stmt->bind_param("s", $user_id);
    
    if ($update_stmt->execute()) {

        // Step 2: Insert into finalpayment
        $insert_stmt = $conn->prepare("
            INSERT INTO finalpayment (
                user_id, firstname, lastname, address, city, country, pincode,
                mobile, email, product_details, total_amount, payment_status, payment_id
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ");
        $insert_stmt->bind_param(
            "sssssssssssss",
            $user_id, $firstname, $lastname, $address, $city, $country, $pincode,
            $mobile, $email, $product_details, $total_amount, $payment_status, $razorpay_payment_id
        );

        if ($insert_stmt->execute()) {
            $last_id = $conn->insert_id;
            echo json_encode([
                'status' => 'success',
                'message' => 'Payment recorded successfully.',
                'payment_id' => $last_id
            ]);
        } else {
            echo json_encode([
                'status' => 'error',
                'message' => 'Failed to insert payment data: ' . $insert_stmt->error
            ]);
        }

        $insert_stmt->close();
    } else {
        echo json_encode([
            'status' => 'error',
            'message' => 'Failed to update cart: ' . $update_stmt->error
        ]);
    }

    $update_stmt->close();
} else {
    echo json_encode(['status' => 'error', 'message' => 'Missing payment ID.']);
}

$conn->close();
