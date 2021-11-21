package uz.abdulhay.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.abdulhay.currency.entity.user.superEntity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    boolean existsByName(String name);

}
