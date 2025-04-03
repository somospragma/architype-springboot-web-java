package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.sql;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanSqlPersistencePort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanSqlEntity;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IGenericRepository;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.mappers.ILoanEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class LoanSqlPersistence implements ILoanSqlPersistencePort {

    private final IGenericRepository<LoanSqlEntity, String> loanRepository;
    private final ILoanEntityMapper iLoanEntityMapper;

    @Override
    public void save(LoanSqlModel loanSqlModel) {
        loanRepository.save(iLoanEntityMapper.loanSqlModelToLoanSqlEntity(loanSqlModel));
    }

    @Override
    public Optional<LoanSqlModel> getLoanById(String loanId) {
        return loanRepository.findById(loanId)
                .map(this::buildLoanSqlModel);
    }

    private LoanSqlModel buildLoanSqlModel(LoanSqlEntity loan) {
        LoanSqlModel loanSqlModel = new LoanSqlModel();
        loanSqlModel.setLoanId(loan.getLoanId());
        loanSqlModel.setAmount(loan.getAmount());
        loanSqlModel.setEndDate(loan.getEndDate());
        loanSqlModel.setStartDate(loan.getStartDate());
        loanSqlModel.setInterestRate(loan.getInterestRate());
        return loanSqlModel;
    }

}