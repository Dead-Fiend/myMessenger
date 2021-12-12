package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.domain.Role;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public String main(Model model, @AuthenticationPrincipal UserDetails userDetails) {
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
    public String login() {
        return "login";
    }
    @GetMapping("registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("registration")
    public String addUser(UserDetails userDetails, Map<String, Object> model) {
        UserDetails userDetailsFromDB = userRepo.findByUsername(userDetails.getUsername());

        if (userDetailsFromDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        userDetails.setActive(true);
        userDetails.setRoles(Collections.singleton(Role.USER));
        userRepo.save(userDetails);
        return "redirect:/login";
    }

    @GetMapping("profile")
    public String profile(Model model, @AuthenticationPrincipal User user, @AuthenticationPrincipal UserDetails userDetails) {
        HashMap<Object, Object> data = new HashMap<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        ArrayList<Object> userProfile = new ArrayList<>();
        userProfile.add(principal);

        //Object test = principal;
        //Object test = userDetails.getLastVisit();
        //userProfile.add(test);
        if (userProfile.get(0) != null) {
            data.put("profile", principal);
            data.put("messages", messageRepo.findAll());
        //    data.put("tst", test);
        } else {
            data.put("profile", null);
            data.put("messages", null);
        }
        model.addAttribute("frontendData", data);



        return "profile";
    }

    @GetMapping("test")
    public String test(Model model, @AuthenticationPrincipal UserDetails userDetails) {
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
