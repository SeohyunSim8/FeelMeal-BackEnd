package feelmeal.global.common.exception;

public record ErrorResponse(Integer code, String message) {
    public static ErrorResponse of(ResponseCode code) {
        return new ErrorResponse(code.getStatus().value(), code.getMessage());
    }

    public static ErrorResponse of(ResponseCode code, Exception e) {
        return new ErrorResponse(code.getStatus().value(), code.getMessage(e));
    }

    public static ErrorResponse of(ResponseCode code, String message) {
        return new ErrorResponse(code.getStatus().value(), code.getMessage(message));
    }
}