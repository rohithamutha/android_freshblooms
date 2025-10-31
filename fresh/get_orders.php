<?php
include("config.php");

header("Content-Type: application/json");

$user_id = $_POST['user_id']; // passed from Android app

$query = "
SELECT 
    c.cid, 
    c.quantity, 
    c.status,
    f.price, 
    f.flowername, 
    f.image, 
    fp.address, 
    fp.created_at
FROM 
    cart c
JOIN 
    flowersales f ON c.product_id = f.id
JOIN 
    finalpayment fp ON c.user_id = fp.user_id
WHERE 
    c.user_id = ? 
    AND (c.status = 'Delivering' OR c.status = 'Delivered' OR c.status = 'cancelled')
ORDER BY 
    CASE 
        WHEN c.status = 'Delivering' THEN 1 
        WHEN c.status = 'Delivered' THEN 2 
        ELSE 3 
    END, c.cid
";

$stmt = $conn->prepare($query);
$stmt->bind_param("i", $user_id);
$stmt->execute();
$result = $stmt->get_result();

$orders = [];

while ($row = $result->fetch_assoc()) {
    // Format delivery date (+2 days from order date)
    $created_at = new DateTime($row['created_at']);
    $created_at->modify('+2 days');
    $delivery_date = $created_at->format('l, j M');

    $orders[] = [
        'cid' => $row['cid'],
        'flowername' => $row['flowername'],
        'image' => $row['image'],
        'quantity' => $row['quantity'],
        'price' => $row['price'],
        'total' => $row['price'] * $row['quantity'],
        'address' => $row['address'],
        'delivery_date' => $delivery_date,
        'status' => $row['status']
    ];
}

echo json_encode([
    'status' => 'success',
    'orders' => $orders
]);
?>
