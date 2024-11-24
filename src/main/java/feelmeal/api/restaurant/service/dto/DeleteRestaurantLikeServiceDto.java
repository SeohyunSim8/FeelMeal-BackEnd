package feelmeal.api.restaurant.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteRestaurantLikeServiceDto {
    private Long memberIdx;
    private Long restaurantIdx;

    @Builder
    public DeleteRestaurantLikeServiceDto(Long memberIdx, Long restaurantIdx) {
        this.memberIdx = memberIdx;
        this.restaurantIdx = restaurantIdx;
    }

    public static DeleteRestaurantLikeServiceDto of(Long memberIdx, Long restaurantIdx) {
        return DeleteRestaurantLikeServiceDto.builder()
                .memberIdx(memberIdx)
                .restaurantIdx(restaurantIdx)
                .build();
    }
}
