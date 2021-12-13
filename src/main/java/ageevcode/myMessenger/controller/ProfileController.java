package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.repo.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final UserRepo userRepo;

    @Autowired
    public ProfileController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("{id}")
    public UserDetails getOne(@PathVariable("id") UserDetails userDetails) {
        return userDetails;
    }

    @PutMapping("{id}")
    public UserDetails update(@PathVariable("id") UserDetails userDetailsFromDB, @RequestBody UserDetails userDetails) {
        userDetails.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(userDetails, userDetailsFromDB, "password", "id");
        return userRepo.save(userDetailsFromDB);
    }
}
