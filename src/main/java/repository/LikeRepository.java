package interaction_service.repository;

import interaction_service.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository
        extends MongoRepository<Like, String> {

    Optional<Like> findByUserIdAndPostId(
            String userId,
            String postId
    );

    boolean existsByUserIdAndPostId(
            String userId,
            String postId
    );

    void deleteByUserIdAndPostId(
            String userId,
            String postId
    );

    long countByPostId(String postId);

    List<Like> findByPostId(String postId);
}