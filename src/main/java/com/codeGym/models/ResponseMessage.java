package com.codeGym.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseMessage<E> {
    private Boolean success;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private E data;

    public ResponseMessage(Boolean success, String message, E data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}