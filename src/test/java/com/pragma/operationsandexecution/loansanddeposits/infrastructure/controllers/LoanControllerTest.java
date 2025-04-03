package com.pragma.operationsandexecution.loansanddeposits.infrastructure.controllers;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiResponseDto;
import com.pragma.operationsandexecution.crosscutting.exceptions.ResponseFactory;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.crosscutting.messages.IMessageService;
import com.pragma.operationsandexecution.loansanddeposits.application.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.application.response.LoanResponse;
import com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces.ICreateLoan;
import com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces.IGetLoanById;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class LoanControllerTest {

    @InjectMocks
    private LoanController loanController;

    @Mock
    private ICreateLoan iCreateLoan;
    @Mock
    private IGetLoanById iGetLoanById;
    @Mock
    private ResponseFactory responseFactory;
    @Mock
    private IMessageService iMessageService;
    @Mock
    private ILoggerService iLoggerService;

    /**
     * Prueba para el endpoint POST /loans (crear préstamo).
     */
    @Test
    @Order(1)
    @DisplayName("Prueba exitosa de creación de préstamo")
    void createLoanSuccess() {
        // Arrange
        LoanDto loanDTO = new LoanDto();
        loanDTO.setLoanId("123");
        loanDTO.setAmount(1000.0);
        loanDTO.setInterestRate(5.0);
        loanDTO.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanDTO.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        WebRequest mockRequest = mock(WebRequest.class);
        String transactionId = "transaction-123";
        String successMessage = "Préstamo creado exitosamente";

        when(iMessageService.getTransactionId(mockRequest)).thenReturn(transactionId);
        when(iMessageService.getMessage(anyString())).thenReturn(successMessage);
        when(responseFactory.createSuccessResponse(any(), any(), any(), any()))
                .thenReturn(new ApiResponseDto<>("200", "Success", loanDTO, transactionId));

        // Act
        ResponseEntity<?> response = loanController.createLoan(loanDTO, mockRequest);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(iMessageService).getTransactionId(mockRequest);
        verify(iCreateLoan).execute(loanDTO, transactionId);
        verify(responseFactory).createSuccessResponse(eq(HttpStatus.OK), eq(successMessage), eq(loanDTO), eq(transactionId));
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
        LoanResponse loanResponse = new LoanResponse();
        loanResponse.setLoanId("123");
        loanResponse.setAmount(1000.0);
        loanResponse.setInterestRate(5.0);
        loanResponse.setStartDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loanResponse.setEndDate(LocalDate.parse("2025-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        String successMessage = "Préstamo obtenido exitosamente";

        WebRequest mockRequest = mock(WebRequest.class);

        when(iMessageService.getTransactionId(mockRequest)).thenReturn(transactionId);
        when(iGetLoanById.execute(loanId, transactionId)).thenReturn(loanResponse);
        when(iMessageService.getMessage(anyString())).thenReturn(successMessage);
        when(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId))
                .thenReturn(new ApiResponseDto<>("200", "Success", loanResponse, transactionId));

        // Act: Ejecución del método que estamos probando
        ResponseEntity<?> response = loanController.getLoanById(loanId, mockRequest);

        // Assert: Verificación de resultados esperados
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCode().value());
        verify(iLoggerService).logInfo(eq(transactionId), eq("infrastructure.controller.LoanController.getLoanById"),
                eq("Se inició la obtención de un prestamo"), eq(loanId));
        verify(iGetLoanById).execute(loanId, transactionId);
        verify(iMessageService).getTransactionId(mockRequest);
        verify(iMessageService).getMessage(anyString());
        verify(responseFactory).createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId);
    }

}