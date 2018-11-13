package io.api.util;

import com.google.gson.Gson;
import io.api.etherscan.error.EtherScanException;
import io.api.etherscan.model.utility.StringResponseTO;
import io.api.etherscan.util.BasicUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.11.2018
 */
public class UtilTests extends Assert {

    @Test(expected = EtherScanException.class)
    public void responseValidateEmpty() {
        String response = "{\"status\":\"0\",\"message\":\"No ether\",\"result\":\"status\"}";
        StringResponseTO responseTO = new Gson().fromJson(response, StringResponseTO.class);
        BasicUtils.validateTxResponse(responseTO);
    }

    @Test(expected = EtherScanException.class)
    public void responseValidateNullable() {
        BasicUtils.validateTxResponse(null);
    }

    @Test
    public void partitionEmpty() {
        ArrayList<String> list = new ArrayList<>();
        List<List<String>> lists = BasicUtils.partition(list, 12);
        assertTrue(lists.isEmpty());
    }
}
