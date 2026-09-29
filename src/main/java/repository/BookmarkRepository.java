package interaction_service.repository;

import interaction_service.entity.Bookmark;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository
        extends MongoRepository<Bookmark, String> {

    Optional<Bookmark> findByUserIdAndPostId(
            String userId,
            String postId
    );

    List<Bookmark> findByUserIdOrderByCreatedAtDesc(
            String userId
    );

    boolean existsByUserIdAndPostId(
            String userId,
            String postId
    );

    void deleteByUserIdAndPostId(
            String userId,
            String postId
    );
}