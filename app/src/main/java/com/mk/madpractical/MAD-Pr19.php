<?php
$servername = "localhost"; // Database server
$username = "mad";        // Database username
$password = "hellomad";            // Database password
$dbname = "maD"; // Database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
if (empty($_POST)){
    die("post method required");
}
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Get POST data from Android app
$rollno = $_POST['rollno']; 
$name = $_POST['name'];
$sem = $_POST['sem'];
$sql = "INSERT INTO Student (rollno, name, sem) VALUES ('$rollno', '$name', $sem)";

if ($conn->query($sql) === TRUE) {
    echo "Data inserted successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>
