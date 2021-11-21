package uz.abdulhay.currency.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.abdulhay.currency.entity.user.superEntity.Role;
import uz.abdulhay.currency.exceptions.ResourceNotFound;
import uz.abdulhay.currency.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role findByName(String name) {
        if (roleRepository.existsByName(name)) {
            return find(name);
        } else {
            return roleRepository.save(new Role("ROLE_USER"));
        }
    }

    private Role find(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> ResourceNotFound.get("Role", "name", name));
    }


}
