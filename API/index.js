
var app = require('express')();
var http = require('http').createServer(app);


var categorias = [];
var eventos = [];

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
    
    connection.connect();
    var _cpf = req.query.cpf;
    var _senha = req.query.senha;
    
    connection.query("select * from tbl_cliente where cpf = '"+ _cpf +"' and senha = '"+ _senha +"'", function(err, row, fields){
        
       if(!err){
           pessoa =  row;
           res.send(pessoa);
       }else{
           
           connection.query("select * from tbl_funcionario where id_funcionario = '"+ _cpf +"' and senha = '" + _senha + "'", function(err, row, fields){
               if(!err){
                   pessoa = row;
                   res.send(pessoa);
               }else{
                   console.log("erro ao logar");
                   res.send("usuários ou senha incorretos");
               }
           });
       } 

    });
    
    connection.end();
});

//============================== BUSCAR CATEGORIAS ==================================

app.get("/BuscarCateggoias", function(req, res){
   
   
    connection.connect();
    connection.query("select * from tbl_tipo_prato", function(err, row, fields){
        if(!err){
            categorias = row;
            res.send(categorias);
        }else{
            console.log('Erro ao buscar categorias');
            red.send("Erro")
        } 
        
        
        
    });
    connection.end;
});

//============================== BUSCAR EVENTOS ==================================

app.get("/BuscarEventos", function(req, res){
  
    connection.connect();
   connection.query("select * from tbl_eventos", function(err, row, fields){
        
        if(!err){
            eventos = row;
            res.send(eventos);
        }else{
            console.log('Erro ao buscar eventos')
            res.send("Erro");
        }
    });
    
    connection.end();
    
});

//========================= BUSCAR PRATOS ======================================

app.get("/BuscarPratos", function(req, res){
    
    var pratos = [];
    
    connection.connect();
    connection.query("call vw_produros", function(err, row, fields){
        
        if(!err){
            pratos = row;
            res.send(pratos);
        }else{
            console.log('Erro ao buscar os pratos');
            res.send("Erro")
        }
        
    });
    
    connection.end();
    
});


//====================== lISTENER ===========================
http.listen(8888, function(){
  console.log("Servidor rodando na porta 8888");
});