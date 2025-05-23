package com.pragma.operationsandexecution.loansanddeposits.infrastructure.drivenadapters.restclient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Getter
@ToString
@AllArgsConstructor
public class HttpRequestConfig<T> {
    private final HttpMethod method;
    private final String url;
    private final Map<String, String> headers;
    private T requestBody;
}
