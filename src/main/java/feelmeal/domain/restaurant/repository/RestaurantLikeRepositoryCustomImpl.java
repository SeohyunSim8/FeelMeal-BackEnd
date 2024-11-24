package feelmeal.domain.restaurant.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import feelmeal.api.member.controller.dto.response.GetRestaurantLikedListResponse;
import feelmeal.api.member.controller.dto.response.QGetRestaurantLikedListResponse;
import feelmeal.domain.restaurant.entity.QRestaurant;
import feelmeal.domain.restaurant.entity.QRestaurantLike;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantLikeRepositoryCustomImpl implements RestaurantLikeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    // 좋아요 한 식당 목록 조회
    public List<GetRestaurantLikedListResponse> findAllRestaurantLikedByMemberIdx(Long memberIdx) {
        QRestaurant qRestaurant = QRestaurant.restaurant;
        QRestaurantLike qRestaurantLike = QRestaurantLike.restaurantLike;

        JPAQuery<GetRestaurantLikedListResponse> response =  jpaQueryFactory
                .select(new QGetRestaurantLikedListResponse(
                        qRestaurant.idx,
                        qRestaurant.name,
                        qRestaurant.address
                ))
                .from(qRestaurant)
                .join(qRestaurantLike).on(qRestaurantLike.restaurant.idx.eq(qRestaurant.idx))
                .where(qRestaurantLike.member.idx.eq(memberIdx))
                .orderBy(qRestaurant.name.asc());

        return response.fetch();
    }
}
