<?php
include('api/config.php');

// Get the category from the AJAX request
$category = $_POST['category'] ?? 'flowers';

// Query based on the selected category
$query = "SELECT * FROM flowersales WHERE category = ?";
$stmt = $conn->prepare($query);
$stmt->bind_param('s', $category);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $flowername = $row['flowername'];
        $image = $row['image'];
        $id = $row['id'];
        $price = $row['price'];
        $seasonal_flowers = $row['seasonal_flowers'];
        $description = $row['description'];
        $maxLength = 80;
        $shortDescription = strlen($description) > $maxLength 
            ? substr($description, 0, $maxLength) . '...' 
            : $description;

        echo '
            <div class="col-md-6 col-lg-6 col-xl-4">  
                <a href="shop-detail.php?id='.$id.'">
                    <div class="rounded position-relative fruite-item">
                        <div class="fruite-img">
                            <img src="images/'.$image.'" style="width:100px; height:160px;" class="img-fluid w-100 rounded-top" alt="">
                        </div>
                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">'.$seasonal_flowers.'</div>
                        <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                            <h4>'.$flowername.'</h4>
                            <p>'.$shortDescription.'</p>
                            <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">Rs.'.$price.'</p>
                                <a href="#" class="btn border border-secondary rounded-pill px-3 text-primary"><i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart</a>
                            </div>
                        </div>
                    </div>
                </a>
            </div>';
    }
} else {
    echo '<p>No items found in this category.</p>';
}

$conn->close();
?>
