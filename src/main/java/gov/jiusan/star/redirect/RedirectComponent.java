package gov.jiusan.star.redirect;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author Marcus Lin
 */
@Component
public class RedirectComponent implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        authorities.forEach(authority -> {
            switch (authority.getAuthority()) {
                case "ROLE_USER":
                    try {
                        redirectStrategy.sendRedirect(arg0, arg1, "/index");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "ROLE_ADMIN":
                    try {
                        redirectStrategy.sendRedirect(arg0, arg1, "/score/list");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        });
    }
}
