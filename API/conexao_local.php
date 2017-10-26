<?php
	$host = "10.107.144.35";
	$usuario = "root";
	$senha = "bcd127";
	$banco = "db_theribs";
	
	$dbcon = new MySQLi("$host", "$usuario", "$senha", "$banco");
	
	
	if($dbcon->connect_error){
		
		echo"conexao_erro";
	}


?>