package com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest;

import com.pragma.operationsandexecution.loansanddeposits.application.usecase.LoanUseCase;
import com.pragma.operationsandexecution.loansanddeposits.application.usecase.MessageUseCase;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerPort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.documentation.CommonApiResponses;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.ApiResponseDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.dto.ResponseFactory;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.entrypoint.rest.mappers.ILoanDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.CREATE_LOAN_PROCESS_STARTED;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.GET_LOAN_BY_ID_PROCESS_STARTED;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_CONTROLLER_CREATE_LOAN;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_CONTROLLER_GET_LOAN_BY_ID;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.MESSAGE_KEY_CREATION_SUCCESS;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.MESSAGE_KEY_GET_SUCCESS;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.PATH_LOAN_CONTROLLER;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.InfrastructureConstants.PATH_VARIABLE_ID;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_OPERATION_CREATE_LOGAN;
import static com.pragma.operationsandexecution.loansanddeposits.infrastructure.constants.SwaggerConstants.SWAGGER_OPERATION_GET_LOAN_BY_ID;

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

    private final LoanUseCase loanUseCase;
    private final ILoanDtoMapper iLoanDtoMapper;
    /**
     * Fábrica de respuestas para centralizar la creación de respuestas exitosas y de error.
     */
    private final ResponseFactory responseFactory;
    /**
     * Servicio de mensajes para obtener textos internacionalizados a partir de llaves.
     */
    private final MessageUseCase messageUseCase;
    private final ILoggerPort loggerService;

    /**
     * Crea un nuevo préstamo en el sistema.
     * <p>
     * Este endpoint recibe los detalles de un préstamo y lo guarda en la base de datos.
     * </p>
     *
     * @param loanDTO el objeto {@link LoanDto} que contiene los detalles del préstamo a crear
     * @return una respuesta con el préstamo creado y el estado HTTP correspondiente
     */
    @PostMapping
    @Operation(summary = SWAGGER_OPERATION_CREATE_LOGAN)
    @CommonApiResponses
    public ResponseEntity<ApiResponseDto<Loan>> createLoan(@Valid @RequestBody LoanDto loanDTO, WebRequest request) {
        var transactionId = messageUseCase.getTransactionId(request);
        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_CONTROLLER_CREATE_LOAN,
                CREATE_LOAN_PROCESS_STARTED, loanDTO);
        var result = loanUseCase.createLoan(iLoanDtoMapper.loanDtoToLoan(loanDTO), transactionId);
        var successMessage = messageUseCase.getMessage(MESSAGE_KEY_CREATION_SUCCESS);
        return ResponseEntity.ok(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, result, transactionId));
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
    @GetMapping("/{id}")
    @Operation(summary = SWAGGER_OPERATION_GET_LOAN_BY_ID)
    @CommonApiResponses
    public ResponseEntity<ApiResponseDto<Loan>> getLoanById(@PathVariable(name = PATH_VARIABLE_ID) String loanId, WebRequest request) {
        String transactionId = messageUseCase.getTransactionId(request);
        loggerService.logInfo(transactionId, LAYER_INFRASTRUCTURE_CONTROLLER_GET_LOAN_BY_ID,
                GET_LOAN_BY_ID_PROCESS_STARTED, loanId);
        var loanResponse = loanUseCase.findById(loanId, transactionId);
        String successMessage = messageUseCase.getMessage(MESSAGE_KEY_GET_SUCCESS);
        return ResponseEntity.ok(responseFactory.createSuccessResponse(HttpStatus.OK, successMessage, loanResponse, transactionId));
    }

}
