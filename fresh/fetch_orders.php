<?php
include("config.php");

$sql = "SELECT * FROM order_request";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
  while ($row = $result->fetch_assoc()) {
    echo '<div class="item-list">';
    echo '<div class="info-user ms-3">';
    echo '<div class="username">' . $row['name'] . '</div>';
    echo '<div class="status">+91 ' . $row['mobile_no'] . '</div>';
    echo '</div>';
    echo '<button class="btn btn-icon btn-link op-8 me-1" data-bs-toggle="modal" data-bs-target="#exampleModal" data-id="' . $row['id'] . '">';
    echo '<i class="far fa-envelope"></i>';
    echo '</button>';

    echo '</div>';
  }
} else {
  echo '<p>No orders found.</p>';
}
?>
