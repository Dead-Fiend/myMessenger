package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.service.MessageService;
import ageevcode.myMessenger.service.ProfileService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private final ProfileService profileService;

    @Autowired
    public htmlController(MessageService messageService, UserRepo userRepo, ObjectMapper mapper, ProfileService profileService) {
        this.messageService = messageService;
        this.userRepo = userRepo;
        this.profileService = profileService;
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
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            User userFromDb = userRepo.findById(user.getId()).get();
            String serializedProfile = profileWriter.writeValueAsString(userFromDb);

            model.addAttribute("profile", serializedProfile);

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, MESSAGES_PER_PAGE, sort);
            MessagePageDto messagePageDto = messageService.findForUser(pageRequest, user);

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

    @GetMapping("registration")
    public String registration() {
        return "registration";
    }
    @PostMapping("registration")
    public String addUser(User user, Map<String, Object> model) {
        boolean result = profileService.addUser(user);

        if (result) {
            return "redirect:/auth";
        } else {
            return "redirect:/reg";
        }
    }
}
