package gov.jiusan.star.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import java.util.Collection;

/**
 * @author Marcus Lin
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/", "/index", "/score/**").authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .successHandler(((req, resp, auth) -> {
                Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                authorities.forEach(authority -> {
                    switch (authority.getAuthority()) {
                        case "ROLE_USER":
                            try {
                                redirectStrategy.sendRedirect(req, resp, "/index");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "ROLE_ADMIN":
                            try {
                                redirectStrategy.sendRedirect(req, resp, "/score/list");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        default:
                            break;
                    }
                });
            }))
            .and()
            .logout();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("admin").password("admin").roles("ADMIN")
            .and()
            .withUser("user").password("user").roles("USER");
    }
}
