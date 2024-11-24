package feelmeal.api.restaurant.controller;

import feelmeal.api.restaurant.controller.dto.request.DeleteRestaurantLikeRequest;
import feelmeal.api.restaurant.controller.dto.request.PostRestaurantLikeRequest;
import feelmeal.api.restaurant.controller.dto.response.GetRecommendedMenuResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantInfoResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantListResponse;
import feelmeal.api.restaurant.controller.dto.response.GetRestaurantMenuResponse;
import feelmeal.api.restaurant.service.RestaurantService;
import feelmeal.api.restaurant.service.dto.*;
import feelmeal.global.annotation.NonAuth;
import feelmeal.global.common.entity.Constant;
import feelmeal.global.common.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static feelmeal.global.common.exception.ResponseCode.SUCCESS;

@Tag(name = "[Restaurant]", description = "식당 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    // 식당 목록 조회 (거리순) API
    @NonAuth
    @Operation(summary = "식당 목록 조회 (거리순) API", description = "내 위치와 가까운 순으로 식당 목록을 조회한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/list")
    public ResponseEntity<List<GetRestaurantListResponse>> getRestaurantList(
            @ParameterObject @Schema(description = "멤버 고유번호", example = "1") Long memberIdx,
            @ParameterObject @Schema(description = "음식 종류", example = "KOREAN") Constant.FoodCategory foodCategory
    ) {
        List<GetRestaurantListResponse> response = restaurantService.getRestaurantList(GetRestaurantListServiceDto.of(memberIdx, foodCategory));
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }

    // 식당 정보 조회 API
    @NonAuth
    @Operation(summary = "식당 정보 조회 API", description = "식당의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 식당입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{restaurantIdx}/info")
    public ResponseEntity<GetRestaurantInfoResponse> getRestaurantInfo(
            @PathVariable Long restaurantIdx
    ) {
        GetRestaurantInfoResponse response = restaurantService.getRestaurantInfo(GetRestaurantInfoServiceDto.of(restaurantIdx));
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }

    // 식당 메뉴 조회 API
    @NonAuth
    @Operation(summary = "식당 메뉴 조회 API", description = "식당의 메뉴를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 식당입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{restaurantIdx}/menus")
    public ResponseEntity<List<GetRestaurantMenuResponse>> getRestaurantMenu(
            @PathVariable Long restaurantIdx
    ) {
        List<GetRestaurantMenuResponse> response = restaurantService.getRestaurantMenu(GetRestaurantMenuServiceDto.of(restaurantIdx));
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }

    // 식당 좋아요 API
    @Operation(summary = "식당 좋아요 API", description = "식당 '좋아요'를 합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/like")
    public ResponseEntity<String> postRestaurantLike(
            @Valid @RequestBody PostRestaurantLikeRequest request
    ) {
        restaurantService.postRestaurantLike(request.toServiceDto());
        return new ResponseEntity<>(SUCCESS.getMessage(), SUCCESS.getStatus());
    }

    // 식당 좋아요 취소 API
    @Operation(summary = "식당 좋아요 취소 API", description = "식당 '좋아요'를 취소합니다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/like")
    public ResponseEntity<String> deleteRestaurantLike(
            @Valid @RequestBody DeleteRestaurantLikeRequest request
    ) {
        restaurantService.deleteRestaurantLike(request.toServiceDto());
        return new ResponseEntity<>(SUCCESS.getMessage(), SUCCESS.getStatus());
    }

    // 식당 메뉴 추천 API
    @NonAuth
    @Operation(summary = "식당 메뉴 추천 API", description = "식당의 메뉴를 추천합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 식당입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{restaurantIdx}/recommend")
    public ResponseEntity<GetRecommendedMenuResponse> getRecommendedMenu(
            @PathVariable Long restaurantIdx,
            @ParameterObject Constant.Emotion emotion
            ) {
        GetRecommendedMenuResponse response = restaurantService.getRecommendedMenu(GetRecommendedMenuServiceDto.of(restaurantIdx, emotion));
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }
}