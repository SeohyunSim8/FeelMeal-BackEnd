package feelmeal.api.restaurant.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantMenuServiceDto {
    private Long restaurantIdx;

    @Builder
    public GetRestaurantMenuServiceDto(Long restaurantIdx) {
        this.restaurantIdx = restaurantIdx;
    }

    public static GetRestaurantMenuServiceDto of(Long restaurantIdx) {
        return GetRestaurantMenuServiceDto.builder()
                .restaurantIdx(restaurantIdx)
                .build();
    }
}
