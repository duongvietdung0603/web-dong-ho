package com.web_dong_ho.service;

 import com.web_dong_ho.model.LoaiSanPham;
 import com.web_dong_ho.model.Role;
 import com.web_dong_ho.model.SanPham;
 import com.web_dong_ho.model.User;
 import com.web_dong_ho.repository.RoleRepository;
 import com.web_dong_ho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.stereotype.Service;

 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("k có id user"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    public List<User> index(String search) {
        List<User> users = new ArrayList<>();
        if(search.equals("")){
            users = findAll();
        }else {
            users = userRepository.findByUsernameContaining(search);
        }
        return users;
    }


    public User edit(Long id, User user) {
        user.setId(id);
        userRepository.save(user);
        return user;
    }

    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        save(user);
        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
