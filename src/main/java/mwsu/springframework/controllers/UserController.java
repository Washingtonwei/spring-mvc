package mwsu.springframework.controllers;

import mwsu.springframework.domain.User;
import mwsu.springframework.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by bingyang.wei on 5/15/2017.
 */
@RequestMapping("/user")
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model){
        model.addAttribute("users", userService.listAll());
        return "user/list";
    }
    @RequestMapping("/show/{id}")
    public String getProduct(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "user/show";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

        model.addAttribute("user", userService.getById(id));
        return "user/userform";
    }

    @RequestMapping("/new")
    public String newProduct(Model model){
        model.addAttribute("user", new User());
        return "user/userform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveOrUpdate(User user){
        User savedUser = userService.saveOrUpdate(user);
        return "redirect:/user/show/" + savedUser.getId();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/user/list";
    }
}
