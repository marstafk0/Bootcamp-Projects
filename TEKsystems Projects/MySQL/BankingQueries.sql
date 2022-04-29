USE banking;

-- 1. For each product, show the product name "Product" and the product type name "Type".
SELECT NAME AS 'Name', product_type_cd AS 'Type' FROM product;

-- 2. For each branch, list the branch name and city, plus the last name and title of each employee who works in that branch.
SELECT b.name, b.city, e.last_name, e.title FROM employee e JOIN branch b ON e.ASSIGNED_BRANCH_ID = b.BRANCH_ID;

-- 3. Show a list of each unique employee title.
SELECT DISTINCT title FROM employee;

-- 4. Show the last name and title of each employee, along with the last name and title of that employee's boss.
SELECT e.last_name, e.title, s.last_name, s.title FROM employee e JOIN employee s ON e.SUPERIOR_EMP_ID = s.EMP_ID;

-- 5. For each account, show the name of the account's product, the available balance, and the customer's last name.
SELECT p.name, a.avail_balance, i.last_name FROM account a JOIN product p ON p.PRODUCT_CD = a.PRODUCT_CD 
JOIN individual i ON i.CUST_ID = a.CUST_ID;

-- 6. List all account transaction details for individual customers whose last name starts with 'T'.
SELECT a.* FROM acc_transaction a JOIN account ac ON ac.ACCOUNT_ID = a.ACCOUNT_ID JOIN individual i ON i.CUST_ID = ac.CUST_ID WHERE i.LAST_NAME REGEXP '^[T]';