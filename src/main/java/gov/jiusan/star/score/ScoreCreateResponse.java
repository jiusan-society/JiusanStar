package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreCreateResponse {

    private boolean success;
    private ScoreResponseStatus status;

    static final ScoreCreateResponse SUCCESS = new ScoreCreateResponse(true, ScoreResponseStatus.SUCCESS);
    static final ScoreCreateResponse NO_CONFER_ACTIVITY_SCORE = new ScoreCreateResponse(false, ScoreResponseStatus.NO_CONFER_ACTIVITY_SCORE);
    static final ScoreCreateResponse NO_SOCIAL_WORK_SCORE = new ScoreCreateResponse(false, ScoreResponseStatus.NO_SOCIAL_WORK_SCORE);
    static final ScoreCreateResponse NO_SOCIAL_CONTRIBUTION_SCORE = new ScoreCreateResponse(false, ScoreResponseStatus.NO_SOCIAL_CONTRIBUTION_SCORE);
    static final ScoreCreateResponse NO_POLITIC_ACTIVITY_SCORE = new ScoreCreateResponse(false, ScoreResponseStatus.NO_POLITIC_ACTIVITY_SCORE);


    private ScoreCreateResponse() {
    }

    private ScoreCreateResponse(boolean success, ScoreResponseStatus status) {
        this.success = success;
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ScoreResponseStatus getScoreResponseStatus() {
        return status;
    }

    public void setScoreResponseStatus(ScoreResponseStatus status) {
        this.status = status;
    }
}
