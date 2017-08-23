package gov.jiusan.star.score.api;

public class DeleteResponse {

    private boolean success;
    private Status status;

    public static final DeleteResponse NO_SCORE = new DeleteResponse(false, Status.NO_SCORE);
    public static final DeleteResponse SUCCESS = new DeleteResponse(true, Status.SUCCESS);

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
