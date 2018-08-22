/* CREAR DB CREDENCIALES */
CREATE DATABASE IF NOT EXISTS `Credenciales` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `Credenciales`;

/* CREAR TABLAS */
CREATE TABLE IF NOT EXISTS `dns` (
  `id` int(11) NOT NULL,
  `nombreDns` varchar(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS `Servers` (
  `id` int(11) NOT NULL,
  `ip` varchar(16) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `contrasena` blob NOT NULL,
  `actualizado` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/* CREACION DE CLAVES PRIMARIAS */
ALTER TABLE `dns`
 ADD PRIMARY KEY (`id`,`nombreDns`);

ALTER TABLE `Servers`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `ip` (`ip`);

 /* CREACION DE CLAVES FORANEAS */
ALTER TABLE `dns`
ADD CONSTRAINT `dns_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Servers` (`id`);