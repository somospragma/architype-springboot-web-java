package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.secrets.dto;

public class EntrustCredentials {

    private String applicationId;
    private String sharedSecret;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    @Override
    public String toString() {
        return "EntrustCredentials{" +
                "applicationId='" + applicationId + '\'' +
                ", sharedSecret='" + sharedSecret + '\'' +
                '}';
    }
}
