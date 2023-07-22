create table CUSTOMER_PURCHASE(
  `ID` int NOT NULL AUTO_INCREMENT,
  `customer_Id` varchar NOT NULL ,
  `amount` float NOT NULL,
  `purchase_Date` date not null,
  `reward` int not null,
   PRIMARY KEY (`ID`));

insert into Customer_Purchase (customer_Id,amount,purchase_Date,reward) values
('123',100,CURRENT_TIMESTAMP ,50),
('456',120,CURRENT_TIMESTAMP ,90),
('456',120,CURRENT_TIMESTAMP ,90),
('789',100,CURRENT_TIMESTAMP ,50),
('789',100,CURRENT_TIMESTAMP ,50),
('789',100,CURRENT_TIMESTAMP ,50);