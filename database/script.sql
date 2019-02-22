CREATE DATABASE IF NOT EXISTS `TaskServiceDB`;
USE `TaskServiceDB`;

DROP TABLE IF EXISTS `argument`;
DROP TABLE IF EXISTS `environment`;
DROP TABLE IF EXISTS `task`;

CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `command` varchar(25) NOT NULL,
  `working_directory` varchar(1000) NOT NULL,
  `status` varchar(25) NOT NULL,
  `output` varchar(100),
  `error` varchar(100),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `environment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `path` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_environment_task FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB;

CREATE TABLE `argument` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `sort` int(11) NOT NULL,
  `arg` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_argument_task FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB;


