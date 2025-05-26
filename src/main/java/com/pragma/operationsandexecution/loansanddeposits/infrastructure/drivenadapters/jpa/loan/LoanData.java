package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.loan;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.CommonConstants.UTILITY_NAME_TABLE_LOAN_SQL;

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

@Getter
@Setter
@Entity
@Table(name = UTILITY_NAME_TABLE_LOAN_SQL)
@NoArgsConstructor
public class LoanData {

    /**
     * Identificador único del préstamo.
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * Monto del préstamo.
     */
    @Column(name = "amount")
    private Double amount;

    /**
     * Tasa de interés aplicada al préstamo.
     */
    @Column(name = "interest_rate")
    private Double interestRate;

    /**
     * Fecha de inicio del préstamo.
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * Fecha de finalización del préstamo.
     */
    @Column(name = "end_date")
    private LocalDate endDate;

    public String getId() {
        return id;
    }

    public void setId(String loanId) {
        this.id = loanId;
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
                "loanId='" + id + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
