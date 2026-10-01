package interaction_service.response;

public class ApiResponse<T> {

    public enum ResponseStatus {
        SUCCESS,
        FAILURE
    }

    private String message;
    private ResponseStatus status;
    private T data;
    private int statusCode;

    public ApiResponse() {
    }

    public ApiResponse(
            String message,
            ResponseStatus status,
            T data,
            int statusCode) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}