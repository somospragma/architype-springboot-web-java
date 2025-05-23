package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.dto;

import lombok.Getter;

@Getter
public class PartyDataReferenceDto {

    private String username;
    private String firstName;
    private String lastName;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "PartyDataReferenceDto{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

}
