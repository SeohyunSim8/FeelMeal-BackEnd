package feelmeal.api.restaurant.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantListResponse {
    @Schema(description = "식당 고유번호", example = "1")
    private Long restaurantIdx;

    @Schema(description = "이름", example = "참진해물짜장짬뽕")
    private String name;

    @Schema(description = "주소", example = "복정동 689-1")
    private String address;

    @QueryProjection
    @Builder
    public GetRestaurantListResponse(Long restaurantIdx, String name, String address) {
        this.restaurantIdx = restaurantIdx;
        this.name = name;
        this.address = address;
    }

    public static GetRestaurantListResponse of(Long restaurantIdx, String name, String address) {
        return GetRestaurantListResponse.builder()
                .restaurantIdx(restaurantIdx)
                .name(name)
                .address(address)
                .build();
    }
}