package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.domain.model.PartyDataReference;
import com.pragma.operationsandexecution.loansanddeposits.domain.model.response.PartyDataReferenceResponseModel;

public interface IAuthenticationPort {

    PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId);

    void createUserEntrust(PartyDataReference[] partyDataReference, String transactionId);

}
