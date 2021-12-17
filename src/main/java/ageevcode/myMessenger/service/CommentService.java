package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.Comment;
import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.repo.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment create(Comment comment, UserDetails userDetails) {
        comment.setAuthor(userDetails);
        commentRepo.save(comment);
        return comment;
    }
}
