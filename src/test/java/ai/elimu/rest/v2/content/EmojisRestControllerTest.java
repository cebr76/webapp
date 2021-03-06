package ai.elimu.rest.v2.content;

import ai.elimu.util.JsonLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.hamcrest.CoreMatchers.*;
import org.json.JSONArray;
import org.json.JSONObject;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import selenium.DomainHelper;

public class EmojisRestControllerTest {
    
    private Logger logger = LogManager.getLogger();
    
    @Test
    public void testHandleGetRequest() {
        String jsonResponse = JsonLoader.loadJson(DomainHelper.getRestUrlV2() + "/content/emojis");
        logger.info("jsonResponse: " + jsonResponse);
        
        JSONArray emojisJSONArray = new JSONArray(jsonResponse);
        logger.info("emojisJSONArray.length(): " + emojisJSONArray.length());
        assertThat(emojisJSONArray.length() > 0, is(true));
        
        JSONObject emojiJsonObject = emojisJSONArray.getJSONObject(0);
        assertThat(emojiJsonObject.getString("glyph"), not(nullValue()));
    }
}
