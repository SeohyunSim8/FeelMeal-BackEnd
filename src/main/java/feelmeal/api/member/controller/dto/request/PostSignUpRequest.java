package feelmeal.api.member.controller.dto.request;

import feelmeal.api.member.service.dto.PostSignUpServiceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSignUpRequest {
    @NotBlank
    @Schema(description = "아이디", example = "seohyun")
    private String id;

    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @NotBlank
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @NotBlank
    @Schema(description = "이름", example = "심서현")
    private String name;

    @NotBlank
    @Schema(description = "주소", example = "복정동 495")
    private String address;

    public PostSignUpServiceDto toServiceDto() {
        return PostSignUpServiceDto.builder()
                .id(id)
                .password(password)
                .name(name)
                .address(address)
                .build();
    }
}
