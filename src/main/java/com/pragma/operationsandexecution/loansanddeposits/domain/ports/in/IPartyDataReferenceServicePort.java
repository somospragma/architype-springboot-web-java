package com.pragma.operationsandexecution.loansanddeposits.domain.ports.in;

public interface IPartyDataReferenceServicePort {

    void validateTokenUser(String userName, String transactionId);

}
