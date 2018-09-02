package gov.jiusan.star.score;

import gov.jiusan.star.score.model.ScoreDTO;
import gov.jiusan.star.user.User;
import gov.jiusan.star.user.UserService;
import gov.jiusan.star.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

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
    public String findOwnEffectiveScores(Model model) {
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = uService.findUserByUsername(userAccount);
        List<Score> scores = user.getOrg().getScores().stream()
            .filter(s -> s.getSheetPlan().isEffective())
            .collect(Collectors.toList());
        model.addAttribute("scores", scores);
        return "score/score_list";
    }

    @GetMapping(path = "editor")
    public String editScore(@RequestParam("seq") Long seq, Model model) {
        model.addAttribute("sheet",  sService.find(seq).getSheetPlan().getSheet());
        model.addAttribute("score", new ScoreDTO());
        return "score/score_editor";
    }

    @GetMapping
    public String viewScore(@RequestParam("seq") Long seq, Model model) {
        Score score = sService.find(seq);
        return "score/score_viewer";
    }

    @PostMapping(path = "update")
    public String updateScore(@RequestParam("seq") Long seq, ScoreDTO scoreDTO) {
        int sATotalScore = scoreDTO.getsADetails().values().stream().mapToInt(score -> score).sum();
        String sADetailsString = JacksonUtil.toString(scoreDTO.getsADetails()).get();
        Score score = sService.find(seq);
        score.setsATotalScore(sATotalScore);
        score.setsADetails(sADetailsString);
        score.setsAFinished(true);
        sService.update(score);
        return "redirect:/score?seq=" + seq;
    }

}
