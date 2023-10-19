package automation.api.kanboard;

import automation.api.KanboardJSONRPC;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import org.apache.commons.collections.map.HashedMap;

import java.util.HashMap;
import java.util.Map;

public class Category {
    @Step("Usage a template for body requests during the category creation process.")
    public JsonObject createCategory(String categoryName, Integer projectID){
        Map<String, Object> params = new HashMap<>(  );
        params.put("name",categoryName);
        params.put("project_id", ""+projectID);
        return KanboardJSONRPC.baseRequest("createCategory",params) ;
    }
}
