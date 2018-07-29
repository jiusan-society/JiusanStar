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
import java.util.Optional;

@Controller
@RequestMapping(path = "score")
public class ScoreController {

    private final UserService uService;

    @Autowired
    public ScoreController(UserService uService) {
        this.uService = uService;
    }

    @GetMapping(path = "list")
    public String findOwnScores(Model model) {
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = uService.findUserByUsername(userAccount);
        List<Score> scores = user.getOrg().getScores();
        model.addAttribute("scores", scores);
        return "score/score_list";
    }

    @GetMapping
    public String findOwnScoreBySheet(@RequestParam("sheetSeq") Long sheetSeq, Model model) {
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = uService.findUserByUsername(userAccount);
        Optional<Score> score = user.getOrg().getScores().stream().filter(s -> s.getSheet().getSeq().equals(sheetSeq)).findAny();
        if (!score.isPresent()) {
            return "error";
        }
        model.addAttribute("score", score.get());
        return "score/score_viewer";
    }

}
