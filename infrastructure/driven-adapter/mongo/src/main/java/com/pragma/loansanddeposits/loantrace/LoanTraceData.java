package com.pragma.loansanddeposits.loantrace;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.pragma.loansanddeposits.constant.MongoDbConstants.UTILITY_NAME_TABLE_LOAN_NO_SQL;


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
@Document(collection = UTILITY_NAME_TABLE_LOAN_NO_SQL)
public class LoanTraceData {

    private String loanId;
    private Double amount;
    private String transactionId;


    @Override
    public String toString() {
        return "Trace Loan {" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
