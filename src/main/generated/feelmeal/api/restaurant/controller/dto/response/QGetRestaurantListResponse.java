package feelmeal.api.restaurant.controller.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * feelmeal.api.restaurant.controller.dto.response.QGetRestaurantListResponse is a Querydsl Projection type for GetRestaurantListResponse
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QGetRestaurantListResponse extends ConstructorExpression<GetRestaurantListResponse> {

    private static final long serialVersionUID = -985420530L;

    public QGetRestaurantListResponse(com.querydsl.core.types.Expression<Long> restaurantIdx, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> address) {
        super(GetRestaurantListResponse.class, new Class<?>[]{long.class, String.class, String.class}, restaurantIdx, name, address);
    }

}

