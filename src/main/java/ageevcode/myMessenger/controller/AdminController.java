package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.EventType;
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
    public List<UserDetails> list(Model model) {

        model.addAttribute("userList", userRepo.findAll());
        return userRepo.findAll();
    }
/*    @GetMapping
    public void list(Model model) {
        model.addAttribute("userList", userRepo.findAll());
    }*/
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") UserDetails userDetails) {
        userRepo.delete(userDetails);
    }
}
