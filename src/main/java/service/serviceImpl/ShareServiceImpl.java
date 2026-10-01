package interaction_service.service.impl;

import interaction_service.dto.ShareRequest;
import interaction_service.entity.Share;
import interaction_service.exception.ShareNotFoundException;
import interaction_service.repository.ShareRepository;
import interaction_service.service.ShareService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    private final ShareRepository shareRepository;

    public ShareServiceImpl(ShareRepository shareRepository) {
        this.shareRepository = shareRepository;
    }

    @Override
    public Share createShare(ShareRequest request) {
        Share share = new Share();
        share.setPostId(request.postId().trim());
        share.setUserId(request.userId().trim());
        share.setShareType(request.shareType());
        share.setCreatedAt(LocalDateTime.now());

        return shareRepository.save(share);
    }

    @Override
    public Share getShareById(String id) {
        return shareRepository.findById(id)
                .orElseThrow(() -> new ShareNotFoundException(id));
    }

    @Override
    public List<Share> getSharesByPostId(String postId) {
        return shareRepository.findByPostIdOrderByCreatedAtDesc(postId);
    }

    @Override
    public List<Share> getSharesByUserId(String userId) {
        return shareRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public long getShareCount(String postId) {
        return shareRepository.countByPostId(postId);
    }
}