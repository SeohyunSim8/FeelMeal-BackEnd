package feelmeal.api.member.controller.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * feelmeal.api.member.controller.dto.response.QGetRestaurantLikedListResponse is a Querydsl Projection type for GetRestaurantLikedListResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetRestaurantLikedListResponse extends ConstructorExpression<GetRestaurantLikedListResponse> {

    private static final long serialVersionUID = -238494176L;

    public QGetRestaurantLikedListResponse(com.querydsl.core.types.Expression<Long> idx, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> address) {
        super(GetRestaurantLikedListResponse.class, new Class<?>[]{long.class, String.class, String.class}, idx, name, address);
    }

}

