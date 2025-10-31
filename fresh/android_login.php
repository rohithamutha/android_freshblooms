<?php
session_start();
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *"); // Allow from all domains (or specify for security)
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Allow-Headers: Content-Type");

include 'config.php';

// Read JSON input
$data = json_decode(file_get_contents("php://input"), true);

if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($data['email']) && isset($data['password'])) {
    $Email = $data['email'];
    $Password = $data['password'];

    // Prevent SQL injection
    $Email = mysqli_real_escape_string($conn, $Email);
    $Password = mysqli_real_escape_string($conn, $Password);

    // Query
    $sql = "SELECT * FROM userdeatils WHERE email = '$Email' AND password = '$Password'";
    $result = $conn->query($sql);

    if ($result->num_rows == 1) {
        $userInfo = $result->fetch_assoc();


        $response = [
            'status' => true,
            'message' => 'Login successful',
            'id' => $userInfo["id"],
            'name' => $userInfo["name"],
            'usertype' => $userInfo["usertype"],
            'phone' => $userInfo["phone_no"],
            'email' => $userInfo["email"],
        ];
        echo json_encode($response);
    } else {
        echo json_encode(['status' => false, 'message' => 'Invalid Username or Password']);
    }

    $conn->close();
} else {
    echo json_encode(['status' => false, 'message' => 'Invalid Request']);
}
?>
