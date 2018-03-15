package com.aligntech.inventory.exception;

/**
 * Created by mozg on 15.03.2018.
 * inventory
 */
public class ExceptionResponse {

    private String message;

    private String details;

    public ExceptionResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ExceptionResponse {" +
                " message = '" + message + '\'' +
                ", details = '" + details + '\'' +
                " }";
    }
}
