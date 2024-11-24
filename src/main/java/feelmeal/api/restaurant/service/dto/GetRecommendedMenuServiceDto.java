package feelmeal.api.restaurant.service.dto;

import feelmeal.global.common.entity.Constant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRecommendedMenuServiceDto {
    private Long restaurantIdx;
    private Constant.Emotion emotion;

    @Builder
    public GetRecommendedMenuServiceDto(Long restaurantIdx, Constant.Emotion emotion) {
        this.restaurantIdx = restaurantIdx;
        this.emotion = emotion;
    }

    public static GetRecommendedMenuServiceDto of(Long restaurantIdx, Constant.Emotion emotion) {
        return GetRecommendedMenuServiceDto.builder()
                .restaurantIdx(restaurantIdx)
                .emotion(emotion)
                .build();
    }
}
