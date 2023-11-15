package com.leoric.eshop.services;

import com.leoric.eshop.api.model.RegistrationBody;
import com.leoric.eshop.exception.UserAlreadyExistsException;
import com.leoric.eshop.models.LocalUser;
import com.leoric.eshop.repositories.UserRepository;
import com.leoric.eshop.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EncryptionService encryptionService;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, EncryptionService encryptionService) {
    this.userRepository = userRepository;
    this.encryptionService = encryptionService;
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
}
