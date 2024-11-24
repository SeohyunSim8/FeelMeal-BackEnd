package feelmeal.domain.menu.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = 355348992L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final feelmeal.global.common.entity.QBaseEntity _super = new feelmeal.global.common.entity.QBaseEntity(this);

    public final EnumPath<feelmeal.global.common.entity.Constant.Emotion> emotion = createEnum("emotion", feelmeal.global.common.entity.Constant.Emotion.class);

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final StringPath name = createString("name");

    public final feelmeal.domain.restaurant.entity.QRestaurant restaurant;

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new feelmeal.domain.restaurant.entity.QRestaurant(forProperty("restaurant")) : null;
    }

}

