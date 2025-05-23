package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;

import java.util.Optional;

public interface ILoanPort {

    Loan save(Loan loan);

    Optional<Loan> getLoanById(String loanId);

}
