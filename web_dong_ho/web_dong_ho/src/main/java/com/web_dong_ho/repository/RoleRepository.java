package com.web_dong_ho.repository;

 import com.web_dong_ho.model.Role;
 import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
