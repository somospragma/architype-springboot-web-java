package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.implementation;

import com.pragma.operationsandexecution.loansanddeposits.application.dto.LoanDto;
import com.pragma.operationsandexecution.loansanddeposits.application.mappers.ILoanDtoMapper;
import com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces.ICreateLoan;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.LoanSqlModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanNoSqlServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanSqlServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.services.LoanSqlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para crear un nuevo préstamo.
 * <p>
 * Esta clase encapsula la lógica necesaria para crear un préstamo utilizando el servicio {@link LoanSqlService}.
 */
@RequiredArgsConstructor
@Component
public class CreateLoan implements ICreateLoan {

    private final ILoanSqlServicePort iLoanSqlServicePort;
    private final ILoanNoSqlServicePort iLoanNoSqlServicePort;
    private final ILoanDtoMapper iLoanDtoMapper;

    /**
     * Ejecuta el caso de uso para crear un préstamo.
     *
     * @param loanDTO el DTO que contiene los datos del préstamo a crear
     */
    @Override
    public void execute(LoanDto loanDTO, String transactionId) {
        LoanSqlModel loanSQLModel = iLoanDtoMapper.loanDtoToLoanSqlModel(loanDTO);
        iLoanSqlServicePort.createLoan(loanSQLModel, transactionId);
        validateSaveTraceLoan(loanSQLModel, transactionId);
    }

    private void validateSaveTraceLoan(LoanSqlModel loanSQLModel, String transactionId) {
        if (loanSQLModel.getAmount().equals(10.5)) {
            iLoanNoSqlServicePort.saveTraceLoan(loanSQLModel.getLoanId(), loanSQLModel.getAmount(), transactionId);
        }
    }

}
