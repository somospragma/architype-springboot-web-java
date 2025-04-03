package com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.implementation;

import com.pragma.operationsandexecution.loansanddeposits.application.mappers.ILoanDtoMapper;
import com.pragma.operationsandexecution.loansanddeposits.application.response.LoanResponse;
import com.pragma.operationsandexecution.loansanddeposits.application.usecases.loan.interfaces.IGetLoanById;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.ILoanSqlServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.services.LoanSqlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Caso de uso para obtener un préstamo por su ID.
 * <p>
 * Este caso de uso orquesta la obtención de un préstamo utilizando el servicio de dominio {@link LoanSqlService}.
 * </p>
 */
@RequiredArgsConstructor
@Component
public class GetLoanById implements IGetLoanById {

    private final ILoanSqlServicePort iLoanSqlServicePort;
    private final ILoanDtoMapper iLoanDtoMapper;

    /**
     * Ejecuta el caso de uso para obtener un préstamo por su ID.
     *
     * @param loanId el ID del préstamo a obtener
     * @return el préstamo encontrado
     */
    public LoanResponse execute(String loanId, String transactionId) {
        return iLoanDtoMapper.loanSqlModelToLoanResponse(iLoanSqlServicePort.getLoanById(loanId, transactionId));
    }
}
