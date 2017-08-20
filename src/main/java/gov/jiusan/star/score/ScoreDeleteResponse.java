package gov.jiusan.star.score;

public class ScoreDeleteResponse {

    private boolean success;
    private ScoreResponseStatus status;

    public static final ScoreDeleteResponse NO_SCORE = new ScoreDeleteResponse(false, ScoreResponseStatus.NO_SCORE);
    public static final ScoreDeleteResponse SUCCESS = new ScoreDeleteResponse(true, ScoreResponseStatus.SUCCESS);

    private ScoreDeleteResponse() {

    }

    private ScoreDeleteResponse(boolean success, ScoreResponseStatus status) {
        this.success = success;
        this.status = status;
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
}
