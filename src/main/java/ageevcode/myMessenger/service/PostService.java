package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.*;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.dto.PostPageDto;
import ageevcode.myMessenger.repo.PostRepo;
import ageevcode.myMessenger.repo.UserSubscriptionRepo;
import ageevcode.myMessenger.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepo postRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;
    private final BiConsumer<EventType, Post> wsSender;

    @Autowired
    public PostService(PostRepo postRepo, UserSubscriptionRepo userSubscriptionRepo, WsSender wsSender) {
        this.postRepo = postRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
        this.wsSender = wsSender.getSender(ObjectType.POST, Views.FullPost.class);
    }


    public void delete(Post post) {
        postRepo.delete(post);
        wsSender.accept(EventType.REMOVE, post);
    }

    public Post update(Post postFromDB, Post post) {
        postFromDB.setUpdatedAt(LocalDateTime.now());
        postFromDB.setText(post.getText());
        Post updatedPost = postRepo.save(postFromDB);

        wsSender.accept(EventType.UPDATE, updatedPost);

        return updatedPost;
    }

    public Post create(Post post, User user) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());

        post.setAuthor(user);

        List<Comment> list = new ArrayList<>();
        post.setComments(list);

        Post updatedPost = postRepo.save(post);

        wsSender.accept(EventType.CREATE, updatedPost);

        return updatedPost;
    }

    public PostPageDto findForUser(Pageable pageable, User user) {
        List<User> channels = userSubscriptionRepo.findBySubscriber(user)
                .stream()
                .filter(UserSubscription::isActive)
                .map(UserSubscription::getChannel)
                .collect(Collectors.toList());

        channels.add(user);

        Page<Post> page = postRepo.findByAuthorIn(channels, pageable);

        return new PostPageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }
}
