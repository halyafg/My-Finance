package ua.lv.halya.services;

import ua.lv.halya.app.Status;
import ua.lv.halya.entity.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    void edit(int id, String name,Status status);

    void rename(int idCategory, String newName);

    void delete(int id);

    Category findById (int id);

    List<Category> findByStatusOrderByName(Status status);
}
