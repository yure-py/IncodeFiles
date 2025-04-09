use northwind;

select * from shippers;

select address, city, state_province, country_region from shippers;

desc employees;

-- exercise 4
select * from employees;

SELECT first_name, last_name, job_title jt, company, country_region
FROM employees
WHERE job_title='Sales Representative' AND country_region = 'USA';

-- exercise 5
desc orders;

select * from orders 
where employee_id=9;

-- exercise 6 
SELECT id, first_name, last_name, job_title 
FROM suppliers
WHERE NOT job_title = "Marketing Manager";

-- exercise 7
SELECT DISTINCT id, product_name FROM products
WHERE product_name LIKE "%Chai%";

-- exercise 8
SELECT id, customer_id, ship_city FROM orders
WHERE ship_city in ("Las Vegas", "new york");

-- exercise 10
SELECT transaction_type, transaction_created_date as td, product_id 
FROM inventory_transactions
ORDER BY td;

-- exercise 11 Showing only the Date with a DateTime field