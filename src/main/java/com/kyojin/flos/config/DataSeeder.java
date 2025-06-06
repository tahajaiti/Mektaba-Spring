package com.kyojin.flos.config;

import com.kyojin.flos.entity.Role;
import com.kyojin.flos.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder {


    private final RoleRepository roleRepository;


    public DataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        if (roleRepository.findByName("admin").isEmpty()) {
            Role role = new Role();
            role.setName("admin");
            roleRepository.save(role);
        }
        if (roleRepository.findByName("user").isEmpty()) {
            Role role = new Role();
            role.setName("user");
            roleRepository.save(role);
        }
    }

}
