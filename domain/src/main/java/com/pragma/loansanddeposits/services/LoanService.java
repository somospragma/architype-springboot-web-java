package com.pragma.loansanddeposits.services;

import com.pragma.loansanddeposits.model.Loan;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class LoanService {

    // This method is used to validate the loan date, just to ilustrate the use of domain services
    public void validateLoanDate(Loan loan){
        if(loan.getEndDate().isBefore(LocalDate.now())){
            log.info("The loan must have been paid by {}", loan.getEndDate());
        }
    }

}
