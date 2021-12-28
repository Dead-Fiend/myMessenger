package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public List<User> list(Model model, @AuthenticationPrincipal User user) {
        System.out.println(user.getId());
        model.addAttribute("userList", userRepo.findAll());
        return userRepo.findAll();
    }
/*    @GetMapping
    public void list(Model model) {
        model.addAttribute("userList", userRepo.findAll());
    }*/
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user) {
        userRepo.delete(user);
    }
}
