package feelmeal.global.common.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;


@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    // 직접 정의한 에러
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(final CustomException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(e.getResponseCode());

        return ResponseEntity
            .status(e.getResponseCode().getStatus())
            .body(errorResponse);
    }

    // 지원하지 않는 HttpRequestMethod
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException() {
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.METHOD_NOT_ALLOWED);

        return ResponseEntity
            .status(ResponseCode.METHOD_NOT_ALLOWED.getStatus())
            .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.INTERNAL_SERVER_ERROR);

        return ResponseEntity
                .status(ResponseCode.INTERNAL_SERVER_ERROR.getStatus())
                .body(errorResponse);
    }


    // validation exception 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가

        // 모든 FieldError 추출
        FieldError field = e.getBindingResult().getFieldErrors().getFirst();

        String errorMessage;
        if(Objects.requireNonNull(field.getDefaultMessage()).contains("Failed to convert")) errorMessage = String.format("%s ENUM 값이 잘못되었습니다.", e.getBindingResult().getFieldErrors().getFirst().getField());
        else errorMessage = String.format("%s은 %s.", Objects.requireNonNull(e.getBindingResult().getFieldError()).getField(), e.getBindingResult().getAllErrors().getFirst().getDefaultMessage());

        ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.BAD_REQUEST, errorMessage);

        return ResponseEntity
            .status(e.getStatusCode())
            .body(errorResponse);
    }

    //잘못된 자료형으로 인한 에러
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> methodArgumentTypeMismatchExceptionError(MethodArgumentTypeMismatchException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.BAD_REQUEST, e);

        return ResponseEntity
            .status(ResponseCode.BAD_REQUEST.getStatus())
            .body(errorResponse);
    }

    //잘못된 자료형으로 인한 에러
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.BAD_REQUEST, e);

        return ResponseEntity
            .status(ResponseCode.BAD_REQUEST.getStatus())
            .body(errorResponse);

    }

    //지원하지 않는 media type 에러
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> httpMediaTypeNotSupportedExceptionError(HttpMediaTypeNotSupportedException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.BAD_REQUEST, e);

        return ResponseEntity
            .status(e.getStatusCode())
            .body(errorResponse);
    }

    //외부 api client 에러
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> httpMediaTypeNotSupportedExceptionError(HttpClientErrorException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.INTERNAL_SERVER_ERROR, e);

        return ResponseEntity
            .status(e.getStatusCode())
            .body(errorResponse);
    }

    //외부 api server 에러
    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<ErrorResponse> httpServerErrorExceptionError(HttpServerErrorException e) {
        log.error("Unexpected error occurred", e);  // 예외 로그 추가
        final ErrorResponse errorResponse = ErrorResponse.of(ResponseCode.INTERNAL_SERVER_ERROR, e);

        return ResponseEntity
            .status(e.getStatusCode())
            .body(errorResponse);
    }

}