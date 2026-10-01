package interaction_service.repository;

import interaction_service.entity.Comment;
import interaction_service.enumeration.CommentStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByPostIdAndStatusOrderByCreatedAtAsc(
            String postId,
            CommentStatus status
    );

    List<Comment> findByUserIdAndStatusOrderByCreatedAtDesc(
            String userId,
            CommentStatus status
    );
}