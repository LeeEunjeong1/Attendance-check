<?php
	$con = mysqli_connect("sftp://sep1198.cafe24.com","sep1198","proyoun1!","sep1198");

	$proId = $_POST["proId"];
	$proPassword = $_POST["proPassword"];

	$statement = mysqli_prepare($con, "SELECT * FROM professor WHERE proId=? AND proPassword =? ");
	mysqli_stmt_bind_param($statement,"is",$proId,$proPassword );
	mysqli_stmt_execute($statement);

	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement,$proId);

	$response=array();
	$response["success"]=false;

	while(mysqli_stmt_fetch($statement)){
		$response["success"]=true;
		$response["proId"]=$proId;
	}

	echo json_encode($response);
?>