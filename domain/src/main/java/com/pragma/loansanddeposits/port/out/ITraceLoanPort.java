package com.pragma.loansanddeposits.port.out;

import com.pragma.loansanddeposits.model.LoanTrace;

public interface ITraceLoanPort {
    void saveTraceLoan(LoanTrace loanTrace);
}
