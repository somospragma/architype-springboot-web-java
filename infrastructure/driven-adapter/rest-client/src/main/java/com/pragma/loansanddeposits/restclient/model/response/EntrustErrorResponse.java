package com.pragma.loansanddeposits.restclient.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrustErrorResponse {
    private String code;
    private String message;
    private String description;
}
