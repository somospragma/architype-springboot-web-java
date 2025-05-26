package com.pragma.loansanddeposits.port.out;

import com.pragma.loansanddeposits.model.PartyDataReference;
import com.pragma.loansanddeposits.model.response.PartyDataReferenceResponseModel;

public interface IAuthenticationPort {

    PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId);

    void createUserEntrust(PartyDataReference[] partyDataReference, String transactionId);

}
