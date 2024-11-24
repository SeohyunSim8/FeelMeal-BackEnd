package feelmeal.domain.restaurant.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRestaurant is a Querydsl query type for Restaurant
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestaurant extends EntityPathBase<Restaurant> {

    private static final long serialVersionUID = 1202935228L;

    public static final QRestaurant restaurant = new QRestaurant("restaurant");

    public final feelmeal.global.common.entity.QBaseEntity _super = new feelmeal.global.common.entity.QBaseEntity(this);

    public final StringPath about = createString("about");

    public final StringPath address = createString("address");

    public final EnumPath<feelmeal.global.common.entity.Constant.FoodCategory> foodCategory = createEnum("foodCategory", feelmeal.global.common.entity.Constant.FoodCategory.class);

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath name = createString("name");

    public final EnumPath<feelmeal.global.common.entity.Constant.Status> status = createEnum("status", feelmeal.global.common.entity.Constant.Status.class);

    public QRestaurant(String variable) {
        super(Restaurant.class, forVariable(variable));
    }

    public QRestaurant(Path<? extends Restaurant> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestaurant(PathMetadata metadata) {
        super(Restaurant.class, metadata);
    }

}

