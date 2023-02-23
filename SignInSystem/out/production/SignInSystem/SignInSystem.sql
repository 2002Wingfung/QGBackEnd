create database if not exists mysql_test;
use mysql_test;
create table signIn(
    id int primary key auto_increment,
    userName varchar(20) unique,-- 用户名
    password varchar(20),-- 密码
    phoneNumber bigint,-- 手机号码
    email varchar(20)-- 邮箱
);
desc signIn;
drop table signIn;
insert into signIn (userName, password, phoneNumber, email)
values ('jim','123',13392093870,'1039377283@qq.com'),
       ('jane','567',110,'11111@qq.com'),
       ('marry','894',120,'12345@qq.com');
truncate signIn;