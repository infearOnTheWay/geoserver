/* Copyright (c) 2013 - 2017 Boundless - http://boundlessgeo.com All rights reserved.
 * This code is licensed under the GPL 2.0 license, available at the root
 * application directory.
 */
package com.boundlessgeo.gsr.controller.map;

import com.boundlessgeo.gsr.JsonSchemaTest;
import com.boundlessgeo.gsr.controller.ControllerTest;
import net.sf.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapServiceControllerTest extends ControllerTest {
    private String query(String service, String params) {
        return getBaseURL() + service + "/MapServer" + params;
    }

    @Test
    public void testBasicQuery() throws Exception {
        String result = getAsString(query("cite", "?f=json"));
        assertFalse(result.isEmpty());
        // TODO: Can't validate since ids are not integers.
         assertTrue(result + " ;Root controller validates", JsonSchemaTest.validateJSON(result, "/gsr-ms/1.0/root.json"));
    }

    @Test
    public void testNotFoundException() throws Exception {
        JSONObject result = (JSONObject) getAsJSON(query("cte", "?f=json"));
        JSONObject error = result.getJSONObject("error");
        assertEquals(500, error.getInt("code"));
    }

    @Test
    public void testLayerGet() throws Exception {
        JSONObject result = (JSONObject) getAsJSON(getBaseURL() + "cite/MapServer/0");
        System.out.println(result.toString());
    }
}
