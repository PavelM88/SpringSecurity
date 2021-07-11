package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.dao.UserDao;
import web.model.User;
import web.service.UserService;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserDao userDao;

    public UserController(UserService userService, UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @GetMapping("/user")
    public String getUser(Model model, Principal principal) {
        User user = userDao.getUserByName(principal.getName());
        model.addAttribute(user);
        return "show";
    }
}
