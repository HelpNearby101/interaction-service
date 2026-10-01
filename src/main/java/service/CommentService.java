package interaction_service.service;

import interaction_service.dto.CommentRequest;
import interaction_service.dto.CommentUpdateRequest;
import interaction_service.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment createComment(CommentRequest request);

    Comment getCommentById(String id);

    List<Comment> getCommentsByPostId(String postId);

    List<Comment> getCommentsByUserId(String userId);

    Comment updateComment(
            String id,
            String userId,
            CommentUpdateRequest request
    );

    void deleteComment(String id, String userId);
}