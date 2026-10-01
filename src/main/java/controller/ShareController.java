package interaction_service.controller;

import interaction_service.dto.ShareRequest;
import interaction_service.entity.Share;
import interaction_service.response.ApiResponse;
import interaction_service.service.ShareService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static interaction_service.response.ApiResponse.ResponseStatus.SUCCESS;

@RestController
@RequestMapping("/api/shares")
public class ShareController {

    private final ShareService shareService;

    public ShareController(ShareService shareService) {
        this.shareService = shareService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Share>> createShare(
            @Valid @RequestBody ShareRequest request) {

        Share share = shareService.createShare(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        "Share recorded successfully",
                        SUCCESS,
                        share,
                        201
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Share>> getShare(
            @PathVariable String id) {

        Share share = shareService.getShareById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Share retrieved successfully",
                        SUCCESS,
                        share,
                        200
                )
        );
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<ApiResponse<List<Share>>> getSharesByPost(
            @PathVariable String postId) {

        List<Share> shares =
                shareService.getSharesByPostId(postId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Post shares retrieved successfully",
                        SUCCESS,
                        shares,
                        200
                )
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<Share>>> getSharesByUser(
            @PathVariable String userId) {

        List<Share> shares =
                shareService.getSharesByUserId(userId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "User shares retrieved successfully",
                        SUCCESS,
                        shares,
                        200
                )
        );
    }

    @GetMapping("/post/{postId}/count")
    public ResponseEntity<ApiResponse<Map<String, Long>>> getShareCount(
            @PathVariable String postId) {

        long count = shareService.getShareCount(postId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        "Share count retrieved successfully",
                        SUCCESS,
                        Map.of("count", count),
                        200
                )
        );
    }
}