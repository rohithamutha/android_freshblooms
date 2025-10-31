<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include 'config.php';

// Read JSON input
$data = json_decode(file_get_contents("php://input"), true);

// Check if required fields are present
if (isset($data['name']) && isset($data['email']) && isset($data['Password']) && isset($data['phone_no'])) {
    
    $username = mysqli_real_escape_string($conn, $data['name']);
    $email = mysqli_real_escape_string($conn, $data['email']);
    $password = mysqli_real_escape_string($conn, $data['Password']);
    $phone_no = mysqli_real_escape_string($conn, $data['phone_no']);

    // Check for duplicate email or phone number
    $check_sql = "SELECT * FROM userdeatils WHERE email = '$email' OR phone_no = '$phone_no'";
    $check_result = $conn->query($check_sql);

    if ($check_result->num_rows > 0) {
        echo json_encode(["status" => false, "message" => "Email or phone number already exists"]);
    } else {
        // Insert new user
        $sql = "INSERT INTO userdeatils (name, email, Password, phone_no) 
                VALUES ('$username', '$email', '$password', '$phone_no')";

        if ($conn->query($sql) === TRUE) {
            echo json_encode(["status" => true, "message" => "Signup successful"]);
        } else {
            echo json_encode(["status" => false, "message" => "Error: " . $conn->error]);
        }
    }

} else {
    echo json_encode(["status" => false, "message" => "Missing required fields"]);
}

$conn->close();
?>
