package feelmeal.api.restaurant.controller.dto.request;

import feelmeal.api.restaurant.service.dto.DeleteRestaurantLikeServiceDto;
import feelmeal.api.restaurant.service.dto.GetRestaurantListServiceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteRestaurantLikeRequest {
    @NotNull
    @Schema(description = "멤버 고유번호", example = "1")
    private Long memberIdx;

    @NotNull
    @Schema(description = "식당 고유번호", example = "1")
    private Long restaurantIdx;

    public DeleteRestaurantLikeServiceDto toServiceDto() {
        return DeleteRestaurantLikeServiceDto.builder()
                .memberIdx(memberIdx)
                .restaurantIdx(restaurantIdx)
                .build();
    }
}
