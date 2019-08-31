package com.example.drinkshop.model;

public class CheckUserResponse {

    private Boolean exists;
    private String error_msg;

    public CheckUserResponse() {
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
