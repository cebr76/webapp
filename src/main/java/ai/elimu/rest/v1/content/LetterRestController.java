package ai.elimu.rest.v1.content;

import com.google.gson.Gson;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import ai.elimu.dao.LetterDao;
import ai.elimu.model.content.Letter;
import ai.elimu.model.v1.gson.content.LetterGson;
import ai.elimu.rest.v1.JavaToGsonConverter;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@RequestMapping(value = "/rest/v1/content/letter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LetterRestController {
    
    private Logger logger = LogManager.getLogger();
    
    @Autowired
    private LetterDao letterDao;
    
    @RequestMapping("/list")
    public String list(
            HttpServletRequest request,
            @RequestParam String deviceId
    ) {
        logger.info("list");
        
        logger.info("request.getQueryString(): " + request.getQueryString());
        
        JSONArray numbers = new JSONArray();
        for (Letter letter : letterDao.readAllOrdered()) {
            LetterGson letterGson = JavaToGsonConverter.getLetterGson(letter);
            String json = new Gson().toJson(letterGson);
            numbers.put(new JSONObject(json));
        }
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "success");
        jsonObject.put("letters", numbers);
        logger.info("jsonObject: " + jsonObject);
        return jsonObject.toString();
    }
}
