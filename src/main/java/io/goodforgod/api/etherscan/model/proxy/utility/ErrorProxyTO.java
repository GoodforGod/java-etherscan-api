package io.goodforgod.api.etherscan.model.proxy.utility;

import java.util.Objects;

/**
 * @author GoodforGod
 * @since 03.11.2018
 */
public class ErrorProxyTO {

    private String message;
    private String code;

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ErrorProxyTO that = (ErrorProxyTO) o;
        return Objects.equals(message, that.message) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, code);
    }

    @Override
    public String toString() {
        return "ErrorProxyTO{" +
                "message=" + message +
                ", code=" + code +
                '}';
    }
}
