package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.mappers;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanNoSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanNoSqlEntity;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanSqlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ILoanEntityMapper {

    LoanSqlEntity loanSqlModelToLoanSqlEntity(LoanSqlModel loanSQLModel);

    LoanNoSqlEntity loanNoSqlModelToLoanNoSqlEntity(LoanNoSqlModel loanNoSqlModel);

}
