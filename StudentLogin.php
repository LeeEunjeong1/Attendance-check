<?php
	$con = mysqli_connect("sftp://sep1198.cafe24.com","sep1198","proyoun1!","sep1198");

	$stuId = $_POST["stuId"];
	$stuPassword = $_POST["stuPassword"];

	$statement = mysqli_prepare($con, "SELECT * FROM student WHERE stuId=? AND stuPassword =? ");
	mysqli_stmt_bind_param($statement,"is",$stuId,$stuPassword );
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement,$stuId);

	$response=array();
	$response["success"]=false;

	while(mysqli_stmt_fetch($statement)){
		$response["success"]=true;
		$response["stuId"]=$stuId;
	}

	echo json_encode($response);
?>