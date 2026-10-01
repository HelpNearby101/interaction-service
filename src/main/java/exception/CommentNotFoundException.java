package interaction_service.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(String id) {
        super("Comment not found with ID: " + id);
    }
}