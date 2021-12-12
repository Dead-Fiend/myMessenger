package ageevcode.myMessenger.repo;

import ageevcode.myMessenger.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserDetails, Long>{
    UserDetails findByUsername(String username);
}
