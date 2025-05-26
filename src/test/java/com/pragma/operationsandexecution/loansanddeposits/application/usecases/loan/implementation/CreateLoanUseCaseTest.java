package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.implementation;

import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ITraceLoanPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.application.usecase.LoanUseCase;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CreateLoanUseCaseTest {

    @InjectMocks
    private LoanUseCase createLoanUseCase;


    @Mock
    private ITraceLoanPort iTraceLoanServicePort;
    @Mock
    private ILoanDtoMapper iLoanDtoMapper;

    private LoanDto loanDto;
    private Loan loan;

    @BeforeEach
    void setUp() {
        loanDto = LoanDto.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        loan = Loan.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Debería crear un préstamo correctamente y guardar el rastro cuando el monto sea 10.5")
    void createLoanCreateLoanWithTrace() {
        // Arrange
        String transactionId = "txn-456";
        when(iLoanDtoMapper.loanDtoToLoan(loanDto)).thenReturn(loan);

        // Act
        createLoanUseCase.createLoan(loan, transactionId);

        // Assert
        verify(iLoanDtoMapper).loanDtoToLoan(loanDto);
    }

    @Test
    @Order(2)
    @DisplayName("Debería crear un préstamo sin guardar rastro cuando el monto no sea 10.5")
    void createLoanCreateLoanNotTrace() {
        // Arrange
        String transactionId = "txn-789";
        loan.setAmount(15.0);
        when(iLoanDtoMapper.loanDtoToLoan(loanDto)).thenReturn(loan);

        // Act
        createLoanUseCase.createLoan(loan, transactionId);

        // Assert
        verify(iLoanDtoMapper).loanDtoToLoan(loanDto);

    }

}
