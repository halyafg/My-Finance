package ua.lv.halya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.lv.halya.app.Period;
import ua.lv.halya.app.Status;
import ua.lv.halya.entity.User;
import ua.lv.halya.services.TransactionService;
import ua.lv.halya.services.UserService;
import ua.lv.halya.validator.UserValidator;

import java.security.Principal;
import java.sql.Date;

@Controller
@Transactional
public class UserController {

    private final String REGISTRATION = "registration";

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserValidator userValidator;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    private String openHomePage(){
        //Period.dateFrom = Period.dateTo = new Date(System.currentTimeMillis());

        return "home";
    }

    @RequestMapping (value = "/registration", method = RequestMethod.GET)
    private  String openRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return REGISTRATION;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()){
            return REGISTRATION;
        }

        userService.add(user);
        return "redirect: /";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    private String openLoginPage(Model model, String error){
        Period.dateFrom = Period.dateTo = null;
        if(error != null){
            model.addAttribute("error", "Email or password is incorrect");
        }
        return "login";
    }

    @RequestMapping(value = "/period", method = RequestMethod.GET)
    private String setPeriod(Model model){
        model.addAttribute("dateFrom", Period.dateFrom);
        model.addAttribute("dateTo", Period.dateTo);
        return "period";
    }


    @RequestMapping(value = "/period", method = RequestMethod.POST)
    private String setPeriod(@RequestParam ("dateFrom") Date dateFrom,
                             @RequestParam("dateTo") Date dateTo,
                             Model model){
        if(dateFrom.after(dateTo)){
            model.addAttribute("error", "The period should be from less to bigger");
            return "period";
        }
        Period.dateFrom = dateFrom;
        Period.dateTo = dateTo;
        return "redirect: /cabinet";
    }

    @RequestMapping(value = "" +"/cabinet", method = RequestMethod.GET)
    public String openCabinet(Principal principal, Model model){
        if(Period.dateFrom == null){
            Period.dateFrom = transactionService.findDateOfOldestTransaction(principal.getName());
            Period.dateTo = transactionService.findDateOfNewestTransaction(principal.getName());
        }
        model.addAttribute("periodFrom", Period.formatDate(Period.dateFrom));
        model.addAttribute("periodTo", Period.formatDate(Period.dateTo));
        model.addAttribute("statusIncome", Status.INCOME);
        model.addAttribute("statusOutcome", Status.OUTCOME);
        model.addAttribute("principal", userService.findByLogin(principal.getName()));
        model.addAttribute("userIncomes", transactionService.getUserCost(principal.getName(), Status.INCOME));
        model.addAttribute("userOutcomes", transactionService.getUserCost(principal.getName(), Status.OUTCOME));
        model.addAttribute("categoriesOut", transactionService.getUserFundsSeparateByCategory(principal.getName(), Status.OUTCOME));
        model.addAttribute("categoriesIn", transactionService.getUserFundsSeparateByCategory(principal.getName(), Status.INCOME));
        model.addAttribute("balance",transactionService.getUserCost(principal.getName(), Status.INCOME) - transactionService.getUserCost(principal.getName(), Status.OUTCOME) );
        return "cabinet";

    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.GET)
    public String editUser(Principal principal, Model model){
        model.addAttribute("user", userService.findByLogin(principal.getName()));
        return "editUser";
    }

    @RequestMapping(value = "/userEdit", method = RequestMethod.POST)
    public String editUser(@ModelAttribute User user){
        userService.edit(user);
        return "redirect: /cabinet";
    }

    @RequestMapping(value = "/passwordForgot", method = RequestMethod.GET)
    public  String setNewPassword() {
        return "passwordForgot";
    }

    @RequestMapping(value = "/passwordForgot", method = RequestMethod.POST)
    public  String setNewPassword(@RequestParam("email") String email) {
        userService.sendLinkToRestorePassword(email, "http://localhost:8080/restorePassword/");
        return "redirect:/";
    }

    @RequestMapping(value = "/restorePassword/{passwordEncoder}", method = RequestMethod.GET)
    public String restorePassword(@PathVariable String passwordEncoder, Model model){
        model.addAttribute("oldPassword", passwordEncoder);
        return "restorePassword";
    }
    @RequestMapping(value = "/restorePassword/{oldPassword}", method = RequestMethod.POST)
    public String restorePassword(@PathVariable String oldPassword,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String newPassword){
        userService.restorePassword(email, oldPassword, newPassword);
        return "redirect:/";
    }

}
