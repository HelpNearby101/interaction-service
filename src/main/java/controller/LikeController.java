package interaction_service.controller;

import interaction_service.entity.Like;
import interaction_service.service.LikeService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    // Like a post
    @PostMapping("/{postId}")
    public Like likePost(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String postId) {

        return likeService.likePost(userId, postId);
    }

    // Unlike a post
    @DeleteMapping("/{postId}")
    public String unlikePost(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String postId) {

        likeService.unlikePost(userId, postId);

        return "Post unliked successfully";
    }

    // Check whether user liked a post
    @GetMapping("/{postId}/status")
    public boolean isLiked(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String postId) {

        return likeService.isLiked(userId, postId);
    }

    // Get total likes on a post
    @GetMapping("/{postId}/count")
    public long getLikeCount(
            @PathVariable String postId) {

        return likeService.getLikeCount(postId);
    }
}