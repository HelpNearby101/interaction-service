package interaction_service.controller;

import interaction_service.dto.CommentRequest;
import interaction_service.dto.CommentUpdateRequest;
import interaction_service.entity.Comment;
import interaction_service.response.ApiResponse;
import interaction_service.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static interaction_service.response.ApiResponse.ResponseStatus.SUCCESS;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Comment>> createComment(
            @Valid @RequestBody CommentRequest request) {

        Comment comment = commentService.createComment(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Comment created successfully",
                        SUCCESS,
                        comment,
                        201
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> getComment(
            @PathVariable String id) {

        Comment comment = commentService.getCommentById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Comment retrieved successfully",
                        SUCCESS,
                        comment,
                        200
                )
        );
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsByPost(
            @PathVariable String postId) {

        List<Comment> comments =
                commentService.getCommentsByPostId(postId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Comments retrieved successfully",
                        SUCCESS,
                        comments,
                        200
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Comment>>> getCommentsByUser(
            @PathVariable String userId) {

        List<Comment> comments =
                commentService.getCommentsByUserId(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "User comments retrieved successfully",
                        SUCCESS,
                        comments,
                        200
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Comment>> updateComment(
            @PathVariable String id,
            @RequestParam String userId,
            @Valid @RequestBody CommentUpdateRequest request) {

        Comment comment =
                commentService.updateComment(id, userId, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Comment updated successfully",
                        SUCCESS,
                        comment,
                        200
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable String id,
            @RequestParam String userId) {

        commentService.deleteComment(id, userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Comment deleted successfully",
                        SUCCESS,
                        null,
                        200
                )
        );
    }
}