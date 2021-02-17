package com.web_dong_ho.config;

import com.web_dong_ho.model.Role;
import com.web_dong_ho.model.User;
import com.web_dong_ho.repository.RoleRepository;
import com.web_dong_ho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.UUID;


@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_USER") == null) {
            roleRepository.save(new Role("ROLE_USER"));
        }


        // Admin account
        if (userRepository.findByEmail("admin@gmail.com") == null) {
            User user = new User();
            user.setEmail("admin@gmail.com");
             user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        // User account
        if (userRepository.findByEmail("khachhang1@gmail.com") == null) {
            User user = new User();
            user.setUsername("khach hang 1");
            user.setEmail("khachhang1@gmail.com");
             user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            userRepository.save(user);
        }

        // User account
        if (userRepository.findByEmail("khachhang2@gmail.com") == null) {
            User user = new User();
            user.setUsername("khach hang 2");
            user.setEmail("khachhang2@gmail.com");
            user.setPassword(passwordEncoder.encode("123"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_USER"));
            user.setRoles(roles);
            userRepository.save(user);
        }
    }


}