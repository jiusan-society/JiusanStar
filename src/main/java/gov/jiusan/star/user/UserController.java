/*
 * Copyright 2019 Marcus Lin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.jiusan.star.user;

import gov.jiusan.star.user.model.Password;
import gov.jiusan.star.user.model.Profile;
import gov.jiusan.star.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "user")
public class UserController {

    private final UserRepository repository;

    private final Validator validator;

    @Autowired
    public UserController(UserRepository repository, @Qualifier("passwordValidator") Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @GetMapping(path = "profile")
    public String getProfile(Model model, @LoggedUser UserDetailsImpl userDetails) {
        Profile p = UserUtil.convertToModel(userDetails.getUser());
        model.addAttribute("profile", p);
        return "user/user_profile";
    }

    @PostMapping(path = "profile")
    public String updateProfile(@Valid Profile profile, BindingResult result, @LoggedUser UserDetailsImpl userDetails, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            return "user/user_profile";
        }
        userDetails.getUser().setNickname(profile.getNickName());
        userDetails.getUser().setEmail(profile.getEmail());
        userDetails.getUser().setPhoneNum(profile.getPhoneNum());
        repository.save(userDetails.getUser());
        // TODO 这边文字后面要移到 messages.properties 里
        redirAttrs.addFlashAttribute("successMsg", "信息更新成功");
        return "redirect:/user/profile";
    }

    @GetMapping(path = "password")
    public String getChangePasswordPage(Model model) {
        model.addAttribute("password", new Password());
        return "user/change_password";
    }

    @PostMapping(path = "password")
    public String changePassword(@Valid Password password, BindingResult result, @LoggedUser UserDetailsImpl userDetails, RedirectAttributes redirAttrs) {
        validator.validate(password, result);
        if (result.hasErrors()) {
            return "user/change_password";
        }
        userDetails.getUser().setPassword(PasswordUtil.encode(password.getNewPwd()));
        repository.save(userDetails.getUser());
        // TODO 这边文字后面要移到 messages.properties 里
        redirAttrs.addFlashAttribute("successMsg", "密码更新成功，将于下一次登录时生效");
        return "redirect:/user/password";
    }

}
