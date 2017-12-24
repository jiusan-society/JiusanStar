package gov.jiusan.star.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    // REMIND[2017-12-24][Marcus Lin]: 用于 thymeleaf form 的 obj binding
    @ModelAttribute("score")
    public gov.jiusan.star.score.model.Score generateScoreObjForBinding() {
        return new gov.jiusan.star.score.model.Score();
    }

    // TODO[2017-12-24][Marcus Lin]: 页面显示查找到的数据功能待添加
    @GetMapping(path = "editor")
    public String scoreEditPage(@RequestParam(value = "seq", required = false) Long seq, Model model) {
        Optional<Score> score = scoreService.find(seq);
        score.ifPresent(score1 -> model.addAttribute("score", ScoreUtil.convertToDTO(score1)));
        return "score_editor";
    }

    @PostMapping
    public String createScore(@ModelAttribute gov.jiusan.star.score.model.Score score, Model model) {
        model.addAttribute("score", ScoreUtil.convertToDTO(scoreService.create(score)));
        return "score_viewer";
    }

    // TODO[2017-12-24][Marcus Lin]: 页面显示查找到的数据功能待添加
    @GetMapping
    public String retrieveScore(@RequestParam(value = "seq", required = false) Long seq, Model model) {
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

    @DeleteMapping
    public String deleteScore(@RequestParam("seq") Long seq) {
        scoreService.delete(seq);
        return "success";
    }
}
