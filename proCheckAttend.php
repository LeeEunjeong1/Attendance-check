<?php
   $con = mysqli_connect("localhost","sep1198","proyoun1!","sep1198");


  $sql="SELECT * from checkAttend WHERE checkWhether=1";

   $statement=mysqli_prepare($con,$sql);
   
   mysqli_stmt_execute($statement);

   mysqli_stmt_store_result($statement);
   mysqli_stmt_bind_result($statement ,$checkNum,$checkToStuNum,$checkWheter,$checkTime);

   $response=array();
   $alleves=array();
   
   while (mysqli_stmt_fetch($statement))
   {
      $response[0]=$checkNum;
      $response[1]=$checkToStuNum;
	$response[2]=$checkWheter;
	$response[3]=$checkTime;
      array_push($alleves,$response);
      
   }
   
   echo json_encode($alleves);
   
   mysqli_close($con);
?>