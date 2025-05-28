package com.pragma.loansanddeposits.util;

import com.pragma.loansanddeposits.model.Loan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestUtil {

    public static Loan buildLoan(){
        var loan = new Loan();
        loan.setId("123");
        loan.setAmount(1000.0);
        loan.setInterestRate(5.0);
        loan.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loan.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return loan;
    }
}
