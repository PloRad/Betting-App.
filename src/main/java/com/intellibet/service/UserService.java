package com.intellibet.service;

import com.intellibet.dto.UserForm;
import com.intellibet.mapper.UserMapper;
import com.intellibet.model.Role;
import com.intellibet.model.User;
import com.intellibet.repository.RoleRepository;
import com.intellibet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private void assignRoles(User user) {

        final List<Role> allRoles = roleRepository.findAll();
        user.setRoles(new HashSet<>(allRoles));
    }

    private void encodePassword(User user) {

        String passwordInPlainText = user.getPassword();
        String passwordEncoded = bCryptPasswordEncoder.encode(passwordInPlainText);
        user.setPassword(passwordEncoded);
    }

    public void save(UserForm userForm) {

        User user = userMapper.map(userForm);
        assignRoles(user);
        encodePassword(user);
        userRepository.save(user);
    }

    public void markRegistrationSuccessfull(UserForm userForm) {

        userForm.setPageSection("section-4");
    }
}
