package feelmeal.domain.restaurant.entity;

import feelmeal.domain.member.entity.Member;
import feelmeal.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant_like")
public class RestaurantLike extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_idx", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_idx", nullable = false)
    private Restaurant restaurant;

    @Builder
    public RestaurantLike(Member member, Restaurant restaurant) {
        this.member = member;
        this.restaurant = restaurant;
    }

    public static RestaurantLike of(Member member, Restaurant restaurant) {
        return RestaurantLike.builder()
                .member(member)
                .restaurant(restaurant)
                .build();
    }
}