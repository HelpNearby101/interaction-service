package interaction_service.exception;

public class ShareNotFoundException extends RuntimeException {

    public ShareNotFoundException(String id) {
        super("Share not found with ID: " + id);
    }
}