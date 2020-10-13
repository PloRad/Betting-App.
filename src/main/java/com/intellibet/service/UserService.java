package com.intellibet.service;

import com.intellibet.dto.TransactionForm;
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

    public void save(UserForm userForm) {
        User user = userMapper.map(userForm);
        assignRoles(user);
        encodePassword(user);
        userRepository.save(user);
    }

    private void assignRoles(User user) {

        final List<Role> allRoles = roleRepository.findAll();
        user.setRoles(new HashSet<>(allRoles));
    }

    private void encodePassword(User user) {
        String passwordInPlainText = user.getPassword();
        String passwordEncoded = bCryptPasswordEncoder.encode(passwordInPlainText);
        user.setPassword(passwordEncoded);

    }

    public void markRegistrationSuccessful(UserForm userForm) {

        userForm.setPageSection("section-4");

    }

    public void realiseTransaction(TransactionForm transactionForm, String authenticatedUserEmail) {
        User user = userRepository.findByEmail(authenticatedUserEmail);

        boolean isWithdraw = transactionForm.getType().equals("withdraw");

        if(isWithdraw) {
            removeAmountFromUser(user, transactionForm);
        } else {
            addAmountToUser(user, transactionForm);
        }
        userRepository.save(user);
    }

    private void addAmountToUser(User user, TransactionForm transactionForm) {

        Double depositAmount = Double.parseDouble(transactionForm.getDepositAmount());
        Double existingAmount = user.getBalance() == null ? 0d : user.getBalance();

        user.setBalance(existingAmount + depositAmount);
    }

    private void removeAmountFromUser(User user, TransactionForm transactionForm) {

        Double withdrawAmount = Double.parseDouble(transactionForm.getWithdrawAmount());
        Double existingAmount = user.getBalance() == null ? 0d : user.getBalance();

        user.setBalance(existingAmount - withdrawAmount);
    }

    public TransactionForm getTransactionFormBy(String userEmail) {
        User user = userRepository.findByEmail(userEmail);

        TransactionForm result = new TransactionForm();
        Double existingAmount = user.getBalance() == null ? 0d : user.getBalance();
        result.setBalance(existingAmount.toString());

        return result;
    }

}
