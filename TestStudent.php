<?php
	$con = mysqli_connect("sftp://sep1198.cafe24.com","sep1198","proyoun1!","sep1198");

	$stuId = $_GET["stuId"];

	$result = mysqli_query($con,"SELECT student.stuName from student where student.stuId='$stuId'");

	$return_array = array();

	while ($row = mysql_fetch_array($result,MYSQL_ASSOC))
	{
		$row_array['stuName'] = $row['stuName'];
		array_push($return_array,$row_array);
	}

	echo json_encode($return_array);
	mysql_free_result($result);
	mysql_close($con);
?>