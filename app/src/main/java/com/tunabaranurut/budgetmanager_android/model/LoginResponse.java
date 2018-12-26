package com.tunabaranurut.budgetmanager_android.model;

public class LoginResponse {

    private Response response;
    private User user ;
    private String token;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Response getResponse() {
        return response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
