package ua.lv.halya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lv.halya.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
