package feelmeal.api.member.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLoginResponse {
    @Schema(description = "멤버 고유번호", example = "1")
    private Long memberIdx;

    @Builder
    public PostLoginResponse(Long memberIdx) {
        this.memberIdx = memberIdx;
    }

    public static PostLoginResponse of(Long memberIdx) {
        return PostLoginResponse.builder()
                .memberIdx(memberIdx)
                .build();
    }
}
