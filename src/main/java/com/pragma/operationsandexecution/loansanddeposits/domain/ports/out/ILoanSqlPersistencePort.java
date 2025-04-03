package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;

import java.util.Optional;

public interface ILoanSqlPersistencePort {

    void save(LoanSqlModel loanSQLModel);

    Optional<LoanSqlModel> getLoanById(String loanId);

}
