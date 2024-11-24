package feelmeal.domain.restaurant.repository;

import feelmeal.domain.restaurant.entity.RestaurantLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantLikeRepository extends JpaRepository<RestaurantLike, Long>, RestaurantLikeRepositoryCustom {
    Optional<RestaurantLike> findByMemberIdxAndRestaurantIdx(Long memberId, Long restaurantId);

}

