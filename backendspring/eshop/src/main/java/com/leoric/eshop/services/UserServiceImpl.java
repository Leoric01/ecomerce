package com.leoric.eshop.services;

import com.leoric.eshop.api.model.LoginBody;
import com.leoric.eshop.api.model.RegistrationBody;
import com.leoric.eshop.exception.UserAlreadyExistsException;
import com.leoric.eshop.models.LocalUser;
import com.leoric.eshop.repositories.UserRepository;
import com.leoric.eshop.security.EncryptionService;
import com.leoric.eshop.security.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EncryptionService encryptionService;
  private final JWTService jwtService;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, EncryptionService encryptionService, JWTService jwtService) {
    this.userRepository = userRepository;
    this.encryptionService = encryptionService;
    this.jwtService = jwtService;
  }

  @Override
  public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
    if (userRepository.existsByEmail(registrationBody.getEmail())
        || userRepository.existsByUsername(registrationBody.getUsername())) {
      throw new UserAlreadyExistsException();
    }
    LocalUser user = new LocalUser();
    user.setEmail(registrationBody.getEmail());
    user.setFirstName(registrationBody.getFirstName());
    user.setLastName(registrationBody.getLastName());
    user.setUsername(registrationBody.getUsername());
    user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
    return userRepository.save(user);
  }

@Override
public String loginUser(LoginBody loginBody) {
  Optional<LocalUser> opUser = userRepository.findByUsername(loginBody.getUsername());
    if (opUser.isPresent()){
      LocalUser user = opUser.get();
      if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())){
        return jwtService.generateJWT(user);
      }
    } return null;
}
}
