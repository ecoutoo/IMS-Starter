INSERT INTO `customers`(`first_name`, `surname`) VALUES ('Adil', 'Benriltom');
INSERT INTO `items`(`item_name`, `item_price`) VALUES('cola', 1.50);
INSERT INTO `orders`(`cust_id`) VALUES(1);
INSERT INTO `orderlines`(`ord_id`, `item_id`, `quantity`) VALUES(1, 1, 2);