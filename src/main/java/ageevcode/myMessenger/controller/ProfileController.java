package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final UserRepo userRepo;
    private final ProfileService profileService;

    @Autowired
    public ProfileController(UserRepo userRepo, ProfileService profileService) {
        this.userRepo = userRepo;
        this.profileService = profileService;
    }

    @GetMapping("{id}")
    @JsonView(Views.WithoutPassword.class)
    public User getOne(@PathVariable("id") User user, Model model) {
        //UserDetails userDetailsWithoutPasswd = userDetails;
        //userDetailsWithoutPasswd.setPassword(null);
        //return userDetailsWithoutPasswd;


        /*HashSet<Role> updtRole = new HashSet<>();
        updtRole.add(Role.ADMIN);
        userDetails.setRoles(updtRole);
        return userRepo.save(userDetails);*/

        HashMap<Object, Object> data = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User profile = userRepo.findByUsername(auth.getName());
        data.put("profile", profile);
        model.addAttribute("frontendData", data);
        return user;
    }

    @PostMapping("change-subscription/{channelId}")
    @JsonView(Views.WithoutPassword.class)
    public User changeSubscription(@PathVariable("channelId") User channel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User subscriber = userRepo.findByUsername(auth.getName());

        if (subscriber.equals(channel)) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDB, @RequestBody User user) {
        user.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(user, userFromDB, "password", "id");
        //BeanUtils.copyProperties(userDetails, userDetailsFromDB, "id");
        return userRepo.save(userFromDB);
    }
}
