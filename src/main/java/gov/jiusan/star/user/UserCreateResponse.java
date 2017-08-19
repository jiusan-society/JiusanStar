package gov.jiusan.star.user;

public class UserCreateResponse {

    private boolean success;
    private Status status;
    private Long content;

    public static final UserCreateResponse USER_NAME_ERROR = new UserCreateResponse(false, Status.USER_NAME_ERROR);
    public static final UserCreateResponse PASSWORD_ERROR = new UserCreateResponse(false, Status.PASSWORD_ERROR);
    public static final UserCreateResponse ORG_NAME_ERROR = new UserCreateResponse(false, Status.ORG_NAME_ERROR);

    public static UserCreateResponse SUCCESS(Long content) {
        UserCreateResponse userCreateResponse = new UserCreateResponse(true, Status.SUCCESS);
        userCreateResponse.setContent(content);
        return userCreateResponse;
    }

    public enum Status {
        SUCCESS, USER_NAME_ERROR, ORG_NAME_ERROR, PASSWORD_ERROR
    }

    public UserCreateResponse() {
    }

    private UserCreateResponse(boolean success, Status status) {
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

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
    }
}
