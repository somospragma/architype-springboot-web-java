package com.pragma.operationsandexecution.loansanddeposits.domain.models;

import java.time.LocalDate;


public class LoanSqlModel {

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

    private String loanId;
    private Double amount;
    private Double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public String toString() {
        return "LoanSqlModel{" +
                "loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
