package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.interfaces;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.sql.ISqlDataProvider;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanSqlEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanSqlRepository extends ISqlDataProvider<LoanSqlEntity, String> {
    // Métodos personalizados si es necesario
}

