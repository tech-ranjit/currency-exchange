CREATE TABLE currency_exchange
        (
        id NUMBER(10) NOT NULL,
        exchange_from VARCHAR2(50) NOT NULL,
        exchange_to VARCHAR2(50) NOT NULL,
        exchange_rate NUMBER(3) NOT NULL,
        PRIMARY KEY(id)
        );

INSERT INTO currency_exchange(ID, exchange_from, exchange_to, exchange_rate)
VALUES(1001, 'EURO','INR', 95);

INSERT INTO currency_exchange(ID, exchange_from, exchange_to, exchange_rate)
VALUES(1002, 'EURO','USD',83);

INSERT INTO currency_exchange(ID, exchange_from, exchange_to, exchange_rate)
VALUES(1003, 'EURO','GBP',105);