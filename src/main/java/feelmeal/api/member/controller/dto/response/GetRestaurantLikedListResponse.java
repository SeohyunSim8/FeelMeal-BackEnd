package feelmeal.api.member.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantLikedListResponse {
    @Schema(description = "고유번호", example = "1")
    private Long idx;

    @Schema(description = "이름", example = "참진해물짜장짬뽕")
    private String name;

    @Schema(description = "주소", example = "복정동 689-1")
    private String address;

    @QueryProjection
    @Builder
    public GetRestaurantLikedListResponse(Long idx, String name, String address) {
        this.idx = idx;
        this.name = name;
        this.address = address;
    }

    public static GetRestaurantLikedListResponse of(Long idx, String name, String address) {
        return GetRestaurantLikedListResponse.builder()
                .idx(idx)
                .name(name)
                .address(address)
                .build();
    }
}