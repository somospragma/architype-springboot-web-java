package com.pragma.operationsandexecution.loansanddeposits.infrastructure.dataproviders.restclients.response;

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
