package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.generic;

import com.pragma.operationsandexecution.loansanddeposits.domain.exceptions.StandardRestException;
import com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.model.HttpRequestConfig;
import org.springframework.http.ResponseEntity;


public interface IGenericRestClientPort {

    <T, R, E> ResponseEntity<T> sendRequestAndReceiveResponse(HttpRequestConfig<E> requestConfig, Class<T> responseType,
                                                              Class<R> errorResponseType, String transactionId)
            throws StandardRestException;
}
