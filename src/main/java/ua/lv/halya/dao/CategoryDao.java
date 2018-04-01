package ua.lv.halya.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lv.halya.app.Status;
import ua.lv.halya.entity.Category;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    List<Category> findByStatusOrderByName(Status status);
}
