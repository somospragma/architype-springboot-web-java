package com.pragma.loansanddeposits;

import com.pragma.loansanddeposits.exceptions.ApiException;
import com.pragma.loansanddeposits.port.out.ILoanPort;
import com.pragma.loansanddeposits.port.out.ILoggerBuilderPort;
import com.pragma.loansanddeposits.services.LoanService;
import com.pragma.loansanddeposits.usecase.AuthenticationUseCase;
import com.pragma.loansanddeposits.usecase.LoanUseCase;
import com.pragma.loansanddeposits.usecase.TraceLoanUseCase;
import com.pragma.loansanddeposits.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith({MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class GetLoanUseCaseByIdTest {

    @InjectMocks
    private LoanUseCase loanUseCase;

    @Mock
    private ILoanPort iLoan;
    @Mock
    private TraceLoanUseCase traceLoanUseCase;
    @Mock
    private ILoggerBuilderPort iLoggerBuilderPort;
    @Mock
    private AuthenticationUseCase authenticationUseCase;
    @Mock
    private LoanService loanService;


    @Test
    @DisplayName("Debería retornar el préstamo cuando existe")
    void shouldReturnLoanWhenExists() {
        var loanId = "123";
        var loan = TestUtil.buildLoan();
        loan.setId(loanId);
        Mockito.when(iLoan.getLoanById(loanId)).thenReturn(Optional.of(loan));
        var result = loanUseCase.findById(loanId, "tx-1");
        assertEquals(loanId, result.getId());
    }

    @Test
    @DisplayName("Debería lanzar ApiException cuando el préstamo no existe")
    void shouldThrowExceptionWhenLoanNotFound() {
        Assertions.assertThrows(ApiException.class, () -> {
            loanUseCase.findById("999", "tx-2");
        });
    }

    @Test
    @Order(1)
    @DisplayName("Debería crear un préstamo correctamente y guardar el rastro cuando el monto sea 10.5")
    void createLoanCreateLoanWithTrace() {
        var loan = TestUtil.buildLoan();
        loan.setAmount(10.5);
        String transactionId = "txn-456";
        Mockito.lenient().when(iLoan.save(loan)).thenReturn(loan);
        var result = loanUseCase.createLoan(loan, transactionId);
        assertNotNull(result);
        assertEquals(result, loan);
        Mockito.verify(traceLoanUseCase).saveTraceLoan(loan.getId(), loan.getAmount(), transactionId);

    }

    @Test
    @Order(2)
    @DisplayName("Debería crear un préstamo sin guardar rastro cuando el monto no sea 10.5")
    void createLoanCreateLoanNotTrace() {
        var loan = TestUtil.buildLoan();
        String transactionId = "txn-789";
        loan.setAmount(15.0);
        Assertions.assertDoesNotThrow(() -> loanUseCase.createLoan(loan, transactionId));
        Mockito.verify(iLoan).save(loan);

    }

    @Test
    @DisplayName("Debería crear un préstamo correctamente")
    void shouldCreateLoanSuccessfully() {
        var loan = TestUtil.buildLoan();
        var transactionId = "tx-3";
        Mockito.doNothing().when(authenticationUseCase).validateTokenUser(Mockito.anyString(), Mockito.eq(transactionId));
        Mockito.when(iLoan.getLoanById(loan.getId())).thenReturn(Optional.empty());
        Mockito.when(iLoan.save(loan)).thenReturn(loan);
        Mockito.doNothing().when(loanService).validateLoanDate(loan);
        // No lanza excepción en validateFields ni validateSaveTraceLoan

        var result = loanUseCase.createLoan(loan, transactionId);
        Assertions.assertEquals(loan.getId(), result.getId());
    }

    @Test
    @DisplayName("Debería lanzar ApiException si el préstamo ya existe")
    void shouldThrowExceptionIfLoanExists() {
        var loan = TestUtil.buildLoan();
        var transactionId = "tx-4";
        Mockito.doNothing().when(authenticationUseCase).validateTokenUser(Mockito.anyString(), Mockito.eq(transactionId));
        Mockito.when(iLoan.getLoanById(loan.getId())).thenReturn(Optional.of(loan));
        Assertions.assertThrows(ApiException.class, () -> loanUseCase.createLoan(loan, transactionId));
    }

    @Test
    @DisplayName("Debería guardar el rastro si el monto es 10.5")
    void shouldSaveTraceWhenAmountIsThreshold() {
        var loan = TestUtil.buildLoan();
        loan.setAmount(10.5);
        var transactionId = "tx-5";
        Mockito.doNothing().when(authenticationUseCase).validateTokenUser(Mockito.anyString(), Mockito.eq(transactionId));
        Mockito.when(iLoan.getLoanById(loan.getId())).thenReturn(Optional.empty());
        Mockito.when(iLoan.save(loan)).thenReturn(loan);
        Mockito.doNothing().when(loanService).validateLoanDate(loan);
        Mockito.doNothing().when(traceLoanUseCase).saveTraceLoan(loan.getId(), loan.getAmount(), transactionId);

        loanUseCase.createLoan(loan, transactionId);
        Mockito.verify(traceLoanUseCase).saveTraceLoan(loan.getId(), loan.getAmount(), transactionId);
    }

}
