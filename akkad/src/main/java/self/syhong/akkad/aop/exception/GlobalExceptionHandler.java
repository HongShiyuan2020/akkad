package self.syhong.akkad.aop.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import self.syhong.akkad.dto.Result;
import self.syhong.akkad.exception.BizException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    public Result<Void> handleBusinessException(BizException e) {
        log.error("业务异常: ", e.getMessage());
        return Result.failed(500, e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("未知异常: ", e);
        return Result.failed(500, e.getMessage(), null);
    }
}
