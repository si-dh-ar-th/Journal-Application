package com.styx.journalApp.service;

import com.styx.journalApp.entity.User;
import com.styx.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveExistingUser(User user) {
        userRepository.save(user);
    }

    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public void saveNewAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }

    public List<User> getAllEntries(){
        return userRepository.findAll();
    }

    public Optional<User> getEntryById(ObjectId userId){
        return userRepository.findById(userId);
    }

    public void deleteEntryById(ObjectId journalId){
        userRepository.deleteById(journalId);
    }

    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }

}
