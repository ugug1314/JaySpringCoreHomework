--建立 Emp 資料表
create table if not exists emp(
  eid int not null auto_increment, --主鍵(自動產一序號:1,2,3 過號不返回)
  ename varchar(50) not null unique,   --員工姓名(不重覆)
  age int, --員工年齡
  createtime timestamp default current_timestamp,  --建檔時間
  primary key(eid)
)

--新增 Emp 範例資料
insert into emp(ename,age) values('john',28);
insert into emp(ename,age) values('mary',30);
insert into emp(ename,age) values('bobo',29);

--2022/1/16
--建立 Job 資料表
create table if not exists job(
  jid int not null auto_increment, --主鍵(自動產一序號:1,2,3 過號不返回)
  jname varchar(50) not null unique,   --工作名稱
  eid int, --員工id
    primary key(jid),
    foreign key(eid) references emp(eid) --外來鍵約束
)
--由上述可知，一個emp對應多個job，一個job對應一個emp
insert into job(jname,eid) values('report',1);
insert into job(jname,eid) values('coding',3);
insert into job(jname,eid) values('jobA',3);
insert into job(jname,eid) values('jobB',4);
insert into job(jname,eid) values('jobC',7);
insert into job(jname,eid) values('jobD',12);
insert into job(jname,eid) values('jobE',13);
insert into job(jname,eid) values('jobF',1);
insert into job(jname,eid) values('jobG',1);
insert into job(jname,eid) values('jobH',3);
insert into job(jname,eid) values('jobI',12);
insert into job(jname,eid) values('jobJ',7);
insert into job(jname,eid) values('jobK',4);
insert into job(jname,eid) values('jobL',3);
insert into job(jname) values('jobM');
insert into job(jname) values('jobN');


--每一個員工的工作列表
select e.ename,j.jname
from emp e,job j
where e.eid=j.eid
order by e.ename;

--每一個員工有幾項工作
SELECT a.ename, count(b.jname)
FROM emp a inner JOIN job b on a.eid=b.eid
GROUP BY  a.ename

--查詢工作量最多的員工
--方式1
SELECT a.ename, max(a.maxcount)
FROM (SELECT a.ename, count(b.eid) as maxcount
FROM emp a inner JOIN job b on a.eid=b.eid
GROUP BY  a.ename
order by count(b.eid) desc) a;

--方式2
select e.ename, count(j.jname) as work 
from emp e, job j
where e.eid=j.eid 
group by e.ename
order by 2 deSc 
limit 1

-----使用在queryEmps2-------
select e.eid, e.ename, e.age, e.createtime,
       j.jid as job_jid, j.jname as job_jname, j.eid as job_eid
from emp e left outer join job j on j.eid = e.eid
