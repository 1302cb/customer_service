package com.cvte.customer_service.cuse.entity;


public class ResultData {
    public static final int SUCCESS = 200;
    public static final int FAIL = 400;
    public static final int EMPTY = 204;
    private Object data;
    private String message;
    private Integer statusCode;
    private String token;

    public ResultData(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public ResultData(Object data, Integer statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public ResultData(Object data, String message, Integer statusCode) {
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "ResultData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                ", token='" + token + '\'' +
                '}';
    }
}
