package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.implementation;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.mappers.ILoanDtoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GetLoanUseCaseByIdTest {

    @InjectMocks
    private ILoanPort iLoan;

    @Mock
    private ILoanDtoMapper iLoanDtoMapper;

    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId("123");
        loan.setAmount(1000.0);
        loan.setInterestRate(5.0);
        loan.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loan.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));


    }

    @Test
    @Order(1)
    @DisplayName("Debería obtener un objeto del prestamo")
    void createLoanGetLoanById() {
        // Arrange
        String loanId = "loan-123";
        // Act
        var result = iLoan.getLoanById(loanId);

        // Assert
        assertEquals(loan, result.get());
    }

}
