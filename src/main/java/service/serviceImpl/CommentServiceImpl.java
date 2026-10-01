package interaction_service.service.impl;

import interaction_service.dto.CommentRequest;
import interaction_service.dto.CommentUpdateRequest;
import interaction_service.entity.Comment;
import interaction_service.enumeration.CommentStatus;
import interaction_service.exception.CommentNotFoundException;
import interaction_service.exception.ForbiddenOperationException;
import interaction_service.repository.CommentRepository;
import interaction_service.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(CommentRequest request) {
        LocalDateTime now = LocalDateTime.now();

        Comment comment = new Comment();
        comment.setPostId(request.postId().trim());
        comment.setUserId(request.userId().trim());
        comment.setContent(request.content().trim());
        comment.setStatus(CommentStatus.ACTIVE);
        comment.setCreatedAt(now);
        comment.setUpdatedAt(now);

        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(String id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));

        if (comment.getStatus() == CommentStatus.DELETED) {
            throw new CommentNotFoundException(id);
        }

        return comment;
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository
                .findByPostIdAndStatusOrderByCreatedAtAsc(
                        postId,
                        CommentStatus.ACTIVE
                );
    }

    @Override
    public List<Comment> getCommentsByUserId(String userId) {
        return commentRepository
                .findByUserIdAndStatusOrderByCreatedAtDesc(
                        userId,
                        CommentStatus.ACTIVE
                );
    }

    @Override
    public Comment updateComment(
            String id,
            String userId,
            CommentUpdateRequest request) {

        Comment comment = getCommentById(id);

        if (!comment.getUserId().equals(userId)) {
            throw new ForbiddenOperationException(
                    "You cannot update another user's comment"
            );
        }

        comment.setContent(request.content().trim());
        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String id, String userId) {
        Comment comment = getCommentById(id);

        if (!comment.getUserId().equals(userId)) {
            throw new ForbiddenOperationException(
                    "You cannot delete another user's comment"
            );
        }

        comment.setStatus(CommentStatus.DELETED);
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }
}