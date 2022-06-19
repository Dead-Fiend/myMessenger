package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.Role;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.UserSubscription;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.repo.UserSubscriptionRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfileService implements UserDetailsService {
    private final UserRepo userRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileService(UserRepo userRepo, UserSubscriptionRepo userSubscriptionRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
        this.passwordEncoder = passwordEncoder;
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

    @JsonView(Views.Password.class)
    public boolean changePassword(String oldPassword, User user, HttpServletRequest request) throws ServletException {
        User userFromDB = userRepo.findByIdAndActive(user.getId(), true);
        
        if ( passwordEncoder.matches(oldPassword, userFromDB.getPassword()) ) {
            if (user.getPassword().length() >= 8) {
                userFromDB.setPassword(passwordEncoder.encode(user.getPassword()));
                userRepo.save(userFromDB);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @JsonView(Views.Password.class)
    public boolean addUser(User user, HttpServletRequest request) throws ServletException {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        if (user.getUsername().length() < 3 || user.getPassword().length() < 8) {
            return false;
        }

        if (userFromDB != null) {
            return false;
        }

        String usrnm = user.getUsername();
        String passwd = user.getPassword();

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

/*        Set<Role> roles = new HashSet<>();
        roles.add(Role.ADMIN);
        roles.add(Role.USER);

        if (user.getUsername().equals("")) {
            user.setRoles(roles);
        }*/

        userRepo.save(user);

        request.login(usrnm, passwd);
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
