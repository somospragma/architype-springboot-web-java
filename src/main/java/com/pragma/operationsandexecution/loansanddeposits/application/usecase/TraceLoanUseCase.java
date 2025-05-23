package com.pragma.operationsandexecution.loansanddeposits.application.usecase;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.LoanTrace;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ITraceLoanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class TraceLoanUseCase {

    private final ITraceLoanPort iTraceLoanPort;

    @Transactional
    public void saveTraceLoan(String loanId, Double amount, String transactionId) {
        iTraceLoanPort.saveTraceLoan(buildLoan(loanId, amount, transactionId));
    }

    private LoanTrace buildLoan(String loanId, Double amount, String transactionId) {
        return LoanTrace.builder()
                .loanId(loanId)
                .amount(amount)
                .transactionId(transactionId)
                .build();
    }

}
