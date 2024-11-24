package feelmeal.api.member.controller.dto.request;

import feelmeal.api.member.service.dto.PostLoginServiceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLoginRequest {
    @NotBlank
    @Schema(description = "아이디", example = "seohyun")
    private String id;

    @NotBlank
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    public PostLoginServiceDto toServiceDto() {
        return PostLoginServiceDto.builder()
                .id(id)
                .password(password)
                .build();
    }
}
