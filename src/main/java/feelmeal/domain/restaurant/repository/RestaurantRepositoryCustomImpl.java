package feelmeal.domain.restaurant.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import feelmeal.api.member.controller.dto.response.GetRestaurantLikedListResponse;
import feelmeal.api.member.controller.dto.response.QGetRestaurantLikedListResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantListResponse;
import feelmeal.domain.member.entity.QMember;
import feelmeal.domain.restaurant.entity.QRestaurant;
import feelmeal.domain.restaurant.entity.QRestaurantLike;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantRepositoryCustomImpl implements RestaurantRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

}
