package interaction_service.service;

import interaction_service.entity.Like;

public interface LikeService {

    Like likePost(String userId, String postId);

    void unlikePost(String userId, String postId);

    boolean isLiked(String userId, String postId);

    long getLikeCount(String postId);
}