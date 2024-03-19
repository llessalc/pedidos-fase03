alter table pedidos add COLUMN status varchar(20);
update pedidos set status = 'RECEBIDO' where ID_PEDIDO > 0;
update pedidos set status = 'FINALIZADO' where DATA_FINALIZADO IS NOT NULL;
alter table pedidos modify column status varchar(20) not null;