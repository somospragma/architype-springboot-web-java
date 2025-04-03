package com.pragma.operationsandexecution.loansanddeposits.domain.ports.in;

public interface ILoanNoSqlServicePort {
    void saveTraceLoan(String loanId, Double amount, String transactionId);
}
