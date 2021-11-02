DROP DATABASE IF EXISTS Pharmacy;
CREATE DATABASE IF NOT EXISTS Pharmacy;
SHOW DATABASES ;
USE Pharmacy;

DROP TABLE IF EXISTS Users;
CREATE TABLE IF NOT EXISTS Users(
    uId VARCHAR (15),
    userName VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    password VARCHAR(30) NOT NULL DEFAULT 'Unknown',
    possision VARCHAR (20),
    CONSTRAINT PRIMARY KEY (uId)
    );
SHOW TABLES ;
DESCRIBE Users;

DROP TABLE IF EXISTS Medicine;
CREATE TABLE IF NOT EXISTS Medicine(
    id VARCHAR (15),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    pack VARCHAR (10),
    qty INT (5),
    madeDate VARCHAR (20),
    expDate VARCHAR (20),
    company VARCHAR (20),
    CONSTRAINT PRIMARY KEY (id)
    );
SHOW TABLES ;
DESCRIBE Medicine;

DROP TABLE IF EXISTS Agent;
CREATE TABLE IF NOT EXISTS Agent(
    Aid VARCHAR(15),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    age INT (5),
    address TEXT,
    city VARCHAR (15),
    contact VARCHAR (15),
    password VARCHAR (20),
    gender VARCHAR (10),
    CONSTRAINT PRIMARY KEY (Aid)
    );
SHOW TABLES ;
DESCRIBE Agent;

DROP TABLE IF EXISTS Company;
CREATE TABLE IF NOT EXISTS Company(
    Cid VARCHAR(15),
    name VARCHAR(45) NOT NULL DEFAULT 'Unknown',
    address TEXT,
    contact VARCHAR (15),
    CONSTRAINT PRIMARY KEY (Cid)
    );
SHOW TABLES ;
DESCRIBE Company;
