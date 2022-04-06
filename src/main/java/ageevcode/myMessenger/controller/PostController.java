package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Post;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.PostPageDto;
import ageevcode.myMessenger.service.PostService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {
    public static final int POSTS_PER_PAGE = 5;

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @JsonView(Views.FullPost.class)
    public PostPageDto list(@PageableDefault(size = POSTS_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User user) {
        return postService.findForUser(pageable, user);
    }

    @GetMapping("{id}")
    @JsonView(Views.FullPost.class)
    public Post getOne(@PathVariable("id") Post post) {
        return post;
    }

    @PostMapping
    @JsonView(Views.FullPost.class)
    public Post create(@RequestBody Post post, @AuthenticationPrincipal User user) {
        return postService.create(post, user);
    }

    @PutMapping("{id}")
    @JsonView(Views.FullPost.class)
    public Post update(@PathVariable("id") Post postFromDB, @RequestBody Post post) {
        return postService.update(postFromDB, post);
    }

    @DeleteMapping("{id}")
    @JsonView(Views.FullPost.class)
    public void delete(@PathVariable("id") Post post) {
        postService.delete(post);
    }
}
