USE classicmodels;

-- 1. Write a query to display each customer’s name (as “Customer Name”) alongside the name of the employee who is responsible for that customer’s orders. 
-- The employee name should be in a single “Sales Rep” column formatted as “lastName, firstName”. The output should be sorted alphabetically by customer name.

SELECT c.customerName AS 'Customer Name', CONCAT(e.lastName, ' ', e.firstName) AS 'Sales Rep' -- concat allows for sticking multiple expressions together
FROM customers c 
JOIN employees e 
ON c.salesRepEmployeeNumber = e.employeeNumber
ORDER BY c.customerName; 

-- 2. Determine which products are most popular with our customers. For each product, list the total quantity ordered along with the total sale generated 
-- (total quantity ordered * priceEach) for that product. The column headers should be “Product Name”, “Total # Ordered” and “Total Sale”. List the products by Total Sale descending.

SELECT p.productName AS 'Product Name', SUM(o.quantityOrdered) AS 'Total # Ordered', SUM(o.quantityOrdered * o.priceEach) AS 'Total'
FROM products p
LEFT JOIN orderdetails o
ON p.productCode = o.productCode
GROUP BY 1
ORDER BY 3 DESC;  -- reffering to column index number 

-- 3. Write a query which lists order status and the # of orders with that status.
-- Column headers should be “Order Status” and “# Orders”. Sort alphabetically 
-- by status.

SELECT o.status AS 'Order Status', od.quantityOrdered AS '# Orders'
FROM orders o
LEFT JOIN orderdetails od 
ON o.orderNumber = od.orderNumber
GROUP BY status
ORDER BY o.status; 

-- 4. Write a query to list, for each product line, the total # of products 
-- sold from that product line. The first column should be “Product Line” 
-- and the second should be “# Sold”. Order by the second column descending.

SELECT productLine AS 'Product Line', SUM(quantityOrdered) AS '# Sold'
FROM (SELECT p.productCode, p.productName, od.quantityOrdered, p.productLine 
		FROM products p 
		RIGHT JOIN orderdetails od
		ON p.productCode = od.productCode) 
AS x
GROUP BY productLine
ORDER BY 2 DESC;

-- 5. For each employee who represents customers, output the total # of orders that employee’s customers have placed alongside the total sale amount of 
-- those orders. The employee name should be output as a single column named “Sales Rep” formatted as “lastName, firstName”. The second column should
-- be titled “# Orders” and the third should be “Total Sales”.Sort the output by Total Sales descending. Only (and all) employees with the job
-- title ‘Sales Rep’ should be included in the output, and if the employee made no sales the Total Sales should display as “0.00”.

SELECT salesRep AS 'Sales Rep', COUNT(quantityOrdered) AS '# Orders', SUM(totalSales) AS 'Total Sales'
FROM (SELECT e.employeeNumber, CONCAT(e.lastName, ' ', e.firstName) AS salesRep, od.quantityOrdered, od.priceEach,
		CASE 
			WHEN od.quantityOrdered IS NULL THEN 0.00
			WHEN od.quantityOrdered = 0 THEN 0.00
			WHEN od.quantityOrdered > 0 THEN od.quantityOrdered * od.priceEach
		END AS totalSales, e.jobTitle
      FROM customers c
      RIGHT JOIN (SELECT employeeNumber, lastName, firstName, jobTitle
      				FROM employees 
						WHERE jobTitle = 'Sales Rep') AS e 
		ON e.employeeNumber = c.salesRepEmployeeNumber
		LEFT JOIN orders o 
		ON o.customerNumber = c.customerNumber
		LEFT JOIN orderdetails od 
		ON o.orderNumber = od.orderNumber) AS x
GROUP BY employeeNumber 
ORDER BY SUM(totalSales) DESC;


-- 6. Your product team is requesting data to help them create a bar-chart of monthly sales since the company’s inception. Write a query to output the month 
-- (January, February, etc.), 4-digit year, and total sales for that month. The first column should be labeled ‘Month’, the second ‘Year’, and the 
-- third should be ‘Payments Received’. Values in the third column should be formatted as numbers with two decimals – for example: 694,292.68.

SELECT MONTHNAME(paymentDate) AS 'Month', YEAR(paymentDate) AS 'Year', FORMAT(SUM(amount), 2) AS 'Payments Recieved'
FROM payments
GROUP BY 2 ,1
ORDER BY paymentDate;