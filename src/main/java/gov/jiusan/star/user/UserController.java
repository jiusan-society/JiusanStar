package gov.jiusan.star.user;

import gov.jiusan.star.annotation.LoggedUser;
import gov.jiusan.star.user.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String getProfile(Model model, @LoggedUser CustomUserDetails userDetails) {
        Profile p = UserUtil.convertToModel(userDetails.getUser());
        model.addAttribute("profile", p);
        return "user/user_profile";
    }

    @PostMapping(path = "profile")
    public String updateProfile(Profile model, @LoggedUser CustomUserDetails userDetails) {
        userDetails.getUser().setNickname(model.getNickName());
        userDetails.getUser().setEmail(model.getEmail());
        userDetails.getUser().setPhoneNum(model.getPhoneNum());
        repository.save(userDetails.getUser());
        return "redirect:/user/profile";
    }

}
