

<?php
         session_start();
         include 'config.php';
             

            // Check if form is submitted
            if ($_SERVER["REQUEST_METHOD"] == "POST") {
                $Email = $_POST['email'];
                $Password = $_POST['password'];
    
                $sql = "SELECT * FROM userdeatils WHERE email = '$Email' AND password = '$Password'";
            
                $result = $conn->query($sql);
                
                if ($result->num_rows == 1) {
                   
                    $userInfo = $result->fetch_assoc();
                    $_SESSION["id"] = $userInfo["id"];
                    $usertype = $userInfo["usertype"];
                    $_SESSION["name"] = $userInfo["name"];
                    
                    if ($usertype == "1") {
                        header("Location: ../dashboard.php");
                        exit();
                    } elseif ($usertype == "2") {
                        header("Location: ../admin/index.php");
                        exit();
                    }else {
                        echo "<script type='text/javascript'>alert('Unknown Usertype');window.location.href='login.html';</script>"; // Fix: Use $usertype instead of $role
                    }
                } else {
                    // Invalid credentials, show an error message
                    echo "<script type='text/javascript'>alert('Invalid Username and Password');window.location.href='login.html';</script>";
                }

                // Close the database connection
                $conn->close();
            }
            ?>