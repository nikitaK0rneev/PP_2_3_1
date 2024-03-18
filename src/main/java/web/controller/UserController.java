package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/users";
    }

    @GetMapping(value = "/users")
    public String getAllUsers(ModelMap model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping(value = "/add")
    public String addUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/users")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/remove")
    public String removeUser(@RequestParam("id") int id) {
        userService.removeUserById(id);
        return "redirect:/users";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("id") int id, ModelMap model) {
        User user = userService.find(id);
        model.addAttribute("user", user);
        return "/edit";
    }

    @PostMapping("/update")
    public String saveEditedUser(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/users";
    }

}
