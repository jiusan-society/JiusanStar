package gov.jiusan.star;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Marcus Lin
 */
@Controller
public class StarController {

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping(value = "/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }
}
