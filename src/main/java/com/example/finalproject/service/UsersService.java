package com.example.finalproject.service;


import com.example.finalproject.model.UsersModel;
import com.example.finalproject.reporsitory.UsersRepository;
import org.springframework.stereotype.Service;


@Service
public class UsersService {
    private final UsersRepository userRepository;

    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersModel registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null;
        }  else {
            if(userRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Dublicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            return userRepository.save(usersModel);
        }
    }

    public UsersModel authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

}
