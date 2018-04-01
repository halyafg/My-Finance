
USE `myfinance`;

--
-- Dumping data for table `user`
--
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'dudevich@gmail.com','Petro','$2a$10$V8XVAghktXLO/jhOw9kUcOAtKx7e4Ft3it1Al8ufBMW.A5WmzGbXy','Dudevich');
UNLOCK TABLES;

--
-- Dumping data for table `currency`
--
LOCK TABLES `currency` WRITE;
INSERT INTO `currency` VALUES (1,'grn',1),(2,'usd',27.6);
UNLOCK TABLES;

--
-- Dumping data for table `category`
--
LOCK TABLES `category` WRITE;
INSERT INTO `category` VALUES (1,'Sport','OUTCOME'),(2,'Food','OUTCOME'),(3,'Car','OUTCOME'),(4,'Clothes','OUTCOME'),(5,'Entertainment','OUTCOME'),(6,'Education','OUTCOME'),(7,'Different','OUTCOME'),(8,'Salary','INCOME'),(9,'Deposits','INCOME'),(10,'other income','INCOME');
UNLOCK TABLES;

--
-- Dumping data for table `transaction`
--
LOCK TABLES `transaction` WRITE;
INSERT INTO `transaction` VALUES (1,5650,'first salary','2017-02-23',8,1,1),(2,5650,'','2017-03-23',8,1,1),(3,5700,'','2017-04-23',8,1,1),(4,5700,'','2017-05-23',8,1,1),(5,6000,'','2017-06-25',8,1,1),(6,6000,'','2017-07-23',8,1,1),(7,6000,'','2017-08-27',8,1,1),(8,7000,'','2017-09-24',8,1,1),(9,7000,'','2017-10-22',8,1,1),(10,7000,'','2017-11-19',8,1,1),(11,7000,'','2017-12-21',8,1,1),(12,5000,'premium','2017-12-17',8,1,1),(13,2000,'IdeaBank','2017-03-07',9,2,1),(14,800,'CredoBank','2017-06-25',9,2,1),(15,4000,'subscription SportLife','2017-01-03',1,1,1),(16,500,'pool','2017-05-02',1,1,1),(17,500,'pool','2017-05-04',1,1,1),(18,500,'pool','2017-09-03',1,1,1),(19,500,'pool','2017-10-02',1,1,1),(20,2550,'boots','2017-10-26',4,1,1),(21,1430,'white shirt','2017-06-11',4,1,1),(22,400,'','2017-01-06',3,1,1),(23,500,'','2017-01-13',3,1,1),(24,500,'','2017-01-27',3,1,1),(25,4500,'repair','2017-07-18',3,1,1),(26,1700,'fuel','2017-02-27',3,1,1),(27,1400,'','2017-04-23',3,1,1),(28,2500,'','2017-05-07',3,1,1),(29,5800,'english cources','2017-08-30',6,1,1),(31,8000,'skiing','2017-12-21',7,1,1),(32,1500,'','2017-01-05',2,1,1),(33,200,'','2017-02-21',2,1,1),(34,2500,'birthday','2017-11-14',2,1,1),(35,175,'','2017-02-01',2,1,1),(36,189,'','2017-02-16',2,1,1),(37,110,'','2017-02-22',2,1,1);
UNLOCK TABLES;