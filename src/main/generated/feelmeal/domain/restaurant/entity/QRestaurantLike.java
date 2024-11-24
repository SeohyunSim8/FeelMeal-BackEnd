package feelmeal.domain.restaurant.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestaurantLike is a Querydsl query type for RestaurantLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurantLike extends EntityPathBase<RestaurantLike> {

    private static final long serialVersionUID = -293717133L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRestaurantLike restaurantLike = new QRestaurantLike("restaurantLike");

    public final feelmeal.global.common.entity.QBaseEntity _super = new feelmeal.global.common.entity.QBaseEntity(this);

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final feelmeal.domain.member.entity.QMember member;

    public final QRestaurant restaurant;

    public QRestaurantLike(String variable) {
        this(RestaurantLike.class, forVariable(variable), INITS);
    }

    public QRestaurantLike(Path<? extends RestaurantLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRestaurantLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRestaurantLike(PathMetadata metadata, PathInits inits) {
        this(RestaurantLike.class, metadata, inits);
    }

    public QRestaurantLike(Class<? extends RestaurantLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new feelmeal.domain.member.entity.QMember(forProperty("member")) : null;
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant")) : null;
    }

}

