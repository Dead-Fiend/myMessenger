package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.Role;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.UserSubscription;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.repo.UserSubscriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService implements UserDetailsService {
    private final UserRepo userRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileService(UserRepo userRepo, UserSubscriptionRepo userSubscriptionRepo) {
        this.userRepo = userRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
    }


    public User changeSubscription(User channel, User subscriber) {
        List<UserSubscription> subscriptions = channel.getSubscribers()
                .stream()
                .filter(subscription ->
                        subscription.getSubscriber().equals(subscriber)
                )
                .collect(Collectors.toList());

        if (subscriptions.isEmpty()) {
            UserSubscription subscription = new UserSubscription(channel, subscriber);
            channel.getSubscribers().add(subscription);
        } else {
            channel.getSubscribers().removeAll(subscriptions);
        }

        return userRepo.save(channel);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    // ---------------------------------------------------------------------------------------- //

    public boolean addUser(User user) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (user.getUsername().length() < 3 || user.getPassword().length() < 8) {
            return false;
        }

        if (userFromDB != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        return true;
    }

    public List<UserSubscription> getSubscribers(User channel) {
        return userSubscriptionRepo.findByChannel(channel);
    }

    public UserSubscription changeSubscriptionStatus(User channel, User subscriber) {
        UserSubscription subscription = userSubscriptionRepo.findByChannelAndSubscriber(channel, subscriber);
        subscription.setActive(!subscription.isActive());

        return userSubscriptionRepo.save(subscription);
    }
}
