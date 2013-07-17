--
-- Database: `wedlum`
--

-- --------------------------------------------------------

--
-- Table structure for table `Address`
--

CREATE TABLE IF NOT EXISTS Address (
	id INT NOT NULL AUTO_INCREMENT,
	zipcode VARCHAR(10) DEFAULT NULL,
	version INT NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
) ENGINE=INNODB;

--
-- Table structure for table `Person`
--

CREATE TABLE IF NOT EXISTS Person (
	id INT NOT NULL AUTO_INCREMENT,
	firstname VARCHAR(32) NOT NULL,
	lastname VARCHAR(32) NOT NULL,
	gender ENUM('MALE','FEMALE') DEFAULT NULL,
	birthdate DATE DEFAULT NULL,
	address_id INT(11) DEFAULT NULL,
	version INT NOT NULL DEFAULT 0,
	PRIMARY KEY (id),
	INDEX address_index (address_id),
	FOREIGN KEY (address_id) REFERENCES Address(id)
) ENGINE=INNODB;

--
-- Table structure for table `User`
--

CREATE TABLE IF NOT EXISTS User (
	id INT NOT NULL AUTO_INCREMENT,
	email VARCHAR(128) NOT NULL,
	password VARCHAR(128) DEFAULT NULL,
	password_salt varchar(32) DEFAULT NULL,
	role ENUM('ADMIN', 'TEST_TAKER', 'VENDOR') DEFAULT NULL,
	person_id INT DEFAULT NULL,
	version INT NOT NULL DEFAULT 0,
	CONSTRAINT uc_user_email UNIQUE (email),
	PRIMARY KEY (id),
	INDEX email_index (email),
	FOREIGN KEY (person_id) REFERENCES Person(id)
) ENGINE=INNODB;
