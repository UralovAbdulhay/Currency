package uz.abdulhay.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.abdulhay.currency.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByLogin(String login);
}
