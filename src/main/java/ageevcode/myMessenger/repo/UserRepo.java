package ageevcode.myMessenger.repo;

import ageevcode.myMessenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
