<?php
include('config.php');

if (isset($_GET['cid'])) {
    $cid = intval($_GET['cid']);

    $query = "SELECT 
                c.cid, 
                c.quantity, 
                f.price, 
                f.flowername, 
                ud.firstname, 
                ud.mobile, 
                ud.address 
              FROM 
                cart c
              JOIN 
                flowersales f ON c.product_id = f.id
              JOIN 
                finalpayment ud ON c.user_id = ud.user_id
              WHERE 
                c.cid = ?";

    $stmt = $conn->prepare($query);
    $stmt->bind_param("i", $cid);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $row['total'] = $row['price'] * $row['quantity'];
        echo json_encode($row);
    } else {
        echo json_encode(["error" => "Order not found."]);
    }
    $stmt->close();
    $conn->close();
}
?>
