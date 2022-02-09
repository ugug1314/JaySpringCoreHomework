
create table BuyRecord(
orderid int auto_increment,
buyerid int,
buydate timestamp default current_timestamp,
bookid int,
qty int,
price int,
primary key(orderid)
)