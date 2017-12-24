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

    // TODO[2017-12-24][Marcus Lin]: 页面显示查找到的数据功能待添加
    @GetMapping(path = "editor/{seq}")
    public String scoreEditPage(@PathVariable("seq") Long seq, Model model) {
        Optional<Score> score = scoreService.find(seq);
        if (score.isPresent()) {
            model.addAttribute("score", ScoreUtil.convertToDTO(score.get()));
            return "score_editor";
        }
        return "error";
    }

    @PostMapping
    public String createScore(@ModelAttribute gov.jiusan.star.score.model.Score score, Model model) {
        model.addAttribute("score", ScoreUtil.convertToDTO(scoreService.create(score)));
        return "score_viewer";
    }

    // TODO[2017-12-24][Marcus Lin]: 页面显示查找到的数据功能待添加
    @GetMapping(path = "{seq}")
    public String retrieveScore(@PathVariable("seq") Long seq, Model model) {
        Optional<Score> score = scoreService.find(seq);
        if (score.isPresent()) {
            model.addAttribute("score", ScoreUtil.convertToDTO(score.get()));
            return "score_viewer";
        }
        return "error";
    }

    @PutMapping
    public String updateScore(@ModelAttribute gov.jiusan.star.score.model.Score score, Model model) {
        Optional<Score> existedScore = scoreService.find(score.getSeq());
        if (existedScore.isPresent()) {
            model.addAttribute("score", ScoreUtil.convertToDTO(scoreService.update(existedScore.get(), score)));
            return "score_viewer";
        }
        return "error";
    }

    @DeleteMapping(path = "{seq}")
    public String deleteScore(@PathVariable("seq") Long seq) {
        scoreService.delete(seq);
        return "success";
    }
}
