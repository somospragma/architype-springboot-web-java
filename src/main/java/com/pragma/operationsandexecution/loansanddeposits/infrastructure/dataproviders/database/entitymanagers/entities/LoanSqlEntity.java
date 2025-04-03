package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import static com.pragma.operationsandexecution.crosscutting.constants.common.CommonConstants.UTILITY_NAME_TABLE_LOAN_SQL;

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

@Table(name = UTILITY_NAME_TABLE_LOAN_SQL)
@Entity
public class LoanSqlEntity {

    /**
     * Identificador único del préstamo.
     */
    @Id
    private String loanId;

    /**
     * Monto del préstamo.
     */
    private Double amount;

    /**
     * Tasa de interés aplicada al préstamo.
     */
    private Double interestRate;

    /**
     * Fecha de inicio del préstamo.
     */
    private LocalDate startDate;

    /**
     * Fecha de finalización del préstamo.
     */
    private LocalDate endDate;

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

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "LoanSqlEntity{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
