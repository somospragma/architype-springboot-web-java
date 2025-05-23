package com.pragma.operationsandexecution.loansanddeposits.application.usecase;

import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.ApiException;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.Loan;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanPort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerBuilderPort;
import com.pragma.operationsandexecution.loansanddeposits.domain.services.LoanService;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.jpa.loan.LoanData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.MESSAGE_KEY_CREATION_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.MESSAGE_KEY_LOAN_FOUND_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.MESSAGE_KEY_LOAN_NOT_FOUND_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.MESSAGE_LOG_FORMAT_INCORRECT;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.MESSAGE_LOG_LOAN_EXIST_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.MESSAGE_LOG_LOAN_ID_NOT_FOUND;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.UTILITY_EXAMPLE_USER_NAME_VALUE;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.UTILITY_KEY_MESSAGE;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.VALUE_MESSAGE_LOAN_EXIST_ERROR;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.VALUE_MESSAGE_LOAN_ID_FORMAT_INCORRECT;
import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.VALUE_MESSAGE_LOAN_ID_NOT_FOUND;

/**
 * Caso de uso para crear un nuevo préstamo.
 * <p>
 * Esta clase encapsula la lógica necesaria para crear un préstamo utilizando el servicio {@link LoanService}.
 */
@RequiredArgsConstructor
public class LoanUseCase {

    private final TraceLoanUseCase traceLoanUseCase;
    private final ILoanPort iLoanPort;
    private final ILoggerBuilderPort iLoggerBuilderPort;
    private final AuthenticationUseCase authenticationUseCase;
    private final LoanService loanService;


    public static final double TRACE_LOAN_THRESHOLD = 10.5;

    /**
     * Ejecuta el caso de uso para crear un préstamo.
     *
     * @param loan el DTO que contiene los datos del préstamo a crear
     * @return Loan el objeto {@link Loan} creado
     */
    @Transactional
    public Loan createLoan(Loan loan, String transactionId) {
        authenticationUseCase.validateTokenUser(UTILITY_EXAMPLE_USER_NAME_VALUE, transactionId); /*El valor que envio por parametro como userName un ejemplo*/
        validateLoanExist(loan.getLoanId(), transactionId);
        var result = iLoanPort.save(loan);
        validateFields(loan, transactionId);
        validateSaveTraceLoan(loan, transactionId);
        loanService.validateLoanDate(loan);
        return result;
    }

    /**
     * Obtiene los detalles de un préstamo por su ID.
     *
     * @param loanId el ID del préstamo a buscar
     * @return el objeto {@link LoanData} encontrado
     */
    @Transactional
    public Loan findById(String loanId, String transactionId) {
        return iLoanPort.getLoanById(loanId).orElseThrow(() ->
                new ApiException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString(), MESSAGE_KEY_LOAN_NOT_FOUND_ERROR,
                        iLoggerBuilderPort.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_ID_NOT_FOUND,
                                MESSAGE_LOG_LOAN_ID_NOT_FOUND, transactionId)));
    }

    private void validateSaveTraceLoan(Loan loan, String transactionId) {
        if (loan.getAmount().equals(TRACE_LOAN_THRESHOLD)) {
            traceLoanUseCase.saveTraceLoan(loan.getLoanId(), loan.getAmount(), transactionId);
        }
    }

    private void validateLoanExist(String loanId, String transactionId) {
        iLoanPort.getLoanById(loanId).orElseThrow(() ->
            new ApiException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), MESSAGE_KEY_LOAN_FOUND_ERROR,
                iLoggerBuilderPort.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_EXIST_ERROR,
                        MESSAGE_LOG_LOAN_EXIST_ERROR, transactionId))
        );
    }

    private void validateFields(Loan loan, String transactionId) {
        if (!loan.getLoanId().matches("\\d+")) {
            throw new ApiException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), MESSAGE_KEY_CREATION_ERROR,
                    iLoggerBuilderPort.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_ID_FORMAT_INCORRECT,
                            MESSAGE_LOG_FORMAT_INCORRECT, transactionId));
        }
    }

}
