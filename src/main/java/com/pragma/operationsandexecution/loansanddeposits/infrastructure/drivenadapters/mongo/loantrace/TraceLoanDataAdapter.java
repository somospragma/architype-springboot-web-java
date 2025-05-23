package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.LoanTrace;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ITraceLoanPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace.generic.MongoAdapterOperations;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.mongo.loantrace.mapper.TraceLoanDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TraceLoanDataAdapter extends MongoAdapterOperations<LoanTrace, LoanTraceData, String,
        TraceLoanDataRepository, TraceLoanDataMapper> implements ITraceLoanPort {


    protected TraceLoanDataAdapter(TraceLoanDataRepository repository, TraceLoanDataMapper mapper) {
        super(repository, mapper);
    }

    public void saveTraceLoan(LoanTrace loanTrace) {
        repository.save(toData(loanTrace));
    }

}
