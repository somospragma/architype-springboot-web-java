package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.secrets;

import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.secrets.dto.EntrustCredentials;

public interface IEntrustSecret {

    EntrustCredentials retrieveAndParseSecret(String secretName);

}
