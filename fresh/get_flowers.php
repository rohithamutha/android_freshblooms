<?php
include('config.php');

// Check if category and max_price are passed from the frontend
$category = isset($_GET['category']) ? $_GET['category'] : 'flowers'; // Default to 'flowers'
$max_price = isset($_GET['max_price']) ? intval($_GET['max_price']) : 500; // Default to 500 for flowers

// Set the max price based on the selected category
if ($category == "design") {
    $max_price = 100000; // For design category, set max price to 100000
} else {
    $max_price = isset($_GET['max_price']) ? intval($_GET['max_price']) : 500; // Default to 500 for flowers
}

// Prepare the SQL query to filter by category and price
if ($category == "flowers") {
    $query = "WITH flowersales AS (
        SELECT 
            *,
            ROW_NUMBER() OVER (PARTITION BY seasonal_flowers ORDER BY id) AS row_num
        FROM flowersales 
        WHERE category = ? AND price <= ?
    )
    SELECT *
    FROM flowersales
    WHERE row_num = 1;";
    // Prepare statement to avoid SQL injection
    $stmt = $conn->prepare($query);
    $stmt->bind_param("si", $category, $max_price);
} else {
    $query = "SELECT * FROM flowersales WHERE category=? AND price <= ?";
    // Prepare statement to avoid SQL injection
    $stmt = $conn->prepare($query);
    $stmt->bind_param("si", $category, $max_price);
}

// Execute the query
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
        $category = $row['seasonal_flowers'];
        $maxLength = 80;
        $shortDescription = strlen($description) > $maxLength 
            ? substr($description, 0, $maxLength) . '...' 
            : $description;

        echo '
            <div class="col-md-6 col-lg-6 col-xl-4 ">
                <a href="shop-detail.php?id=' . $id . '&category='.$category.'">
                    <div class="rounded position-relative fruite-item">
                        <div class="fruite-img">
                            <img src="images/' . $image . '" style="width:100px; height:160px;" class="img-fluid w-100 rounded-top" alt="">
                        </div>
                        <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">' . $seasonal_flowers . '</div>
                        <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                            <h4>' . $flowername . '</h4>
                            <p>' . $shortDescription . '</p>
                            <div class="d-flex justify-content-between flex-lg-wrap">
                                <p class="text-dark fs-5 fw-bold mb-0">Rs.' . $price . '</p>
                                    <a href="#" 
                                        class="btn border border-secondary rounded-pill px-3 text-primary add-to-cart" 
                                        data-product-id='.$id.' ?>" 
                                        <i class="fa fa-shopping-bag me-2 text-primary"></i> Add to cart
                                     </a>

                            </div>
                        </div>
                    </div>
                </a>
            </div>';
    }
} else {
    echo '<div class="col-12 text-center">No items available for the selected filters.</div>';
}

$stmt->close();
?>
