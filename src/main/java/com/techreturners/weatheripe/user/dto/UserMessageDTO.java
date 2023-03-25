package com.techreturners.weatheripe.user.dto;

public class UserMessageDTO {
  private String message;

  public UserMessageDTO(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
