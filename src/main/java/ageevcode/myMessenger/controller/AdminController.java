package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.repo.UserRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminAccess")
public class AdminController {
    private final UserRepo userRepo;

    @Autowired
    public AdminController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    @JsonView(Views.WithoutPassword.class)
    public List<User> list(Model model) {
        model.addAttribute("userList", userRepo.findAll());
        return userRepo.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }
}
