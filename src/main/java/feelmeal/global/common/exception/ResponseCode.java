package feelmeal.global.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // GLOBAL
    SUCCESS(HttpStatus.OK, "요청이 성공적으로 처리되었습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 오류입니다."),

    // AUTH
    EMPTY_JWT(HttpStatus.BAD_REQUEST, "JWT를 입력해주세요."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 JWT입니다."),

    // MEMBER
    EXIST_MEMBER(HttpStatus.CONFLICT, "이미 가입된 유저입니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    //RESTAURANT
    NOT_FOUND_RESTAURANT(HttpStatus.NOT_FOUND, "존재하지 않는 식당입니다."),
    INVALID_RESTAURANT_LIKE(HttpStatus.BAD_REQUEST, "좋아요를 누르지 않은 식당입니다."),
    EXIST_RESTAURANT_LIKE(HttpStatus.CONFLICT, "이미 좋아요를 한 식당입니다."),




    // MEM
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    INVALID_HORROR_POS(HttpStatus.BAD_REQUEST, "공포테마 포지션를 찾을 수 없습니다."),

    // REVIEW
    NOT_FOUND_THEME_REVIEW(HttpStatus.NOT_FOUND, "존재하지 않는 방탈출 후기입니다."),
    INVALID_DELETE_THEME_REVIEW(HttpStatus.BAD_REQUEST, "본인이 작성한 방탈출 후기만 삭제할 수 있습니다."),
    INVALID_CREATE_THEME_REVIEW_FAIL_REASON(HttpStatus.BAD_REQUEST, "탈출에 실패한 경우 탈출 사유를 반드시 입력해야 합니다."),
    INVALID_CREATE_THEME_REVIEW_PARTICIPANT(HttpStatus.BAD_REQUEST, "방탈출 방문 참가자수가 일치하지 않습니다."),
    INVALID_CREATE_THEME_REVIEW_DATE(HttpStatus.BAD_REQUEST, "방탈출 방문 날짜 형식이 잘못되었습니다."),
    INVALID_CREATE_THEME_REVIEW_TIME(HttpStatus.BAD_REQUEST, "방탈출 남은 시간 형식이 잘못되었습니다."),
    NOT_FLOAT(HttpStatus.BAD_REQUEST, "실수형이 아닙니다."),
    NOT_INTEGER(HttpStatus.BAD_REQUEST, "정수형이 아닙니다."),
    INVALID_REVIEW_ENUM(HttpStatus.BAD_REQUEST, "평점(enum)을 찾을 수 없습니다."),
    INVALID_SEARCH_VALUE(HttpStatus.BAD_REQUEST, "검색어를 입력해주세요."),

    // POST
    INVALID_POSTTYPE(HttpStatus.BAD_REQUEST, "게시판 종류를 찾을 수 없습니다."),
    INVALID_SORTOPTION(HttpStatus.BAD_REQUEST, "정렬 기준을 찾을 수 없습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "존재하지 않는 게시물입니다."),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "존재하지 않는 댓글입니다."),
    NOT_FOUND_POSTLIKE(HttpStatus.NOT_FOUND, "좋아요를 하지 않은 게시물입니다."),
    EXIST_LIKE(HttpStatus.CONFLICT, "이미 좋아요를 한 게시물입니다."),
    UNAUTHORIZED_POST(HttpStatus.UNAUTHORIZED, "유저가 작성하지 않은 게시물입니다."),
    UNAUTHORIZED_COMMENT(HttpStatus.UNAUTHORIZED, "유저가 작성하지 않은 댓글입니다."),
    INVALID_COMMENT(HttpStatus.UNAUTHORIZED, "해당 게시물의 댓글이 아닙니다."),
    ;

    private final HttpStatus status;
    private final String message;


    public String getMessage(Throwable e) {
        return this.getMessage(this.getMessage() + " - " + e.getMessage());
    }

    public String getMessage(String message) {
        return Optional.ofNullable(message)
            .filter(Predicate.not(String::isBlank))
            .orElse(this.getMessage());
    }
}
