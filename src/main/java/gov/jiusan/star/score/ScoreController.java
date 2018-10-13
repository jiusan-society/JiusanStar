package gov.jiusan.star.score;

import gov.jiusan.star.org.Org;
import gov.jiusan.star.org.OrgService;
import gov.jiusan.star.org.OrgUtil;
import gov.jiusan.star.org.model.OrgDTO;
import gov.jiusan.star.score.model.ScoreDTO;
import gov.jiusan.star.sheet.SheetUtil;
import gov.jiusan.star.user.User;
import gov.jiusan.star.user.UserService;
import gov.jiusan.star.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Marcus Lin
 */
@Controller
@RequestMapping(path = "score")
public class ScoreController {

    private final UserService uService;

    private final ScoreService sService;

    private final OrgService oService;

    @Autowired
    public ScoreController(UserService uService, ScoreService sService, OrgService oService) {
        this.uService = uService;
        this.sService = sService;
        this.oService = oService;
    }

    @GetMapping(path = "list/own")
    public String findOwnEffectiveScores(Model model) {
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = uService.findUserByUsername(userAccount);
        List<Score> scores = user.getOrg().getScores().stream()
            .filter(s -> s.getSheetPlan().isEffective())
            .collect(Collectors.toList());
        model.addAttribute("scores", scores);
        return "score/score_list_own";
    }

    @GetMapping(path = "list/children")
    public String findChildrenEffectiveScores(Model model) {
        String userAccount = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = uService.findUserByUsername(userAccount);
        // 找到该组织下的子组织
        List<Org> subOrgs = oService.findOrgsByParentCode(user.getOrg().getCode());
        Map<OrgDTO, List<Score>> scoresOfOrg = new TreeMap<>();
        for (Org o : subOrgs) {
            scoresOfOrg.put(OrgUtil.convert(o), o.getScores().stream().filter(s -> s.getSheetPlan().isEffective()).collect(Collectors.toList()));
        }
        model.addAttribute("scoresOfOrg", scoresOfOrg);
        return "score/score_list_children";
    }

    @PreAuthorize("hasAnyRole('ROLE_L2_ADM', 'ROLE_L3_ADM')")
    @GetMapping(path = "editor/sa")
    public String editSAScore(@RequestParam("seq") Long seq, Model model) {
        Score score = sService.find(seq);
        model.addAttribute("sheet", SheetUtil.convert(score.getSheetPlan().getSheet()));
        model.addAttribute("score", ScoreUtil.convert(score));
        return "score/score_editor_sa";
    }

    @PreAuthorize("hasAnyRole('ROLE_L1_ADM', 'ROLE_L2_ADM')")
    @GetMapping(path = "editor/aa")
    public String editAAScore(@RequestParam("seq") Long seq, Model model) {
        Score score = sService.find(seq);
        model.addAttribute("sheet", SheetUtil.convert(score.getSheetPlan().getSheet()));
        model.addAttribute("score", ScoreUtil.convert(score));
        return "score/score_editor_aa";
    }

    @GetMapping
    public String viewScore(@RequestParam("seq") Long seq, Model model) {
        Score score = sService.find(seq);
        model.addAttribute("sheet", SheetUtil.convert(score.getSheetPlan().getSheet()));
        model.addAttribute("score", ScoreUtil.convert(score));
        return "score/score_viewer";
    }

    @PreAuthorize("hasAnyRole('ROLE_L2_ADM', 'ROLE_L3_ADM')")
    @PostMapping(path = "update/sa")
    public String updateSAScore(@RequestParam("seq") Long seq, ScoreDTO scoreDTO) {
        int sATotalScore = scoreDTO.getsADetails().values().stream().mapToInt(score -> score).sum();
        String sADetailsString = JacksonUtil.toString(scoreDTO.getsADetails()).get();
        Score score = sService.find(seq);
        score.setsATotalScore(sATotalScore);
        score.setsADetails(sADetailsString);
        score.setsAFinished(true);
        score.setFinalScore((score.getsATotalScore() + score.getaATotalScore()) / (double) 2);
        sService.update(score);
        return "redirect:/score?seq=" + seq;
    }

    @PreAuthorize("hasAnyRole('ROLE_L1_ADM', 'ROLE_L2_ADM')")
    @PostMapping(path = "update/aa")
    public String updateAAScore(@RequestParam("seq") Long seq, ScoreDTO scoreDTO) {
        int aATotalScore = scoreDTO.getaADetails().values().stream().mapToInt(score -> score).sum();
        String aADetailsString = JacksonUtil.toString(scoreDTO.getaADetails()).get();
        Score score = sService.find(seq);
        score.setaATotalScore(aATotalScore);
        score.setaADetails(aADetailsString);
        score.setaAFinished(true);
        score.setFinalScore((score.getsATotalScore() + score.getaATotalScore()) / (double) 2);
        sService.update(score);
        return "redirect:/score?seq=" + seq;
    }

}
