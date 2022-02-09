--create databaseData
CREATE TABLE `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invdate` date NOT NULL,
  PRIMARY KEY (`id`)
);
INSERT INTO `invoice` VALUES (1,'2020-11-23'),(2,'2020-11-22'),(3,'2020-11-21');

CREATE TABLE `item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `ipid` int NOT NULL,
  `invid` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ipid` (`ipid`),
  KEY `invid` (`invid`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`ipid`) REFERENCES `itemproduct` (`id`),
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`invid`) REFERENCES `invoice` (`id`)
);

INSERT INTO `item` VALUES (1,5,1,1),(2,3,2,1),(3,4,1,2),(4,1,3,2),(5,6,2,3);

CREATE TABLE `itemproduct` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `price` int NOT NULL,
  `inventory` int NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `itemproduct` VALUES (1,'Pen',10,20),(2,'Book',15,50),(3,'Toy',20,40);


-- 分析1，每一張發票有哪些商品
select a.id,c.text,b.amount,c.price
from item as b inner join itemproduct as c
on b.ipid=c.id inner join invoice as a
on a.id=b.invid
where a.id=2;

-- 分析2每一張發票有幾件商品
select a.id,count(*) as 商品總數
from invoice as a inner join item as b
on a.id=b.invid
group by a.id;

-- 分析3每一張發票的價值是多少
select a.id,sum(b.amount*c.price) as '金額'
from item as b inner join itemproduct as c
on b.ipid=c.id inner join invoice as a
on a.id=b.invid
group by a.id

-- 分析4每一樣商品各賣了多少
select a.id,c.text,sum(b.amount),c.price,sum(b.amount*c.price) as '金額'
from item as b inner join itemproduct as c
on b.ipid=c.id inner join invoice as a
on a.id=b.invid
group by c.text

-- 分析5哪件商品賣了最多錢
select a.id,c.text,sum(b.amount),c.price,sum(b.amount*c.price) as '金額'
from item as b inner join itemproduct as c
on b.ipid=c.id inner join invoice as a
on a.id=b.invid
group by c.text
order by 5 desc limit 1

-- 分析6哪一張發票價值最高
select a.id,sum(b.amount*c.price) as '金額'
from item as b inner join itemproduct as c
on b.ipid=c.id inner join invoice as a
on a.id=b.invid
group by a.id
order by 2 desc limit 1







