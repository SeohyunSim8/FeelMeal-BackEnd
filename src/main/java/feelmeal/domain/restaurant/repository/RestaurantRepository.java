package feelmeal.domain.restaurant.repository;

import feelmeal.api.restaurant.controller.dto.response.GetRestaurantListResponse;
import feelmeal.domain.member.entity.Member;
import feelmeal.domain.restaurant.entity.Restaurant;
import feelmeal.global.common.entity.Constant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {
    // 식당 목록 조회 (멤버와의 가까운순)
    @Query("""
        SELECT new feelmeal.api.restaurant.controller.dto.response.GetRestaurantListResponse(r.idx, r.name, r.address) 
        FROM Restaurant r
        JOIN Member m ON m.idx = :memberIdx
        WHERE r.foodCategory = :foodCategory
        ORDER BY ST_Distance_Sphere(point(r.longitude, r.latitude), point(m.longitude, m.latitude)) ASC
    """)
    List<GetRestaurantListResponse> findAllByFoodCategoryAndDistance(@Param("memberIdx") Long memberIdx,
                                                                     @Param("foodCategory") Constant.FoodCategory foodCategory);

    Optional<Restaurant> findByIdxAndStatus(Long idx, Constant.Status status);
}

