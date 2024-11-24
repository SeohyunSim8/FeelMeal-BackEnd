package feelmeal.api.restaurant.service.dto;

import feelmeal.api.member.service.dto.PatchAddressServiceDto;
import feelmeal.global.common.entity.Constant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetRestaurantListServiceDto {
    private Long memberIdx;
    private Constant.FoodCategory foodCategory;

    @Builder
    public GetRestaurantListServiceDto(Long memberIdx, Constant.FoodCategory foodCategory) {
        this.memberIdx = memberIdx;
        this.foodCategory = foodCategory;
    }

    public static GetRestaurantListServiceDto of(Long memberIdx, Constant.FoodCategory foodCategory) {
        return GetRestaurantListServiceDto.builder()
                .memberIdx(memberIdx)
                .foodCategory(foodCategory)
                .build();
    }
}
