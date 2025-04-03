package com.pragma.operationsandexecution.loansanddeposits.infrastructure.controllers;

import com.pragma.operationsandexecution.crosscutting.documentation.CommonApiResponses;
import com.pragma.operationsandexecution.crosscutting.exceptions.ResponseFactory;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerService;
import com.pragma.operationsandexecution.crosscutting.messages.IMessageService;
import com.pragma.operationsandexecution.loansanddeposits.application.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.application.response.LoanResponse;
import com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces.ICreateLoan;
import com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces.IGetLoanById;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import static com.pragma.operationsandexecution.crosscutting.constants.common.SwaggerConstants.SWAGGER_OPERATION_CREATE_LOGAN;
import static com.pragma.operationsandexecution.crosscutting.constants.common.SwaggerConstants.SWAGGER_OPERATION_GET_LOAN_BY_ID;
import static com.pragma.operationsandexecution.crosscutting.constants.infrastructure.InfrastructureConstants.*;

/**
 * Controlador REST para manejar operaciones relacionadas con préstamos.
 * <p>
 * Esta clase expone endpoints para crear y gestionar préstamos en la
 * aplicación.
 */
@RestController
@RequestMapping(PATH_LOAN_CONTROLLER)
@RequiredArgsConstructor
public class LoanController {

    private final ICreateLoan iCreateLoan;
    private final IGetLoanById iGetLoanById;
    /**
     * Fábrica de respuestas para centralizar la creación de respuestas exitosas y de error.
     */
    private final ResponseFactory responseFactory;
    /**
     * Servicio de mensajes para obtener textos internacionalizados a partir de llaves.
     */
    private final IMessageService messageService;
    private final ILoggerService loggerService;

    /**
     * Crea un nuevo préstamo en el sistema.
     * <p>
     * Este endpoint recibe los detalles de un préstamo y lo guarda en la base de datos.
     * </p>
     *
     * @param loanDTO el objeto {@link LoanDto} que contiene los detalles del préstamo a crear
     * @return una respuesta con el préstamo creado y el estado HTTP correspondiente
     */
    @PostMapping(PATH_LOAN_CONTROLLER_CREATE_LOAN)
    @Operation(summary = SWAGGER_OPERATION_CREATE_LOGAN)
    @CommonApiResponses
    public ResponseEntity<?> createLoan(@Valid @RequestBody LoanDto loanDTO, WebRequest request) {
        String transactionId = messageService.getTransactionId(request);
        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_CONTROLLER_CREATE_LOAN,
                CREATE_LOAN_PROCESS_STARTED, loanDTO);
        iCreateLoan.execute(loanDTO, transactionId);
        String successMessage = messageService.getMessage(MESSAGE_KEY_CREATION_SUCCESS);
        return ResponseEntity.ok(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanDTO, transactionId));
    }

    /**
     * Obtiene los detalles de un préstamo por su ID.
     * <p>
     * Este endpoint busca un préstamo en la base de datos utilizando su ID.
     * </p>
     *
     * @param loanId el ID del préstamo a buscar
     * @return una respuesta con los detalles del préstamo y el estado HTTP
     * correspondiente
     */
    @GetMapping(PATH_LOAN_CONTROLLER_GET_LOAN_BY_ID)
    @Operation(summary = SWAGGER_OPERATION_GET_LOAN_BY_ID)
    @CommonApiResponses
    public ResponseEntity<?> getLoanById(@PathVariable(name = PATH_VARIABLE_ID) String loanId, WebRequest request) {
        String transactionId = messageService.getTransactionId(request);
        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_CONTROLLER_GET_LOAN_BY_ID,
                GET_LOAN_BY_ID_PROCESS_STARTED, loanId);
        LoanResponse loanResponse = iGetLoanById.execute(loanId, transactionId);
        String successMessage = messageService.getMessage(MESSAGE_KEY_GET_SUCCESS);
        return ResponseEntity.ok(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId));
    }

}