package com.pragma.operationsandexecution.loansanddeposits.application.usecase;

import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.ApiException;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.ILoggerBuilderPort;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.PartyDataReference;
import com.pragma.operationsandexecution.loansanddeposits.domain.ports.out.IAuthenticationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static com.pragma.operationsandexecution.loansanddeposits.domain.constants.DomainConstants.*;

@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final ILoggerBuilderPort iLoggerBuilderPort;
    private final IAuthenticationPort iAuthenticationPort;

    public void validateTokenUser(String userName, String transactionId) {
        var partyDataReferenceResponseModel = iAuthenticationPort.getTokenAuthenticationEntrust(userName, transactionId);
        if (partyDataReferenceResponseModel.getUsername() == null) { /*Esta validación solo es un ejemplo*/
            throw new ApiException(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.toString(), MESSAGE_KEY_VALIDATE_TOKEN_USER_ERROR,
                    iLoggerBuilderPort.buildErrorWithLogWarning(UTILITY_KEY_MESSAGE, VALUE_MESSAGE_USER_TOKEN_GET_ERROR,
                            MESSAGE_LOG_USER_TOKEN_GET_ERROR, transactionId));
        }
        iAuthenticationPort.createUserEntrust(buildPartyDataReferenceModel(), transactionId); /*Este codigo que se ejecutará solo se hará para dar ejemplo de como se podria realizar dicha implementación*/
    }

    private PartyDataReference[] buildPartyDataReferenceModel() {
        PartyDataReference[] partyDataReferenceModelArray = new PartyDataReference[1];
        PartyDataReference partyDataReference = new PartyDataReference();
        partyDataReference.setUsername(UTILITY_EXAMPLE_USER_NAME_2_VALUE);
        partyDataReference.setFirstName(UTILITY_EXAMPLE_NAME_VALUE);
        partyDataReference.setLastName(UTILITY_EXAMPLE_LAST_NAME_VALUE);
        partyDataReferenceModelArray[0] = partyDataReference;
        return partyDataReferenceModelArray;
    }

}
