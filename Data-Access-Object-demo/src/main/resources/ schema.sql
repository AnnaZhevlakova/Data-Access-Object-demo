create table IF NOT EXISTS netology.CUSTOMERS (
id bigserial primary  key,
name varchar(255) not null,
surname varchar(255) not null,
age int,
phone_number varchar(255)
);

create table IF NOT EXISTS netology.orders(
id bigserial primary key,
date DATE not null,
customer_id int not null,
product_name varchar(255) not null,
amount DECIMAL(10),
FOREIGN KEY (customer_id) REFERENCES netology.customers (id)
);


MERGE INTO netology.customers AS target
USING (VALUES
('alexey', 'ivanov', 28, '+79161234567'),
('Maria', 'Petrova', 32, '+79161234568'),
('ALEXEY', 'sidorov', 25, '+79161234569'),
('Dmitry', 'Kozlov', 40, '+79161234570'),
('Anna', 'Smirnova', 29, '+79161234571'),
('alexey', 'popov', 35, '+79161234572'),
('Elena', 'Volkova', 27, '+79161234573')
) AS source(name, surname, age, phone_number)
ON target.phone_number = source.phone_number
WHEN NOT MATCHED THEN
  INSERT (name, surname, age, phone_number)
  VALUES (source.name, source.surname, source.age,source.phone_number);


MERGE INTO netology.orders AS target
USING (VALUES
('2024-01-15'::date, 1, 'Laptop', 50000.00),
('2024-01-16'::date, 2, 'Smartphone', 30000.00),
('2024-01-17'::date, 3, 'Tablet', 20000.00),
('2024-01-18'::date, 1, 'Headphones', 5000.00),
('2024-01-19'::date, 3, 'Monitor', 15000.00),
('2024-01-20'::date, 4, 'Keyboard', 3000.00),
('2024-01-21'::date, 2, 'iPhone', 130000.00)
) AS source("date", customer_id, product_name, amount)
ON target."date" = source."date"
and target.customer_id = source.customer_id
and target.product_name = source.product_name
and target.amount = source.amount
WHEN NOT MATCHED THEN
  INSERT ("date", customer_id, product_name, amount)
  VALUES (source."date", source.customer_id, source.product_name, source.amount);

