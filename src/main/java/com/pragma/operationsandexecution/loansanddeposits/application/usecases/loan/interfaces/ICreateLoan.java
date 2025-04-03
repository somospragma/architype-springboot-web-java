package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces;

import com.pragma.operationsandexecution.loansanddeposits.application.dto.LoanDto;

public interface ICreateLoan {

    void execute(LoanDto loanDTO, String transactionId);

}
