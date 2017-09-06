package gov.jiusan.star.score.api;

/**
 * @author Marcus Lin
 */
public class DeleteResponse {

    public static final DeleteResponse SUCCESS = new DeleteResponse(true, Status.SUCCESS);
    public static final DeleteResponse NO_SCORE = new DeleteResponse(true, Status.NO_SCORE);

    private boolean success;
    private Status status;

    private DeleteResponse() {
    }

    private DeleteResponse(boolean success, Status status) {
        this.success = success;
        this.status = status;
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
}
