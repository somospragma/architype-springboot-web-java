package com.pragma.loansanddeposits;


import com.pragma.loansanddeposits.model.PartyDataReference;
import com.pragma.loansanddeposits.model.response.PartyDataReferenceResponseModel;
import com.pragma.loansanddeposits.port.out.IAuthenticationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("local")
@Primary
@RequiredArgsConstructor
public class EntrustAuthenticationLocalAdapter implements IAuthenticationPort {


    public PartyDataReferenceResponseModel getTokenAuthenticationEntrust(String userName, String transactionId) {
        return new PartyDataReferenceResponseModel("local", "test", "last");
    }

    public void createUserEntrust(PartyDataReference[] partyDataReferenceModel, String transactionId) {

        log.info("Creating user in Entrust with transactionId: {}", transactionId);

    }

}
