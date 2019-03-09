package io.api.util;

import com.google.gson.Gson;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.error.ParseException;
import io.api.etherscan.model.utility.StringResponseTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.api.etherscan.util.BasicUtils.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.11.2018
 */
public class BasicUtilsTests extends Assert {

    @Test(expected = EtherScanException.class)
    public void responseValidateEmpty() {
        String response = "{\"status\":\"0\",\"message\":\"No ether\",\"result\":\"status\"}";
        StringResponseTO responseTO = new Gson().fromJson(response, StringResponseTO.class);
        validateTxResponse(responseTO);
    }

    @Test
    public void partitionEmpty() {
        ArrayList<String> list = new ArrayList<>();
        List<List<String>> lists = partition(list, 12);
        assertTrue(lists.isEmpty());
    }

    @Test
    public void partitionNullParam() {
        List<List<String>> lists = partition(null, 12);
        assertTrue(lists.isEmpty());
    }

    @Test
    public void isBlankNull() {
        boolean result = isBlank(null);
        assertTrue(result);
    }

    @Test
    public void isEmptyCollectionNull() {
        List<String> list = null;
        boolean result = isEmpty(list);
        assertTrue(result);
    }

    @Test
    public void isEmptyCollectionEmpty() {
        ArrayList<Object> list = new ArrayList<>();
        boolean result = isEmpty(list);
        assertTrue(result);
    }

    @Test
    public void isNotAddressNull() {
        boolean result = isNotAddress("");
        assertTrue(result);
    }

    @Test
    public void isNotHexNull() {
        boolean result = isNotHex("");
        assertTrue(result);
    }

    @Test
    public void isNotAddressInvalid() {
        boolean result = isNotAddress("125125");
        assertTrue(result);
    }

    @Test
    public void isNotHexInvalid() {
        boolean result = isNotHex("1215%");
        assertTrue(result);
    }

    @Test(expected = EtherScanException.class)
    public void isResponseStatusInvalidThrows() {
        StringResponseTO responseTO = new StringResponseTO();
        validateTxResponse(responseTO);
    }

    @Test(expected = EtherScanException.class)
    public void isResponseNullThrows() {
        StringResponseTO responseTO = null;
        validateTxResponse(responseTO);
    }

    @Test(expected = ParseException.class)
    public void isThrowParseException() {
        throw new ParseException("Test", null);
    }
}
