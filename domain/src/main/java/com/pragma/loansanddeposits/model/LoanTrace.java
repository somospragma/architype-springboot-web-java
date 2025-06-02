package com.pragma.loansanddeposits.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanTrace {

    private String loanId;
    private Double amount;
    private String transactionId;


    @Override
    public String toString() {
        return "LoanNoSqlModel{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
