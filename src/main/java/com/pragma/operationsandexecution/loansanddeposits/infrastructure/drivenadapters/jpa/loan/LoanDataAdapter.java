package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.loan;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.generic.AdapterOperations;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.mapper.LoanDataMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class LoanDataAdapter extends AdapterOperations<Loan, LoanData, String, LoanDataRepository, LoanDataMapper>
        implements ILoanPort {


    protected LoanDataAdapter(LoanDataRepository repository, LoanDataMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public Optional<Loan> getLoanById(String loanId) {
        return repository.findById(loanId)
                .map(this::toEntity);
    }

}
