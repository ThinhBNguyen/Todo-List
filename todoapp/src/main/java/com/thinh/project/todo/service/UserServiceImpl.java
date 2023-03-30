package com.thinh.project.todo.service;

import com.thinh.project.todo.dao.UserRepository;
import com.thinh.project.todo.entity.Task;
import com.thinh.project.todo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int theId) {
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User save(User theUserEntity) {
        String encodedPassword = passwordEncoder.encode(theUserEntity.getPassWord());
        theUserEntity.setPassWord(encodedPassword);
        return userRepository.save(theUserEntity);
    }

    @Override
    public void deleteById(int theId) {

    }
}
