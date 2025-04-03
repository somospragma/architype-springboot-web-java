package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import static com.pragma.operationsandexecution.crosscutting.constants.common.CommonConstants.UTILITY_NAME_TABLE_LOAN_NO_SQL;

/**
 * Representa un préstamo en el sistema.
 * <p>
 * Esta entidad mapea la tabla {@code loans} en la base de datos y contiene información
 * relevante sobre un préstamo, como el identificador del préstamo, el monto, la tasa de interés,
 * y las fechas de inicio y finalización.
 * </p>
 *
 * @Entity indica que esta clase es una entidad JPA.
 * @Table especifica el nombre de la tabla en la base de datos a la que se mapea esta entidad.
 */

@Document(collection = UTILITY_NAME_TABLE_LOAN_NO_SQL)
public class LoanNoSqlEntity {

    private String loanId;
    private Double amount;
    private String transactionId;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "LoanNoSqlEntity{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
