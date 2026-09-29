package interaction_service.controller;

import interaction_service.entity.Bookmark;
import interaction_service.service.BookmarkService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(
            BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    // Save a post
    @PostMapping("/{postId}")
    public Bookmark saveBookmark(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String postId) {

        return bookmarkService.saveBookmark(userId, postId);
    }

    // Remove a bookmark
    @DeleteMapping("/{postId}")
    public String removeBookmark(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String postId) {

        bookmarkService.removeBookmark(userId, postId);

        return "Bookmark removed successfully";
    }

    // Get all saved posts
    @GetMapping
    public List<Bookmark> getSavedPosts(
            @RequestHeader("X-User-Id") String userId) {

        return bookmarkService.getSavedPosts(userId);
    }

    // Check bookmark status
    @GetMapping("/{postId}/status")
    public boolean isBookmarked(
            @RequestHeader("X-User-Id") String userId,
            @PathVariable String postId) {

        return bookmarkService.isBookmarked(userId, postId);
    }
}