package feelmeal.domain.restaurant.repository;

import feelmeal.api.member.controller.dto.response.GetRestaurantLikedListResponse;

import java.util.List;

public interface RestaurantLikeRepositoryCustom {
    // 좋아요 한 식당 목록 조회
    List<GetRestaurantLikedListResponse> findAllRestaurantLikedByMemberIdx(Long memberIdx);
}
