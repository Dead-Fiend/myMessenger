package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Comment;
import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.repo.CommentRepo;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;
    private final UserRepo userRepo;
    private final CommentRepo commentRepo;


    @Autowired
    public CommentController(CommentService commentService, UserRepo userRepo, CommentRepo commentRepo) {
        this.commentService = commentService;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }

    @PostMapping
    public Comment create(@RequestBody Comment comment, @AuthenticationPrincipal UserDetails userDetails) {
        //return commentService.create(comment, userDetails);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        comment.setAuthor(userRepo.findByUsername(auth.getName()));
        return commentRepo.save(comment);
    }
}
