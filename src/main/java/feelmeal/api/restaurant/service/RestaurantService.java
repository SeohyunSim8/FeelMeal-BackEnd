package feelmeal.api.restaurant.service;

import feelmeal.api.restaurant.controller.dto.response.GetRecommendedMenuResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantInfoResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantListResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantMenuResponse;
import feelmeal.api.restaurant.service.dto.*;

import java.util.List;

public interface RestaurantService {
    List<GetRestaurantListResponse> getRestaurantList(GetRestaurantListServiceDto dto);

    GetRestaurantInfoResponse getRestaurantInfo(GetRestaurantInfoServiceDto dto);

    List<GetRestaurantMenuResponse> getRestaurantMenu(GetRestaurantMenuServiceDto dto);

    void postRestaurantLike(PostRestaurantLikeServiceDto dto);

    void deleteRestaurantLike(DeleteRestaurantLikeServiceDto dto);

    GetRecommendedMenuResponse getRecommendedMenu(GetRecommendedMenuServiceDto dto);
}
