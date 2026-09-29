package interaction_service.serviceImpl;

import interaction_service.entity.Like;
import interaction_service.repository.LikeRepository;
import interaction_service.service.LikeService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public Like likePost(String userId, String postId) {

        return likeRepository
                .findByUserIdAndPostId(userId, postId)
                .orElseGet(() -> {

                    Like like = new Like();

                    like.setUserId(userId);
                    like.setPostId(postId);
                    like.setCreatedAt(LocalDateTime.now());

                    return likeRepository.save(like);
                });
    }

    @Override
    public void unlikePost(String userId, String postId) {

        likeRepository.deleteByUserIdAndPostId(
                userId,
                postId
        );
    }

    @Override
    public boolean isLiked(String userId, String postId) {

        return likeRepository
                .existsByUserIdAndPostId(userId, postId);
    }

    @Override
    public long getLikeCount(String postId) {

        return likeRepository.countByPostId(postId);
    }
}