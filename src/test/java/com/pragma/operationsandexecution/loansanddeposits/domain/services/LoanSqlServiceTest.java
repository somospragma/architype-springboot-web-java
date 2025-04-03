package com.pragma.operationsandexecution.loansanddeposits.domain.services;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerBuilder;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.IPartyDataReferenceServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanSqlPersistencePort;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.pragma.operationsandexecution.crosscutting.constants.domain.DomainConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanSqlServiceTest {

    @InjectMocks
    private LoanSqlService loanSqlService;

    @Mock
    private ILoanSqlPersistencePort iLoanSQLPersistencePort;
    @Mock
    private IPartyDataReferenceServicePort iPartyDataReferenceServicePort;
    @Mock
    private ILoggerBuilder iLoggerBuilder;

    private LoanSqlModel loanSqlModel;

    @BeforeEach
    void setUp() {
        loanSqlModel = new LoanSqlModel();
        loanSqlModel.setLoanId("123");
        loanSqlModel.setAmount(1000.0);
        loanSqlModel.setInterestRate(5.0);
        loanSqlModel.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanSqlModel.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Test
    @Order(1)
    @DisplayName("Debería crear un préstamo exitosamente cuando no existe previamente")
    void shouldCreateLoanSuccessfully() {
        // Arrange
        String transactionId = "txn-123";
        when(iLoanSQLPersistencePort.getLoanById(loanSqlModel.getLoanId())).thenReturn(Optional.empty());

        // Act
        assertDoesNotThrow(() -> loanSqlService.createLoan(loanSqlModel, transactionId));

        // Assert
        verify(iPartyDataReferenceServicePort).validateTokenUser("miUserName", transactionId);
        verify(iLoanSQLPersistencePort).save(loanSqlModel);
        verifyNoMoreInteractions(iLoggerBuilder);
    }

    @Test
    @Order(2)
    @DisplayName("Debería lanzar una excepción si el préstamo ya existe")
    void shouldThrowExceptionIfLoanExists() {
        // Arrange
        String transactionId = "txn-456";
        when(iLoanSQLPersistencePort.getLoanById(loanSqlModel.getLoanId())).thenReturn(Optional.of(loanSqlModel));

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> loanSqlService.createLoan(loanSqlModel, transactionId));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        verify(iLoggerBuilder).buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_EXIST_ERROR,
                MESSAGE_LOG_LOAN_EXIST_ERROR, transactionId);
    }

    @Test
    @Order(3)
    @DisplayName("Debería lanzar una excepción si el formato del Loan ID es incorrecto")
    void shouldThrowExceptionIfLoanIdFormatIsInvalid() {
        // Arrange
        String transactionId = "12341";

        LoanSqlModel loanSqlModelInvalidId = new LoanSqlModel();
        loanSqlModelInvalidId.setLoanId("123 +");

        when(iLoanSQLPersistencePort.getLoanById(loanSqlModel.getLoanId())).thenReturn(Optional.empty());

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> loanSqlService.createLoan(loanSqlModelInvalidId, transactionId));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
        verify(iLoggerBuilder).buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_ID_FORMAT_INCORRECT,
                MESSAGE_LOG_FORMAT_INCORRECT, transactionId);
    }


    @Test
    @Order(1)
    @DisplayName("Debería devolver un préstamo existente")
    void shouldReturnExistingLoan() {
        // Arrange
        String transactionId = "txn-123";
        when(iLoanSQLPersistencePort.getLoanById(loanSqlModel.getLoanId())).thenReturn(Optional.of(loanSqlModel));

        // Act
        LoanSqlModel result = loanSqlService.getLoanById(loanSqlModel.getLoanId(), transactionId);

        // Assert
        assertNotNull(result);
        assertEquals(loanSqlModel.getLoanId(), result.getLoanId());
        verify(iLoanSQLPersistencePort, times(1)).getLoanById(loanSqlModel.getLoanId());
        verifyNoInteractions(iLoggerBuilder);
    }

    @Test
    @Order(2)
    @DisplayName("Debería lanzar una excepción si el préstamo no existe")
    void shouldThrowExceptionWhenLoanNotFound() {
        // Arrange
        String transactionId = "txn-456";
        String nonExistentLoanId = "999";
        when(iLoanSQLPersistencePort.getLoanById(nonExistentLoanId)).thenReturn(Optional.empty());

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> loanSqlService.getLoanById(nonExistentLoanId, transactionId));

        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
        assertEquals(MESSAGE_KEY_LOAN_NOT_FOUND_ERROR, exception.getMessageKey());
        verify(iLoggerBuilder, times(1)).buildErrorWithLogWarning(
                UTILITY_KEY_MESSAGE,
                VALUE_MESSAGE_LOAN_ID_NOT_FOUND,
                MESSAGE_LOG_LOAN_ID_NOT_FOUND,
                transactionId
        );
        verify(iLoanSQLPersistencePort, times(1)).getLoanById(nonExistentLoanId);
    }

}