CREATE DATABASE  IF NOT EXISTS `mais_saude` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mais_saude`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mais_saude
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `consulta`
--

DROP TABLE IF EXISTS `consulta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consulta` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  `id_paciente` bigint DEFAULT NULL,
  `sintomas` varchar(300) DEFAULT NULL,
  `prontuario` varchar(300) DEFAULT NULL,
  `cpf_paciente` varchar(11) DEFAULT NULL,
  `nome_medico` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_paciente_idx` (`id_paciente`),
  CONSTRAINT `id_paciente` FOREIGN KEY (`id_paciente`) REFERENCES `pacientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consulta`
--

LOCK TABLES `consulta` WRITE;
/*!40000 ALTER TABLE `consulta` DISABLE KEYS */;
INSERT INTO `consulta` VALUES (41,'2025-07-31 23:19:04',NULL,30,'dasdasd','dadasda','12314585420','sdfasdfad'),(42,'2025-07-31 23:45:40',NULL,7,'tete','tetet','10101010101','novo');
/*!40000 ALTER TABLE `consulta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pacientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `data_de_nascimento` datetime DEFAULT NULL,
  `sexo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacientes`
--

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (7,'10101010101','Ismael','rua do C','88888888888','2000-08-08 00:00:00','masculino'),(9,'21212121210','Julio luiz','Rua J','21212121210','2011-11-11 00:00:00','masculino'),(10,'12314715960','Patricia','Rua p','12345678956','1999-08-10 00:00:00','feminino'),(22,'12354645466','Brenda','rua da A','2152525252','2000-10-11 00:00:00','feminino'),(30,'12314585420','Arthur','rua da B','2155585588','2000-10-10 00:00:00','masculino');
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissoes`
--

DROP TABLE IF EXISTS `permissoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissoes` (
  `id` int NOT NULL,
  `descricao` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissoes`
--

LOCK TABLES `permissoes` WRITE;
/*!40000 ALTER TABLE `permissoes` DISABLE KEYS */;
INSERT INTO `permissoes` VALUES (0,'Administrador'),(1,'Medico'),(2,'Atendente');
/*!40000 ALTER TABLE `permissoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_permissao`
--

DROP TABLE IF EXISTS `usuario_permissao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_permissao` (
  `usuario_id` bigint NOT NULL,
  `permissao_id` int NOT NULL,
  KEY `fk_usuario_id_idx` (`usuario_id`),
  KEY `fk_permissao_id_idx` (`permissao_id`),
  CONSTRAINT `fk_permissao_id` FOREIGN KEY (`permissao_id`) REFERENCES `permissoes` (`id`),
  CONSTRAINT `fk_usuario_id` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_permissao`
--

LOCK TABLES `usuario_permissao` WRITE;
/*!40000 ALTER TABLE `usuario_permissao` DISABLE KEYS */;
INSERT INTO `usuario_permissao` VALUES (8,0),(8,1),(20,0),(20,1),(21,1),(1,0),(1,1),(22,0),(22,1);
/*!40000 ALTER TABLE `usuario_permissao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `senha` varchar(255) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `telefone` varchar(15) NOT NULL,
  `data_de_nascimento` datetime NOT NULL,
  `sexo_biologico` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'$2a$10$GCvsyO5dTVOUH7SQ4WYgD.eKRQhDT8m.nK/EW1WVPLCmikZE.RS6i','00000000000','Adm','Adm@adm','adm','000000','2021-10-10 00:00:00','masculino'),(8,'$2a$10$GCvsyO5dTVOUH7SQ4WYgD.eKRQhDT8m.nK/EW1WVPLCmikZE.RS6i','99999999988','Erick Rodrigues ','erick@erick.com','Rua Almirante','99999999999','2010-10-10 00:00:00','masculino'),(20,'$2a$10$GCvsyO5dTVOUH7SQ4WYgD.eKRQhDT8m.nK/EW1WVPLCmikZE.RS6i','33333333333','testado com tres','teste@teste3.com','testes3','21987633333','1993-03-30 00:00:00','feminino'),(21,'$2a$10$GCvsyO5dTVOUH7SQ4WYgD.eKRQhDT8m.nK/EW1WVPLCmikZE.RS6i','11111111111','usuario de testes','teste','teste','teste','2021-10-10 00:00:00','masculino'),(22,'$2a$10$ShvHOpAhQ6ADr1g0D1q3NOtZwVTFGf9InboG5BHBGE9DTC7uR0E1S','12345670000','usuario de testes','teste@teste.com','testes','21987653557','2010-10-10 00:00:00','masculino');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-20  9:35:31
