package com.example.testio.models;

/**
 * Created by mantas on 7/22/17.
 */

public class Token {

  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  @Override
  public String toString() {
    return "Token{" + "token='" + token + '\'' + '}';
  }
}
