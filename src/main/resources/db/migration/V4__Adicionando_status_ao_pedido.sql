alter table Pedidos add COLUMN status varchar(20);
update Pedidos set status = 'RECEBIDO' where ID_PEDIDO > 0;
update Pedidos set status = 'FINALIZADO' where DATA_FINALIZADO IS NOT NULL;
alter table Pedidos modify column status varchar(20) not null;