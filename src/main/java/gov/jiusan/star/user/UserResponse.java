package gov.jiusan.star.user;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private boolean success;
    private Status status;
    private UserDTO content;

    public static final UserResponse USER_NAME_ERROR = new UserResponse(false, Status.USER_NAME_ERROR);
    public static final UserResponse PASSWORD_ERROR = new UserResponse(false, Status.PASSWORD_ERROR);
    public static UserResponse SUCCESS(UserDTO content) {
        UserResponse userResponse = new UserResponse(true, Status.SUCCESS);
        userResponse.setContent(content);
        return userResponse;
    }

    public enum Status {
        SUCCESS, USER_NAME_ERROR, PASSWORD_ERROR
    }

    public UserResponse() {}

    private UserResponse(boolean success, Status status) {
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

    public UserDTO getContent() {
        return content;
    }

    public void setContent(UserDTO content) {
        this.content = content;
    }
}
