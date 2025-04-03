package com.pragma.operationsandexecution.loansanddeposits.domain.services;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanNoSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanNoSqlServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanNoSqlPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoanNoSqlService implements ILoanNoSqlServicePort {

    private final ILoanNoSqlPersistencePort iLoanNoSqlPersistencePort;

    @Transactional
    public void saveTraceLoan(String loanId, Double amount, String transactionId) {
        iLoanNoSqlPersistencePort.saveTraceLoan(buildNoSqlModel(loanId, amount, transactionId));
    }

    private LoanNoSqlModel buildNoSqlModel(String loanId, Double amount, String transactionId) {
        return LoanNoSqlModel.builder()
                .loanId(loanId)
                .amount(amount)
                .transactionId(transactionId)
                .build();
    }

}
