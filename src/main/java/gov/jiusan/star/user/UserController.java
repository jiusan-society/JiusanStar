package gov.jiusan.star.user;

import gov.jiusan.star.annotation.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "user")
public class UserController {

    @GetMapping(path = "profile")
    public String getProfile(Model model, @LoggedUser UserDetailsImpl userDetails) {
        model.addAttribute("profile", userDetails.getUser());
        return "user/user_profile";
    }

}
