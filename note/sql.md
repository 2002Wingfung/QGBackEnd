# 1 SQL的概述

SQL全称: Structured Query Language，是结构化查询语言，用于**访问和处理数据库的标准的计算机语言**。

SQL语言1974年由Boyce和Chamberlin提出，并首先在IBM公司研制的关系数据库系统SystemR上实现。

---

# 2 SQL的特点

## 语言特点

* 具有综合统一性，不同数据库的支持的SQL稍有不同
* 非过程化语言
* 语言简捷，用户容易接受
* 以一种语法结构提供两种使用方式，既可以单独使用SQL，也可以和其他语言搭配使用。

## 语法特点

* SQL对关键字的大小写不敏感

  * select/SELECT/Select都行
  * 关键字建议使用大写

* SQL语句可以以单行或者多行书写，以分号结束

  * ```sql
    SELECT 
    * 
    from 
    emp;
    -- 以上相当于：
    SELECT * FROM emp;
    ```

    

* SQL的注释

  * -- 单行注释 ，**-- 后面一定要加一个空格**

  * #单行注释，**#后面可加可不加空格**

  * ```sql
    SELECT * FROM emp;-- 这里是注释
    ```

  * ```sql
    /*
    多行注释 
    多行注释
    */
    ```

---

# 3 数据库系统

## 1、数据库

数据库(DataBase)是一个以某种组织方式存储在磁盘上的数据的集合.

## 2、数据库应用

数据库应用系统是指基于数据库的应用软件.

## 3、数据库管理系统(数据库软件)

### 关系型数据库(RDBMS)

* Oracle数据库
* MysQL数据库
* SQL server数据库 
* PostgreSQL
* SQLite

### 非关系型数据库(NoSQL)

* Redis
* MongoDB
* Elasticsearch 
* Cassandra
* HBase

### SQL和数据库管理系统的关系

* SQL是一种用于操作数据库的语言，SQL适用于所有关系型数据库。
* MysQL、Oracle、SQLServer是一个数据库软件，这些数据库软件支持标准SQL，也就是通过SQL可以使用这些软件，不过每一个数据库系统会在标准SQL的基础上扩展自己的SQL语法。
* 大部分的NoSQL数据库有自己的操作语言，对SQL支持的并不好。

### 关系型数据数据库管理系统组成

数据库管理系统(DBMS) 主要由数据库和表组成，一个数据库系统可以有很多数据库，每个数据库可以有很多表。

---

# 4 MySQL

## 4.1 MySQL的特点

* MySQL数据库是用C和C++语言编写的，以保证源码的**可移植性**
* 支持多个操作系统，例如: Windows、Linux、Mac OS等等
* 支持多线程，可以充分的利用**CPU资源**
* 为多种编程语言提供API，包括C语言，Java，PHP，Python语言等
* MySQL优化了SQL算法，有效的**提高了查询速度**
* MySQL**开放源代码且无版权制约**，自主性强、使用成本低。
* MySQL历史悠久、社区及用户非常活跃，遇到问题，可以很快获取到帮助。

## 4.1 MySQL的版本

MySQL的命名机制：使用由3个数字和一个后缀组成的版本号。

例如，像mysql-8.0.26的版本号这样解释：

* 第1个数字(8)是主版本号，描述了文件格式。所有版本的发行都有相同的文件格式
* 第2个数字(0)是发行级别，主版本号和发行级别组合到一起便构成了发行序列号
* 第3个数字(26)是在此发行系列的版本号，随每个新分发版递增。

## 4.2 卸载MySQL

* 停止服务
  * 方式1: 打开DOS，使用net stop mysql命令
  * 方式2:[win+R快捷键]-->输入 services.msc -->进入服务窗口关闭mysql服务
* 卸载程序 (可选)(通过安装包安装的就用这个方法卸载，如果是直接解压安装的就不需要执行此方法)
  * 使用360卸载mysql程序或[控制面板]-->[程序和功能]-->右键卸载程序
* 删除项目根文件夹:
  * 进入mysql安装位置，删除mysql的解压(安装)文件夹
* 删除C盘隐藏文件夹 (可选)(通过安装包安装的就执行此步骤，解压安装则不需要执行此步骤)
  * 显示隐藏文件后，删除C盘下的“C:\ProgramData\MySQL”所有文件;
* 删除注册表信息:
  * [win+R快捷键]-->输入regedit 命令打开注册表窗口，删除以下文件:
    * HKEY_LOCAL_ MACHINE/SYSTEM/ControlSet001/ Services/Eventlog/Applications/MySQL
    * HKEY_LOCAL_MACHINE/SYSTEM/ControlSet002/Services/Eventlog/Applications/MySQL
    * HKEY_LOCAL_MACHINE/SYSTEM/CurrentControlSet/Services/Eventlog/Applications/MySQL
* 删除环境变量里的配置:
  * 如果更改了环境变量，那么进入[环境变量]中删除系统变量中的MYSQL_HOME变量和删除Path变量中的mysql路径
* 删除MYSQL服务
  * 管理员权限cmd-->sc delete MySQL的服务名，在任务管理器中可以查看。

---

# 5 SQL的分类

- DDL数据定义语言，用来定义数据库对象：数据库，表，列
- DML数据操作语言，用来对数据库中表的数据进行增删改查
- DQL数据查询语言，用来查询数据库中表的记录
- DCL数据控制语言，用来定义数据库的访问权限和安全级别以及创建用户

---

## 5.1 DDL解释

DDL(Data Definition Language)，数据定义语言，该语言部分包括以下内容：

* 对数据库的常用操作
* 对表结构的常用操作
* 修改表结构

## 5.2 对数据库的常用操作

| 功能                                            | SQL代码                                                    |
| ----------------------------------------------- | ---------------------------------------------------------- |
| 查看所有的数据库                                | show databases;                                            |
| 创建数据库                                      | create database [if not exists] 数据库名字 [charset=utf8]; |
| 切换 (选择要操作的/选择使用哪一个数据库) 数据库 | use 数据库名字;                                            |
| 删除数据库                                      | drop database [if exists] 数据库名字;                      |
| 修改数据库编码                                  | alter database 数据库名字 character set utf8;              |

```SQL
select database();//查询当前使用的数据库
```

注意：

创建数据库时，如果创建一个重名的数据库，则会报错，加上if not exists语句则不会报错。

删除数据库时同理。

* `show databases;`

  查询后可以看到这四个数据库，是MySQL自带的数据库

  * information_schema是记录MySQL有哪些库和表的信息，是一种视图.
  * mysql是存储这个MySQL数据库中最核心的信息，权限，安全等等
  * performance_schema是存储性能相关信息
  * sys是存储系统相关的信息

---

## 5.3 MySQL数据库基本操作-DDL

### 5.3.1 对表结构的常用操作--创建表

**[ ]内的内容可以省略。**

* 创建表格式

  ```sql
  create table [if not exists]表名(
  	字段名1 类型[(宽度)][约束条件][comment '字段说明'],
      字段名2 类型[(宽度)][约束条件][comment '字段说明'],
      字段名3 类型[(宽度)][约束条件][comment '字段说明']
  )[表的一些设置];
  ```

  

* 创建表是构建一张空表，指定这个表的名字，这个表有几列，每一列叫什么名字(字段名)，以及每一列存储的数据类型。

注意：只要不是数字、日期，就算是字符串，属于varchar类型。

整数是int类型，日期是date类型，小数是double类型。

```sql
name varchar(20)
-- 20就是20个字符串的意思
```

#### 数值类型

| 类型         | 大小    | 范围（有符号）     | 范围（无符号） |
| ------------ | ------- | ------------------ | -------------- |
| TINYINT      | 1 byte  | (-128,127)         | (0,255)        |
| SMALLINT     | 2 bytes | (-32768,32767)     | (0,65535)      |
| MEDIUMINT    | 3 bytes | (-8388608,8388607) | (0,16777215)   |
| INT或INTEGER | 4 bytes | ...                | ...            |
| BIGINT       | 8 bytes | ...                | ...            |
| FLOAT        | 4 bytes | ...                | ...            |
| DOUBLE       | 8 bytes | ...                | ...            |
| DECIMAL      | 无      | 依赖于M和D的值     | 依赖于M和D的值 |

注意：

* decimal(M,D)有效位数为M位，小数点保留D位。
  * decimal也是小数的一种
* 无符号数则在数据类型后面加一个**unsigned**即可。

---

#### 字符串类型

| 类型       | 大小         | 用途                          |
| ---------- | ------------ | ----------------------------- |
| CHAR       | 0-255bytes   | 定长字符串                    |
| VARCHAR    | 0-65535bytes | 变长字符串                    |
| TINYBLOB   | 0-255bytes   | 不超过255个字符的二进制字符串 |
| TINYTEXT   | 0-255bytes   | 短文本字符串                  |
| BLOB       | 0-65535bytes | 二进制形式的长文本数据        |
| TEXT       | 0-65535bytes | 长文本数据                    |
| MEDIUMBLOB | 更大         | 二进制形式的中等长度文本数据  |
| MEDIUMTEXT | 更大         | 中等长度文本数据              |
| LONGBLOB   | 最大         | 二进制形式的极大文本数据      |
| LONGTEXT   | 最大         | 极大文本数据                  |



注意：

* 赋值时一般用单引号引用要存入的内容。
* 这并不是一个数组，即使存入的字符少于自己设定的字符，也不会浪费空间。
* 一个汉字也算是一个字符，与英文字符一样。

---

#### 日期类型

| 类型      | 大小(bytes) | 范围                                    | 格式                | 用途                     |
| --------- | ----------- | --------------------------------------- | ------------------- | ------------------------ |
| DATE      | 3           | 1000-01-01/9999-12-31                   | YYYY-MM-DD          | 日期值                   |
| TIME      | 3           | '-838:59:59'/'838:59:59'                | HH:MM:SS            | 时间值或持续时间         |
| YEAR      | 1           | 1901/2155                               | YYYY                | 年份值                   |
| DATETIME  | 8           | 1000-01-01 00:00:00/9999-12-31 23:59:59 | YYYY-MM-DD HH:MM:SS | 混合日期和时间值         |
| TIMESTAMP | 4           | 1000-01-01 00:00:00/2038                | YYYY-MM-DD HH:MM:SS | 混合日期和时间值，时间戳 |

注意：

* TIMESTAMP 的结束时间是：

  第 2147483647 秒，北京时间 2038-1-19 11:14:07，格林尼治时间 2038年1月19日 03:14:07

* 日期类型也要用单引号引用，易错！

---

### 5.3.2 对表结构的常用操作一其他操作

| 功能                       | SQL                          |
| -------------------------- | ---------------------------- |
| 查看当前数据库的所有表名称 | show tables;                 |
| 查看指定某个表的创建语句   | show create table 表名;      |
| 查看表结构                 | desc 表名;                   |
| 删除表                     | drop table [if exists] 表名; |

`desc 表名;`通过查询可以查看到该表的结构信息而不是具体的数据。

---

### 5.3.3 修改表结构

* 添加列

  语法格式

  ```sql
  alter table 表名 add 列名 类型(长度) [约束];
  #为student表添加一个新的字段为:系别 dept 类型为 varchar(20)
  alter table student add dept varchar(20);
  ```

* 修改列明和类型

  语法格式

  ```sql
  alter table 表名 change 旧列名 新列名 类型(长度)[约束];
  -- 为student表的dept字段更换为department varchar(30)
  alter table student change dept department varchar(30);
  ```

  注意：

  在DataGrip中右键点击左侧的表名，里面有一个修改表（旧UI），点进去可以看到详细的表结构（列名、数据类型等等）

* 删除列

  语法格式

  ```sql
  alter table 表名 drop 列名;
  #删除student表中department这列
  alter table student drop department;
  ```

* 修改表名

  语法格式

  ```sql
  rename table 表名 to 新表名;
  #例如:将表student改名成 stu
  rename table student to stu;
  ```

## 5.4 MySQL数据库基本操作-DML

### 5.4.1 基本介绍

DML是指数据操作语言，英文全称是Data Manipulation Language，用来对数据库中表的数据记录进行更新。

关键字:

* 插入insert
* 删除delete
* 更新update

#### 数据插入

列的类型和值的类型要一致，第一列要对应第一个值。

前面写了多少列，后面就写多少个值，且要一一对应。

若没有指定哪一列，则默认给所有列赋值。

* 格式1: 

  ```sql
  insert into 表 (列名1,列名2,列名3...) values (值1,值2,值3...);
  -- 或者：
  insert into 表 (列名1,列名2,列名3...) values (值1,值2,值3...),
  (值1,值2,值3...),
  (值1,值2,值3...);
  -- 相当于增加了3行数据，但是只用了一条语句，用逗号间隔开。
  
  insert into stu (sid, name, birth, gender, age, score) VALUES (1000,'洪永峰','2002-10-24','男',21,99.9);
  
  insert into stu (sid, name, birth, gender, age, score)
  VALUES (1001,'jim','2004-12-29','男',19,66.9),
         (1002,'marry','2001-1-23','男',22,88.9),
         (1003,'jane','2003-11-21','男',20,77.9);
         
  -- 只插入部分数据
  insert into stu (sid,score)values (1006,88.8);
  ```

* 格式2：

  ```sql
  insert into 表 values(值1,值2,值3...);
  -- 向表中插入一行数据（默认所有列都要插入）
  
  insert into stu values (1007,'booby','2000-5-31','女',23,55.5),
                         (1008,'bob','2006-2-3','女',17,55.5);
  ```

---

#### 数据修改

语法格式

```sql
-- 修改同一列的所有行的数据
update 表名 set 字段名(列名)=值,字段名=值...;
-- 将所有学生的性别改为男
update stu set gender ='男';

-- 修改同一列的所有符合条件的行的数据
update 表名 set 字段名=值,字段名=值...where 条件;
-- 将性别为女的学生的sid改为666
update stu set sid=666 where gender='女';
-- 将sid小于1000的学生的sid改为888
update stu set sid=888 where sid<1000;
-- 将sid为1006的学生的姓名改为tom，生日改为1999-10-10
update stu set name='tom',birth='1999-10-10' where sid=1006;
```

---

#### 数据删除

```SQL
delete from 表名 [where 条件];-- 不加条件则会把整个表的数据都删除。
-- 以下两行代码是一样的。
truncate table 表名;
truncate 表名;
```

注意: delete和truncate原理不同，delete只删除内容，而truncate类似于drop table，可以理解为是将整个表删除,然后再创建该表:

---

#### 查询所有数据

```SQL
select * from 表名;
-- “*”意思是：指查询表或视图中的所有字段。
-- 查询单个字段
select 字段名 from 表名;

-- 查询多个字段
select 字段1,字段2.... from 表名;

-- 条件查询
select 字段1,字段2,... from 表名 where 条件;
```

---

### 练习

```sql
-- 1.创建表
/*
创建员工表employee,字段如下:
id (员工编号)，name (员工名字)，gender(员工性别)，salary(员工薪资)
 */
-- use mysql_test1;
-- 这里可以直接用数据库名.表名的方法
-- 但下文所涉及表名employee的代码前都要加上mysql_test1.
create table if not exists mysql_test1.employee(
    id int,
    name varchar(20),
    gender varchar(2),
    salary double
);

-- 2.插入数据
/*
1，'张三'，'男',2000
2，'李四'，'男',1000
3，'王五'，'女',4000
 */
insert into employee (id, name, gender, salary)
values (1,'张三','男',2000),
       (2,'李四','男',1000),
       (3,'王五','女',4000);


-- 3.修改表数据
-- 3.1 将所有员工薪水修改为5000元。
update employee set salary=5000;
-- 3.2 将姓名为’张三’的员工薪水修改为3000元。
update employee set salary=3000 where name='张三';
-- 3.3 将姓名为'李四’的员工薪水修改为4900元,gender改为女
update employee set salary=4900,gender='女' where  name='李四';
-- 3.4 将王五的薪水在原有基础上增加1099元。
update employee set salary=salary+1000 where name='王五';
```

如果没有使用`use 数据库名;`来运行数据库，且又想更改数据库的表里面的数据，可以用`数据库名.表名`的方法修改表的数据。

---

## 5.5 MySQL约束

**概念**

* 约束英文:constraint
* 约束实际上就是表中数据的限制条件
* 创建表的时候可以用到约束

**作用**

* 表在设计的时候加入约束的目的就是为了保证表中的记录完整性和有效性，比如用户表有些列的值(手机号)不能为空，有些列的值(身份证号)不能重复。

**分类**

* **主键约束**(primary key) PK
  * 限定列的值唯一，而且不能为空
* **自增长约束**(auto increment)
  * 与主键约束搭配使用
  * 加上了这个约束后，这一列的值会自动增长，不用手动操作。
* 非空约束(not null)
  * 这一列的值不能为空
  * 用法和主键约束的方法1一样。
* 唯一性约束(unique)
  * 这一列的值不能重复
* 默认约束(default)
  * 没有人为给这一列赋值的话，会自动赋一个默认值。
* 零填充约束(zerofill)
  * 不赋值时，自动填充值为0
* 外键约束(foreign key) FK

---

### 5.5.1 MySQL约束--主键约束

#### 概念

* MySQL主键约束是**一个列或者多个列**的组合，其值能**唯一**地标识表中的每一行，方便在RDBMS中尽快的找到某一行。
* 主键约束相当于 **唯一约束**+**非空约束** 的组合，主键约束列不允许重复，也不允许出现空值。
* 每个表最多只允许**一个**主键
* 主键约束的关键字是: **primary key**
* 当创建主键的约束时，系统默认会在所在的列和列组合上建立**对应的唯一索引**。

#### 操作

##### 创建单列主键

* 方法1：在定义字段的同时指定主键

  * ```sql
    -- 在 create table 语句中，通过 PRIMARY KEY 关键字来指定主键
    create table 表名(
    	字段名 数据类型 primary key,
        -- 例如：
        id int primary key
    );
    
    ```

    

* 方法2：定义完字段之后指定主键

  * ```sql
    -- 在定义字段之后再指定主键，语法格式如下:
    create table 表名(
    	[constraint 约束名(自己定的)] primary key [字段名]
    );
    
    create table emp2(
    	constraint pk1 primary key(id)
    );
    ```

注意：不能在有主键标记的列中插入重复的数据，也不能插入以null为值的数据。

---

##### 添加多列联合主键

所谓的联合主键，就是这个主键是由一张表中多个字段组成的。

**注意**:

* 1.当主键是由多个字段组成时，**不能直接**在字段名后面声明主键约束（即单列主键的方法1不适用）
* 2.一张表只能有一个主键，联合主键也是一个主键。
* 3.只要联合主键标记的字段不同时相同则可以写入该行数据。
* 4.SQL代码是一行一行执行的，即使下面的代码有错误，他也会执行完上面没有错误的代码。
* 5.联合主键的每一列都不能为空

**语法**：

```sql
create table 表名(
	[constraint 约束名] primary key(字段1,字段2,...,字段n)
);

create table emp3(
    name varchar(20),
    deptid int,
    salary double,
	primary key(name,deptid)
);
```

---

##### 通过修改表结构添加主键

主键约束不仅可以在创建表的同时创建，也可以在修改表时添加。

语法:

```sql
create table 表名(
	....
);
alter table 表名 add primary key(字段列表);
```

字段列表可以是一列也可以是多列.

```sql
alter table emp4 add primary key(eid);
alter table emp5 add primary key(eid,name);
```

---

##### 删除主键约束

一个表中不需要主键约束时，就需要从表中将其删除。删除主键约束的方法要比创建主键约束容易的多。

格式:

```sql
alter table 表名 drop primary key;
```

无论是单列主键还是联合主键都是用这行代码来删除的。

---

### 5.5.2 MySQL约束-自增长约束(auto increment)

#### 概念

在 MySQL中，当主键定义为自增长后，这个主键的值就不再需要用户输入数据了，而由数据库系统根据定义自动赋值每增加一条记录，主键会自动以相同的步长进行增长。

通过给字段添加auto_increment 属性来实现主键自增长。

#### 语法

```sql
字段名 数据类型 auto_increment;
-- 例：
create table t_user1(
	id int primary key auto_increment,
    name varchar(20)
);
```

#### 特点

* 默认情况下，auto_increment的初始值是 1，每新增一条记录，字段值自动加1。
* 一个表中只能有一个字段使用 auto_increment约束(相当于只能有一个主键约束)，且该字段必须有唯一索引，以避免序号重复(即为主键或主键的一部分)。
* auto_increment约束的字段必须具备 NOT NULL 属性。
  * 如果人为给该受约束的字段赋值，则该行存储的数据就是人为赋值的数据；如果没有为该受约束的字段赋值或给该受约束的字段赋NULL值，则给该受约束的字段赋的值默认为上一行的值+1.
* auto_increment约束的字段只能是整数类型 ，如TINYINT、SMALLINT、INT、BIGINT等。一开始可以赋值为NULL，即使用户输入进去的是NULL，该列第一行的数据默认值为1，且从1开始增加。
* auto_increment约束字段的最大值受该字段的数据类型约束，如果达到上限，auto_increment就会失效。

#### 指定自增长的初始值

方式一：创建表时创建

```sql
create table t_user3(
	id int primary key auto_increment,
    name varchar(20)
)auto_increment=100;
-- 初始值设定为100
```

方式二：创建表之后指定

```sql
create table t_user3(
	id int primary key auto_increment,
    name varchar(20)
);
alter table t_user2 auto_increment=100;
-- 初始值设定为100
```

---

#### delete和truncate在删除后自增列的变化

* delete数据之后自动增长从断点开始（自增长会保留上一次的数据，上一次增长到多少这次就是多少）
  * delete删除数据（可以是删除整个表的数据，也可以删除其中一行或几行的数据）之后，自增长还是在最后一个值基础上加1。
* truncate删除数据之后自动增长**从默认起始值1**开始，无论你有没有设定起始值。

---

### 5.5.3 MySQL约束-非空约束(not null)

#### 概念

MySQL非空约束(not null)指字段的值不能为空。对于使用了非空约束的字段，如果用户在添加数据时没有指定值，数据库系统就会报错。

#### 语法

```sql
-- 方式1：创建表时指定
字段名 数据类型 not null;
name varchar(20) not null;
-- 方式2：创建表之后指定
alter table 表名 modify 字段 类型 not null;
alter table t_user7 modify name varchar(20) not null;

-- 删除非空约束
alter table 表名 modify 字段 类型;
alter table t_user7 modify name varchar(20);
-- 即创建的方式2不加后面的not null。
```

注意：

一个语句是从左到右执行的。多个语句是从上到下执行的。

如果有一处有错误，则不会往后继续执行。

null是空的意思，与'null'不同，与''也不同。

'null'是一个字符串null。

''是空串，引号之间没有内容。

---

### 5.5.4 MySQL约束-唯一约束(unique)

#### 概念

唯一约束(Unique Key) 是指所有记录中字段的值不能重复出现。例如，为id 字段加上唯一性约束后，每条记录的id 值都是唯一的，不能出现重复的情况。

#### 语法

```sql
-- 方式1：
字段名 数据类型 unique;
phone_number varchar(20) unique;

-- 方式2：
alter table 表名 add constraint 约束名 unique(列名);
alter table t_user9 add constraint unique_pn unique(phone_number);

-- 删除唯一约束
alter table 表名 drop index 唯一约束名;
alter table t_user9 drop index unique_pn;
-- 如果没有约束名（使用方式1创建唯一约束），则该约束名默认为该列的名字。
```

注意：

在MySQL中NULL和任何值都不相同，甚至和自己都不相同。

唯一约束修饰的字段可以有重复的null，但除了null外，输入其他重复的数据则会报错。

---

### 5.5.5 MySQL约束-默认约束(default)

#### 概念

MysQL默认值约束用来指定某列的默认值。

#### 语法

```sql
-- 方式1：
字段名 数据类型 default 默认值;
address varchar(20) default '北京';

-- 方式2：
alter table 表名 modify 列名 类型 default 默认值;
alter table t_user11 modify address varchar(20) default '深圳';

-- 删除默认约束
alter table 表名 modify 列名 类型 default null;
alter table t_user11 modify address varchar(20) default null;
```

#### 特点

如果指定了默认值后，还对该字段赋值，那么存储的则是自己人为赋的值（可以赋值null值），该行的默认值失效；没有人为地对该字段赋值的话，则存储的是默认值。

---

### 5.5.6 MySQL约束- 零填充约束(zerofill)

#### 概念

* 1、插入数据时，当该字段的值的长度**小于**定义的长度时，会在该值的前面补上相应的0
* 2、zerofill默认为int(10)，整数的长度为10位数。
* 3、当使用zerofill 时，默认会自动加unsigned(无符号)属性，使用unsigned属性后，数值范围是原值的2倍，例如，有符号为-128~+127，无符号为0~256。
* 注意：
  * 补0的操作并不是把0存储进相应的位置，而是看起来数值的前面多了几个0，实际上的数值还是原来没有补0的数值.

#### 操作

```sql
create table t_user12(
	id int zerofill,
    name varchar(20)
);

-- 删除
alter table t_user12 modify id int;
```



---

## 5.6 MySQL数据库基本操作-DQL-基本查询

### 概念

* 数据库管理系统一个重要功能就是数据查询，数据查询不应只是简单返回数据库中存储的数据，还应该根据需要对数据进行筛选以及确定数据以什么样的格式显示。
* MySQL提供了功能强大、灵活的语句来实现这些操作。
* MySQL数据库使用select语句来查询数据。

### 语法格式

```sql
select
[all|distinct]
	<目标列的表达式1> [别名],
	<目标列的表达式2> [别名]...
from <表名或视图名>[别名],<表名或视图名> [别名]...
[where<条件表达式>]
[group by <列名>
[having <条件表达式>]]
[order by <列名> [asc|desc]]-- 升序还是降序
[limit <数字或者列表>];
```

### 简化版语法

```sql
select * 列名 from 表 where 条件;
```

---

### 简单查询

```sql
-- 1.查询所有的商品.
select * from product;
select 列名1,列名2,....,所有列名 from product;
-- product是表名

-- 2.查询商品名和商品价格.
select pname,price from product;

-- 3.别名查询,使用的关键字是as (as是可以省略的)
-- 3.1表别名:
select * from product as p;
-- 给表product取了个别名p

select e.salary from employee e;
-- 给表employee取了个别名e，可以直接用e来调用该表的列名salary。

-- 3.2列别名:
select pname as pn from product;
select pname as '商品名',price '商品价格' from product;

-- 4.去掉重复值.
select distinct price from product;

-- 去除所有列都重复了的那一行的值。
-- 如果某一行和另外一行的数据全部一样，则把这一行去掉，只留下一行不重复的数据。
select distinct * from product;

-- 5.查询结果是表达式(运算查询) : 将所有商品的价格+10元进行显示
select pname,price+10 new_price from product;
-- 其中new_price是别名，如果不起别名，这里就会显示该列列名为price+10
```

注意：

*号表示所有的列

---

### 运算符

数据库中的表结构确立后，表中的数据代表的意义就已经确定。通过MySQL运算符进行运算，就可以获取到表结构以外的另一种数据。

例如，学生表中存在一个birth字段，这个字段表示学生的出生年份。而运用MySQL的算术运算符用当前的年份减学生出生的年份，那么得到的就是这个学生的实际年龄数据。

MySQL支持4种运算符

* 算术运算符

  * ```sql
    select name,price*1.1 as new_price from product;
    ```

    将所有商品的价格上调10%，这只会在控制台输出对应的表格，不会将上调后的数据写入原来的表格product中。

  * 除以可以是/，也可以是div；取余可以是%，也可以是mod。

* 比较运算符

* 逻辑运算符

* 位运算符

#### 比较运算符

| 比较运算符       | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| =                | 等于                                                         |
| <和<=            | 小于和小于等于                                               |
| >和>=            | 大于和大于等于                                               |
| <=>              | 安全的等于，两个操作码均为NULL时，其所得值为1;而当一个操作码为NULL时，其所得值为0 |
| <>或!=           | 不等于                                                       |
| IS NULL 或ISNULL | 判断一个值是否为 NULL                                        |
| IS NOT NULL      | 判断一个值是否不为 NULL                                      |
| LEAST            | 当有两个或多个参数时，返回最小值                             |
| GREATEST         | 当有两个或多个参数时，返回最大值                             |
| BETWEEN AND      | 判断一个值是否落在两个值之间                                 |
| IN               | 判断一个值是IN列表中的任意一个值                             |
| NOT IN           | 判断一个值不是IN列表中的任意一个值                           |
| LIKE             | 通配符匹配                                                   |
| REGEXP           | 正则表达式匹配                                               |

<=>是完全等于，用于比较null值。

---

#### 逻辑运算符

| 逻辑运算符  | 说明     |
| ----------- | -------- |
| NOT 或者!   | 逻辑非   |
| AND 或者 && | 逻辑与   |
| OR 或者\|\| | 逻辑或   |
| XOR         | 逻辑异或 |

  逻辑异或:不同为真，相同为假。

---

#### 位运算符

| 位运算符 | 说明                   |
| -------- | ---------------------- |
| \|       | 按位或                 |
| &        | 按位与                 |
| ^        | 按位异或               |
| <<       | 按位左移               |
| >>       | 按位右移               |
| ~        | 按位取反，反转所有比特 |

位运算符是在二进制数上进行计算的运算符。位运算会先将操作数变成二进制数，进行位运算。

然后再将计算结果从二进制数变回十进制数。

---

#### 练习

```sql
-- 查询价格不是800的所有产品
select * from product where price != 800;
select * from product where price <> 800;
select * from product where not (price= 800);
```

```sql
-- 查询商品价格大于等于60元的所有商品信息
select * from product where price>=60;

-- 查询商品价格在200到1000之间所有商品
select * from product where price>=200 and price<=1000;
select * from product where between 200 and 1000;
select * from product where price>=200 && price<=1000;

-- 查询商品价格是200或800的所有商品
select * from product where price in(200,800);
select * from product where price =200 or price=800;
select * from product where price =200 || price=800;
```

```sql
-- 查询含有'裤'字的所有商品
select * from product where pname like '%裤%';-- %用来匹配任意字符

-- 查询以'海'开头的所有商品
select * from product where pname like '海%';-- %用来匹配任意(可以是多个)字符

-- 查询以'海'结尾的所有商品
select * from product where pname like '%海';

-- 查询第二个字为'澜'的所有商品
select * from product where pname like '_澜%';-- 下划线匹配单个字符
```

```sql
-- 查询category_id为null的商品
select * from product where category_id is null;

-- 查询category_id不为null分类的商品
select * from product where category_id is not null;

-- 使用least求最小植
select least(10,5,20) as small_number;
select least(10,null,20) as small_number;-- 如果求最小值时，有个值为null，则不会进行比较，结果直接为null;

-- 使用greatest求最大值
select greatest(10,30,20) as big_number;
select greatest(10,null,20) as big_number;-- 如果求最大值时，有个值为null，则不会进行比较，结果直接为null;
```

##### 位运算练习

```sql
select 3&5; -- 位与
3-- 0011
5-- 0101
1-- 0001
-- 有0就是0，全1才为1

select 3|5;-- 位或
3-- 0011
5-- 0101
7-- 0111
-- 有1则为1，全0则为0

select 3^5;-- 位异或
3-- 0011
5-- 0101
6-- 0110
-- 相同为0，不同为1

select m>>n;-- 将十进制数m的二进制位向右移n位，最高位补0.（正数）
select 3>>1;-- 位右移
-- 0011>>向右移1位----------->0001
select 8>>2;-- 2

select 3<<1;-- 位左移
-- 0011<<1 --->0110--->6

select -3;-- 位取反
-- 将32位二进制位全部取反，0变成1，1变成0。
```

---

### 排序查询

#### 介绍

如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 **order by** 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果

```sql
select
字段名1，字段名2，.....
from 表名
order by 字段名1 [asc|desc]，字段名2[asc|desc]...........;
-- 先对字段名1进行排序，如果字段1的值有相同的再按字段2来排序
-- 字段名1是排序的主要条件，字段名2是排序的次要条件。
```

#### 特点

* 1.asc代表升序，desc代表降序，如果**不写默认升序**
* 2.order by用于子句中可以支持单个字段，多个字段，表达式，函数，别名
* 3.order by子句，放在查询语句的最后面。LIMIT子句除外。

```sql
-- 1.使用价格排序(降序)
select * from product order by price desc ;

-- 2.在价格排序(降序) 的基础上，以分类排序(降序)
select * from product order by price desc ,category_id;

-- 3.显示商品的价格(去重复)，并排序(降序)
select distinct price from product order by price desc ;
-- 注意：去重排序只能显示一个字段，下面这种是错的。
select pname,distinct price from product order by price desc;
```

---



### 聚合查询

#### 简介

之前我们做的查询都是横向查询，它们都是根据条件一行一行的进行判断，而使用聚合函数查询是纵向查询，它是对一列的值进行计算，然后返回一个单一的值;另外聚合函数会忽略空值。

| 聚合函数 | 作用                                                         |
| -------- | ------------------------------------------------------------ |
| count()  | 统计指定列不为NULL的**记录行数**;如果是null，则不会统计进去。 |
| sum()    | 计算指定列的**数值和**，如果指定列类型不是数值类型，那么计算结果为0。如果是null，则不会统计进去。 |
| max()    | 计算指定列的**最大值**，如果指定列是字符串类型，那么使用字符串排序运算。如果是null，则不会统计进去。 |
| min()    | 计算指定列的**最小值**，如果指定列是字符串类型，那么使用字符串排序运算。如果是null，则不会统计进去。 |
| avg()    | 计算指定列的**平均值**，如果指定列类型不是数值类型，那么计算结果为0。如果是null，则不会统计进去。 |

```sql
-- 1 查询商品的总条数
select count(pid) from product;
select count(*) from product;

-- 2 查询价格大于200商品的总条数
select count(pid) from product where price>200;

-- 3 查询分类为'c001'的所有商品的总和
select * from product where category_id='c001';

-- 4 查询商品的最大价格
select max(price) from product;

-- 5 查询商品的最小价格
select max(price) ,min(price) from product;
select max(price) max_price,min(price) min_price from product;
-- select max(price) （别名1）,min(price) （别名2） from product;

-- 6 查询分类为'c002'所有商品的平均价格
select avg(price) from product where category_id='c002';
```

---

#### NULL值的处理

```sql
insert into test values('aaa',3);
insert into test values('bbb',3);
insert into test values('ccc',null);
insert into test values('ddd',6);

select count(*),count(1),count(c) from test;-- 4 4 3
-- count(*)等价于count(1)

select sum(c2),max(c2),min(c2),avg(c2) from test;-- 12 6 3 4
-- 平均值为4==12/3，忽略了null值。
-- 如果将null改为0，则平均值为3.
```

---

### 分组查询

分组查询是指使用**group by**字句对查询信息进行分组

```sql
select 字段1,字段2... from 表名 group by 分组字段 having 分组条件;
```

注意；分组之后，select的后边只能写**分组字段**和**聚合函数（统计函数）**

```sql
-- 统计各个分类商品的个数
select category_id,count(pid) from product group by category_id;
```

group by后面的字段的顺序可以随机。

`select ... from product group by 市，省，县;`

group by后面的所有字段相同才能分到同一组。

#### 分组之后的条件筛选-having

* 分组之后对统计结果进行筛选的话必须使用having，不能使用where。

* where子句用来筛选FROM子句中指定的操作所产生的行，where肯定是加在from的后面

* group by 子句用来分组WHERE子句的输出。

* having 子句用来从分组的结果中筛选行

* 格式：

  ```sql
  select 字段1,字段2... from 表名 where 条件 group by 分组字段 having 分组条件;
  ```

* 操作：

  * 统计各个分类商品的个数，且只显示个数大于4的信息

  * ```sql
    select category_id,count(pid) cnt from product group by  category_id having  cnt>4 order by cnt;
    ```

注意：

**SQL执行顺序;from -> group by -> count(pid) -> select -> having -> order by**

---

### 分页查询-limit

#### 简介

分页查询在项目开发中常见，由于数据量很大，显示屏长度有限，因此对数据需要采取分页显示方式。例如数据共有30条，每页显示5条，第一页显示1-5条，第二页显示6-10条。

#### 格式

```sql
-- 方式1->显示前n条
select 字段1，字段2... from 表名 limit n;
-- 方式2-分页显示
select 字段1，字段2... from 表名 limit m,n;
-- m:整数，表示从第几条索引开始，计算方式 (当前页-1) *每页显示条数
-- n:整数，表示查询多少条数据
```

注意：

* m是索引编号，第一条的编号是0。
* limit语句一般放在一条语句的最后。

#### 操作

```sql
-- 分页查询-limit
-- 1.查询product表的前5条记录
select * from product limit 5;

-- 2.从第4条开始显示，显示5条
select * from product limit 3,5;

-- 3.分页显示
select * from product limit 0,60;       -- 第1页-->m=(1-1)*60
select * from product limit 60,60;      -- 第2页-->m=(2-1)*60
select * from product limit 120,60;     -- 第3页-->m=(3-1)*60
-- select * from product limit (n-1),60;-- 第n页-->m=(n-1)*60
```

---

### insert into select语句

#### 简介

将一张表的数据导入到另一张表中，可以使用INSERT INTO SELECT语句。

格式

```sql
insert into Table2(field1,field2,...) select valuel.value2... from Table1;
或者:
insert into Table2 select * from Tablel;
```

注意：

* 要求目标表Table2必须存在
* 字段field1、field2可以不写，但是select后面的字段一定要和Table2表的列的数据类型匹配。

```sql
create table product2(
    pname varchar(20) not null ,-- 商品名字
    price double
);

insert into product2(pname, price) select pname,price from product;
select * from product2;
```

```sql
create table product3(
    category_id varchar(20) ,-- 商品类型
    sum int
);

insert into product3 select category_id,count(*) from product group by category_id;
select * from product3;
```

---

### 正则表达式

#### 介绍

正则表达式(regular expression)描述了一种字符串匹配的规则，正则表达式本身就是一个字符串，使用这个字符串来描述、用来定义匹配规则，匹配一系列符合某个句法规则的字符串。在开发中，正则表达式通常被用来检索、替换那些符合某个规则的文本。

MySQL通过**REGEXP**关键字支持正则表达式进行字符串匹配。

#### 格式

| 模式     | 描述                                                         |
| -------- | ------------------------------------------------------------ |
| ^        | 匹配输入字符串的开始位置。                                   |
| $        | 匹配输入字符串的结束位置。                                   |
| .        | 匹配除“\n”之外的任何单个字符。                               |
| [...]    | 字符集合。匹配所包含的任意一个字符。例如，'[abc]' 可以匹配“plain”中的'a'。 |
| [^...]   | 负值字符集合。匹配未包含的任意字符。例如，'`[abc]`' 可以匹配“plain”中的'p'。 |
| p1lp2lp3 | 匹配 p1或p2或p3。例如，'z\|food 能匹配“z"或"food”。'(z\|f)ood' 则匹配"zood"或"food"。 |
| *        | 匹配前面的子表达式零次或多次。例如，zo* 能匹配“z”以及“zoo”。* 等价于(0,}。 |
| +        | 匹配前面的子表达式一次或多次。例如，'zo+' 能匹配“zo”以及“zoo"，但不能匹配“z”。+等价于{1,}。 |
| {n}      | n是一个非负整数。匹配确定的n次。例如，'o{2}'不能匹配“Bob”中的'o'，但是能匹配'food"中的两个'o'。 |
| {n,m}    | m和n 均为非负整数，其中n<= m。最少匹配n次且最多匹配 m次。    |



---

# 6 MySQL的JDBC操作

## 6.1 JDBC概述

JDBC(Java DataBase Connectivity，java数据库连接)是一种用于执行SQL语句的Java API。

**JDBC是Java访问数据库的标准规范**，可以为不同的关系型数据库提供统一访问，**它由一组用Java语言编写的接口和类组成**。

JDBC需要连接驱动，驱动是两个设备要进行通信，满足一定通信数据格式，数据格式由设备提供商规定，设备提供商为设备提供驱动软件，通过软件可以与该设备进行通信。

---

## 6.2 JDBC核心类和接口

* DriverManager:用于注册驱动
* Connection:表示与数据库创建的连接
* Statement/PrepareStatement:操作数据库sql语句的对象
* ResultSet:]结果集或一张虚拟表

## 6.3 执行流程

* 1.在java程序中**注册驱动**
  * DriverManager .registerDriver(驱动);
* 2:建立与数据库服务器的连接
  * DriverManager.getConnection(ip,端口,数据库,用户名，密码) ;
* 3:将sql指令发送给数据库服务器执行
  * statement/Preparedstatement
  * executeUpdate()
    * 增删改
  * executeQuery ()
    * 查询
* 4:处理数据库服务器返回的结果
  * 返回行数rows
  * 返回一张表（结果集）ResultSet
* 5:释放资源
  * close();

---

## 6.4 代码实现--jdbc查找操作

把驱动文件（.jar）添加进idea的某个项目的lib文件夹中

右键该jar包，选择add as library，添加为库。

本机的IP地址就直接写localhost

如果是网络服务器则写对应的ip地址

```java
import java.sql.*;

public class MySQLConnection {
    public static void main(String[] args) throws SQLException {
        //1: 注册驱动
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //这个驱动已经过时了，已经弃用了，其实早就集成了。所以不需要执行这句话了

        //2: 获取连接
        Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql_test","root","root");

        //3: 执行sql
        Statement statement=connection.createStatement();
        ResultSet resultSet=statement.executeQuery("select * from signIn");

        //4: 处理结果集
        while (resultSet.next()){//获取每一行数据
            //获取每一列数据
            int id=resultSet.getInt(1);
            String userName=resultSet.getNString(2);
            //索引编号为1，则为第一列，而不是从0开始，从0开始是错的。
            String password=resultSet.getNString("password");
            long phoneNumber=resultSet.getLong("phoneNumber");
            String email=resultSet.getNString(5);
            System.out.println(id+"\t"+userName+"\t"+password+"\t"+phoneNumber+"\t"+email);
        }

        //5:关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
```

url的写法：

```java
getConnection(String url,String user,String password);
//url:连接路径
url="jdbc:mysql://localhost:3306/mysql_test";
url="jdbc:mysql://IP地址（域名）:端口号/数据库名称?参数键值对1&参数键值对2...";
//user:用户名
//password:密码
```

如果连接的是本机mysql服务器，并且mysql服务默认端口是3306，则url可以简写为: 

```
jdbc:mysql:///数据库名称?参数键值对
```

配置useSSL=false 参数，禁用安全连接方式，解决警告提示.

---

### 获取执行SQL的对象

* 普通执行SQL对象

  ```java
  Statement createStatement()
  ```

* 预编译SQL的执行SQL对象: 防止SQL注入

  ```java
  PreparedStatement prepareStatement(sql)
  ```

* 执行存储过程的对象

  ```java
  CallableStatement prepareCall(sql)
  ```


---

### Statement作用

1. 执行SQL语句

```java
int executeUpdate(sql);//: 执行DML、DDL语句
//返回值:(1) DML语句影响的行数(2) DDL语句执行后，执行成功也可能返回 0

ResultSet executeQuery(sql);// 执行DQL 语句
//返回值:ResultSet 结果集对象

```

---

### ResultSet(结果集对象)作用

封装了DQL查询语句的结果

```java
ResultSet statement对象.executeQuery(sql);// 执行DQL 语句，返回 ResultSet 对象
```

* 获取查询结果

* ```java
  boolean next(); //(1)将光标从当前位置向前移动一行 (2)判断当前行是否为有效行
  /*返回值:
  true: 有效行，当前行有数据
  false: 无效行，当前行没有数据
  ```

  ```java
  XXX getXxx(参数);//获取数据
  xxx:数据类型，如: 
  int getInt(参数);
  String getString(参数);
  //参数:
  int: 列的编号，从1开始
  String: 列的名称
  ```

* 使用步骤

  * 1.游标向下移动一行，并判断该行否有数据:next()

  * 2.获取数据:getXxx(参数)

    ```java
    //循环判断游标是否是最后一行末尾
    while(resultSet.next()){
        //获取数据
        resultSet.getXxx(参数)
    }
    ```

    

---

结果集优化

getColumnCount()获取表的列数

getColumnName()获取表的列名

```java
//3: 执行sql
Statement statement=connection.createStatement();
ResultSet resultSet=statement.executeQuery("select * from stu");

//获取表的列数
ResultSetMetaData metaData= resultSet.getMetaData();
int columnCount= metaData.getColumnCount();

//4: 处理结果集
while (resultSet.next()){//获取每一行数据
    //获取每一列数据
    for (int i=1;i<columnCount;i++){
        System.out.print(resultSet.getObject(i)+"\t");
    }
    System.out.println();
}
```

---

## 6.5 jdbc增删改操作

```java
//3: 执行sql
        Statement statement=connection.createStatement();
        //3.1：增加数据
        row表示收到影响的行数
        int rows1= statement.executeUpdate("insert into signIn values (null,'tom','888',122,'123asd')");

        //3.2：删除数据
        int rows2= statement.executeUpdate("delete from signIn where userName='tom'");
        System.out.println(rows2);

        //3.3：修改数据
        int rows3= statement.executeUpdate("update signIn set phoneNumber =555 where id=3");

        //4:关闭连接
        statement.close();
        connection.close();
```

---

## 6.6 错误代码

```
ERROR 2003 (HY000): Can't connect to MySQL server on 'localhost:3306' (10061)
```

可能是MySQL的服务没有启动，用完jdbc之后，MySQL的服务可能会停止了。所以需要手动开启。

---

## 6.7 事务管理

* MySQL事务管理
  * 开启事务: BEGIN;/START TRANSACTION;
  * 提交事务: COMMIT;
  * 回滚事务: ROLLBACK;
  * MySQL默认自动提交事务
* JDBC事务管理: Connection接口中定义了3个对应的方法
  * 开启事务: setAutoCommit(boolean autoCommit): true为自动提交事务；
  * false为手动提交事务，即为开启事务
  * 提交事务: commit()
  * 回滚事务: rollback()



[^abc]: 
