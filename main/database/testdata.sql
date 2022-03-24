
INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)
	VALUES ('Leibniz Hans', '+066233989', 'hans@gmail.com', '1987-10-10');
INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)
	VALUES ('Leopold Domay', '+0341346253', 'domay.leo@gmail.com', '1887-11-10');
INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)
	VALUES ('Ochoa Trem', '+3208245332', 'ochoa@ymail.com', '1999-10-20');
INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)
	VALUES ('Nelly Santa', '+04122234555', 'santa@december.com', '1908-3-30');
INSERT INTO CUSTOMERS (NAME, PHONE, EMAIL, BIRTHDAY)
	VALUES ('Reily Trem', '+04536332439', 'reily.trem@ymail.com', '1999-7-10');

INSERT INTO PRODUCTS (NAME, WEIGHT, PRICE)
	VALUES ('Hanlogen lights', 400, 12);
INSERT INTO PRODUCTS (NAME, WEIGHT, PRICE)
	VALUES ('Roasted beans', 600, 11);
INSERT INTO PRODUCTS (NAME, WEIGHT, PRICE)
	VALUES ('Cat food', 1000, 5);
INSERT INTO PRODUCTS (NAME, WEIGHT, PRICE)
	VALUES ('Dog food', 1500, 24);
INSERT INTO PRODUCTS (NAME, WEIGHT, PRICE)
	VALUES ('Logitech Keyboard', 700, 23);
INSERT INTO PRODUCTS (NAME, WEIGHT, PRICE)
	VALUES ('Logitech Mouse', 500, 24);
	
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR982774', '1999-7-10 10:20:30', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'reily.trem@ymail.com'));
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR98W774', '2010-7-10 10:21:30', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'reily.trem@ymail.com'));
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR18W772', '2010-10-10 10:21:30', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'hans@gmail.com'));
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR982771', '1999-7-10 10:20:30', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'hans@gmail.com'));
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR98W770', '2010-7-10 08:21:30', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'hans@gmail.com'));
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR98M772', '1999-7-12 12:20:30', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'hans@gmail.com'));
INSERT INTO ORDERS (NUMBER, PLACED, CUSTOMER_ID)
	VALUES ('FNR98R332', '2010-7-13 08:08:22', (SELECT ID FROM CUSTOMERS WHERE EMAIL = 'hans@gmail.com'));

INSERT INTO ORDERS_PRODUCTS (ORDER_ID, PRODUCT_ID, QUANTITY)
SELECT (SELECT ID FROM ORDERS WHERE NUMBER = ORD_NAME),
	   (SELECT ID FROM PRODUCTS WHERE NAME = PRD_NAME),
       QUANT
FROM (values
      	('FNR982774', 'Hanlogen lights',    2),
      	('FNR982774', 'Roasted beans',      4),
      	('FNR98W774', 'Cat food',           1),
      	('FNR18W772', 'Roasted beans',      1),
      	('FNR18W772', 'Hanlogen lights',    2),
      	('FNR982771', 'Roasted beans',      2),
      	('FNR98W770', 'Cat food',           4),
      	('FNR98W770', 'Dog food',           1),
      	('FNR98W770', 'Logitech Keyboard',  1),
      	('FNR98M772', 'Hanlogen lights',    1),
      	('FNR98R332', 'Cat food',           3),
      	('FNR98R332', 'Logitech Mouse',     6)
      ) AS T(ORD_NAME, PRD_NAME, QUANT);


ALTER TABLE orders_products DROP CONSTRAINT IF EXISTS orders_products_order_id_fkey;
ALTER TABLE public.orders_products ADD CONSTRAINT orders_products_order_id_fkey FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE;
ALTER TABLE orders ADD COLUMN IF NOT EXISTS status_code SMALLINT NOT NULL DEFAULT 0;

UPDATE products SET image = '/images/products/cat-food.png' WHERE name ~* 'cat food';
UPDATE products SET image = '/images/products/dog-food.png' WHERE name ~* 'dog food';
UPDATE products SET image = '/images/products/roasted-beans.png' WHERE name ~* 'roasted';
UPDATE products SET image = '/images/products/keyboard.png' WHERE name ~* 'keyboard';
UPDATE products SET image = '/images/products/mouse.png' WHERE name ~* 'mouse';
UPDATE products SET image = '/images/products/light-bulb.png' WHERE name ~* 'light';