SELECT * FROM customers;

SELECT * FROM customers
where company = "company a";

SELECT count(*) as total_customers
FROM customers;

select job_title, count(*) as jobs_by_category 
FROM employees
group by job_title
having count(*) > 2
;


