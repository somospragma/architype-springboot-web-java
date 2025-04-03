package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanNoSqlModel;

public interface ILoanNoSqlPersistencePort {
    void saveTraceLoan(LoanNoSqlModel loanNoSqlModel);
}
