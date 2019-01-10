CREATE TABLE t_dept (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	dept_name VARCHAR (255) NOT NULL,
	db_name VARCHAR (255) NOT NULL
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

INSERT INTO t_dept (dept_name, db_name)
VALUE
	('研发部', DATABASE());

INSERT INTO t_dept (dept_name, db_name)
VALUE
	('会计部', DATABASE());

INSERT INTO t_dept (dept_name, db_name)
VALUE
	('产品部', DATABASE());