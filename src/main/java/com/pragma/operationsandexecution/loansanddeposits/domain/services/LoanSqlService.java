package com.pragma.operationsandexecution.loansanddeposits.domain.services;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerBuilder;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanSqlServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.IPartyDataReferenceServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoanSqlPersistencePort;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.database.entitymanagers.entities.LoanSqlEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.pragma.operationsandexecution.crosscutting.constants.domain.DomainConstants.*;

/**
 * Servicio para manejar operaciones relacionadas con préstamos.
 */
@RequiredArgsConstructor
@Service
public class LoanSqlService implements ILoanSqlServicePort {

    private final ILoanSqlPersistencePort iLoanSQLPersistencePort;
    private final IPartyDataReferenceServicePort iPartyDataReferenceServicePort;
    private final ILoggerBuilder iLoggerBuilder;

    /**
     * Crea un nuevo préstamo.
     *
     * @param loanSqlModel el que contiene los detalles del préstamo a crear
     */
    @Transactional
    public void createLoan(LoanSqlModel loanSqlModel, String transactionId) {
        iPartyDataReferenceServicePort.validateTokenUser(UTILITY_EXAMPLE_USER_NAME_VALUE, transactionId); /*El valor que envio por parametro como userName un ejemplo*/
        validateLoanExist(loanSqlModel.getLoanId(), transactionId);

        iLoanSQLPersistencePort.save(loanSqlModel);
        validateFields(loanSqlModel, transactionId);
    }

    private void validateLoanExist(String loanId, String transactionId) {
        LoanSqlModel loan = iLoanSQLPersistencePort.getLoanById(loanId).orElse(null);
        if (loan != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), MESSAGE_KEY_LOAN_FOUND_ERROR,
                    iLoggerBuilder.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_EXIST_ERROR,
                            MESSAGE_LOG_LOAN_EXIST_ERROR, transactionId));
        }
    }

    private void validateFields(LoanSqlModel loanSQLModel, String transactionId) {
        if (!loanSQLModel.getLoanId().matches("\\d+")) {
            throw new ApiException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.toString(), MESSAGE_KEY_CREATION_ERROR,
                    iLoggerBuilder.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_ID_FORMAT_INCORRECT,
                            MESSAGE_LOG_FORMAT_INCORRECT, transactionId));
        }
    }

    /**
     * Obtiene los detalles de un préstamo por su ID.
     *
     * @param loanId el ID del préstamo a buscar
     * @return el objeto {@link LoanSqlEntity} encontrado
     */
    @Transactional
    public LoanSqlModel getLoanById(String loanId, String transactionId) {
        LoanSqlModel loan = iLoanSQLPersistencePort.getLoanById(loanId).orElse(null);
        if (loan == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.toString(), MESSAGE_KEY_LOAN_NOT_FOUND_ERROR,
                    iLoggerBuilder.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_LOAN_ID_NOT_FOUND,
                            MESSAGE_LOG_LOAN_ID_NOT_FOUND, transactionId));
        }
        return loan;
    }

}