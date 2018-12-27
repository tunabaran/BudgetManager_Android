package com.tunabaranurut.budgetmanager_android.model;

public class LoginResponse {

    private Response response;
    private SimpleUser simpleUser;
    private String token;

    public SimpleUser getSimpleUser() {
        return simpleUser;
    }

    public void setSimpleUser(SimpleUser simpleUser) {
        this.simpleUser = simpleUser;
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
