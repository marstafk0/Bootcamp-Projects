use testStore;

-- 1. Create a query to return all orders made by users with the first name of “Marion”
SELECT o.* 
FROM ORDERS o 
JOIN USERS u ON u.USER_ID = o.USER_ID
WHERE u.FIRST_NAME = 'Marion';

-- 2. Create a query to select all users that have not made an order
SELECT u.* 
FROM users u 
LEFT JOIN orders o ON u.user_id = o.user_id 
WHERE o.user_id IS NULL;

-- 3. Create a Query to select the names and prices of all items that have been part of 2 or 
-- more separate orders. 
SELECT DISTINCT i.name, i.price 
FROM items i 
JOIN order_items oi ON i.item_id = oi.item_id
JOIN orders o ON o.order_id = oi.order_id;

-- 4. Create a query to return the Order Id, Item name, Item Price, and Quantity from orders 
-- made at stores in the city “New York”. Order by Order Id in ascending order. 
SELECT oi.order_id, i.name, i.price, oi.quantity 
FROM order_items oi 
JOIN items i ON oi.item_id = i.item_id
JOIN orders o ON o.order_id = oi.order_id 
JOIN stores s ON s.store_id = o.store_id 
WHERE s.city = 'New York' 
ORDER BY o.order_id;

-- 5. Your boss would like you to create a query that calculates the total revenue generated 
-- by each item. Revenue for an item can be found as (Item Price * Total Quantity 
-- Ordered). Please return the first column as ‘ITEM_NAME’ and the second column as 
-- ‘REVENUE’. 
SELECT i.name AS 'ITEM_NAME', SUM(i.price * oi.quantity) AS REVENUE
FROM items i 
JOIN order_items oi ON i.item_id = oi.item_id
GROUP BY i.name
ORDER BY REVENUE DESC;

/* 6. Create a query with the following output: 
	a. Column 1 - Store Name 
		i. The name of each store 
	b. Column 2 - Order Quantity 
		i. The number of times an order has been made in this store 
	c. Column 3 - Sales Figure 
		i. If the store has been involved in more than 3 orders, mark as ‘High’ 
		ii. If the store has been involved in less than 3 orders but more than 1 order, 
			mark as ‘Medium’ 
		iii. If the store has been involved with 1 or less orders, mark as ‘Low’ 
	d. Should be ordered by the Order Quantity in Descending Order */
    
SELECT s.name AS 'NAME', count(o.order_id) AS 'ORDER_QUANTITY', (CASE 
	WHEN count(o.order_id) <= 1 THEN 'Low'
    WHEN count(o.order_id) > 1 AND count(o.order_id) < 4 THEN 'Medium'
    ELSE 'High'
    END) AS 'SALES_FIGURE'
FROM stores s 
JOIN orders o ON s.store_id = o.store_id
GROUP BY s.name
ORDER BY 2 DESC;


