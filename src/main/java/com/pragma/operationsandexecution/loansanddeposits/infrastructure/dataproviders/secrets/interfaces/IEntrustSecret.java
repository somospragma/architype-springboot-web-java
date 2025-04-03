package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.interfaces;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.dto.EntrustCredentials;

public interface IEntrustSecret {

    EntrustCredentials retrieveAndParseSecret(String secretName);

}
