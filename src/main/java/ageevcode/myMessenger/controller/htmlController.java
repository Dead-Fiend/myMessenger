package ageevcode.myMessenger.controller;


import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class htmlController {
    private final MessageRepo messageRepo;

    @Autowired
    public htmlController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (test != "anonymousUser") {
            data.put("profile", test);
            data.put("messages", messageRepo.findAll());
            model.addAttribute("frontendData", data);
        } else {
            data.put("profile", null);
            data.put("messages", null);
            model.addAttribute("frontendData", data);
        }

        return "index";
    }

    @GetMapping("test")
    public String admin() {
        return "test";
    }
}
