package feelmeal.domain.menu.entity;

import feelmeal.domain.restaurant.entity.Restaurant;
import feelmeal.global.common.entity.BaseEntity;
import feelmeal.global.common.entity.Constant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu")
public class Menu extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_idx", nullable = false)
    private Restaurant restaurant;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "emotion", nullable = false)
    private Constant.Emotion emotion;

    @Builder
    public Menu(Restaurant restaurant, String name, Constant.Emotion emotion) {
        this.restaurant = restaurant;
        this.name = name;
        this.emotion = emotion;
    }

    public static Menu of(Restaurant restaurant, String name, Constant.Emotion emotion) {
        return Menu.builder()
                .restaurant(restaurant)
                .name(name)
                .emotion(emotion)
                .build();
    }
}