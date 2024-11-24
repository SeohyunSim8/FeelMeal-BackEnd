package feelmeal.api.restaurant.service;

import feelmeal.api.member.service.MemberServiceImpl;
import feelmeal.api.restaurant.controller.dto.response.GetRecommendedMenuResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantInfoResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantListResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantMenuResponse;
import feelmeal.api.restaurant.service.dto.*;
import feelmeal.domain.member.entity.Member;
import feelmeal.domain.member.repository.MemberRepository;
import feelmeal.domain.menu.entity.Menu;
import feelmeal.domain.menu.repository.MenuRepository;
import feelmeal.domain.restaurant.entity.Restaurant;
import feelmeal.domain.restaurant.entity.RestaurantLike;
import feelmeal.domain.restaurant.repository.RestaurantLikeRepository;
import feelmeal.domain.restaurant.repository.RestaurantRepository;
import feelmeal.global.common.entity.Constant;
import feelmeal.global.common.exception.CustomException;
import feelmeal.global.common.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final RestaurantLikeRepository restaurantLikeRepository;

    private final MemberServiceImpl memberService;

    // 식당 목록 조회 (거리순) API
    public List<GetRestaurantListResponse> getRestaurantList(GetRestaurantListServiceDto dto) {
        // 회원가입된 멤버인지 확인한다
        Member member = memberService.findMemberByIdx(dto.getMemberIdx(), Constant.Status.ACTIVE);

        // 식당 목록 조회 (멤버와 가까운순)
        return restaurantRepository.findAllByFoodCategoryAndDistance(member.getIdx(), dto.getFoodCategory());
    }

    // 식당 정보 조회 API
    public GetRestaurantInfoResponse getRestaurantInfo(GetRestaurantInfoServiceDto dto) {
        // 존재하는 식당인지 확인한다
        Restaurant restaurant = findRestaurantByIdx(dto.getRestaurantIdx(), Constant.Status.ACTIVE);

        return new GetRestaurantInfoResponse(
                restaurant.getIdx(), restaurant.getName(), restaurant.getAddress(), restaurant.getAbout());
    }

    // 식당 메뉴 조회 API
    public List<GetRestaurantMenuResponse> getRestaurantMenu(GetRestaurantMenuServiceDto dto) {
        // 존재하는 식당인지 확인한다
        Restaurant restaurant = findRestaurantByIdx(dto.getRestaurantIdx(), Constant.Status.ACTIVE);

        return menuRepository.findByRestaurant(restaurant).stream()
                .map(menu -> GetRestaurantMenuResponse.builder()
                        .name(menu.getName())
                        .build()
                )
                .toList();
    }

    // 식당 좋아요 API
    @Transactional
    public void postRestaurantLike(PostRestaurantLikeServiceDto dto) {
        // 회원가입된 멤버인지 확인한다
        Member member = memberService.findMemberByIdx(dto.getMemberIdx(), Constant.Status.ACTIVE);

        // 존재하는 식당인지 확인한다
        Restaurant restaurant = findRestaurantByIdx(dto.getRestaurantIdx(), Constant.Status.ACTIVE);

        // restaurantLike 조회 후, 있다면 예외 발생, 없다면 새로 생성
        if(restaurantLikeRepository.findByMemberIdxAndRestaurantIdx(member.getIdx(), restaurant.getIdx()).isPresent())
            throw new CustomException(ResponseCode.EXIST_RESTAURANT_LIKE);

        restaurantLikeRepository.save(RestaurantLike.of(member, restaurant));
    }

    // 식당 좋아요 취소 API
    @Transactional
    public void deleteRestaurantLike(DeleteRestaurantLikeServiceDto dto) {
        // 회원가입된 멤버인지 확인한다
        Member member = memberService.findMemberByIdx(dto.getMemberIdx(), Constant.Status.ACTIVE);

        // 존재하는 식당인지 확인하고, 식당 좋아요가 있는지 확인한다
        Restaurant restaurant = findRestaurantByIdx(dto.getRestaurantIdx(), Constant.Status.ACTIVE);
        Optional<RestaurantLike> restaurantLike = restaurantLikeRepository.findByMemberIdxAndRestaurantIdx(member.getIdx(), restaurant.getIdx());

        // 좋아요를 눌렀다면 좋아요를 삭제하고, 누르지 않았다면 예외 처리를 진행한다
        if(restaurantLike.isPresent()) restaurantLikeRepository.delete(restaurantLike.get());
        else throw new CustomException(ResponseCode.INVALID_RESTAURANT_LIKE);
    }

    // 식당 메뉴 추천 API
    public GetRecommendedMenuResponse getRecommendedMenu(GetRecommendedMenuServiceDto dto) {
        // 존재하는 식당인지 확인한다
        Restaurant restaurant = findRestaurantByIdx(dto.getRestaurantIdx(), Constant.Status.ACTIVE);

        // 추천 메뉴 조회
        Menu recommendedMenu = menuRepository.findByRestaurantAndEmotion(restaurant, dto.getEmotion());

        return new GetRecommendedMenuResponse(recommendedMenu.getName());
    }

    public Restaurant findRestaurantByIdx(Long idx, Constant.Status status) {
        return restaurantRepository.findByIdxAndStatus(idx, status)
                .orElseThrow(() -> new CustomException(ResponseCode.NOT_FOUND_RESTAURANT));
    }
}
