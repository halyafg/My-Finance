package ua.lv.halya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.lv.halya.app.Status;
import ua.lv.halya.entity.Transaction;
import ua.lv.halya.services.CategoryService;
import ua.lv.halya.services.CurrencyService;
import ua.lv.halya.services.TransactionService;
import ua.lv.halya.services.UserService;

import java.security.Principal;
import java.sql.Date;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController {

    private final String TRANSACTION = "transaction";
    private final String ADD_TRANSACTION = "addTransaction";
    private final String EDIT_TRANSACTION = "editTransaction";
    private final String REDIRECT_CABINET = "redirect:/cabinet";

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/addPage/{status}", method = RequestMethod.GET)
    private String openAddTransactionPage(@PathVariable Status status, Model model, Principal principal){
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("categories", categoryService.findByStatusOrderByName(status));
        model.addAttribute("currentDate", transactionService.currentDate());
        model.addAttribute("status", status);
        model.addAttribute(TRANSACTION, new Transaction());
        model.addAttribute("principal", userService.findByLogin(principal.getName()));
        return ADD_TRANSACTION;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    private String addTransaction(@RequestParam("date") Date date,
                                  @RequestParam ("idCategory") Integer categoryId,
                                  @RequestParam("amount") Double amount,
                                  @RequestParam("idCurrency") Integer currencyId,
                                  @RequestParam("comment") String comment,
                                  Principal principal){
        try {
            transactionService.add(principal.getName(), categoryService.findById(categoryId), amount, currencyService.findById(currencyId), date, comment);
        }catch (Exception e){
            System.out.println(e.toString());
        }

        return REDIRECT_CABINET;
    }

    @RequestMapping(value = "/editPage/{status}/{idTransaction}", method = RequestMethod.GET)
    private String openEditTransactionPage(@PathVariable int idTransaction,
                                           @PathVariable Status status,
                                           Model model, Principal principal){
        model.addAttribute("transaction", transactionService.findById(idTransaction));
        model.addAttribute("categories", categoryService.findByStatusOrderByName(status));
        model.addAttribute("currencies", currencyService.findAll());
        model.addAttribute("status", status);
        model.addAttribute("principal", userService.findByLogin(principal.getName()));
        return EDIT_TRANSACTION;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    private String editTransaction(@RequestParam("transactionId") int transactionId,
                                   @RequestParam("date") Date date,
                                   @RequestParam("categoryId") int categoryId,
                                   @RequestParam("amount") double amount,
                                   @RequestParam("idCurrency") int idCurrency,
                                   @RequestParam("comment") String comment){
        transactionService.edit(transactionId, date, categoryId, amount, currencyService.findById(idCurrency), comment);
        return REDIRECT_CABINET;
    }

    @RequestMapping(value = "/delete/{idTransaction}", method = RequestMethod.POST)
    private String deleteTransaction(@PathVariable int idTransaction){
        transactionService.delete(idTransaction);
        return REDIRECT_CABINET;
    }

}
