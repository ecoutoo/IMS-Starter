drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;

USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers` (
    `cust_id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`cust_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`items` (
    `item_id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(40) DEFAULT NULL,
    `item_price` DOUBLE DEFAULT NULL,
    PRIMARY KEY (`item_id`)
);

CREATE TABLE IF NOT EXISTS `ims`.`orders` (
    `ord_id` INT(11) NOT NULL AUTO_INCREMENT,
    `cust_id` INT(11) NOT NULL,
    PRIMARY KEY (`ord_id`),
    FOREIGN KEY (`cust_id`) REFERENCES customers(`cust_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `ims`.`orderlines` (
    `line_id` INT(11) NOT NULL AUTO_INCREMENT,
    `ord_id` INT(11) NOT NULL,
    `item_id` INT(11) NOT NULL,
    `quantity` INT(11) NOT NULL,
    PRIMARY KEY (`line_id`),
    FOREIGN KEY (`ord_id`) REFERENCES orders(`ord_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`item_id`) REFERENCES items(`item_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
