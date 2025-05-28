package com.pragma.loansanddeposits.services;

import com.pragma.loansanddeposits.model.Loan;

import java.time.LocalDate;
import java.util.logging.Logger;

public class LoanService {

    private static final Logger log = Logger.getLogger(LoanService.class.getName());

    // This method is used to validate the loan date, just to ilustrate the use of domain services
    public void validateLoanDate(Loan loan){
        if(loan.getEndDate().isBefore(LocalDate.now())){
            log.info("The loan must have been paid by {}" + loan.getEndDate());
        }
    }

}
