package com.pragma.loansanddeposits.mapper;

import com.pragma.loansanddeposits.loan.LoanData;
import com.pragma.loansanddeposits.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LoanDataMapper extends GenericDataMapper<Loan, LoanData> {
}
