package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    // REMIND[2017-12-24][Marcus Lin]: 用于 thymleaf form 的 obj binding
    @ModelAttribute("score")
    public gov.jiusan.star.score.model.Score generateScoreObjForBinding() {
        return new gov.jiusan.star.score.model.Score();
    }

    @GetMapping
    public String scoreViewPage() {
        return "score_viewer";
    }

    @GetMapping(path = "editor")
    public String scoreEditPage() {
        return "score_editor";
    }

    @PostMapping
    public String createScore(@ModelAttribute gov.jiusan.star.score.model.Score score) {
        scoreService.create(score);
        return "success";
    }

    @GetMapping(path = "{seq}")
    public String retrieveScore(@PathVariable("seq") Long seq, Model model) {
        Optional<Score> score = scoreService.find(seq);
        score.ifPresent(score1 -> model.addAttribute("score", ScoreUtil.convertToDTO(score1)));
        return "score_viewer";
    }

    @PutMapping(path = "{seq}")
    public String updateScore(@PathVariable("seq") Long seq, @ModelAttribute gov.jiusan.star.score.model.Score score) {
        scoreService.update(seq, score);
        return "success";
    }

    @DeleteMapping(path = "{seq}")
    public String deleteScore(@PathVariable("seq") Long seq) {
        scoreService.delete(seq);
        return "success";
    }
}
