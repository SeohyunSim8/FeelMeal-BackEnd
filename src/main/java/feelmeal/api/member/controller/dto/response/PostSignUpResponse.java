package feelmeal.api.member.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSignUpResponse {
    @Schema(description = "멤버 고유번호", example = "1")
    private Long memberIdx;

    @Builder
    public PostSignUpResponse(Long memberIdx) {
        this.memberIdx = memberIdx;
    }

    public static PostSignUpResponse of(Long memberIdx) {
        return PostSignUpResponse.builder()
                .memberIdx(memberIdx)
                .build();
    }
}
