package interaction_service.repository;

import interaction_service.entity.Share;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShareRepository extends MongoRepository<Share, String> {

    List<Share> findByPostIdOrderByCreatedAtDesc(String postId);

    List<Share> findByUserIdOrderByCreatedAtDesc(String userId);

    long countByPostId(String postId);
}