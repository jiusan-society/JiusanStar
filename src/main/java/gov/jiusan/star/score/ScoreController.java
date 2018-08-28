package gov.jiusan.star.score;

import gov.jiusan.star.user.User;
import gov.jiusan.star.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "score")
public class ScoreController {

    private final UserService uService;

    private final ScoreService sService;

    @Autowired
    public ScoreController(UserService uService, ScoreService sService) {
        this.uService = uService;
        this.sService = sService;
    }

    @GetMapping(path = "list")
    public String findOwnScores(Model model) {
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = uService.findUserByUsername(userAccount);
        List<Score> scores = user.getOrg().getScores();
        model.addAttribute("scores", scores);
        return "score/score_list";
    }

    @GetMapping(path = "editor")
    public String editScore(@RequestParam("seq") Long seq, Model model) {
        Score score = sService.find(seq);
        return "score/score_editor";
    }

    @GetMapping
    public String viewScore(@RequestParam("seq") Long seq, Model model) {
        Score score = sService.find(seq);
        return "score/score_viewer";
    }

}
