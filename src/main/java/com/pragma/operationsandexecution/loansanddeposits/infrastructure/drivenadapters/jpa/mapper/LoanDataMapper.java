package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.mapper;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.loan.LoanData;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.helper.mapper.GenericDataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LoanDataMapper extends GenericDataMapper<Loan, LoanData> {
}
