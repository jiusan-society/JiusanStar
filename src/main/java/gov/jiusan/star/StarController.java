package gov.jiusan.star;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Marcus Lin
 */
@Controller
public class StarController {

    @GetMapping(value = {"/", "/welcome"})
    public ModelAndView welcomePage() {
        return new ModelAndView("welcome");
    }
}
