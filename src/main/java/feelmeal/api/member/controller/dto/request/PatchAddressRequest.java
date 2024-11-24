package feelmeal.api.member.controller.dto.request;

import feelmeal.api.member.service.dto.PatchAddressServiceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchAddressRequest {
    @NotNull
    @Schema(description = "멤버 고유번호", example = "1")
    private Long idx;

    @NotBlank
    @Schema(description = "주소", example = "복정동 495")
    private String address;

    public PatchAddressServiceDto toServiceDto() {
        return PatchAddressServiceDto.builder()
                .idx(idx)
                .address(address)
                .build();
    }
}
