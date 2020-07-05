<?php
	$con = mysqli_connect("sftp://sep1198.cafe24.com","sep1198","proyoun1!","sep1198");

	$stuId = $_GET["stuId"];

	$result = mysqli_query($con,"SELECT class.className FROM class, student, classToStudent WHERE class.classId = classToStudent.classId AND classToStudent.stuId = '$stuId'");
	$response = array();
	while($row = mysql_fetch_array($result)){
		array_push($response, array("className"=>$row[0]));
	}

	echo json_encode(array("response"=>$response), JSON_UNESCAPED_UNICODE);
	mysqli_close($con);
?>