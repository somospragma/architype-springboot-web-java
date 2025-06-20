package com.pragma.loansanddeposits.loan;


import com.pragma.loansanddeposits.generic.AdapterOperations;
import com.pragma.loansanddeposits.mapper.LoanDataMapper;
import com.pragma.loansanddeposits.model.Loan;
import com.pragma.loansanddeposits.port.out.ILoanPort;
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
