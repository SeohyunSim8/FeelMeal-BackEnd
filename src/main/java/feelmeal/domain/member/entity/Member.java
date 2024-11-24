package feelmeal.domain.member.entity;

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
@Table(name = "member")
public class Member extends BaseEntity {
    @Column(name = "id", nullable = false, length = 30)
    private String id;

    @Column(name = "pw", nullable = false, columnDefinition = "TEXT")
    private String pw;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "address", nullable = false, length = 20)
    private String address;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Constant.Status status;


    @Builder
    public Member(String id, String pw, String name, String address, Constant.Status status) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.address = address;
        this.status = status;
    }

    public static Member of(String id, String pw, String name, String address, Constant.Status status) {
        return Member.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .address(address)
                .status(status)
                .build();
    }

    public void modifyCoordinate(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void modifyAddress(String address) {
        this.address = address;
    }
}