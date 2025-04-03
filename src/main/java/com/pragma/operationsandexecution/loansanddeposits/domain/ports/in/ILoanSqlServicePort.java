package com.pragma.operationsandexecution.loansanddeposits.domain.ports.in;


import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;

public interface ILoanSqlServicePort {

    void createLoan(LoanSqlModel loanSQLModel, String transactionId);

    LoanSqlModel getLoanById(String loanId, String transactionId);

}
