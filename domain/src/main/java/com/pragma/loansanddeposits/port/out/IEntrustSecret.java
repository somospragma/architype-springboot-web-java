package com.pragma.loansanddeposits.port.out;

import com.pragma.loansanddeposits.model.EntrustCredentials;

public interface IEntrustSecret {

    EntrustCredentials retrieveAndParseSecret(String secretName);

}
