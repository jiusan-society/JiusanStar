package gov.jiusan.star.score.controller;

import gov.jiusan.star.score.Score;
import gov.jiusan.star.score.ScoreService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

/**
 * @author Marcus Lin
 */
@Named
@RequestScoped
public class ScoreController {

    @EJB
    private ScoreService ss;
    private List<Score> scores;

    public ScoreController() {
    }

    @PostConstruct
    private void init() {
        scores = ss.findAllScores();
    }

    public ScoreService getSs() {
        return ss;
    }

    public void setSs(ScoreService ss) {
        this.ss = ss;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
