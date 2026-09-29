package interaction_service.serviceImpl;

import interaction_service.entity.Bookmark;
import interaction_service.repository.BookmarkRepository;
import interaction_service.service.BookmarkService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkServiceImpl(
            BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    @Override
    public Bookmark saveBookmark(
            String userId,
            String postId) {

        return bookmarkRepository
                .findByUserIdAndPostId(userId, postId)
                .orElseGet(() -> {

                    Bookmark bookmark = new Bookmark();
                    bookmark.setUserId(userId);
                    bookmark.setPostId(postId);
                    bookmark.setCreatedAt(LocalDateTime.now());

                    return bookmarkRepository.save(bookmark);
                });
    }

    @Override
    public void removeBookmark(
            String userId,
            String postId) {

        bookmarkRepository.deleteByUserIdAndPostId(
                userId,
                postId
        );
    }

    @Override
    public List<Bookmark> getSavedPosts(String userId) {

        return bookmarkRepository
                .findByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public boolean isBookmarked(
            String userId,
            String postId) {

        return bookmarkRepository
                .existsByUserIdAndPostId(userId, postId);
    }
}