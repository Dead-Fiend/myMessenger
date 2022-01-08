package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Role;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.UserSubscription;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.service.ProfileService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {
    private final ProfileService profileService;
    private final UserRepo userRepo;

    @Autowired
    public ProfileController(ProfileService profileService, UserRepo userRepo) {
        this.profileService = profileService;
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    @JsonView(Views.WithoutPassword.class)
    public User getOne(@PathVariable("id") User user, Model model) {


        /*user.getRoles().add(Role.USER);*/
        /*user.getRoles().add(Role.ADMIN);*/
        /*userRepo.save(user);
        return user;*/

        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        model.addAttribute("frontendData", data);
        return user;
    }

    @PostMapping("change-subscription/{channelId}")
    public User changeSubscription(@PathVariable("channelId") User channel, @AuthenticationPrincipal User subscriber) {
        if (subscriber.equals(channel)) {
            return channel;
        } else {
            return profileService.changeSubscription(channel, subscriber);
        }
    }

    @GetMapping("get-subscribers/{channelId}")
    @JsonView(Views.IdName.class)
    public List<UserSubscription> subscribers(@PathVariable("channelId") User channel) {
        return profileService.getSubscribers(channel);
    }

    @PostMapping("change-status/{subscriberId}")
    @JsonView(Views.IdName.class)
    public UserSubscription changeSubscriptionStatus(@PathVariable("subscriberId") User subscriber, @AuthenticationPrincipal User channel) {
        return profileService.changeSubscriptionStatus(channel, subscriber);
    }
}
