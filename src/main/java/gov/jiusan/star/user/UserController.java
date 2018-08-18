package gov.jiusan.star.user;

import gov.jiusan.star.annotation.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "user")
public class UserController {

    private final UserRepository repository;

    @Autowired
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "profile")
    public String getProfile(Model model, @LoggedUser UserDetailsImpl userDetails) {
        model.addAttribute("profile", userDetails.getUser());
        return "user/user_profile";
    }

    @PostMapping(path = "profile")
    public String updateProfile(User userModel, @LoggedUser UserDetailsImpl userDetails) {
        userDetails.getUser().setNickname(userModel.getNickname());
        userDetails.getUser().setEmail(userModel.getEmail());
        userDetails.getUser().setPhoneNum(userModel.getPhoneNum());
        repository.save(userDetails.getUser());
        return "redirect:/user/profile";
    }

}
