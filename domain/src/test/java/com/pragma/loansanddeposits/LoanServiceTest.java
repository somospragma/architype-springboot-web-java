package com.pragma.loansanddeposits;

import com.pragma.loansanddeposits.model.Loan;
import com.pragma.loansanddeposits.services.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanServiceTest {

    @Mock
    private LoanService loanService;

    @Test
    void validateLoanDateTest(){
        var loan = Loan.builder()
                .id("1")
                .amount(1000.0)
                .interestRate(0.05)
                .startDate(LocalDate.of(2023, 1, 1))
                .endDate(LocalDate.of(2023, 12, 31))
                .build();
        Assertions.assertDoesNotThrow(() -> loanService.validateLoanDate(loan));
    }
}
