-- Inserir dados na tabela Produtos
INSERT INTO Produtos (ID_PRODUTO, ID_CATEGORIA, NOME, DESCRICAO, FOTO, PRECO_ATUAL, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (2, 1, 'X-BACON', 'Pão, hambúrguer 120gr, bacon, alface, tomate, picles e molho da casa', 'base64encodeddata2', 29.99, '2023-10-21 12:00:00', '2023-10-21 12:00:00', NULL);

-- Inserir dados na tabela Clientes
INSERT INTO Clientes (ID_CLIENTE, CPF, NOME, CRIADO_EM, ATUALIZADO_EM, DELETADO_EM)
VALUES (2, '0123020202', 'Sicrano', '2023-10-21 12:00:00', '2023-10-21 12:00:00', NULL);

-- Inserir dados na tabela Pedidos
INSERT INTO Pedidos (ID_PEDIDO, ID_CLIENTE, DATA_PEDIDO, DATA_FINALIZADO, STATUS)
VALUES (2, 1,'2023-10-21 12:00:00', null, "Recebido");

-- Inserir dados na tabela Pedidos
INSERT INTO Pedidos (ID_PEDIDO, ID_CLIENTE, DATA_PEDIDO, DATA_FINALIZADO, STATUS)
VALUES (3, 2,'2023-10-21 12:02:00', null, "Recebido");

-- Inserir dados na tabela Pedido_Produto
INSERT INTO Pedido_Produto (ID_PEDIDO_PRODUTO, ID_PEDIDO, ID_PRODUTO, QUANTIDADE, PRECO_VENDA, OBSERVACAO)
VALUES (2, 1, 2, 2, 29.99, null);

-- Inserir dados na tabela Pedido_Produto
INSERT INTO Pedido_Produto (ID_PEDIDO_PRODUTO, ID_PEDIDO, ID_PRODUTO, QUANTIDADE, PRECO_VENDA, OBSERVACAO)
VALUES (3, 2, 2, 2, 29.99, null);

-- Inserir dados na tabela Pedido_Produto
INSERT INTO Pedido_Produto (ID_PEDIDO_PRODUTO, ID_PEDIDO, ID_PRODUTO, QUANTIDADE, PRECO_VENDA, OBSERVACAO)
VALUES (4, 3, 1, 1, 19.99, null);