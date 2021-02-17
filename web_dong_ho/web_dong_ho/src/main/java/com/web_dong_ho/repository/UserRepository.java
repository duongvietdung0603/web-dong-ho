package com.web_dong_ho.repository;

  import com.web_dong_ho.model.User;
 import org.springframework.data.jpa.repository.JpaRepository;

  import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByUsernameContaining(String search);
}
