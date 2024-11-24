package feelmeal.api.member.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatchAddressServiceDto {
    private Long idx;
    private String address;

    @Builder
    public PatchAddressServiceDto(Long idx, String address) {
        this.idx = idx;
        this.address = address;
    }
}
