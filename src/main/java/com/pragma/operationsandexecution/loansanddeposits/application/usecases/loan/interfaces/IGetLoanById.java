package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces;

import com.pragma.operationsandexecution.loansanddeposits.application.response.LoanResponse;

public interface IGetLoanById {

    LoanResponse execute(String loanId, String transactionId);

}
