CREATE DATABASE  IF NOT EXISTS `myfinance`;

USE `myfinance`;

--
-- Table structure for table `user`
--
CREATE TABLE `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `secondName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `UK_e6gkqunxajvyxl5uctpl2vl2p` (`email`),
  UNIQUE KEY `UK_cyvpn91xe2a9kbvfr001klaom` (`password`)
);


--
-- Table structure for table `currency`
--
CREATE TABLE `currency` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `rate` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ocsst6ixgkhiji9xt1c1sijgu` (`name`)
);

--
-- Table structure for table `category`
--
CREATE TABLE `category` (
  `idCategory` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idCategory`),
  UNIQUE KEY `UK_foei036ov74bv692o5lh5oi66` (`name`)
);

--
-- Table structure for table `transaction`
--
CREATE TABLE `transaction` (
  `idTransaction` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `category_idCategory` int(11) DEFAULT NULL,
  `currency_id` int(11) DEFAULT NULL,
  `user_idUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTransaction`),
  KEY `FK_d988k41wv2n3np8nxa2g7bgpg` (`category_idCategory`),
  KEY `FK_iwnet6w3gf6annnhu9lhldnta` (`currency_id`),
  KEY `FK_63hodhjdxul9v70t6kuh0qljs` (`user_idUser`),
  CONSTRAINT `FK_63hodhjdxul9v70t6kuh0qljs` FOREIGN KEY (`user_idUser`) REFERENCES `user` (`idUser`),
  CONSTRAINT `FK_d988k41wv2n3np8nxa2g7bgpg` FOREIGN KEY (`category_idCategory`) REFERENCES `category` (`idCategory`),
  CONSTRAINT `FK_iwnet6w3gf6annnhu9lhldnta` FOREIGN KEY (`currency_id`) REFERENCES `currency` (`id`)
);
