package com.pragma.loansanddeposits.usecase;


import com.pragma.loansanddeposits.exceptions.ApiException;
import com.pragma.loansanddeposits.model.PartyDataReference;
import com.pragma.loansanddeposits.port.out.IAuthenticationPort;
import com.pragma.loansanddeposits.port.out.ILoggerBuilderPort;
import lombok.RequiredArgsConstructor;

import static com.pragma.loansanddeposits.constant.DomainConstants.MESSAGE_KEY_VALIDATE_TOKEN_USER_ERROR;
import static com.pragma.loansanddeposits.constant.DomainConstants.MESSAGE_LOG_USER_TOKEN_GET_ERROR;
import static com.pragma.loansanddeposits.constant.DomainConstants.UTILITY_EXAMPLE_LAST_NAME_VALUE;
import static com.pragma.loansanddeposits.constant.DomainConstants.UTILITY_EXAMPLE_NAME_VALUE;
import static com.pragma.loansanddeposits.constant.DomainConstants.UTILITY_EXAMPLE_USER_NAME_2_VALUE;
import static com.pragma.loansanddeposits.constant.DomainConstants.UTILITY_KEY_MESSAGE;
import static com.pragma.loansanddeposits.constant.DomainConstants.VALUE_MESSAGE_USER_TOKEN_GET_ERROR;

@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final ILoggerBuilderPort iLoggerBuilderPort;
    private final IAuthenticationPort iAuthenticationPort;

    public void validateTokenUser(String userName, String transactionId) {
        var partyDataReferenceResponseModel = iAuthenticationPort.getTokenAuthenticationEntrust(userName, transactionId);
        if (partyDataReferenceResponseModel.getUsername() == null) { /*Esta validación solo es un ejemplo*/
            throw new ApiException(401, "401 UNAUTHORIZED", MESSAGE_KEY_VALIDATE_TOKEN_USER_ERROR,
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
