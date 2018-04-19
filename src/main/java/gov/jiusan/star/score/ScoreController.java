package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "score")
public class ScoreController {

    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping(path = "editor")
    public String editScore(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        if (seq == null) {
            model.addAttribute("score", new gov.jiusan.star.score.model.Score());
            return "score/score_editor";
        }
        Optional<Score> score = scoreService.find(seq);
        if (score.isPresent()) {
            model.addAttribute("score", ScoreUtil.convertToDTO(score.get()));
            return "score/score_editor";
        }
        return "error";
    }

    @PostMapping
    public String createScore(@ModelAttribute gov.jiusan.star.score.model.Score score) {
        return "redirect:/score?seq=" + scoreService.create(score);
    }

    @GetMapping
    public String retrieveScore(@RequestParam(value = "seq") Long seq, Model model) {
        Optional<Score> score = scoreService.find(seq);
        if (score.isPresent()) {
            model.addAttribute("score", ScoreUtil.convertToDTO(score.get()));
            return "score/score_viewer";
        }
        return "error";
    }

    @PostMapping(path = "update")
    public String updateScore(@RequestParam(value = "seq") Long seq, @ModelAttribute gov.jiusan.star.score.model.Score score) {
        Optional<Score> existedScore = scoreService.find(seq);
        return existedScore.map(s -> "redirect:/score?seq=" + scoreService.update(s, score)).orElse("error");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "list")
    public String retrieveScoreList(Model model) {
        model.addAttribute("scoreList", scoreService.findAll().stream().map(ScoreUtil::convertToDTO).collect(Collectors.toList()));
        return "score/score_list";
    }

}
