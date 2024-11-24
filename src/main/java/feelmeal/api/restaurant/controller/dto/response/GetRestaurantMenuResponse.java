package feelmeal.api.restaurant.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantMenuResponse {
    @Schema(description = "이름", example = "짜장면")
    private String name;

    @Builder
    public GetRestaurantMenuResponse(String name) {
        this.name = name;
    }

    public static GetRestaurantMenuResponse of(String name) {
        return GetRestaurantMenuResponse.builder()
                .name(name)
                .build();
    }
}