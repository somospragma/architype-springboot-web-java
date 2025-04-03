package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.interfaces;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanNoSqlEntity;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.interfaces.nosql.INoSqlDataProvider;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoanNoSqlRepository extends INoSqlDataProvider<LoanNoSqlEntity, String> {
    // Métodos personalizados si es necesario
}

