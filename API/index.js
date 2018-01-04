
var app = require('express')();
var http = require('http').createServer(app);
var mysql = require('mysql');
var bodyParser = require('body-parser');
var io = require('socket.io').listen(http);

//======================= CONEXÃO COM O MYSQL ===========================

var connection = mysql.createConnection({
    host : 'localhost',
    user : 'root',
    password : 'bcd127',
    database : 'db_theribs'
});

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
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
           pessoa = row;
           res.send(pessoa);
       }else{
           console.log("erro ao logar");
           res.send("usuários ou senha incorretos");
       } 

    });
    
});

//======================== LOGIN FUNCIONÁRIO =========================

app.get("/LoginFuncionario", function(req, res){
   
    var funcionario;
    
    var _cpf = req.query.cpf;
    var _senha = req.query.senha;
    
    connection.query("select * from tbl_funcionario where id_funcionario = '"+ _cpf +"' and senha = '"+ _senha +"'", function(err, row, flieds){
        
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

//============================= BUSCAR DADOS CLIENTE ============================

app.get("/BuscarDadosCliente", function(req, res){
    
    var _id = req.query.id;
    var cliente;
    
    connection.query("select * from tbl_cliente where id_cliente = " + _id, function(err, row, fields){
        
        if(!err){
            cliente = row;
            res.send(cliente);
        }else{
            console.log("Erro ao buscar cliente");
            res.send("Erro");
        }
        
    });
    
});


//============================== BUSCAR EVENTOS ==================================

app.get("/BuscarEventos", function(req, res){
    
    var eventos = [];
    
    connection.query("select te.*, ti.url as img_evento from tbl_eventos as te inner join tbl_evento_imagem as tvm on tvm.id_evento = te.id_evento inner join tbl_imagem as ti on ti.id_imagem = tvm.id_img group by id_evento;", function(err, row, fields){

        if(!err){
            eventos = row;
            res.send(eventos);
        }else{
            console.log('Erro ao buscar eventos');
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
    console.log("erro ao logar");
    connection.query("select * from tbl_restaurante", function(err, row, fields){
        
        if(!err){
            filais = row;
            res.send(filais);
        }else{
            console.log("Erro ao buscar filiais: " + err);
            res.send(err);
        }
        
    });
    
});

// ======================== BUSCAR FILIAL ID ================================

app.get("/BuscarFiliaisID", function(req, res){
    
    
    var _id = req.query.id;
    var filais = [];
    
    connection.query("select * from tbl_restaurante where id_restaurante = " + _id, function(err, row, fields){
        
        if(!err){
            filais = row;
            res.send(filais);
        }else{
            console.log("Erro ao buscar filiais: " + err);
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

//========================================== FILTRO DE PRATOS ====================================


app.get("/BuscarPratosFiltro", function(req, res){
    
    var pratos = [];
    
    var idCategoria = req.query.idCategoria;
    var idFilial = req.query.idFilial;
    
    connection.query("select * from vw_pratos_filtro where id_tipo_prato = "+ idCategoria + "  and id_restaurante = " + idFilial + " group by id_produto", function(err, row, fields){
        if(!err){
            pratos = row;
            res.send(pratos);            
        }else{
            console.log("Erro ao buscar pratos filtro" + err);
            res.send("Erro ao filtrar pratos");
        }
        
    });
    
});

//=================================== PESQUISAR PRATOS ========================================================


app.get("/BuscarPratoNome", function(req, res){
    
    var pratos = [];
    
    var nome = req.query.nome;
    
    connection.query("select * from tbl_produto where nome like '%" + nome + "%'" , function(err, row, fields){
        
        if(!err){
            pratos = row;
            res.send(pratos);
        }else{
            console.log("Erro ao buscar pratos: " + err);
        }
        
    });
    
    
});

//================================ BUSCAR DADOS FILIAIS ====================================

app.get("/BuscarDadosFilial", function(req, res){
    
    var filial;
    var id = req.query.id;
    
    connection.query("select * from tbl_restaurante where id_restaurante = "+ id, function(err, row, fields){
        
        if(!err){
            filial = row;
            res.send(filial);
        }else{
           console.log("Erro ao buscar detalhes da filial: ", err);
            res.send("Erro ao buscar filiais");
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

//============================== BUSCAR CARTAO =================================

app.get("/BuscarCartoes", function(req, res){
    
    var _id = req.query.id;
    var cartao = [];
    
    connection.query("select * from tbl_cartao where id_cliente = " + _id , function(err, row, fields){
        
        if(!err){
            cartao = row;
            res.send(cartao);
        }else{
            console.log("Erro ao buscar cartões: " + err);
            res.send("Erro");
        }
        
    });
    
});

//============================= ALTERAR DADOS USUARIOS ================================

app.post("/AlterarUsuarioSemSenha", function(req, res){
    
    var _celular = req.body.celular;
    var _telefone = req.body.telefone;
    var _email = req.body.email;
    var _cep = req.body.cep;
    var _numero = req.body.numero;
    var _id = req.body.id;
    
    
    var resultado;
    
    connection.query("update tbl_cliente set celular = '"+ _celular + "', telefone = '"+ _telefone +"', email = '"+ _email + "', cep = '"+ _cep +"', numero = '"+ _numero +"' where id_cliente = " + _id, function(err, row, fields){
        

        if(!err){
            resultado = "Dados alterados com sucesso";
            res.send(resultado);
            
        }else{
            resultado = "Infelizmente não conseguimos alterar seus dados, tente novamente mais tarde. =(";
            res.send(resultado);
            console.log("Erro ao alterar usuario");
        }
        
    });   
    
});

//=================================== ALTERAR DADOS COM SENHA ====================================

app.post("/AlterarDadosSenha", function(req, res){
    
    var _celular = req.body.celular;
    var _telefone = req.body.telefone;
    var _email = req.body.email;
    var _cep = req.body.cep;
    var _numero = req.body.numero;
    var _id = req.body.id;
    var _senha = req.body.senha;
    
    var resultado;
    
    connection.query("update tbl_cliente set celular = '"+ _celular + "', telefone = '"+ _telefone + "', email = '"+ _email + "', cep = '"+ _cep +"', numero = '"+ _numero +"', senha = '"+ _senha +"' where id_cliente = " + _id, function(err, row, fields){
        
        if(!err){
            resultado = "Dados alterados com sucesso";
            res.send(resultado);
            
        }else{
            resultado = "Infelizmente não conseguimos alterar seus dados, tente novamente mais tarde. =(";
            res.send(resultado);
            console.log("Erro ao alterar usuario: " + err);
        }
        
    });   
    
});

//====================================== LISTAR MESAS EM ATENDIMENTO ========================================

app.get("/BuscarMesasAtendimento", function(req, res){
    
    var _idFuncionario = req.query.id;
    
    var mesa = [];
    
    connection.query("select tm.*, tp.id_pedido, tp.id_cliente from tbl_pedido as tp inner join tbl_mesa as tm on tm.id_mesa = tp.id_mesa where status = 1 and tp.id_garcom = " + _idFuncionario , function(err, row, fields){
        
        if(!err){
            mesa = row;
            res.send(mesa);
            
        }else{
            console.log("Erro ao buscar mesas: " + err);
            res.send("Erro ao buscar mesas em atendimento");            
        }
        
    });
    
    
});


//========================   buscar funcionário id ===============================


app.get("/BuscarFuncionarioID", function(req, res){
   
    var id = req.query.id;
    
    var funcionario;
    console.log("to aqui func");
    
    connection.query("select * from tbl_funcionario where id_funcionario = "+ id, function(err, row, fields){
        
        if(!err){
            funcionario = row;
            res.send(funcionario);
            console.log(funcionario);
        }else{
            console.log("Erro ao buscar funcionário: " + err);
            res.send("Erro ao buscar funcionário");
        }
        
    });
    
});

//======================= BUSCAR MESAS DISPONIVEIS ========================

app.get("/BuscarMesasDisponiveis", function(req, res){
    
    var id = req.query.id;
    var mesa = [];
    
    console.log("to aqui");
    
    connection.query("select * from tbl_mesa where id_restaurante = "+ id +" and id_mesa not in(select tp.id_mesa from tbl_pedido as tp where tp.status = 1)", function(err, row, fields){
       
        if(!err){
            mesa = row;
            res.send(mesa);
            console.log(mesa);
        }else{
            console.log("Erro ao buscar mesas: " + err);
            res.send("Erro ao buscar messas");
        }
    });
    
});

//===================== BUSCAR EVENTOS POR ID ==========================

app.get("/BucarEventoID", function(req, res){
    
    var _id = req.query.id;
    
    var eventos;
    
    
    connection.query("select te.*, tr.Nome as nome_filial, date_format(data, '%d/%m/%Y') as data from tbl_eventos as te inner join tbl_restaurante as tr on tr.id_restaurante = te.id_restaurante where id_evento = "+ _id, function(err, row, fields){
        
        if(!err){
            eventos = row;
            res.send(eventos);
        }else{
            console.log("Erro ao listar evento: " + err);
            res.send("Erro ao buscar evento");
        }
        
    });

});


//============================ BUSCAR ULTIMO PEDIDO ========================================

app.get("/buscarUltimoPedido", function(req, res){

    var pedido;
    
    connection.query("select * from tbl_pedido order by id_pedido desc", function(err, row, fields){
       
        if(!err){
            pedido = row;
            res.send(pedido);                        
        }else{
            console.log("erro: " + err);
            res.send("erro");
        }
        
    });
    
});
    


//============================ ABRIR NOVA CONTA ======================================


app.post("/AbrirPedido", function(req, res){
    
    var _idMesa = req.body.idMesa;
    var _idGarcom = req.body.idGarcom;
    var _status = 1;
    var _codigo = req.body.codigo;
    var _data = req.body.data;
    
    var resultado;
    
    connection.query("insert into tbl_pedido (id_garcom, id_mesa, status, data_hora_abertura, codigo) values ("+  _idGarcom +", "+ _idMesa +", "+ _status +", '"+ _data +"', '"+ _codigo +"')", function(err, row, fields){
        
        if(!err){
            resultado = "Conta Aberta";
            res.send(resultado);
        }else{
            resultado = "Erro ao abrir conta";
            res.send(resultado);
            console.log("Erro ao abrir conta: " + err);
        }
        
    });   
    
});

//========================= ABRIR CONTA CLIENTE ===============================

app.get("/AbrirPedidoCliente", function(req, res){
    
    var _codigo = req.query.codigo;
    var _idCliente = req.query.idCliente;
    
    var resultado;
    
    connection.query("update tbl_pedido set id_cliente = "+ _idCliente + " where codigo = '" + _codigo + "'", function(err, row, fields){
        
        if(!err){
            resultado = "Agora você está conectado com o nosso garçom, pode fazer seus pedidos!";
            res.send(resultado);
        }else{
            resultado = "Acho que não encontramos esse código, ou você errou na digitação, por favor, verifique.";
            res.send(resultado);
            console.log("Erro ao adicionar cliente a conta: " + err);
        }
        
        
    });
    
    
});


//==================== BUSCAR PERIODOS ========================================

app.get("/BuscarPeriodos", function(req, res){
    
    var periodos = [];
    
    connection.query("select * from tbl_periodo", function(err, row, fields){
        
        if(!err){
            periodos = row;
            res.send(periodos);
        }else{
            console.log("erro ao buscar periodos: " + err);
            res.send("Erro ao buscar periodos");
        }
    
    });
    
});

//=========================== BUSCAR MESAS DISPONIVEIS =========================


app.get("/BuscarMesasDisponiveisReserva", function(req, res){
    
    var idRestaurante = req.query.idRestaurante;
    var idPeriodo = req.query.idPeriodo;
    var dataEcolhida = req.query.data;
    
    var mesas = [];
    
    connection.query("select * from tbl_mesa where validacao_reserva = 1 and id_restaurante = " + idRestaurante + " and id_mesa not in(select r.id_mesa from tbl_reservas as r inner join tbl_mesa as m on r.id_mesa = m.id_mesa and m.id_restaurante = " + idRestaurante +" where data ='" + dataEcolhida + "' and id_periodo=" + idPeriodo + " and validacao = 0 or null)", function(err, row, fields){
        
        if(!err){
            mesas = row;
            res.send(mesas);
        }else{
            console.log("Erro ao buscar mesas dispooveis para reserva: " + err);
            res.send("Erro ao buscar mesas disponiveis para reserva");
        }
        
    });
    
});


//======================== NOVA RESERVA ====================================

app.post("/NovaReserva", function(req, res){
    
    var idPeriodo = req.body.idPeriodo;
    var dataEcolhida = req.body.data;
    var idCliente = req.body.idCliente;
    var idMesa = req.body.idMesa;
    
    var resultado;
    
    connection.query("insert into tbl_reservas (id_cliente, id_periodo, data, id_mesa) values ("+ idCliente +", "+ idPeriodo +", '"+ dataEcolhida +"', "+ idMesa +")", function(err, row, fields){
        
        if(!err){
            resultado = "Muito obrigado pela preferencia, entraremos em contato para confirmar a sua reserva...";
            res.send(resultado);
        }else{
            resultado = "Ops, não conseguimos realizar a sua reserva, por favor tente novamente e confira os dados";
            res.send(resultado);     
        }
        
    });
    
    
});

//=========================== ENVIAR PEDIDO ========================

app.post("/EnviarPedido", function(req, res){
   
    var _idPedido = req.body.idPedido;
    var _idPrato = req.body.idProduto;
    var _obs = req.body.obs;
    var _qtd = req.body.qtd;
    
    io.sockets.emit("novo_pedido", "novo_pedido");
    
    var resultado;
    
    connection.query("insert into tbl_pedido_produto(id_pedido, id_produto, qtd, obs) values ("+ _idPedido +", "+ _idPrato +", "+ _qtd +", '"+ _obs +"')", function(err, row, fields){
        
        if(!err){
            resultado = "Pedido enviado.";
            res.send(resultado);
        }else{
            resultado = "Erro ao enviar pedido.";
            res.send(resultado);
            console.log("Erro ao enviar pedido: " + err);
        }
    });
    
});

//=======================  BUSCAR ITENS SOLICITADOS =================================

app.get("/BuscarItensSolicitados", function(req, res){
    
    var idPedido = req.query.id;
    
    var itens = [];
    
    connection.query("select tpp.*, tp.nome from tbl_pedido_produto tpp inner join tbl_produto as tp on tp.id_produto = tpp.id_produto where id_pedido = " + idPedido, function(err, row, fields){
        if(!err){
            itens = row;
            res.send(itens);
            
        }else{
            console.log("Erro ao listar pedidos: " + err);
            res.send("Erro ao listar pedidos");
            
        }
        
    });
    
});

//======================= FECHAR CONTA ===================================

app.get("/FecharConta", function(req, res){
   
    var id = req.query.id;
    
    var resultado;
    
    io.sockets.emit("conta_fechada","conta_fechada");
    
    connection.query("update tbl_pedido set status = 0 where id_pedido = " + id, function(err, row, fields){
        
        if(!err){
            resultado = "Conta finalizada com sucesso";
            res.send(resultado);
        }else{
            console.log("Erro ao finaizar conta: " + err);
            res.send("Erro ao finalizar conta");
        }
        
    });
    
});


//========================== BUSCAR PEDIDO ID CLIENTE ===========================


app.get("/BuscarPedidoIDCliente", function(req, res){
    
    var idCliente = req.query.id;
    
    var pedido;
    
    connection.query("select * from tbl_pedido where status = 1 and id_cliente = "+ idCliente, function(err, row, fields){
        
        if(!err){
            pedido = row;
            res.send(pedido);
        }else{
           console.log("Erro ao buscar pedidos: "+ err);
            res.send("Erro ao buscar os pedidos desse cliente");
        }
        
        
    });
    
    
});


//============================== BUSCAR PRATOS PEDIDO ============================

app.get("/BuscarPratosPedido", function(req, res){
   
    var id = req.query.id;
    var idCliente =  req.query.idCliente;
    
    var pratos = [];
    
    connection.query("select tpp.id_pedido, tpp.id_produto, tpp.qtd, tp.nome, tp.preco, ti.url, tped.status from tbl_pedido_produto as tpp inner join tbl_produto as tp on tp.id_produto = tpp.id_produto inner join tbl_produto_img as tpi on tpi.id_produto = tp.id_produto inner join tbl_imagem as ti on ti.id_imagem = tpi.id_img inner join tbl_pedido as tped on tped.id_pedido = tpp.id_pedido where tpp.id_pedido = " + id + " and tped.id_cliente = " + idCliente, function(err, row, fields){
       
        if(!err){
            pratos = row;
            res.send(pratos); 
        }else{
            res.send("Erro ao listar os pratos desse pedido");
            console.log("Erro ao listar pratos do pedido: " + err);
        }
    });
    
});

//==================================== BUSCAR ULTIMA NOTA FISCAL =====================================

app.get("/BuscarUltimaNota", function(req, res){
    
    var nota;
    
    connection.query("select * from tbl_nota_fiscal order by id_nota_fiscal desc limit 1", function(err, row, fields){
        
        if(!err){
            nota = row;
            res.send(nota);
        }else{
            console.log("Erro ao buscar nota: " + err);
            res.send("Erro ao buscar nota");
        }
        

    });
    
    
});


//============================= GERER NOTA FISCAL =========================================

app.get("/GerarNota", function(req, res){
   
    var id_pedido = req.query.idPedido;
    var data = req.query.data;
    var numero = req.query.numero;
    
    
    var resultado;
    
    connection.query("insert into tbl_nota_fiscal (id_pedido, emissao, numero) values("+ id_pedido +", '"+ data +"', '"+ numero + "') ", function(err, row, fields){
        
        if(!err){
            resultado = "Nota fiscal gerada";
            res.send(resultado);
        }else{
            resultado = "Nota fiscal não foi gerada";
            res.send(resultado);
        }
        
    });
    
    
});

//=================================== BUSCAR NOTA FISCAL GERADA ============================

app.get("/BuscarNotaFiscalID", function(req, res){
    
    var id = req.query.id;
    
    var nota;
    
    connection.query("select *, date_format(emissao, '%d/%m/%Y às %h:%m:%s') as emissao from tbl_nota_fiscal where id_pedido = " + id, function(err, row, fields){
        
        if(!err){
            nota = row;
            res.send(nota);
        }else{
           console.log("Erro ao buscar nota: " + err);
        }
        
    });
    
});

//=========================== PESQUISAR PRATOS ==========================================

app.get("/BuscarPratosID", function(req, res){
    
    var _texto = req.query.text;
    
    var pratos;
    
    connection.query("select tp.*, ti.url from tbl_produto as tp inner join tbl_produto_img as tpi on tpi.id_produto = tp.id_produto inner join tbl_imagem as ti on tpi.id_img = ti.id_imagem where nome like '%"+ _texto +"%' ", function(err, row, fields){
        
        if(!err){
            pratos = row;
            res.send(pratos);            
        }else{
            console.log("Erro ao pesquisar pratos: " + err);
        }
        
    });
    
});

//======================= CANCELAR PEDIDO ===================================



//====================== LISTENER ===========================
http.listen(8888, function(){
  console.log("Servidor rodando na porta 8888");
});