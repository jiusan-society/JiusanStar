package gov.jiusan.star.score;

/**
 * @author Marcus Lin
 */
public class ScoreRetrieveResponse {

    private boolean success;
    private ScoreResponseStatus status;
    private Score content;

    static final ScoreRetrieveResponse NO_SCORE = new ScoreRetrieveResponse(false, ScoreResponseStatus.NO_SCORE);

    static ScoreRetrieveResponse SUCCESS(Score content) {
        return new ScoreRetrieveResponse(true, ScoreResponseStatus.SUCCESS, content);
    }

    private ScoreRetrieveResponse() {

    }

    private ScoreRetrieveResponse(boolean success, ScoreResponseStatus status) {
        this.success = success;
        this.status = status;
    }

    private ScoreRetrieveResponse(boolean success, ScoreResponseStatus status, Score content) {
        this(success, status);
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ScoreResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ScoreResponseStatus status) {
        this.status = status;
    }

    public Score getContent() {
        return content;
    }

    public void setContent(Score content) {
        this.content = content;
    }
}
