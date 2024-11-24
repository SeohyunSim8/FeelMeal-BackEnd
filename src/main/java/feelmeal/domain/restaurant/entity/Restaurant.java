package feelmeal.domain.restaurant.entity;

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
@Table(name = "restaurant")
public class Restaurant extends BaseEntity {
    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "address", nullable = false, length = 70)
    private String address;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "about", columnDefinition = "TEXT")
    private String about;

    @Enumerated(EnumType.STRING)
    @Column(name = "foodCategory", nullable = false)
    private Constant.FoodCategory foodCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Constant.Status status;

    @Builder
    public Restaurant(String name, String address, Double longitude, Double latitude, String about,
                      Constant.FoodCategory foodCategory, Constant.Status status) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.about = about;
        this.foodCategory = foodCategory;
        this.status = status;
    }

    public static Restaurant of(String name, String address, Constant.FoodCategory foodCategory, Constant.Status status) {
        return feelmeal.domain.restaurant.entity.Restaurant.builder()
                .name(name)
                .address(address)
                .foodCategory(foodCategory)
                .status(status)
                .build();
    }

    public void modifyCoordinate(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

}