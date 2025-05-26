package entrypoint.rest;

import entrypoint.rest.documentation.CommonApiResponses;
import entrypoint.rest.dto.ApiResponseDto;
import entrypoint.rest.dto.LoanDto;
import entrypoint.rest.dto.ResponseFactory;
import entrypoint.rest.mappers.ILoanDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import com.pragma.loansanddeposits.model.Loan;
import com.pragma.loansanddeposits.port.out.ILoggerPort;
import com.pragma.loansanddeposits.usecase.LoanUseCase;
import com.pragma.loansanddeposits.usecase.MessageUseCase;
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

import static constants.InfrastructureConstants.CREATE_LOAN_PROCESS_STARTED;
import static constants.InfrastructureConstants.GET_LOAN_BY_ID_PROCESS_STARTED;
import static constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_CONTROLLER_CREATE_LOAN;
import static constants.InfrastructureConstants.LAYER_INFRASTRUCTURE_CONTROLLER_GET_LOAN_BY_ID;
import static constants.InfrastructureConstants.MESSAGE_KEY_CREATION_SUCCESS;
import static constants.InfrastructureConstants.MESSAGE_KEY_GET_SUCCESS;
import static constants.InfrastructureConstants.PATH_LOAN_CONTROLLER;
import static constants.InfrastructureConstants.PATH_VARIABLE_ID;
import static constants.SwaggerConstants.SWAGGER_OPERATION_CREATE_LOGAN;
import static constants.SwaggerConstants.SWAGGER_OPERATION_GET_LOAN_BY_ID;

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
