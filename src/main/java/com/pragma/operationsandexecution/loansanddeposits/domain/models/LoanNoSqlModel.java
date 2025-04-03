package com.pragma.operationsandexecution.loansanddeposits.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanNoSqlModel {

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
        return "LoanNoSqlModel{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
