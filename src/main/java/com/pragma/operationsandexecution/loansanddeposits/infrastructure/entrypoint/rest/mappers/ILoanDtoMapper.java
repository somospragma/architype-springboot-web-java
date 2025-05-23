package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.mappers;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.LoanDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoanDtoMapper {

    Loan loanDtoToLoan(LoanDto loanDTO);

}
