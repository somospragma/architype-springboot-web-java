package com.pragma.operationsandexecution.loansanddeposits.domain.ports.out;

import com.pragma.operationsandexecution.loansanddeposits.domain.models.PartyDataReferenceModel;
import com.pragma.operationsandexecution.loansanddeposits.domain.models.response.PartyDataReferenceResponseModel;

public interface IPartyDataReferenceRestClientPort {

    PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId);
    void createUserEntrust(PartyDataReferenceModel[] partyDataReferenceModel, String transactionId);

}
