package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Marcus Lin
 */
@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @GetMapping(value = {"/score"})
    public String welcomePage() {
        return "score";
    }
}
