package com.intellibet.controller;

import com.intellibet.dto.EventForm;
import com.intellibet.dto.UserForm;
import com.intellibet.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@SpringBootApplication
public class HomeController {

    @Autowired
    private EventService eventService;

    @GetMapping({"/home", "/login", "/"})
    public String getHomePage(Model model) {

        model.addAttribute("userForm", new UserForm());

        List<EventForm> eventFormList = eventService.retrieveAllEvents();
        model.addAttribute("eventFormList", eventFormList);

        return "home";
    }
}
