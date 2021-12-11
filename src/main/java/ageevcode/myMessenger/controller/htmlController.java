package ageevcode.myMessenger.controller;


import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.Role;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/")
public class htmlController {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    @Autowired
    public htmlController(MessageRepo messageRepo, UserRepo userRepo) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (test != "anonymousUser") {
            data.put("profile", test);
            data.put("messages", messageRepo.findAll());
        } else {
            data.put("profile", null);
            data.put("messages", null);
        }

        model.addAttribute("frontendData", data);

        return "index";
    }
    @GetMapping("login")
    public String login(@RequestBody User user) {
        return "login";
    }
    @GetMapping("registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (userFromDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

    @GetMapping("profile")
    public String profile(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        ArrayList<Object> userProfile = new ArrayList<>();
        userProfile.add(principal);

        if (userProfile.get(0) != null) {
            data.put("profile", principal);
            data.put("messages", messageRepo.findAll());
            data.put("Tst", "TestTest");
        } else {
            data.put("profile", null);
            data.put("messages", null);
        }
        model.addAttribute("frontendData", data);



        return "profile";
    }

    @GetMapping("test")
    public String test(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        Object test = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (test != "anonymousUser") {
            data.put("profile", test);
            data.put("messages", messageRepo.findAll());
        } else {
            data.put("profile", null);
            data.put("messages", null);
        }

        model.addAttribute("frontendData", data);
        return "test";
    }
}
