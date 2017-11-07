
var app = require('express')();
var http = require('http').createServer(app);

var mysql = require('mysql');

var bodyParser = require('body-parser');

//======================= CONEXÃO COM O MYSQL ===========================

var connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : 'bcd127',
    database : 'db_theribs'
});

app.use( bodyParser.json() );
app.use( bodyParser.urlencoded({
    extended: true
}));

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

//======================== LOGIN FUNCIONÁRIO =========================

app.get("/LoginFuncionario", function(req, res){
   
    var funcionario;
    
    var _codigo = req.query.codigo;
    var _senha = req.query.senha;
    
    connection.query("select * from tbl_funcionario where id_funcionario = '"+ _codigo +"' and senha = '"+ _senha +"'", function(err, row, flieds){
        
        if(!err){
            funcionario = row;
            res.send(funcionario);
        }else{
            console.log("Erro" + err);
            res.send("Erro ao tentar logar");
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



//======================= BUSCAR PRATO POR ID ====================================

app.get("/BuscarPratoID", function(req, res){
   
    var id = req.query.id;
    var prato;
    
    connection.query("select * from vw_produros where id_produto = " + id, function(err, row, fields){
        
        if(!err){
            prato = row;
            res.send(prato); 
        }else{
            console.log("Erro ao buscar prato: " + err);
            res.send("Infelizmente mão conseguimos buscar o prato selecionado, volte mais tarde.");
        }
        
    });
});

//======================= BUSCAR INGREDIENTES POR PRATO ============================

app.get("/BuscarIngredientesPrato", function(req, res){
    
    var ingredientes = [];
    
    var id = req.query.id;
    
    connection.query("select * from vw_ingredentes_produto where id_produto = "+ id, function(err, row, fields){
        
        if(!err){
            ingredientes = row;
            res.send(ingredientes);            
        }else{
            console.log("Erro ao buscar ingredientes: " + err);
            res.send("Erro ao buscar ingredientes " +  err);
        }
        
    });   
    
});


//====================== BUSCAR FILIAIS POR PRATO ==================================

app.get("/BuscarFiliaisPrato", function(req, res){
    var filiais = [];
    
    var id = req.query.id;
    
    connection.query("select * from vw_produto_filial where id_produto = "+ id + "  group by Nome", function(err, row, fields){
        
        if(!err){
            filiais = row;
            res.send(filiais);
        }else{
            console.log("Erro ao buscar as filiais: " + err);
            res.send("Erro ao buscar as filiais");
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
            res.send(err);
        }
        
    });
    
});

//======================= ENVIAR CONTATO =========================================

app.post("/EnviarContato", function(req, res){
   
    var resultado;
    
    var _nome = req.body.nome;
    var _telefone = req.body.telefone;
    var _celular = req.body.celular;
    var _email = req.body.email;
    var _ocorrencia = req.body.idOcorrencia;
    var _mensagem = req.body.menssagem;
    var _unidade = req.body.idFilial;
    
    connection.query(" insert into tbl_fale (nome, email, telefone, celular, ocorencia, descritivo, unidade) value ('"+ _nome +"', '"+ _email+"', '"+ _telefone+"', '"+ _celular + "', "+ _ocorrencia +", '" + _mensagem +"'," + _unidade + ")", function(err, result){
        
        if(!err){
            resultado = "Obrigado por entrar em contato! Retornaremos em breve.";
            res.send(resultado);
            console.log("Contato enviado com sucesso!");
        }else{
            resultado = "Infelizmente não conseguimos encaminhar a sua mensagem, tente novamente mais tarde.";
            res.send(resultado);
            console.log("Erro ao enviar contato: " + resultado);
        }
        
    });      
        
});

//================================= CADASTRAR UM NOVO USUARIO ========================================
app.post("/CadastrarUsuario", function(req, res){
   
    var resultado;
    
    var _nome = req.body.nome;
    var _cpf = req.body.cpf;
    var _email = req.body.email;
    var _telefone = req.body.telefone;
    var _celular = req.body.celular;
    var _cep = req.body.cep;
    var _numero = req.body.numero;
    var _senha = req.body.senha;
    
    connection.query("insert into tbl_cliente(nome, telefone, celular, email, cpf, senha, numero, cep) values('"+ _nome +"', '"+ _telefone+"', '"+ _celular +"', '"+ _email +"', '"+ _cpf +"', '"+ _senha +"', '"+  _numero +"', '" + _cep +"')", function(err, row, fields){
        
        if(!err){
            resultado = "Cadstrado com sucesso, faça o login e aproveite nossos recursos!";
            res.send(resultado);
        }else{
            resultado = "Infelizmente não conseguimos concluir seu cadastro. Mas você ainda pode navegar pelo nosso aplicativo. Divirta-se e tente novamente mais tarde.";
            res.send(resultado);
        }
    });
    
});


//====================== LISTENER ===========================
http.listen(8888, function(){
  console.log("Servidor rodando na porta 8888");
});