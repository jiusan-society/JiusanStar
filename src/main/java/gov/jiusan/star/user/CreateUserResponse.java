package gov.jiusan.star.user;

import java.io.Serializable;

public class CreateUserResponse implements Serializable {

    private boolean success;
    private Status status;
    private Long content;

    public static final CreateUserResponse USER_NAME_ERROR = new CreateUserResponse(false, Status.USER_NAME_ERROR);
    public static final CreateUserResponse PASSWORD_ERROR = new CreateUserResponse(false, Status.PASSWORD_ERROR);
    public static final CreateUserResponse ORG_NAME_ERROR = new CreateUserResponse(false, Status.ORG_NAME_ERROR);

    public static CreateUserResponse SUCCESS(Long content) {
        CreateUserResponse createUserResponse = new CreateUserResponse(true, Status.SUCCESS);
        createUserResponse.setContent(content);
        return createUserResponse;
    }

    public enum Status {
        SUCCESS, USER_NAME_ERROR, ORG_NAME_ERROR, PASSWORD_ERROR
    }

    public CreateUserResponse() {
    }

    private CreateUserResponse(boolean success, Status status) {
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
