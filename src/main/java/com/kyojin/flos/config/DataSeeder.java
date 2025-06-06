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
    public void seedData() {
        if (roleRepository.findByName("user").isEmpty()) {
            roleRepository.save(new Role(1L,"user"));
        }
        if (roleRepository.findByName("admin").isEmpty()) {
            roleRepository.save(new Role(2L,"admin"));
        }
    }

}
