package interaction_service.entity;

import interaction_service.enumeration.ShareType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "shares")
@CompoundIndexes({
        @CompoundIndex(
                name = "post_created_idx",
                def = "{'postId': 1, 'createdAt': -1}"
        ),
        @CompoundIndex(
                name = "user_created_idx",
                def = "{'userId': 1, 'createdAt': -1}"
        )
})
public class Share {
    @Id
    private String id;

    private String postId;
    private String userId;
    private ShareType shareType;
    private LocalDateTime createdAt;

    public Share() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ShareType getShareType() {
        return shareType;
    }

    public void setShareType(ShareType shareType) {
        this.shareType = shareType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
