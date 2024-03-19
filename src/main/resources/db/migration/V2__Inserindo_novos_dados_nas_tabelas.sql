-- Inserir dados na tabela Clientes
INSERT INTO Clientes (ID_CLIENTE, CPF, NOME, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (1, '12345678900', 'Fulano de Tal', '2023-10-17 12:00:00', '2023-10-17 12:00:00', NULL);

-- Inserir dados na tabela Enderecos
INSERT INTO Enderecos (ID_ENDERECO, ID_CLIENTE, RUA, NUMERO, CIDADE, ESTADO, COMPLEMENTO, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (1, 1, 'Avenida Reboucas', '123', 'Sao Paulo', 'SP', 'Complemento A', '2023-10-17 12:00:00', '2023-10-17 12:00:00', NULL);

-- Inserir dados na tabela Telefones
INSERT INTO Telefones (ID_TELEFONE, ID_CLIENTE, DDD, NUMERO, TIPO, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (1, 1, '11', '999999999', 'Celular', '2023-10-17 12:00:00', '2023-10-17 12:00:00', NULL);

-- Inserir dados na tabela Categorias
INSERT INTO Categorias (ID_CATEGORIA, NOME_CATEGORIA, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (1, 'Lanche', '2023-10-17 12:00:00', '2023-10-17 12:00:00', NULL);

-- Inserir dados na tabela Produtos
INSERT INTO Produtos (ID_PRODUTO, ID_CATEGORIA, NOME, DESCRICAO, FOTO, PRECO_ATUAL, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (1, 1, 'X-BURGUER', 'Pão, hambúrguer 120gr, alface, tomate, picles e molho da casa', 'base64encodeddata', 19.99, '2023-10-17 12:00:00', '2023-10-17 12:00:00', NULL);

-- Inserir dados na tabela Pedidos
INSERT INTO Pedidos (ID_PEDIDO, ID_CLIENTE, DATA_PEDIDO, DATA_FINALIZADO)
VALUES (1, 1,'2023-10-17 12:00:00', '2023-10-18 14:30:00');

-- Inserir dados na tabela Pedido_Produto
INSERT INTO Pedido_Produto (ID_PEDIDO_PRODUTO, ID_PEDIDO, ID_PRODUTO, QUANTIDADE, PRECO_VENDA, OBSERVACAO)
VALUES (1, 1, 1, 5, 19.99, 'Retirar o picles');
