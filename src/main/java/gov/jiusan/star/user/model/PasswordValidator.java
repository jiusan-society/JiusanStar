/*
 * Copyright 2019 Marcus Lin
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package gov.jiusan.star.user.model;

import gov.jiusan.star.user.UserDetailsImpl;
import gov.jiusan.star.util.PasswordUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * @author Marcus Lin
 */
@Component
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Password.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPwd", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPwd", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmNewPwd", "field.required");
        Password password = (Password) target;
        UserDetailsImpl loggedUser = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!PasswordUtil.matches(password.getCurrentPwd(), loggedUser.getUser().getPassword())) {
            errors.rejectValue("currentPwd", "password.notMatch.dbPwd");
        }
        if (!Objects.equals(password.getNewPwd(), password.getConfirmNewPwd())) {
            errors.rejectValue("confirmNewPwd", "password.notMatch.newPwd");
        }
    }

}
