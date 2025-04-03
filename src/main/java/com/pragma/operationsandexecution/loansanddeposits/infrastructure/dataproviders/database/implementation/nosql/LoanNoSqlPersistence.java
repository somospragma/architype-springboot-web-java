package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.implementation.nosql;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanNoSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanNoSqlPersistencePort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanNoSqlEntity;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.generic.IGenericRepository;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.mappers.ILoanEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class LoanNoSqlPersistence implements ILoanNoSqlPersistencePort {

    private final IGenericRepository<LoanNoSqlEntity, String> loanRepository;
    private final ILoanEntityMapper iLoanEntityMapper;

    public void saveTraceLoan(LoanNoSqlModel loanNoSqlModel) {
        loanRepository.save(iLoanEntityMapper.loanNoSqlModelToLoanNoSqlEntity(loanNoSqlModel));
    }


}
