<?php
include('config.php');

if (isset($_GET['id'])) {
    $cid = $_GET['id'];

    // Delete the item from the cart based on the cart ID
    $query = "DELETE FROM cart WHERE cid = $cid";
    if ($conn->query($query) === TRUE) {
        // Redirect to the cart page after successful deletion
        header('Location: ../cart.php');
        exit;
    } else {
        echo "Error: " . $conn->error;
    }
}
?>
