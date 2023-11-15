package com.leoric.eshop.api.controller.auth;

import com.leoric.eshop.api.model.RegistrationBody;
import com.leoric.eshop.exception.UserAlreadyExistsException;
import com.leoric.eshop.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final UserService userService;

  @Autowired
  public AuthenticationController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody @Valid RegistrationBody registrationBody) {
    try {
      userService.registerUser(registrationBody);
      return ResponseEntity.ok().build();
    }
    catch (UserAlreadyExistsException ex){
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
