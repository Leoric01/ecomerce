package com.leoric.eshop.api.model;

import jakarta.validation.constraints.*;

public class RegistrationBody {
  @NotNull
  @NotBlank(message = "username is required")
  @Size(min = 3, max = 255, message = "Username length must be between 3 and 255 characters")
  private String username;

  @NotNull
  @NotBlank(message = "email is required")
  @Email
  private String email;

  @NotNull
  @NotBlank(message = "password is required")
  @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()-=_+\\[\\]{}|;:'\",<.>/?]*$")
  private String password;

  @NotNull
  @NotBlank(message = "first name is required")
  private String firstName;

  @NotNull
  @NotBlank(message = "last name is required")
  private String lastName;

  public RegistrationBody() {}

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
