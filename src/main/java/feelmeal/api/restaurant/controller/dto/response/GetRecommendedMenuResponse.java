package feelmeal.api.restaurant.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRecommendedMenuResponse {
    @Schema(description = "이름", example = "짜장면")
    private String name;

    @Builder
    public GetRecommendedMenuResponse(String name) {
        this.name = name;
    }

    public static GetRecommendedMenuResponse of(String name) {
        return GetRecommendedMenuResponse.builder()
                .name(name)
                .build();
    }
}