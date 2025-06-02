package com.pragma.loansanddeposits.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

@Getter
@RequiredArgsConstructor
public class RequestFile {

    private final String name;

    private final String originalFilename;

    private final String contentType;

    private final long size;

    private final InputStream inputStream;

}

