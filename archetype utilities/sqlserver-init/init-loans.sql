CREATE DATABASE LoansDB;
GO
USE LoansDB;
GO
CREATE TABLE Loan (
    id NVARCHAR(255) PRIMARY KEY,
    amount DECIMAL(18,2) NOT NULL,
    interest_rate DECIMAL(18,4) NULL,
    start_date DATE NULL,
    end_date DATE NULL
);


INSERT INTO Loan (id, amount, interest_rate, start_date, end_date) VALUES ('LN-001', 10000.00, 5.5, '2025-05-26', '2026-05-26');
INSERT INTO Loan (id, amount, interest_rate, start_date, end_date) VALUES ('LN-002', 5000.00, 4.2, '2025-06-01', '2026-06-01');


