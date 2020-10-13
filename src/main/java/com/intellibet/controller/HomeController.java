package com.intellibet.controller;

import com.intellibet.dto.BetForm;
import com.intellibet.dto.EventForm;
import com.intellibet.dto.TransactionForm;
import com.intellibet.dto.UserForm;
import com.intellibet.service.EventService;
import com.intellibet.service.UserService;
import com.intellibet.validator.TransactionFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SpringBootApplication
public class HomeController {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionFormValidator transactionFormValidator;

    @GetMapping({"/login", "/home", "/"})
    public String getHomePage(Model model) {
        model.addAttribute("userForm", new UserForm());

        List<EventForm> eventFormList = eventService.retrieveAllEvents();
        model.addAttribute("eventFormList", eventFormList);
        model.addAttribute("betForm", new BetForm("10"));

        return "home";
    }

    @GetMapping({"/myAccount"})
    public String getMyAccountPage(Model model, Authentication authentication) {
        TransactionForm transactionForm = userService.getTransactionFormBy(authentication.getName());
        model.addAttribute("transactionForm", transactionForm);

        return "myAccount";
    }

    @PostMapping({"/deposit"})
    public String postDepositRequest(@ModelAttribute("transactionForm") TransactionForm transactionForm,
        BindingResult bindingResult, Authentication authentication) {

        transactionFormValidator.validate(transactionForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "myAccount";
        }

        String authenticatedUserEmail = authentication.getName();

        userService.realiseTransaction(transactionForm, authenticatedUserEmail);

        return "redirect:/myAccount";

    }

    @PostMapping({"/home"})
    public String postDepositRequest(@ModelAttribute("betForm") BetForm betForm,
            BindingResult bindingResult, Model model, Authentication authentication) {


        List<EventForm> eventFormList = eventService.retrieveAllEvents();
        model.addAttribute("eventFormList", eventFormList);

        return "home";

    }


}
