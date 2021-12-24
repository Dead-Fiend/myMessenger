package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Role;
import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static ageevcode.myMessenger.controller.MessageController.MESSAGES_PER_PAGE;

@Controller
@RequestMapping("/")
public class htmlController {
    private final MessageService messageService;
    private final UserRepo userRepo;
    private final ObjectWriter messageWriter;
    private final ObjectWriter profileWriter;

    @Autowired
    public htmlController(MessageService messageService, UserRepo userRepo, ObjectMapper mapper) {
        this.messageService = messageService;
        this.userRepo = userRepo;
        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());
        messageWriter = objectMapper
                .writerWithView(Views.FullMessage.class);
        profileWriter = objectMapper
                .writerWithView(Views.WithoutPassword.class);

    }

    @Value("${spring.profiles.active}")
    private String isDevMode;

    @GetMapping
    public String main(Model model) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = userRepo.findByUsername(auth.getName());
        if (user != null) {
            UserDetails userFromDb = userRepo.findById(user.getId()).get();
            String serializedProfile = profileWriter.writeValueAsString(userFromDb);
            model.addAttribute("profile", serializedProfile);

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MESSAGES_PER_PAGE, sort);
            MessagePageDto messagePageDto = messageService.findAll(pageRequest);

            String messages = messageWriter.writeValueAsString(messagePageDto.getMessages());

            model.addAttribute("messages", messages);

            data.put("currentPage", messagePageDto.getCurrentPage());
            data.put("totalPages", messagePageDto.getTotalPages());
        } else {
            model.addAttribute("profile", "null");
            model.addAttribute("messages", "[]");
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(isDevMode));

        return "index";
    }
 /*   @GetMapping("login")
    @JsonView(Views.WithoutPassword.class)
    public String login() {
        return "login";
    }
    @PostMapping("login") String loginUser(UserDetails userDetails, Map<String, Object> model) {
        UserDetails userDetailsFromDB = userRepo.findByUsernameAndPassword(userDetails.getUsername(), userDetails.getPassword());

        if (userDetailsFromDB == null) {
            model.put("message", "Неправильный логин или пароль");
            return "login";
        }
        return "redirect:/";
    }*/
    /*@GetMapping("auth")
    public String auth() {
        return "index";
    }
    @PostMapping("auth")
    public String authUser(UserDetails userDetails, Map<String, Object> model) {
        UserDetails userDetailsFromDB = userRepo.findByUsernameAndPassword(userDetails.getUsername(), userDetails.getPassword());

        if (userDetailsFromDB == null) {
            model.put("message", "Неправильный логин или пароль");
            return "redirect:/auth";
        }
        return "redirect:/";
    }*/
    @GetMapping("registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("registration")
    public String addUser(UserDetails userDetails, Map<String, Object> model) {
        UserDetails userDetailsFromDB = userRepo.findByUsername(userDetails.getUsername());

        if (userDetailsFromDB != null) {
            model.put("err", "User exists!");
            return "redirect:/reg";
        }

        userDetails.setActive(true);
        userDetails.setRoles(Collections.singleton(Role.USER));
        userRepo.save(userDetails);
        //loginUser(userRepo.findByUsername(userDetails.getUsername()), model);
        return "redirect:/auth";
    }

    private class MESSAGES_PER_PAGE {
    }
}
