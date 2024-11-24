package feelmeal.api.restaurant.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantInfoResponse {
    @Schema(description = "고유번호", example = "1")
    private Long idx;

    @Schema(description = "이름", example = "참진해물짜장짬뽕")
    private String name;

    @Schema(description = "주소", example = "복정동 689-1")
    private String address;

    @Schema(description = "설명", example = "참되고 진실되게 만드는 참진짜장입니다. 최고의 맛과 서비스를 드립니다.")
    private String about;

    @Builder
    public GetRestaurantInfoResponse(Long idx, String name, String address, String about) {
        this.idx = idx;
        this.name = name;
        this.address = address;
        this.about = about;
    }

    public static GetRestaurantInfoResponse of(Long idx, String name, String address, String about) {
        return GetRestaurantInfoResponse.builder()
                .idx(idx)
                .name(name)
                .address(address)
                .about(about)
                .build();
    }
}