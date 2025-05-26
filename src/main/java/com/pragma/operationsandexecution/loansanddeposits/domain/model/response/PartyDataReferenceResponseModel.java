package com.pragma.operationsandexecution.loansanddeposits.domain.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PartyDataReferenceResponseModel {

    private String username;
    private String firstName;
    private String lastName;

    public String getUsername() {
        return username;
    }



    @Override
    public String toString() {
        return "PartyDataReferenceResponseModel{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
