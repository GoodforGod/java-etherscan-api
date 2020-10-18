package io.api.etherscan.error;

import io.api.etherscan.model.utility.BaseResponseTO;
import io.api.etherscan.model.utility.StringResponseTO;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class EtherScanException extends ApiException {

    public EtherScanException(BaseResponseTO response) {
        this(response.getMessage() + ", with status: " + response.getStatus());
    }

    public EtherScanException(StringResponseTO response) {
        this(response.getResult() + ", with status: " + response.getStatus() + ", with message: " + response.getMessage());
    }

    public EtherScanException(String message) {
        super(message);
    }
}
