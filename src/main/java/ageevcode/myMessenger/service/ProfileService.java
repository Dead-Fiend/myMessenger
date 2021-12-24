package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfileService {
    private final UserRepo userRepo;

    @Autowired
    public ProfileService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    public UserDetails changeSubscription(UserDetails channel, UserDetails subscriber) {
        Set<UserDetails> subscribers = channel.getSubscribers();

        if (subscribers.contains(subscriber)) {
            subscribers.remove(subscriber);
        } else {
            subscribers.add(subscriber);
        }

        return userRepo.save(channel);
    }
}
