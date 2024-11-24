package feelmeal.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 331956150L;

    public static final QMember member = new QMember("member1");

    public final feelmeal.global.common.entity.QBaseEntity _super = new feelmeal.global.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath id = createString("id");

    //inherited
    public final NumberPath<Long> idx = _super.idx;

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final StringPath name = createString("name");

    public final StringPath pw = createString("pw");

    public final EnumPath<feelmeal.global.common.entity.Constant.Status> status = createEnum("status", feelmeal.global.common.entity.Constant.Status.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

