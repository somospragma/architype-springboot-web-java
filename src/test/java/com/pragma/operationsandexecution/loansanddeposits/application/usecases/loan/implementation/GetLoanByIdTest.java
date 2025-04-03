package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.implementation;

import com.pragma.operationsandexecution.loansanddeposits.application.mappers.ILoanDtoMapper;
import com.pragma.operationsandexecution.loansanddeposits.application.response.LoanResponse;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanSqlServicePort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GetLoanByIdTest {

    @InjectMocks
    private GetLoanById getLoanById;

    @Mock
    private ILoanSqlServicePort iLoanSqlServicePort;
    @Mock
    private ILoanDtoMapper iLoanDtoMapper;

    private LoanSqlModel loanSqlModel;
    private LoanResponse loanResponse;

    @BeforeEach
    void setUp() {
        loanSqlModel = new LoanSqlModel();
        loanSqlModel.setLoanId("123");
        loanSqlModel.setAmount(1000.0);
        loanSqlModel.setInterestRate(5.0);
        loanSqlModel.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanSqlModel.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        loanResponse = new LoanResponse();
        loanResponse.setLoanId("123");
        loanResponse.setAmount(1000.0);
        loanResponse.setInterestRate(5.0);
        loanResponse.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanResponse.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

    }

    @Test
    @Order(1)
    @DisplayName("Debería obtener un objeto del prestamo")
    void executeGetLoanById() {
        // Arrange
        String loanId = "loan-123";
        String transactionId = "txn-456";
        when(iLoanSqlServicePort.getLoanById(loanId, transactionId)).thenReturn(loanSqlModel);
        when(iLoanDtoMapper.loanSqlModelToLoanResponse(loanSqlModel)).thenReturn(loanResponse);

        // Act
        LoanResponse result = getLoanById.execute(loanId, transactionId);

        // Assert
        assertEquals(loanResponse, result);
        verify(iLoanSqlServicePort).getLoanById(loanId, transactionId);
        verify(iLoanDtoMapper).loanSqlModelToLoanResponse(loanSqlModel);
    }

}