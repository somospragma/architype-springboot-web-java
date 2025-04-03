package com.pragma.operationsandexecution.loansanddeposits.application.mappers;

import com.pragma.operationsandexecution.loansanddeposits.application.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.application.response.LoanResponse;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoanDtoMapper {

    LoanSqlModel loanDtoToLoanSqlModel(LoanDto loanDTO);
    LoanResponse loanSqlModelToLoanResponse(LoanSqlModel loanSqlModel);

}
