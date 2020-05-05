# PAF_PracticalTest
**IT No-: IT18119718**
 
**Name: Liyanage D.R.Y**

**Batch: Y3S1.1.1**

**MicroService: Doctor** 


**DatabaseName: doctor**

**Table Name: doctor**


===============CREATE DATABASE=====================

create database doctor;

use doctor;


=================CREATE TABLE=======================


create table doctor(
docNic varchar(15) not null primary key,
docName varchar(100),
docEmail varchar(50),
docContact varchar(20),
docGender varchar(50),
docFee double,
docSpec varchar(100) ,
docHospital varchar(100),
docNumAppointments int ,
docPassword varchar(20) 
);
