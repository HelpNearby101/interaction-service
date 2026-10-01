package interaction_service.service;

import interaction_service.dto.ShareRequest;
import interaction_service.entity.Share;

import java.util.List;

public interface ShareService {

    Share createShare(ShareRequest request);

    Share getShareById(String id);

    List<Share> getSharesByPostId(String postId);

    List<Share> getSharesByUserId(String userId);

    long getShareCount(String postId);
}