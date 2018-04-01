package ua.lv.halya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lv.halya.app.Period;
import ua.lv.halya.app.Status;
import ua.lv.halya.entity.Category;
import ua.lv.halya.services.CategoryService;
import ua.lv.halya.services.TransactionService;
import ua.lv.halya.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {
    private final String CATEGORY = "category";
    private final String EDIT_CATEGORY = "editCategory";
    private final String REDIRECT_CATEGORY_INF = "redirect:/category/editPage/";
    private final String CATEGORY_INF = "categoryInf";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/editPage/{status}", method = RequestMethod.GET)
    private String openEditTransactionNamePage(@PathVariable Status status, Model model, Principal principal){
        model.addAttribute("categories", categoryService.findByStatusOrderByName(status));
        model.addAttribute("status", status);
        model.addAttribute(CATEGORY, new Category());
        model.addAttribute("principal", userService.findByLogin(principal.getName()));
        return EDIT_CATEGORY;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addCategory(@ModelAttribute Category category){
        categoryService.add(category);
        return REDIRECT_CATEGORY_INF + category.getStatus();
    }

    @RequestMapping(value="/rename", method = RequestMethod.POST)
    private  String renameCategory(@RequestParam("idCategory") int idCategory,
                                       @RequestParam("newName") String newName){
        categoryService.rename(idCategory, newName);
        return REDIRECT_CATEGORY_INF + categoryService.findById(idCategory).getStatus();
    }

    @RequestMapping(value = "/delete/{status}", method = RequestMethod.POST)
    private String deleteCategory(@PathVariable Status status,
                                      @RequestParam("idCategory") int idCategory){
        categoryService.delete(idCategory);
        return REDIRECT_CATEGORY_INF + status;
    }

    @RequestMapping(value = "/inf/{name}/{sum}", method = RequestMethod.GET)
    private String openInfPage(@PathVariable String name,
                               @PathVariable double sum,
                               Model model,
                               Principal principal){
        model.addAttribute("categories", transactionService.findUserTransactionsByName(principal.getName(), name));
        model.addAttribute("categoryName", name);
        model.addAttribute("suma", sum);
        model.addAttribute("periodFrom", Period.formatDate(Period.dateFrom));
        model.addAttribute("periodTo", Period.formatDate(Period.dateTo));
        model.addAttribute("principal", userService.findByLogin(principal.getName()));
        return CATEGORY_INF;
    }
}
