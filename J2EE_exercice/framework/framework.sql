
CREATE DATABASE IF NOT EXISTS framework;
 
USE framework;
 
CREATE TABLE IF NOT EXISTS user(
	user_name CHAR(64) PRIMARY KEY,
	user_pwd VARCHAR(128)
);

CREATE TABLE IF NOT EXISTS tach(
	tach_id 	INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
	tach_name	VARCHAR(128)
);
 
truncate tach; /* delete datas et recommencer id(AUTO_INCREMENT) ид partir de 1 */
 
INSERT INTO user(user_name,user_pwd)
	VALUES('bin','binbin');
