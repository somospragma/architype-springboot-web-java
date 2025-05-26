package com.pragma.operationsandexecution.loansanddeposits.infrastructure.controllers;

import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.ApiResponseDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.ResponseFactory;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import com.pragma.operationsandexecution.loansanddeposits.application.usecase.MessageUseCase;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.LoanController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanUseCaseControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private ILoanPort iLoan;
    @Mock
    private ResponseFactory responseFactory;
    @Mock
    private MessageUseCase messageUseCase;
    @Mock
    private ILoggerPort iLoggerPort;

    /**
     * Prueba para el endpoint POST /loans (crear préstamo).
     */
    @Test
    @Order(1)
    @DisplayName("Prueba exitosa de creación de préstamo")
    void createLoanSuccess() {
        // Arrange
        LoanDto loanDTO = LoanDto.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        var loan = Loan.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
        WebRequest mockRequest = mock(WebRequest.class);
        String transactionId = "transaction-123";
        String successMessage = "Préstamo creado exitosamente";

        when(messageUseCase.getTransactionId(mockRequest)).thenReturn(transactionId);
        when(messageUseCase.getMessage(anyString())).thenReturn(successMessage);
        when(responseFactory.createSuccessResponse(any(), any(), any(), any()))
                .thenReturn(new ApiResponseDto<>("200", "Success", loanDTO, transactionId));

        // Act
        ResponseEntity<?> response = loanController.createLoan(loanDTO, mockRequest);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(messageUseCase).getTransactionId(mockRequest);
        verify(iLoan).save(loan);
        verify(responseFactory).createSuccessResponse(HttpStatus.OK), eq(successMessage), eq(loanDTO), eq(transactionId);
    }

    /**
     * Prueba para el endpoint GET /loans/{id} (consultar préstamo por ID exitoso).
     */
    @Test
    @Order(2)
    @DisplayName("Debería devolver un préstamo existente por ID")
    void getLoanById() {
        // Arrange: Configuración inicial
        String loanId = "123";
        String transactionId = "txn-456";
        Loan loanResponse = Loan.builder()
                .id("123")
                .amount(1000.0)
                .interestRate(5.0)
                .startDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();

        String successMessage = "Préstamo obtenido exitosamente";

        WebRequest mockRequest = mock(WebRequest.class);

        when(messageUseCase.getTransactionId(mockRequest)).thenReturn(transactionId);
        when(iLoan.getLoanById(loanId)).thenReturn(Optional.of(loanResponse));
        when(messageUseCase.getMessage(anyString())).thenReturn(successMessage);
        when(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId))
                .thenReturn(new ApiResponseDto<>("200", "Success", loanResponse, transactionId));

        // Act: Ejecución del método que estamos probando
        ResponseEntity<?> response = loanController.getLoanById(loanId, mockRequest);

        // Assert: Verificación de resultados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        verify(iLoggerPort).logInfo(transactionId, eq("infrastructure.controller.LoanController.getLoanById"),
                eq("Se inició la obtención de un prestamo"), eq(loanId));
        verify(iLoan).getLoanById(loanId);
        verify(messageUseCase).getTransactionId(mockRequest);
        verify(messageUseCase).getMessage(anyString());
        verify(responseFactory).createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId);
    }

}
