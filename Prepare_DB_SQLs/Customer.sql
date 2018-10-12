CREATE TABLE `Customer` (
	`ID` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(20),
    `dateOfBirth` DATE,
    `phone` VARCHAR(20),
    `email` VARCHAR(20),
    `gender` BOOLEAN,
    `addressLine` VARCHAR(50),
    `title` VARCHAR(20),
    PRIMARY KEY (`ID`)
);