package com.web_dong_ho.controller;

import com.web_dong_ho.model.Role;
import com.web_dong_ho.model.User;
import com.web_dong_ho.repository.RoleRepository;
import com.web_dong_ho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/403")
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/login")
    public String getLogin(@RequestParam(name = "error",required = false,defaultValue = "error") String error, Model model) {
        if(!error.equals("error")){
            model.addAttribute("err", true);
        }
        return "dang_nhap";
    }

    @GetMapping("/dang-ki")
    public String dangKi(Model model) {
        model.addAttribute("User", new User());
        return "dang_ki";
    }

    @PostMapping("/dang-ki")
    public String dangKi(@ModelAttribute User user) {
        System.out.println("=================================");
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
        return "redirect:/login";
    }
}
