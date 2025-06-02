package com.pragma.loansanddeposits.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PartyDataReference {

    private String username;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "PartyDataReferenceModel{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
