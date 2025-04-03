package com.pragma.operationsandexecution.loansanddeposits.domain.services;

import com.pragma.operationsandexecution.crosscutting.exceptions.ApiException;
import com.pragma.operationsandexecution.crosscutting.logging.ILoggerBuilder;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.PartyDataReferenceModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.response.PartyDataReferenceResponseModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.in.IPartyDataReferenceServicePort;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.IPartyDataReferenceRestClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.pragma.operationsandexecution.crosscutting.constants.domain.DomainConstants.*;

@RequiredArgsConstructor
@Service
public class PartyDataReferenceService implements IPartyDataReferenceServicePort {

    private final ILoggerBuilder iLoggerBuilder;
    private final IPartyDataReferenceRestClientPort iPartyDataReferenceRestClientPort;

    public void validateTokenUser(String userName, String transactionId) {
        PartyDataReferenceResponseModel partyDataReferenceResponseModel = iPartyDataReferenceRestClientPort.getTokenAuthenticationEntrust(userName, transactionId);
        if (partyDataReferenceResponseModel.getUsername() == null) { /*Esta validación solo es un ejemplo*/
            throw new ApiException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.toString(), MESSAGE_KEY_VALIDATE_TOKEN_USER_ERROR,
                    iLoggerBuilder.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_USER_TOKEN_GET_ERROR,
                            MESSAGE_LOG_USER_TOKEN_GET_ERROR, transactionId));
        }
        iPartyDataReferenceRestClientPort.createUserEntrust(buildPartyDataReferenceModel(), transactionId); /*Este codigo que se ejecutará solo se hará para dar ejemplo de como se podria realizar dicha implementación*/
    }

    private PartyDataReferenceModel[] buildPartyDataReferenceModel() {
        PartyDataReferenceModel[] partyDataReferenceModelArray = new PartyDataReferenceModel[1];
        PartyDataReferenceModel partyDataReferenceModel = new PartyDataReferenceModel();
        partyDataReferenceModel.setUsername(UTILITY_EXAMPLE_USER_NAME_2_VALUE);
        partyDataReferenceModel.setFirstName(UTILITY_EXAMPLE_NAME_VALUE);
        partyDataReferenceModel.setLastName(UTILITY_EXAMPLE_LAST_NAME_VALUE);
        partyDataReferenceModelArray[0] = partyDataReferenceModel;
        return partyDataReferenceModelArray;
    }

}
