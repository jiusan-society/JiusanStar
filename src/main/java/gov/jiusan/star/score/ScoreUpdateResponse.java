package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreUpdateResponse {

    private boolean success;
    private ScoreResponseStatus status;
    private Score content;

    public static final ScoreUpdateResponse NO_CONFER_ACTIVITY_SCORE = new ScoreUpdateResponse(false, ScoreResponseStatus.NO_CONFER_ACTIVITY_SCORE);
    public static final ScoreUpdateResponse NO_SOCIAL_WORK_SCORE = new ScoreUpdateResponse(false, ScoreResponseStatus.NO_SOCIAL_WORK_SCORE);
    public static final ScoreUpdateResponse NO_SOCIAL_CONTRIBUTION_SCORE = new ScoreUpdateResponse(false, ScoreResponseStatus.NO_SOCIAL_CONTRIBUTION_SCORE);
    public static final ScoreUpdateResponse NO_POLITIC_ACTIVITY_SCORE = new ScoreUpdateResponse(false, ScoreResponseStatus.NO_POLITIC_ACTIVITY_SCORE);

    public static ScoreUpdateResponse SUCCESS(Score content) {
        return new ScoreUpdateResponse(true, ScoreResponseStatus.SUCCESS, content);
    }

    private ScoreUpdateResponse() {
    }

    private ScoreUpdateResponse(boolean success, ScoreResponseStatus status) {
        this.success = success;
        this.status = status;
    }

    private ScoreUpdateResponse(boolean success, ScoreResponseStatus status, Score content) {
        this(success, status);
        this.content = content;
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

    public Score getContent() {
        return content;
    }

    public void setContent(Score content) {
        this.content = content;
    }
}
