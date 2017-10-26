<?php

	header("Cache-Control: no-store, no-cache, must-revalidate, max-age=0");
    header("Cache-Control: post-check=0, pre-check=0", false);
    header("Pragma: no-cache");
	ini_set('default_charset','UTF-8');
	header("Content-Type: text/html; charset=ISO-8859-1", true);
	header("Content-Type: text/html; charset=UTF-8", true);
	
	$cpf = "";
	$senha = "";
	
	include_once 'conexao_local.php';
	
	$cpf = $_POST['cpf'];
	$senha = $_POST['senha'];

	$sql = $dbcon->query("select * from tbl_cliente where cpf = '".$cpf."' and senha = '".$senha."'");
	
	if(mysqli_num_rows($sql) > 0){
		session_start();
		echo"login_ok";
		echo",";
		while($dados = $sql->fetch_array()){
			
			
            echo utf8_encode ($dados['id_cliente']);
            echo",";
            echo utf8_encode ($dados['nome']);
            echo",";
            echo utf8_encode ($dados['telefone']);
            echo",";
            echo utf8_encode ($dados['celular']);
            echo",";
            echo utf8_encode ($dados['email']);
            echo",";	
            echo utf8_encode ($dados['cpf']);
            echo",";
            echo utf8_encode ($dados['senha']);
            echo",";
            echo utf8_encode ($dados['numero']);
            echo",";
            echo utf8_encode ($dados['foto']);
            echo",";
            echo utf8_encode ($dados['cep']);
			
		}
		
		
	}else{
		
		echo"login_erro";
		
	}
	
	


?>


