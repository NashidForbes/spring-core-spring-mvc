package guru.springframework.controller;

import guru.springframework.domain.User;
import guru.springframework.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping({"/users", "/user/list"})
    public String listAllUsers(Model Model) {
        List<User> allUsers = (List<User>) userService.listAll();
        Model.addAttribute("allUsers", allUsers);
        return "users/users";

    }

    @RequestMapping("/user/update/{id}")
    public String editUser(@PathVariable String id, Model model) {
        User user = userService.getById(Integer.parseInt(id));
        model.addAttribute("user", user);
        return "users/userform";
    }


    @RequestMapping("/user/get/{id}")
    public String findUserById(@PathVariable String id, Model Model) {
        User user = userService.getById(Integer.parseInt(id));
        Model.addAttribute("user", user);
        return "users/user";

    }

    @RequestMapping("/user/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "users/userform";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveOrUpdateUser(User user) {
        User savedUser = userService.saveOrUpdate(user);
        return "redirect:/v1/user/get/" + savedUser.getId();
    }

    // not functional, cascade type bounded to User object, merge and persist operations only.
    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public String editUser(User user) {
        userService.deleteById(user.getId());
        return "redirect:/v1/users";
    }
}
