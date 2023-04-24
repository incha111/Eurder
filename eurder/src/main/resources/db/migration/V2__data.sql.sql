set schema 'eurder';

INSERT INTO customer(firstname, lastname, email, password, address, phone)
VALUES('Louis','Dupont','ldu@gmail.com','123','Bruggestraat 15, 8000 Brugge','0478659898');

INSERT INTO customer(firstname, lastname, email, password, address, phone)
VALUES('Valerie','Normand','vno@gmail.com','123','Rue du bois 25, 4000 Liège','0495124578');

INSERT INTO item(name, description, price, stock_amount, urgency_indicator)
VALUES('Tennis rackets','Tennis racket to play tennis.',120.00,12,2);

INSERT INTO item(name, description, price, stock_amount, urgency_indicator)
VALUES('Tennis ball','Package of 4 balls',4.00,8,1);

