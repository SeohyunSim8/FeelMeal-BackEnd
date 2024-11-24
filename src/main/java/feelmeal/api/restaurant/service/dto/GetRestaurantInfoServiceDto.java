package feelmeal.api.restaurant.service.dto;

import feelmeal.api.restaurant.controller.dto.response.GetRestaurantInfoResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantInfoServiceDto {
    private Long restaurantIdx;

    @Builder
    public GetRestaurantInfoServiceDto(Long restaurantIdx) {
        this.restaurantIdx = restaurantIdx;
    }

    public static GetRestaurantInfoServiceDto of(Long restaurantIdx) {
        return GetRestaurantInfoServiceDto.builder()
                .restaurantIdx(restaurantIdx)
                .build();
    }
}
