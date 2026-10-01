package interaction_service.exception;

import interaction_service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static interaction_service.response.ApiResponse.ResponseStatus.FAILURE;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(interaction_service.exception.CommentNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleCommentNotFound(
            interaction_service.exception.CommentNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(ex.getMessage(), FAILURE, null, 404)
        );
    }

    @ExceptionHandler(interaction_service.exception.ShareNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleShareNotFound(
            interaction_service.exception.ShareNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(ex.getMessage(), FAILURE, null, 404)
        );
    }

    @ExceptionHandler(interaction_service.exception.ForbiddenOperationException.class)
    public ResponseEntity<ApiResponse<Void>> handleForbidden(
            interaction_service.exception.ForbiddenOperationException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiResponse<>(ex.getMessage(), FAILURE, null, 403)
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(
            MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(message, FAILURE, null, 400)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidRequest(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.badRequest().body(
                new ApiResponse<>(
                        "Invalid request body or enum value",
                        FAILURE,
                        null,
                        400
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnexpectedException(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>(
                        "An unexpected server error occurred",
                        FAILURE,
                        null,
                        500
                )
        );
    }
}