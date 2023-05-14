package io.goodforgod.api.etherscan.model.response;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class StringResponseTO extends BaseResponseTO {

    private String result;

    public String getResult() {
        return result;
    }

    public static StringResponseBuilder builder() {
        return new StringResponseBuilder();
    }

    public static final class StringResponseBuilder {

        private String status;
        private String message;
        private String result;

        private StringResponseBuilder() {}

        public StringResponseBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public StringResponseBuilder withMessage(String message) {
            this.message = message;
            return this;
        }

        public StringResponseBuilder withResult(String result) {
            this.result = result;
            return this;
        }

        public StringResponseTO build() {
            StringResponseTO stringResponseTO = new StringResponseTO();
            stringResponseTO.status = this.status;
            stringResponseTO.message = this.message;
            stringResponseTO.result = this.result;
            return stringResponseTO;
        }
    }
}
