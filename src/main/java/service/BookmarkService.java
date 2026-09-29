package interaction_service.service;

import interaction_service.entity.Bookmark;

import java.util.List;

public interface BookmarkService {

    Bookmark saveBookmark(String userId, String postId);

    void removeBookmark(String userId, String postId);

    List<Bookmark> getSavedPosts(String userId);

    boolean isBookmarked(String userId, String postId);
}