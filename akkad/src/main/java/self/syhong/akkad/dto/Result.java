package self.syhong.akkad.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success;
    private Integer code;
    private String  message;
    private T       payload;

    public static<T> Result<T> success(Integer code, String message, T payload) {
        return Result.<T>builder()
            .success(true)
            .code(code)
            .message(message)
            .payload(payload)
            .build();
    }

    public static<T> Result<T> success(String message, T payload) {
        return Result.<T>builder()
            .success(true)
            .code(200)
            .message(message)
            .payload(payload)
            .build();
    }

    public static<T> Result<T> success(T payload) {
        return Result.<T>builder()
            .success(true)
            .code(200)
            .message("")
            .payload(payload)
            .build();
    }

    public static<T> Result<T> failed(Integer code, String message, T payload) {
        return Result.<T>builder()
            .success(false)
            .code(code)
            .message(message)
            .payload(payload)
            .build();
    }

    public static<T> Result<T> failed(String message, T payload) {
        return Result.<T>builder()
            .success(false)
            .code(500)
            .message(message)
            .payload(payload)
            .build();
    }

    public static<T> Result<T> failed(String message) {
        return Result.<T>builder()
            .success(false)
            .code(500)
            .message(message)
            .payload(null)
            .build();
    }
}
