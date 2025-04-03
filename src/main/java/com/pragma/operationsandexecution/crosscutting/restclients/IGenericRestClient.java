package com.pragma.operationsandexecution.crosscutting.restclients;

import com.pragma.operationsandexecution.crosscutting.exceptions.StandardRestException;
import org.springframework.http.ResponseEntity;


public interface IGenericRestClient {

    <T, R, E> ResponseEntity<T> sendRequestAndReceiveResponse(HttpRequestConfig<E> requestConfig, Class<T> responseType,
                                                              Class<R> errorResponseType, String transactionId) throws StandardRestException;
}
