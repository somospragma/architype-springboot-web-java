package com.pragma.loansanddeposits;

import com.pragma.loansanddeposits.port.out.ITraceLoanPort;
import com.pragma.loansanddeposits.usecase.TraceLoanUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanTraceUseCaseTest {

    @InjectMocks
    private TraceLoanUseCase traceLoanUseCase;

    @Mock
    private ITraceLoanPort iTraceLoanPort;

    @Test
    @Order(1)
    @DisplayName("Debería guardar correctamente un rastro de préstamo en NoSQL")
    void shouldSaveTraceLoanSuccessfully() {
        String loanId = "12345";
        Double amount = 1000.0;
        String transactionId = "txn-12345";
        traceLoanUseCase.saveTraceLoan(loanId, amount, transactionId);
        verify(iTraceLoanPort, times(1)).saveTraceLoan(Mockito.any());
    }

}
