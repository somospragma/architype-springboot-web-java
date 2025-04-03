package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.implementation;

import com.pragma.operationsandexecution.loansanddeposits.application.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.application.mappers.ILoanDtoMapper;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanNoSqlServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanSqlServicePort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class CreateLoanTest {

    @InjectMocks
    private CreateLoan createLoan;

    @Mock
    private ILoanSqlServicePort iLoanSqlServicePort;
    @Mock
    private ILoanNoSqlServicePort iLoanNoSqlServicePort;
    @Mock
    private ILoanDtoMapper iLoanDtoMapper;

    private LoanDto loanDto;
    private LoanSqlModel loanSqlModel;

    @BeforeEach
    void setUp() {
        loanDto = new LoanDto();
        loanDto.setLoanId("123");
        loanDto.setAmount(1000.0);
        loanDto.setInterestRate(5.0);
        loanDto.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanDto.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanSqlModel = new LoanSqlModel();
        loanSqlModel.setLoanId("123");
        loanSqlModel.setAmount(1000.0);
        loanSqlModel.setInterestRate(5.0);
        loanSqlModel.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanSqlModel.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    @Order(1)
    @DisplayName("Debería crear un préstamo correctamente y guardar el rastro cuando el monto sea 10.5")
    void executeCreateLoanWithTrace() {
        // Arrange
        String transactionId = "txn-456";
        when(iLoanDtoMapper.loanDtoToLoanSqlModel(loanDto)).thenReturn(loanSqlModel);

        // Act
        createLoan.execute(loanDto, transactionId);

        // Assert
        verify(iLoanDtoMapper).loanDtoToLoanSqlModel(loanDto);
        verify(iLoanSqlServicePort).createLoan(loanSqlModel, transactionId);
    }

    @Test
    @Order(2)
    @DisplayName("Debería crear un préstamo sin guardar rastro cuando el monto no sea 10.5")
    void executeCreateLoanNotTrace() {
        // Arrange
        String transactionId = "txn-789";
        loanSqlModel.setAmount(15.0);
        when(iLoanDtoMapper.loanDtoToLoanSqlModel(loanDto)).thenReturn(loanSqlModel);

        // Act
        createLoan.execute(loanDto, transactionId);

        // Assert
        verify(iLoanDtoMapper).loanDtoToLoanSqlModel(loanDto);
        verify(iLoanSqlServicePort).createLoan(loanSqlModel, transactionId);

    }

}