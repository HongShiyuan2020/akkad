package self.syhong.akkad.utils;

import self.syhong.akkad.exception.BizException;

public class Assert {
    public static void assertExpression(boolean expression, String message) {
        if (!expression) {
            throw new BizException(message);
        }
    }
}
