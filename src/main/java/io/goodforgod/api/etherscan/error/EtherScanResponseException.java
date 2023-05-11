package io.goodforgod.api.etherscan.error;

import io.goodforgod.api.etherscan.model.response.BaseResponseTO;
import io.goodforgod.api.etherscan.model.response.StringResponseTO;

/**
 * @author GoodforGod
 * @since 29.10.2018
 */
public class EtherScanResponseException extends EtherScanException {

    public EtherScanResponseException(BaseResponseTO response) {
        this(response.getMessage() + ", with status: " + response.getStatus());
    }

    public EtherScanResponseException(StringResponseTO response) {
        this(response.getResult() + ", with status: " + response.getStatus() + ", with message: " + response.getMessage());
    }

    public EtherScanResponseException(String message) {
        super(message);
    }
}
