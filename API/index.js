
var app = require('express')();
var http = require('http').createServer(app);

var mysql = require('mysql');

//======================= CONEXÃO COM O MYSQL ===========================

var connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : 'bcd127',
    database : 'db_theribs'
});



app.get("/", function(req, res){
   res.send("Ouvindo"); 
    
});

//============================== LOGIN ==================================

app.get('/Login', function(req, res){
    
        
    var pessoa;
    
    var _cpf = req.query.cpf;
    var _senha = req.query.senha;
    
    connection.query("select * from tbl_cliente where cpf = '"+ _cpf +"' and senha = '"+ _senha +"'", function(err, row, fields){
        
       if(!err){
           pessoa =  row;
           res.send(pessoa);
       }else{
           console.log("erro ao logar");
           res.send("usuários ou senha incorretos 1");
       } 

    });
    
});

//============================== BUSCAR CATEGORIAS ==================================

app.get("/BuscarCateggoias", function(req, res){
   
    
    var categorias = [];
   
    connection.query("select * from tbl_tipo_prato", function(err, row, fields){
        if(!err){
            categorias = row;
            res.send(categorias);             
        }else{
            console.log('Erro ao buscar categorias');
            red.send("Erro");
        } 
    });
    
    
   
});

//============================== BUSCAR EVENTOS ==================================

app.get("/BuscarEventos", function(req, res){
    
    var eventos = [];
    
    connection.query("select * from tbl_eventos", function(err, row, fields){

        if(!err){
            eventos = row;
            res.send(eventos);
        }else{
            console.log('Erro ao buscar eventos')
            res.send("Erro");
        }
    });
    
    
});

//========================= BUSCAR PRATOS ======================================

app.get("/BuscarPratos", function(req, res){
    
    var pratos = [];
    
    connection.query("select * from vw_produros", function(err, row, fields){
        
        if(!err){
            pratos = row;
            res.send(pratos);
        }else{
            console.log('Erro ao buscar os pratos');
            res.send("Erro");
        }
        
    });
    
    
});

//======================= BUSCAR FILIAIS =======================================

app.get("/BuscarFiliais", function(req, res){
    
    var filais = [];
    
    connection.query("select * from tbl_restaurante", function(err, row, fields){
        
        if(!err){
            filais = row;
            res.send(filais);
        }else{
            console.log("Erro ao buscar filiais");
            res.send(err);
        }
        
    });
    
});


//========================= BUSCAR OCORRENCIAS ==================================

app.get("/BuscarOcorrencias", function(req, res){
   
    var ocorrencias = [];
    
    
    connection.query("select * from tbl_ocorrencia", function(err, row, fields){
       
        if(!err){
            ocorrencias = row;
            res.send(ocorrencias);
            
        }else{
            console.log("Erro ao buscar ocorrencias");
            res.send(err)y;
        }
        
    });
    
});

//======================= ENVIAR CONTATO =========================================

app.post("/EnviarContato", function(req, res){
   
    var resultado;
    
    var _nome = req.query.nome;
    var _telefone = req.query.telefone;
    var _celular = req.query.celular;
    var _email = req.query.email;
    var _ocorrencia = req.query.ocorrencia;
    var _mensagem = req.query.menssagem;
    var _unidade = req.query.unidade;
    
    connection.query(" insert into tbl_fale (nome, email, telefone, celular, ocorrencia, descritivo, unidade) value ('"+ _nome +"', '"+ _email+"', '"+ _telefone+"', '"+ _celular + "', "+ _ocorrencia +", '" + descritivo +"'," + _unidade + ")", function(err, result){
        
        if(!err){
            resultado = "Obrigo por entrar em contato! Retornaremos em breve.";
            res.send(resultado);
        }else{
            resultado = "Infelizmente não conseguimos encaminhar a sua mensagem, tente novamente mais tarde.";
            res.send(resultado);
        }
        
    });      
        
});


//====================== LISTENER ===========================
http.listen(8888, function(){
  console.log("Servidor rodando na porta 8888");
});