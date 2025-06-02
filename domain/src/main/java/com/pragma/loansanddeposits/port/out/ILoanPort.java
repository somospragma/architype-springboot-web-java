package com.pragma.loansanddeposits.port.out;

import com.pragma.loansanddeposits.model.Loan;

import java.util.Optional;

public interface ILoanPort {

    Loan save(Loan loan);

    Optional<Loan> getLoanById(String loanId);

}
