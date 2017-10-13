INSERT INTO users(username,password,enabled)VALUES('admin','admin',1);
INSERT INTO authorities(username, authority)VALUES('admin','ROLE_ADMIN');

insert into product(type,price,category,image) values ('Burger',90,'Burgery','img/product/burger.jpg');