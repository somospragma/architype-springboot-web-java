package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.LoanTrace;

public interface ITraceLoanPort {
    void saveTraceLoan(LoanTrace loanTrace);
}
