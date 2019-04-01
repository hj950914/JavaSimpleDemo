#建数据库
create database Demo1;

#用户表
create table user(
  id int auto_increment primary key,
  username varchar(10) not null,
  password varchar(20) not null
);

#插入数据
insert into user (username,password) values('test1','test1');
#查询数据
select username,password from user where username='?' and password='?';

#文件表
create table file(
  fid int primary key auto_increment,
  fname varchar(20) not null,
  fcontent BLOB not null
);
#插入数据
insert into file (fname,fcontent) values('?',?)
#查询数据
select fcontent from file where fid=(select max(fid) from file) for update;
