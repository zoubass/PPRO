create table IF NOT EXISTS users(
      id int IDENTITY(1,1) primary key,
      username nvarchar(50) not null,
      password nvarchar(50) not null,
      enabled bit not null);
	  create unique index ix_username on users(username);

  create table IF NOT EXISTS authorities (
	    username nvarchar(50) NOT NULL,
	    authority nvarchar(50) NOT NULL,
	    ID int IDENTITY(1,1) NOT NULL,
      constraint fk_authorities_users foreign key(username) references users(username) on delete cascade);
      create unique index ix_auth_username on authorities(username,authority);

CREATE TABLE if not exists Product(
	id int IDENTITY(1,1) primary key,
	type nvarchar(500) not NULL,
	price numeric(8, 5) not NULL,
	category nvarchar(500) not NULL,
	imageFile nvarchar(120) not NULL,
	idOrder int NULL);

CREATE TABLE if not exists order_info(
	id int IDENTITY(1,1) NOT NULL,
	totalPrice numeric(8, 5) NOT NULL,
	totalProductCount numeric(8, 5) NOT NULL,
	orderType nvarchar(500) NOT NULL,
	userEmail nvarchar(500) NOT NULL,
	deliveryType nvarchar(500) NOT NULL,
	street nvarchar(200) NULL,
	city nvarchar(200) NULL,
	psc int NOT NULL);

CREATE TABLE if not exists feedback(
	id int IDENTITY(1,1) NOT NULL,
	authorEmail nvarchar(500) NOT NULL,
	rating numeric(5) not null,
	comment nvarchar(1500) NOT NULL
	);