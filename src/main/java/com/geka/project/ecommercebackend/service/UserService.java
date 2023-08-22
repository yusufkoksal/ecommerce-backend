package com.geka.project.ecommercebackend.service;

import com.geka.project.ecommercebackend.api.model.RegistrationBody;
import com.geka.project.ecommercebackend.exception.UserAlreadyExistException;
import com.geka.project.ecommercebackend.model.LocalUser;
import com.geka.project.ecommercebackend.model.dao.LocalUserDAO;

import org.springframework.stereotype.Service;


@Service
public class UserService  {

    private LocalUserDAO localUserDAO;

    public UserService(LocalUserDAO localUserDAO) {
        this.localUserDAO = localUserDAO;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException{
        if(localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() ||
        localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw  new UserAlreadyExistException();
        }
        localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername());
        LocalUser user = new LocalUser();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        //TODO:Encrypt passwords!!
        user.setPassword(registrationBody.getPassword());
        return localUserDAO.save(user);

    }

}
