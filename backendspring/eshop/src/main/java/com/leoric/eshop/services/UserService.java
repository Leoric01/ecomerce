package com.leoric.eshop.services;

import com.leoric.eshop.api.model.RegistrationBody;
import com.leoric.eshop.exception.UserAlreadyExistsException;
import com.leoric.eshop.models.LocalUser;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException;
}
