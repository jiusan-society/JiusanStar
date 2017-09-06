package gov.jiusan.star.score.api;

/**
 * @author Marcus Lin
 */
public class CreateResponse {

    public static final CreateResponse SUCCESS = new CreateResponse(true, Status.SUCCESS);
    public static final CreateResponse NO_CONFER_ACTIVITY_SCORE = new CreateResponse(false, Status.NO_CONFER_ACTIVITY_SCORE);
    public static final CreateResponse NO_SOCIAL_WORK_SCORE = new CreateResponse(false, Status.NO_SOCIAL_WORK_SCORE);
    public static final CreateResponse NO_SOCIAL_CONTRIBUTION_SCORE = new CreateResponse(false, Status.NO_SOCIAL_CONTRIBUTION_SCORE);
    public static final CreateResponse NO_POLITIC_ACTIVITY_SCORE = new CreateResponse(false, Status.NO_POLITIC_ACTIVITY_SCORE);

    private boolean success;
    private Status status;

    private CreateResponse() {
    }

    private CreateResponse(boolean success, Status status) {
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
