package com.web_dong_ho.controller.Admin;

import com.web_dong_ho.model.Role;
import com.web_dong_ho.model.User;
import com.web_dong_ho.repository.RoleRepository;
import com.web_dong_ho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
@CrossOrigin
@RequestMapping("admin/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("")
    public String index(Model model){
        model.addAttribute("DanhSach", userService.findAll());
        return "Admin/user";
    }

    @GetMapping("/them")
    public String add(Model model){
        model.addAttribute("User", new User());
        model.addAttribute("Role", roleRepository.findAll());
        model.addAttribute("Active","Thêm mới");

        return "Admin/user_add";
    }

    @PostMapping("/save")
    public String add_post(@ModelAttribute User user, @RequestParam Integer role ){
           Role role1 = roleRepository.findById(role).orElseThrow(()->new IllegalArgumentException("k có id này"));
           HashSet<Role> roles = new HashSet<>();
           roles.add(roleRepository.findByName(role1.getName()));
           user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
           userService.save(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/sua/{id}")
    public String sua(Model model,@PathVariable Long id){
        model.addAttribute("User", userService.findById(id));
        model.addAttribute("Role", roleRepository.findAll());
        model.addAttribute("Active","Sửa id "+id);

        return "Admin/user_add";
    }

    @GetMapping("/xoa/{id}")
    public String xoa(Model model,@PathVariable Long id){
        userService.deleteById(id);
        return "redirect:/admin/user";
    }
}
