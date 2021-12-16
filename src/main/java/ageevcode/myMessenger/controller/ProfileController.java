package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.repo.UserRepo;
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
@RequestMapping("/profile")
public class ProfileController {
    private final UserRepo userRepo;

    @Autowired
    public ProfileController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    @JsonView(Views.WithoutPassword.class)
    public UserDetails getOne(@PathVariable("id") UserDetails userDetails, Model model) {
        //UserDetails userDetailsWithoutPasswd = userDetails;
        //userDetailsWithoutPasswd.setPassword(null);
        //return userDetailsWithoutPasswd;


        /*HashSet<Role> updtRole = new HashSet<>();
        updtRole.add(Role.ADMIN);
        userDetails.setRoles(updtRole);
        return userRepo.save(userDetails);*/

        HashMap<Object, Object> data = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object profile = userRepo.findByUsername(auth.getName());
        data.put("profile", profile);
        model.addAttribute("frontendData", data);
        return userDetails;
    }

    @PutMapping("{id}")
    public UserDetails update(@PathVariable("id") UserDetails userDetailsFromDB, @RequestBody UserDetails userDetails) {
        userDetails.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(userDetails, userDetailsFromDB, "password", "id");
        //BeanUtils.copyProperties(userDetails, userDetailsFromDB, "id");
        return userRepo.save(userDetailsFromDB);
    }
}
