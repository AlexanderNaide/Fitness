-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fitness
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `title` varchar(45) DEFAULT NULL,
                            `start` timestamp NULL DEFAULT NULL,
                            `end` timestamp NULL DEFAULT NULL,
                            `duration` int DEFAULT NULL,
                            `replay` int DEFAULT NULL,
                            `discipline_id` bigint DEFAULT NULL,
                            `trainer_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `fk_cal_spec_idx` (`discipline_id`),
                            KEY `fk_cal_trainer_idx` (`trainer_id`),
                            CONSTRAINT `fk_cal_spec` FOREIGN KEY (`discipline_id`) REFERENCES `specialization` (`id`),
                            CONSTRAINT `fk_cal_trainer` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (1,'Yoga','2023-07-23 13:00:00','2025-07-23 13:00:00',60,7,1,2),(2,'Yoga','2023-07-25 15:00:00','2025-07-23 15:00:00',60,3,1,3),(3,'Water','2023-07-22 15:00:00','2025-07-22 15:00:00',60,1,2,2),(4,'Water One','2023-07-22 15:00:00','2023-07-23 15:00:00',60,NULL,2,3);
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `club_service`
--

DROP TABLE IF EXISTS `club_service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `club_service` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `description` varchar(1200) DEFAULT NULL,
                                `ico` varchar(255) NOT NULL,
                                `title` varchar(255) NOT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `club_service`
--

LOCK TABLES `club_service` WRITE;
/*!40000 ALTER TABLE `club_service` DISABLE KEYS */;
INSERT INTO `club_service` VALUES (1,'В тренажерных залах нашего клуба установлено профессиональное оборудование ведущих мировых производителей.','assets/images/services/gum.png','ТРЕНАЖЕРНЫЙ ЗАЛ, КАРДИОТЕАТРЫ'),(2,'Просторная аквазона нашего фитнес клуба включает три бассейна: тренировочный, детский, развлекательный бассейн с гидромассажными линиями и джакузи, а также банный комплекс, суши-бар, зоны отдыха и массажный кабинет.','assets/images/services/pool.png','ТРИ БАССЕЙНА'),(3,'Групповые программы в фитнес клубе «Три Океана» - это прекрасная физическая форма и отличная компания! Здесь никогда не бывает скучно!','assets/images/services/group.png','ГРУППОВЫЕ ПРОГРАММЫ'),(4,'Персональный тренинг используется в любом направлении фитнеса: плавание и аквааэробика, тренажерный зал, аэробика, Mind Body, единоборства, танцевальные направления.','assets/images/services/personal.png','ПЕРСОНАЛЬНЫЙ ТРЕНИНГ');
/*!40000 ALTER TABLE `club_service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `spec` bit(1) DEFAULT NULL,
                         `title` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,_binary '\0','super'),(2,_binary '\0','admin'),(3,_binary '','trainer'),(4,_binary '\0','user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `season_ticket`
--

DROP TABLE IF EXISTS `season_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `season_ticket` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `beginning` time(6) DEFAULT NULL,
                                 `completion` time(6) DEFAULT NULL,
                                 `description` varchar(255) NOT NULL,
                                 `price` decimal(38,2) DEFAULT NULL,
                                 `title` varchar(255) NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `season_ticket`
--

LOCK TABLES `season_ticket` WRITE;
/*!40000 ALTER TABLE `season_ticket` DISABLE KEYS */;
INSERT INTO `season_ticket` VALUES (1,'16:20:14.000000','16:20:14.000000','Карта 18+. Посещение клуба не ограничено в часы работы клуба. Есть возможность заморозки на 60 дней.',20000.00,'Полная'),(2,'18:16:54.000000','18:16:54.000000','Карта 18+. Посещение клуба не ограничено в часы работы клуба. Без возможности заморозки.',18000.00,'Индивидуальная'),(3,'19:06:54.000000','19:06:54.000000','Карта 18+. Посещение клуба с 6:00 до 17:00. Есть возможность заморозки на 60 дней.',16000.00,'Дневная'),(4,'19:56:54.000000','19:56:54.000000','Карта 18+. Посещение клуба не ограничено в часы работы клуба. Есть возможность заморозки на 60 дней. Оформляется при предъявлении студенческого билета.',16000.00,'Студенческая'),(5,'21:03:34.000000','21:03:34.000000','Карта полного дня. Возраст от 3 до 13 лет. Посещение не ограничено в часы работы клуба. Возможность заморозки на 60 дней.',12000.00,'Детская');
/*!40000 ALTER TABLE `season_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialization` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `specialization` varchar(255) NOT NULL,
                                  `club_service_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKea0jskr7od6b9j2065g1qud5q` (`club_service_id`),
                                  CONSTRAINT `FKea0jskr7od6b9j2065g1qud5q` FOREIGN KEY (`club_service_id`) REFERENCES `club_service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (1,'functional',1),(2,'water',2),(3,'group',1),(4,'kids',NULL),(5,'massage',NULL);
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `created_at` datetime(6) DEFAULT NULL,
                          `updated_at` datetime(6) DEFAULT NULL,
                          `client_id` bigint DEFAULT NULL,
                          `season_ticket_id` bigint DEFAULT NULL,
                          `user_id` bigint DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FKaofe5bfcbkmfh71x5gw8jtdcg` (`client_id`),
                          KEY `FKjvseo4ufyi29y77i9ihy382yi` (`season_ticket_id`),
                          KEY `FKmvugyjf7b45u0juyue7k3pct0` (`user_id`),
                          CONSTRAINT `FKaofe5bfcbkmfh71x5gw8jtdcg` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`),
                          CONSTRAINT `FKjvseo4ufyi29y77i9ihy382yi` FOREIGN KEY (`season_ticket_id`) REFERENCES `season_ticket` (`id`),
                          CONSTRAINT `FKmvugyjf7b45u0juyue7k3pct0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_classes`
--

DROP TABLE IF EXISTS `user_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_classes` (
                                `class_id` bigint NOT NULL,
                                `user_id` bigint NOT NULL,
                                KEY `FKqen46xej8c5lyl9hwmxnoq4s0` (`user_id`),
                                KEY `FKiplo48clygrd5695c187bjt8t` (`class_id`),
                                CONSTRAINT `FKiplo48clygrd5695c187bjt8t` FOREIGN KEY (`class_id`) REFERENCES `workouts` (`id`),
                                CONSTRAINT `FKqen46xej8c5lyl9hwmxnoq4s0` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_classes`
--

LOCK TABLES `user_classes` WRITE;
/*!40000 ALTER TABLE `user_classes` DISABLE KEYS */;
INSERT INTO `user_classes` VALUES (1,5),(2,5),(3,5),(4,5),(5,5),(6,5),(7,5),(8,5);
/*!40000 ALTER TABLE `user_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `avatar` varchar(255) DEFAULT NULL,
                         `background` varchar(255) DEFAULT NULL,
                         `birthday` date DEFAULT NULL,
                         `created_at` datetime(6) DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `gender` tinyint DEFAULT NULL,
                         `login` varchar(255) NOT NULL,
                         `middle_name` varchar(255) DEFAULT NULL,
                         `name` varchar(255) DEFAULT NULL,
                         `password` varchar(255) NOT NULL,
                         `phone` varchar(255) DEFAULT NULL,
                         `surname` varchar(255) DEFAULT NULL,
                         `updated_at` datetime(6) DEFAULT NULL,
                         `role_id` bigint DEFAULT NULL,
                         `specialization_id` bigint DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`),
                         KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
                         KEY `FKp1jxd1bnxe3rpnvnkodwbsm2o` (`specialization_id`),
                         CONSTRAINT `FKp1jxd1bnxe3rpnvnkodwbsm2o` FOREIGN KEY (`specialization_id`) REFERENCES `specialization` (`id`),
                         CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
                         CONSTRAINT `users_chk_1` CHECK ((`gender` between 0 and 1))
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'../images/gallery_5.jpg','../images/contact.jpg',NULL,'2023-07-29 21:10:10.781007',NULL,NULL,'super',NULL,'Super','$2a$10$TJeH42vxdeNqiOGiY4Mi6eRQHSB.55ilgPCF8quO9AJH7WmF3mroy',NULL,'Admin','2023-07-29 21:10:10.781007',1,NULL),(2,NULL,NULL,NULL,'2023-07-29 21:10:10.867009',NULL,NULL,'tr1',NULL,'Trainer1','$2a$10$9cel2qcy6SXBZtnPwudtuOvHMa96z8tXRD7Uf4r3o45POQENuoeEy',NULL,'***','2023-07-29 21:10:10.867009',3,1),(3,NULL,NULL,NULL,'2023-07-29 21:10:10.950008',NULL,NULL,'tr2',NULL,'Trainer2','$2a$10$jFH99hT2CVMQAX4amCcvNOIvRSU2izGWkFA64cLYSmd27vRqzDvDu',NULL,'***','2023-07-29 21:10:10.950008',3,2),(4,NULL,NULL,NULL,'2023-07-29 21:10:11.031022',NULL,NULL,'admin',NULL,'Admin','$2a$10$BKHS7Tlem8fKlTkZZW09GeSs4U.1BiLRtEOVB1fRYSLdrh5O1Q2eq',NULL,'***','2023-07-29 21:10:11.031022',2,NULL),(5,NULL,NULL,NULL,'2023-07-29 21:10:11.109023','use0@gmail.com',0,'user0',NULL,'User0_Name','$2a$10$BfYGTHkEduqBc.GXmPq7TOMaESWW8ZDH8oSE2WvRrYJWf4smAImQu','8-927-683-2723','User0_Surname','2023-07-29 21:10:11.109023',4,NULL),(6,NULL,NULL,NULL,'2023-07-29 21:10:11.187009','use1@gmail.com',1,'user1',NULL,'User1_Name','$2a$10$gT3B/ua3kqUfN02yKY9Up.c/sD9Ul7ynuj3PuX/Ag7X3UCZZQLWuq','8-927-683-2724','User1_Surname','2023-07-29 21:10:11.187009',4,NULL),(7,NULL,NULL,NULL,'2023-07-29 21:10:11.265007','use2@gmail.com',0,'user2',NULL,'User2_Name','$2a$10$sxP3wGKdmU7ce/ieIFpYlONLAfcEixcRcexvN2.GrYfjuGfTx7Cu2','8-927-683-2725','User2_Surname','2023-07-29 21:10:11.265007',4,NULL),(8,NULL,NULL,NULL,'2023-07-29 21:10:11.346152','use3@gmail.com',1,'user3',NULL,'User3_Name','$2a$10$.rVlAI2xHriah0FVWxTzf.TEDtpvS5wIUkE20dc8VXN1G5nt3oMTC','8-927-683-2726','User3_Surname','2023-07-29 21:10:11.346152',4,NULL),(9,NULL,NULL,NULL,'2023-07-29 21:10:11.425152','use4@gmail.com',0,'user4',NULL,'User4_Name','$2a$10$gNfYhO/MPEnFsIShGU0y1u6xpeWaY7r1Ga98fljc0lhBUsrMIN1Su','8-927-683-2727','User4_Surname','2023-07-29 21:10:11.425152',4,NULL),(10,NULL,NULL,NULL,'2023-07-29 21:10:11.507005','use5@gmail.com',1,'user5',NULL,'User5_Name','$2a$10$X1072NgFxweglSuDuTISxOvQXOZmnrJ1dBrrO67lUswn.Z6FSxeay','8-927-683-2728','User5_Surname','2023-07-29 21:10:11.507005',4,NULL),(11,NULL,NULL,NULL,'2023-07-29 21:10:11.587023','use6@gmail.com',0,'user6',NULL,'User6_Name','$2a$10$LiOJMRvPtpKl0OMHawVG5uRRkmMGoz.3xpPhZ3bZOFCyD.tWz.T5G','8-927-683-2729','User6_Surname','2023-07-29 21:10:11.587023',4,NULL),(12,NULL,NULL,NULL,'2023-07-29 21:10:11.664009','use7@gmail.com',1,'user7',NULL,'User7_Name','$2a$10$T0.eFRHnPqMY15.nBr4gqOHWYMDyTMZ6aGwB/7cHYUMN05N/9lOhq','8-927-683-2730','User7_Surname','2023-07-29 21:10:11.664009',4,NULL),(13,NULL,NULL,NULL,'2023-07-29 21:10:11.743009','use8@gmail.com',0,'user8',NULL,'User8_Name','$2a$10$0dSb.YTke0.2bdUWxyRHiOzurYIGVxPIpG5RZLy5rhd.6Zhi4/rv6','8-927-683-2731','User8_Surname','2023-07-29 21:10:11.743009',4,NULL),(14,NULL,NULL,NULL,'2023-07-29 21:10:11.824008','use9@gmail.com',1,'user9',NULL,'User9_Name','$2a$10$P.a3sxMtSxWypwEBMdw8Hue1zLtItplBJOPL.QtKD6W5ZVJZqSxsW','8-927-683-2732','User9_Surname','2023-07-29 21:10:11.824008',4,NULL),(15,NULL,NULL,NULL,'2023-07-29 21:10:11.903013','use10@gmail.com',0,'user10',NULL,'User10_Name','$2a$10$zsMp6LLNmaTone.g.Hpsces6fiLUfV3vKzSxpoQ3XAZPvYJUyNFme','8-927-683-2733','User10_Surname','2023-07-29 21:10:11.903013',4,NULL),(16,NULL,NULL,NULL,'2023-07-29 21:10:11.980010','use11@gmail.com',1,'user11',NULL,'User11_Name','$2a$10$y.XgjudmUbkF2PTTUtxmYOj6qN9frhxzTEsejiKhsRF1SeZzDcsMa','8-927-683-2734','User11_Surname','2023-07-29 21:10:11.980010',4,NULL),(17,NULL,NULL,NULL,'2023-07-29 21:10:12.061009','use12@gmail.com',0,'user12',NULL,'User12_Name','$2a$10$DrEG3H4PzCPPCR3HIK3zcO9VkOmBR.oEW4FNEIK49fGvS.5hTlpS2','8-927-683-2735','User12_Surname','2023-07-29 21:10:12.061009',4,NULL),(18,NULL,NULL,NULL,'2023-07-29 21:10:12.138006','use13@gmail.com',1,'user13',NULL,'User13_Name','$2a$10$6HsWb7yrBSRgQrKONxYEL.qILGm6OC78Fh3q9Yw3Yhmx50v//dXBm','8-927-683-2736','User13_Surname','2023-07-29 21:10:12.138006',4,NULL),(19,NULL,NULL,NULL,'2023-07-29 21:10:12.217009','use14@gmail.com',0,'user14',NULL,'User14_Name','$2a$10$6d4k3Rtu5tLa9RJw8EEPR.3LpbM.N4bbsF5B5bABm35sU22ThNglC','8-927-683-2737','User14_Surname','2023-07-29 21:10:12.217009',4,NULL),(20,NULL,NULL,NULL,'2023-07-29 21:10:12.295010','use15@gmail.com',1,'user15',NULL,'User15_Name','$2a$10$KUpNzqczjCmiKpaKRUZpeOPh7JiI2ZT.yezcOZWxBego.7A3x1QBS','8-927-683-2738','User15_Surname','2023-07-29 21:10:12.295010',4,NULL),(21,NULL,NULL,NULL,'2023-07-29 21:10:12.373133','use16@gmail.com',0,'user16',NULL,'User16_Name','$2a$10$bdLW0iO80J/tK2JmxvPtDu.uobGg8cwk93hvlPyFcRP.Q2RK1XRq.','8-927-683-2739','User16_Surname','2023-07-29 21:10:12.373133',4,NULL),(22,NULL,NULL,NULL,'2023-07-29 21:10:12.453012','use17@gmail.com',1,'user17',NULL,'User17_Name','$2a$10$3eV0FsEM4gHSwnX5AKemBej/fOWXgeCGibx1zIIhdKKYC7o7JGYu.','8-927-683-2740','User17_Surname','2023-07-29 21:10:12.453012',4,NULL),(23,NULL,NULL,NULL,'2023-07-29 21:10:12.532009','use18@gmail.com',0,'user18',NULL,'User18_Name','$2a$10$mEryJeouJbKgcbVc6i8wQ.RCBOLqW4wOf/UiE4c4IBMB1SAEYq1w6','8-927-683-2741','User18_Surname','2023-07-29 21:10:12.532009',4,NULL),(24,NULL,NULL,NULL,'2023-07-29 21:10:12.610143','use19@gmail.com',1,'user19',NULL,'User19_Name','$2a$10$.l77G01Eh6Sv.bY5BmjFsurDO.tT5ja5VboI4e5u.TjMAk25bt.rm','8-927-683-2742','User19_Surname','2023-07-29 21:10:12.610143',4,NULL),(25,NULL,NULL,NULL,'2023-07-29 21:10:12.690024','use20@gmail.com',0,'user20',NULL,'User20_Name','$2a$10$WJpYSmyHZQ7iwv4rgF7N0uhKLq5S800iG1.9bbiG1/NGX18eq2diq','8-927-683-2743','User20_Surname','2023-07-29 21:10:12.690024',4,NULL),(26,NULL,NULL,NULL,'2023-07-29 21:10:12.770009','use21@gmail.com',1,'user21',NULL,'User21_Name','$2a$10$K38iroerowlR5lrjpCcKFOQDK9VW8KVmwiE46a/sZSqd/.uCLGVJS','8-927-683-2744','User21_Surname','2023-07-29 21:10:12.770009',4,NULL),(27,NULL,NULL,NULL,'2023-07-29 21:10:12.848012','use22@gmail.com',0,'user22',NULL,'User22_Name','$2a$10$oyivjuXUeNTNj6Tckf6SRO1pYkGl/ftRoPpQKHrUP/0dN/tLlCeRi','8-927-683-2745','User22_Surname','2023-07-29 21:10:12.848012',4,NULL),(28,NULL,NULL,NULL,'2023-07-29 21:10:12.923024','use23@gmail.com',1,'user23',NULL,'User23_Name','$2a$10$hIjSxkGiB9If5yFot89vEeoZ66SjBLY0LQNFyIeI4aY5uqHP1ItBS','8-927-683-2746','User23_Surname','2023-07-29 21:10:12.923024',4,NULL),(29,NULL,NULL,NULL,'2023-07-29 21:10:12.999024','use24@gmail.com',0,'user24',NULL,'User24_Name','$2a$10$mbfL1PwNpTVqQGUhWywYP.ejPFZeqNlOHbIlxPHxK8VILUlgxOW/i','8-927-683-2747','User24_Surname','2023-07-29 21:10:12.999024',4,NULL),(30,NULL,NULL,NULL,'2023-07-29 21:10:13.076010','use25@gmail.com',1,'user25',NULL,'User25_Name','$2a$10$czGvPPJKtXlXv14DbEq6MuPv.vWiRE9aUYmGLZgq9UDNqxeHt5kN.','8-927-683-2748','User25_Surname','2023-07-29 21:10:13.076010',4,NULL),(31,NULL,NULL,NULL,'2023-07-29 21:10:13.151024','use26@gmail.com',0,'user26',NULL,'User26_Name','$2a$10$VAjtTgAW9HP77qeNCsU4teXXQFS1BpQgKkKeM0MAAaO4yAgsjZIz2','8-927-683-2749','User26_Surname','2023-07-29 21:10:13.151024',4,NULL),(32,NULL,NULL,NULL,'2023-07-29 21:10:13.228259','use27@gmail.com',1,'user27',NULL,'User27_Name','$2a$10$QtamazmMzuuGm0EnEptRou7/OHtdKRmKlp12wJ6i8pBIbSiuE/8jS','8-927-683-2750','User27_Surname','2023-07-29 21:10:13.228259',4,NULL),(33,NULL,NULL,NULL,'2023-07-29 21:10:13.304009','use28@gmail.com',0,'user28',NULL,'User28_Name','$2a$10$t.ynEukhaOMiY2Ea2aggxuCf8I42EyIAYlAMj6IbXi2zUN4f7UNVq','8-927-683-2751','User28_Surname','2023-07-29 21:10:13.304009',4,NULL),(34,NULL,NULL,NULL,'2023-07-29 21:10:13.381024','use29@gmail.com',1,'user29',NULL,'User29_Name','$2a$10$VpSFqFyufnXXn5v9w9DBleDuD/R2YVt5ig1Mve7KWYy38QoiiZav6','8-927-683-2752','User29_Surname','2023-07-29 21:10:13.381024',4,NULL),(35,NULL,NULL,NULL,'2023-07-29 21:10:13.460009','use30@gmail.com',0,'user30',NULL,'User30_Name','$2a$10$Mn0SVV9sHEekJ3A.pbHQ8.PzifQrWRrbpGfcl4DORyCa.SVXOU4r2','8-927-683-2753','User30_Surname','2023-07-29 21:10:13.460009',4,NULL),(36,NULL,NULL,NULL,'2023-07-29 21:10:13.539004','use31@gmail.com',1,'user31',NULL,'User31_Name','$2a$10$WBneJfRfdcIoutQfi6txk.6Dg4MSe7xCB4QUDCMcLtmDPEg7yZwhS','8-927-683-2754','User31_Surname','2023-07-29 21:10:13.539004',4,NULL),(37,NULL,NULL,NULL,'2023-07-29 21:10:13.619008','use32@gmail.com',0,'user32',NULL,'User32_Name','$2a$10$knMqfCmjUZZUNZZRvS8xNuSL54zMsyI2AhN4OuxDgaZ6oZ3ALBRvm','8-927-683-2755','User32_Surname','2023-07-29 21:10:13.619008',4,NULL),(38,NULL,NULL,NULL,'2023-07-29 21:10:13.695009','use33@gmail.com',1,'user33',NULL,'User33_Name','$2a$10$Y7MwaNhvJc/gDEqvaN/IaOUZhDBhtkN4vIhUNiX1qXxSMu2xDIgqS','8-927-683-2756','User33_Surname','2023-07-29 21:10:13.695009',4,NULL),(39,NULL,NULL,NULL,'2023-07-29 21:10:13.771010','use34@gmail.com',0,'user34',NULL,'User34_Name','$2a$10$hRyPf6FxMOb3vz2MRPfoj..TBv2JextxywKFaJ86gjBcpMxZtwQeu','8-927-683-2757','User34_Surname','2023-07-29 21:10:13.771010',4,NULL),(40,NULL,NULL,NULL,'2023-07-29 21:10:13.845009','use35@gmail.com',1,'user35',NULL,'User35_Name','$2a$10$iyb2WnyHIgwCEFRxVnuWYeqX0sgrvY6gR.WRdkYWmYlt7v1clu2mi','8-927-683-2758','User35_Surname','2023-07-29 21:10:13.845009',4,NULL),(41,NULL,NULL,NULL,'2023-07-29 21:10:13.924008','use36@gmail.com',0,'user36',NULL,'User36_Name','$2a$10$kXbH6LDdUkg5rYhMYc.nBOp7X3lqxFhGstUW7A7FIx.UxL6tK50CO','8-927-683-2759','User36_Surname','2023-07-29 21:10:13.924008',4,NULL),(42,NULL,NULL,NULL,'2023-07-29 21:10:14.001133','use37@gmail.com',1,'user37',NULL,'User37_Name','$2a$10$46K6uucqEaecuC81A2mk0eU8vTtrFE84sf2ud06H/kg/i/2LaGx.W','8-927-683-2760','User37_Surname','2023-07-29 21:10:14.001133',4,NULL),(43,NULL,NULL,NULL,'2023-07-29 21:10:14.082008','use38@gmail.com',0,'user38',NULL,'User38_Name','$2a$10$70keeGn8xLpVAFWg5sculeKlCuApZ/1nmpIC8mzxdRXhSw5S8gnqW','8-927-683-2761','User38_Surname','2023-07-29 21:10:14.082008',4,NULL),(44,NULL,NULL,NULL,'2023-07-29 21:10:14.159022','use39@gmail.com',1,'user39',NULL,'User39_Name','$2a$10$aSIu9383x6p7AL2FCQkpk.FbcQbdyVGNfnIV56CgYbfYyfLx4TnhO','8-927-683-2762','User39_Surname','2023-07-29 21:10:14.159022',4,NULL),(45,NULL,NULL,NULL,'2023-07-29 21:10:14.235024','use40@gmail.com',0,'user40',NULL,'User40_Name','$2a$10$Iq2/K/7sERNnIdeESZXo3OykNOz.gyc1IJu7kxtxGAkG17d/aKE1C','8-927-683-2763','User40_Surname','2023-07-29 21:10:14.235024',4,NULL),(46,NULL,NULL,NULL,'2023-07-29 21:10:14.312008','use41@gmail.com',1,'user41',NULL,'User41_Name','$2a$10$uIkA2pcXpcAFvbla/Bres.MDeMBCF5bgpXIp.TarbkeZz7oyNAmzS','8-927-683-2764','User41_Surname','2023-07-29 21:10:14.312008',4,NULL),(47,NULL,NULL,NULL,'2023-07-29 21:10:14.392007','use42@gmail.com',0,'user42',NULL,'User42_Name','$2a$10$Kw/xy60ASUfFhrZGiAAuoe06pvKI6JKsZQtIUHNXIJY/Fg8l1NPoO','8-927-683-2765','User42_Surname','2023-07-29 21:10:14.392007',4,NULL),(48,NULL,NULL,NULL,'2023-07-29 21:10:14.467009','use43@gmail.com',1,'user43',NULL,'User43_Name','$2a$10$honNnNBsz0.JfXQiKoLpRuCtHtMQJsM056pglu5jU62iBUdpnDxbu','8-927-683-2766','User43_Surname','2023-07-29 21:10:14.467009',4,NULL),(49,NULL,NULL,NULL,'2023-07-29 21:10:14.545007','use44@gmail.com',0,'user44',NULL,'User44_Name','$2a$10$eugAeGlNaa2eikTnnm0BPuoQd6r79hPh7XcgNHVl4WaC1lFbOMiLC','8-927-683-2767','User44_Surname','2023-07-29 21:10:14.545007',4,NULL),(50,NULL,NULL,NULL,'2023-07-29 21:10:14.625020','use45@gmail.com',1,'user45',NULL,'User45_Name','$2a$10$pPu/A4H5/f9UIrJvB.dEJuePmTbvJxiDpDmQ0.55WurwuQeNoioXi','8-927-683-2768','User45_Surname','2023-07-29 21:10:14.625020',4,NULL),(51,NULL,NULL,NULL,'2023-07-29 21:10:14.702007','use46@gmail.com',0,'user46',NULL,'User46_Name','$2a$10$NDAUieURahXC5cWjFrD2QeR6MRwVW3h9qsoaiM94OSPBUb5mV46km','8-927-683-2769','User46_Surname','2023-07-29 21:10:14.702007',4,NULL),(52,NULL,NULL,NULL,'2023-07-29 21:10:14.778006','use47@gmail.com',1,'user47',NULL,'User47_Name','$2a$10$WSmXhCQycPO/xY8wr05hWeYALeiEQ9TWRDJUDBy1LpCCsoOomaRvK','8-927-683-2770','User47_Surname','2023-07-29 21:10:14.778006',4,NULL),(53,NULL,NULL,NULL,'2023-07-29 21:10:14.854005','use48@gmail.com',0,'user48',NULL,'User48_Name','$2a$10$n45Yi2z9n/SIskTaDwKd6.UVNTqylrzEuUjfILZSUOFyG2lRrXyPy','8-927-683-2771','User48_Surname','2023-07-29 21:10:14.854005',4,NULL),(54,NULL,NULL,NULL,'2023-07-29 21:10:14.928006','use49@gmail.com',1,'user49',NULL,'User49_Name','$2a$10$ovYlFex8yECbF.xQ8fbKg.4CWSLzKdVnJk2VICc4WIZaA3Bty075C','8-927-683-2772','User49_Surname','2023-07-29 21:10:14.928006',4,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `week`
--

DROP TABLE IF EXISTS `week`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `week` (
                        `id` int NOT NULL,
                        `title` varchar(45) NOT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `week`
--

LOCK TABLES `week` WRITE;
/*!40000 ALTER TABLE `week` DISABLE KEYS */;
INSERT INTO `week` VALUES (0,'Понедельник'),(1,'Вторник'),(2,'Среда'),(3,'Четверг'),(4,'Пятница'),(5,'Суббота'),(6,'Воскресение');
/*!40000 ALTER TABLE `week` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workouts`
--

DROP TABLE IF EXISTS `workouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workouts` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `is_group` bit(1) DEFAULT NULL,
                            `time` datetime(6) DEFAULT NULL,
                            `specialization_id` bigint DEFAULT NULL,
                            `trainer_id` bigint DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `FKpmsxr9959s1gftx9jsx3c5ftj` (`specialization_id`),
                            KEY `FKkh62hggnvdcj7wdn9tep1qosr` (`trainer_id`),
                            CONSTRAINT `FKkh62hggnvdcj7wdn9tep1qosr` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`),
                            CONSTRAINT `FKpmsxr9959s1gftx9jsx3c5ftj` FOREIGN KEY (`specialization_id`) REFERENCES `specialization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workouts`
--

LOCK TABLES `workouts` WRITE;
/*!40000 ALTER TABLE `workouts` DISABLE KEYS */;
INSERT INTO `workouts` VALUES (1,_binary '','2023-07-21 16:00:00.000000',1,2),(2,_binary '','2023-07-21 20:00:00.000000',2,2),(3,_binary '','2023-07-20 18:00:00.000000',1,2),(4,_binary '','2023-07-19 16:00:00.000000',1,2),(5,_binary '','2023-07-18 16:00:00.000000',1,2),(6,_binary '','2023-07-18 20:00:00.000000',2,2),(7,_binary '','2023-07-13 16:00:00.000000',1,2),(8,_binary '','2023-07-11 16:00:00.000000',1,2);
/*!40000 ALTER TABLE `workouts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-30 21:13:05
