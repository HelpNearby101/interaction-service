package interaction_service.dto;

import interaction_service.enumeration.ShareType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShareRequest(

        @NotBlank(message = "Post ID is required")
        String postId,

        @NotBlank(message = "User ID is required")
        String userId,

        @NotNull(message = "Share type is required")
        ShareType shareType

) {
}