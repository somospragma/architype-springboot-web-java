CREATE DATABASE LoansDB;
GO
USE LoansDB;
GO
CREATE TABLE Loan (
    id INT PRIMARY KEY IDENTITY(1,1),
    amount DECIMAL(18,2) NOT NULL,
    customer_name NVARCHAR(100) NOT NULL,
    status NVARCHAR(50) NOT NULL
);

CREATE TABLE TraceLoan (
    id INT PRIMARY KEY IDENTITY(1,1),
    loan_id INT NOT NULL,
    action NVARCHAR(100) NOT NULL,
    action_date DATETIME NOT NULL,
    FOREIGN KEY (loan_id) REFERENCES Loan(id)
);

INSERT INTO Loan (amount, customer_name, status) VALUES (10000.00, 'Juan Perez', 'APPROVED');
INSERT INTO Loan (amount, customer_name, status) VALUES (5000.00, 'Maria Gomez', 'PENDING');

INSERT INTO TraceLoan (loan_id, action, action_date) VALUES (1, 'CREATED', GETDATE());
INSERT INTO TraceLoan (loan_id, action, action_date) VALUES (2, 'CREATED', GETDATE());

