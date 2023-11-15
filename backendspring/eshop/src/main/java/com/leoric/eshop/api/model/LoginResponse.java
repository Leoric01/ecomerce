package com.leoric.eshop.api.model;

public class LoginResponse {
  private String jwt;

  public LoginResponse() {}

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }
}
