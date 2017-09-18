package gov.jiusan.star.score.api;

/**
 * @author Marcus Lin
 */
public class UpdateResponse {

    public static final UpdateResponse NO_CONFER_ACTIVITY_SCORE = new UpdateResponse(false, Status.NO_CONFER_ACTIVITY_SCORE);
    public static final UpdateResponse NO_SOCIAL_WORK_SCORE = new UpdateResponse(false, Status.NO_SOCIAL_WORK_SCORE);
    public static final UpdateResponse NO_SOCIAL_CONTRIBUTION_SCORE = new UpdateResponse(false, Status.NO_SOCIAL_CONTRIBUTION_SCORE);
    public static final UpdateResponse NO_POLITIC_ACTIVITY_SCORE = new UpdateResponse(false, Status.NO_POLITIC_ACTIVITY_SCORE);
    public static final UpdateResponse NO_SCORE = new UpdateResponse(false, Status.NO_SCORE);

    private boolean success;
    private Status status;
    private Score content;

    private UpdateResponse() {
    }

    private UpdateResponse(boolean success, Status status) {
        this.success = success;
        this.status = status;
    }

    private UpdateResponse(boolean success, Status status, Score content) {
        this(success, status);
        this.content = content;
    }

    public static UpdateResponse SUCCESS(Score content) {
        return new UpdateResponse(true, Status.SUCCESS, content);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Score getContent() {
        return content;
    }

    public void setContent(Score content) {
        this.content = content;
    }
}
