package gov.jiusan.star.score.api;

/**
 * @author Marcus Lin
 */
public class RetrieveResponse {

    public static final RetrieveResponse NO_SCORE = new RetrieveResponse(false, Status.NO_SCORE);
    private boolean success;
    private Status status;
    private Score content;

    private RetrieveResponse() {

    }

    private RetrieveResponse(boolean success, Status status) {
        this.success = success;
        this.status = status;
    }

    private RetrieveResponse(boolean success, Status status, Score content) {
        this(success, status);
        this.content = content;
    }

    public static RetrieveResponse SUCCESS(Score content) {
        return new RetrieveResponse(true, Status.SUCCESS, content);
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
