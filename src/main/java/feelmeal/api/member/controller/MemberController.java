package feelmeal.api.member.controller;

import feelmeal.api.member.controller.dto.request.PatchAddressRequest;
import feelmeal.api.member.controller.dto.request.PostLoginRequest;
import feelmeal.api.member.controller.dto.request.PostSignUpRequest;
import feelmeal.api.member.controller.dto.response.GetRestaurantLikedListResponse;
import feelmeal.api.member.controller.dto.response.PostLoginResponse;
import feelmeal.api.member.controller.dto.response.PostSignUpResponse;
import feelmeal.api.member.service.MemberService;
import feelmeal.api.member.service.dto.GetRestaurantLikedListServiceDto;
import feelmeal.global.annotation.NonAuth;
import feelmeal.global.common.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static feelmeal.global.common.exception.ResponseCode.SUCCESS;

@Tag(name = "[Member]", description = "멤버 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    // 회원가입 API
    @NonAuth
    @Operation(summary = "회원가입 API", description = "회원가입을 진행한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "이미 가입된 유저입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/sign-up")
    public ResponseEntity<PostSignUpResponse> signUp(
            @Valid @RequestBody PostSignUpRequest request
    ) {
        PostSignUpResponse response = memberService.signUp(request.toServiceDto());
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }

    // 로그인 API
    @NonAuth
    @Operation(summary = "로그인 API", description = "로그인을 진행한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.<br>" + "비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<PostLoginResponse> login(
            @Valid @RequestBody PostLoginRequest request
    ) {
        PostLoginResponse response = memberService.login(request.toServiceDto());
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }

    // 주소 수정 API
    @Operation(summary = "주소 수정 API", description = "주소를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/address")
    public ResponseEntity<String> modifyAddress(
            @Valid @RequestBody PatchAddressRequest request
    ) {
        memberService.modifyAddress(request.toServiceDto());
        return new ResponseEntity<>(SUCCESS.getMessage(), SUCCESS.getStatus());
    }

    // 좋아요 한 식당 목록 조회 API
    @Operation(summary = "좋아요 한 식당 목록 조회 API", description = "좋아요 한 식당 목록을 조회한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "405", description = "허용되지 않은 메서드입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 유저입니다.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/liked-list")
    public ResponseEntity<List<GetRestaurantLikedListResponse>> getRestaurantLikedList(
            @RequestParam @Schema(description = "멤버 고유번호", example = "1") Long memberIdx
    ) {
        List<GetRestaurantLikedListResponse> response = memberService.getRestaurantLikedList(GetRestaurantLikedListServiceDto.of(memberIdx));
        return new ResponseEntity<>(response, SUCCESS.getStatus());
    }
}