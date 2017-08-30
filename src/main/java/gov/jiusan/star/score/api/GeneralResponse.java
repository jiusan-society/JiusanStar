package gov.jiusan.star.score.api;

/**
 * @author Marcus Lin
 */
public class GeneralResponse {

    private boolean success;
    private Status status;

    public static final GeneralResponse SUCCESS = new GeneralResponse(true, Status.SUCCESS);
    public static final GeneralResponse NO_SCORE = new GeneralResponse(true, Status.NO_SCORE);
    public static final GeneralResponse NO_CONFER_ACTIVITY_SCORE = new GeneralResponse(false, Status.NO_CONFER_ACTIVITY_SCORE);
    public static final GeneralResponse NO_SOCIAL_WORK_SCORE = new GeneralResponse(false, Status.NO_SOCIAL_WORK_SCORE);
    public static final GeneralResponse NO_SOCIAL_CONTRIBUTION_SCORE = new GeneralResponse(false, Status.NO_SOCIAL_CONTRIBUTION_SCORE);
    public static final GeneralResponse NO_POLITIC_ACTIVITY_SCORE = new GeneralResponse(false, Status.NO_POLITIC_ACTIVITY_SCORE);

    private GeneralResponse() {
    }

    private GeneralResponse(boolean success, Status status) {
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
