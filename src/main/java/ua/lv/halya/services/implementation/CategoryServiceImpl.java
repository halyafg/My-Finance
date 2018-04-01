package ua.lv.halya.services.implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lv.halya.app.Status;
import ua.lv.halya.dao.CategoryDao;
import ua.lv.halya.entity.Category;
import ua.lv.halya.services.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void add(Category category) {
        if(!category.getName().equalsIgnoreCase("")){
            categoryDao.save(category);
        }
    }

    @Override
    public void edit(int id, String name, Status status) {
        Category category = categoryDao.findOne(id);
        if(name != null && !name.equalsIgnoreCase("")){
            category.setName(name);
        }
        if(status != null && !status.toString().equalsIgnoreCase("")){
            category.setStatus(status);
        }
        categoryDao.save(category);
    }

    @Override
    public void rename(int idCategory, String newName) {
        Category category = categoryDao.findOne(idCategory);
        category.setName(newName);
        categoryDao.save(category);
    }

    @Override
    public void delete(int id) {
        if (id!=-1 && categoryDao.findOne(id).getTransactionList().isEmpty()){
            categoryDao.delete(id);
        }
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findOne(id);
    }

    @Override
    public List<Category> findByStatusOrderByName(Status status) {
        return categoryDao.findByStatusOrderByName(status);
    }
}
