package com.pragma.operationsandexecution.loansanddeposits.domain.services;

import com.pragma.operationsandexecution.loansanddeposits.application.usecase.TraceLoanUseCase;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.LoanTrace;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ITraceLoanPort;
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
class LoanTraceUseCaseServiceTest {

    @InjectMocks
    private TraceLoanUseCase traceLoanUseCase;

    @Mock
    private ITraceLoanPort iTraceLoanPort;

    @Test
    @Order(1)
    @DisplayName("Debería guardar correctamente un rastro de préstamo en NoSQL")
    void shouldSaveTraceLoanSuccessfully() {
        // Arrange
        String loanId = "12345";
        Double amount = 1000.0;
        String transactionId = "txn-12345";

        LoanTrace expectedModel = LoanTrace.builder()
                .loanId(loanId)
                .amount(amount)
                .transactionId(transactionId)
                .build();

        // Act
        traceLoanUseCase.saveTraceLoan(loanId, amount, transactionId);

        // Assert
        verify(iTraceLoanPort, times(1)).saveTraceLoan(expectedModel);
    }

}
