package io.api.util;

import static io.api.etherscan.util.BasicUtils.*;

import com.google.gson.Gson;
import io.api.ApiRunner;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.model.utility.StringResponseTO;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author GoodforGod
 * @since 13.11.2018
 */
class BasicUtilsTests extends ApiRunner {

    @Test
    void responseValidateEmpty() {
        String response = "{\"status\":\"0\",\"message\":\"No ether\",\"result\":\"status\"}";
        StringResponseTO responseTO = new Gson().fromJson(response, StringResponseTO.class);

        assertThrows(EtherScanException.class, () -> validateTxResponse(responseTO));
    }

    @Test
    void partitionEmpty() {
        ArrayList<String> list = new ArrayList<>();
        List<List<String>> lists = partition(list, 12);
        assertTrue(lists.isEmpty());
    }

    @Test
    void partitionNullParam() {
        List<List<String>> lists = partition(null, 12);
        assertTrue(lists.isEmpty());
    }

    @Test
    void isBlankNull() {
        boolean result = isBlank(null);
        assertTrue(result);
    }

    @Test
    void isEmptyCollectionEmpty() {
        ArrayList<Object> list = new ArrayList<>();
        boolean result = isEmpty(list);
        assertTrue(result);
    }

    @Test
    void isNotAddressNull() {
        boolean result = isNotAddress("");
        assertTrue(result);
    }

    @Test
    void isNotHexNull() {
        boolean result = isNotHex("");
        assertTrue(result);
    }

    @Test
    void isNotAddressInvalid() {
        boolean result = isNotAddress("125125");
        assertTrue(result);
    }

    @Test
    void isNotHexInvalid() {
        boolean result = isNotHex("1215%");
        assertTrue(result);
    }

    @Test
    void isResponseStatusInvalidThrows() {
        StringResponseTO responseTO = new StringResponseTO();
        assertThrows(EtherScanException.class, () -> validateTxResponse(responseTO));
    }

    @Test
    void isResponseNullThrows() {
        StringResponseTO responseTO = null;
        assertThrows(EtherScanException.class, () -> validateTxResponse(responseTO));
    }
}
