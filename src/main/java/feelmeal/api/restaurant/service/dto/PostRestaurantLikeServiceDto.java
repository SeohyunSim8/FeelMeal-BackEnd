package feelmeal.api.restaurant.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRestaurantLikeServiceDto {
    private Long memberIdx;
    private Long restaurantIdx;

    @Builder
    public PostRestaurantLikeServiceDto(Long memberIdx, Long restaurantIdx) {
        this.memberIdx = memberIdx;
        this.restaurantIdx = restaurantIdx;
    }

    public static PostRestaurantLikeServiceDto of(Long memberIdx, Long restaurantIdx) {
        return PostRestaurantLikeServiceDto.builder()
                .memberIdx(memberIdx)
                .restaurantIdx(restaurantIdx)
                .build();
    }
}
