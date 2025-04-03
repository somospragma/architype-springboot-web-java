package com.pragma.operationsandexecution.loansanddeposits.domain.services;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanNoSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanNoSqlPersistencePort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanNoSqlServiceTest {

    @InjectMocks
    private LoanNoSqlService loanNoSqlService;

    @Mock
    private ILoanNoSqlPersistencePort iLoanNoSqlPersistencePort;

    @Test
    @Order(1)
    @DisplayName("Debería guardar correctamente un rastro de préstamo en NoSQL")
    void shouldSaveTraceLoanSuccessfully() {
        // Arrange
        String loanId = "12345";
        Double amount = 1000.0;
        String transactionId = "txn-12345";

        LoanNoSqlModel expectedModel = LoanNoSqlModel.builder()
                .loanId(loanId)
                .amount(amount)
                .transactionId(transactionId)
                .build();

        // Act
        loanNoSqlService.saveTraceLoan(loanId, amount, transactionId);

        // Assert
        verify(iLoanNoSqlPersistencePort, times(1)).saveTraceLoan(expectedModel);
    }

}