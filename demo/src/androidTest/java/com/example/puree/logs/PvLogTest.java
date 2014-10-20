package com.example.puree.logs;

import android.test.AndroidTestCase;

import com.cookpad.android.puree.LogSpec;
import com.cookpad.android.puree.plugins.OutLogcat;
import com.example.puree.DemoApplication;
import com.example.puree.logs.plugins.OutBufferedDisplay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PvLogTest extends AndroidTestCase {
    public void testFormat() {
        new LogSpec(PureeConfigurator.buildConf(getContext()))
                .log(new PvLog("MainActivity"), OutLogcat.TYPE)
                .log(new PvLog("MainActivity"), OutLogcat.TYPE)
                .targetType(OutLogcat.TYPE)
                .shouldBe(new LogSpec.Matcher() {
                    @Override
                    public void expect(JSONArray serializedLogs) throws JSONException {
                        assertEquals(2, serializedLogs.length());
                        JSONObject serializedLog = serializedLogs.getJSONObject(0);
                        assertEquals("MainActivity", serializedLog.getString("screen_name"));
                        assertTrue(serializedLog.has("event_time"));
                    }
                });
    }
}
