package com.didbre.emr.web.rest.exception;

import lombok.Data;

/**
 * Error information's used by the controller and send back to the client (JSON format) if an error occurs
 */
@Data
public final class ErrorInfo
{
    private int code;
    private String message;
    private String uri;

    public ErrorInfo(int code, String message, String uri)
    {
        this.code = code;
        this.message = message;
        this.uri = uri;
    }
    public ErrorInfo(int code, String uri)
    {
        this.code = code;
        this.message = message;
        this.uri = uri;
    }
}
