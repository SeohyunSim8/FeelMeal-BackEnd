package feelmeal.domain.menu.repository;

import feelmeal.domain.menu.entity.Menu;
import feelmeal.domain.restaurant.entity.Restaurant;
import feelmeal.global.common.entity.Constant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurant(Restaurant restaurant);

    Menu findByRestaurantAndEmotion(Restaurant restaurant, Constant.Emotion emotion);
}

