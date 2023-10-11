package automation.api;

import automation.api.kanboard.Category;
import automation.api.kanboard.Project;
import automation.api.kanboard.Task;
import automation.api.kanboard.User;
import com.google.gson.JsonObject;

import java.util.Map;

public class KanboardJSONRPC {
    static private long rpcCounter = 1;

    final public Category category = new Category();
    final public User user = new User();
    final public Project project = new Project();
    final public Task task = new Task();

    public static JsonObject baseRequest(String method, Map<String,Object> parameters){
        JsonObject requsetPayload = new JsonObject();
        requsetPayload.addProperty("jsonrpc","2.0");
        requsetPayload.addProperty("method",method);
        requsetPayload.addProperty("id",rpcCounter++);

        JsonObject params = new JsonObject();
        for(String param: parameters.keySet( )){
            Object value =parameters.get(param);
            if (value.getClass()==String.class){
                params.addProperty(param,(String) value);
            } else if (value.getClass()==Integer.class){
                params.addProperty(param,(Integer) value);
            } else if (value.getClass()==Boolean.class) {
                params.addProperty(param, (Boolean) value);
            } else {
                throw new RuntimeException( "Unsupported value"+value.getClass().getSimpleName() );
            }
        }

        requsetPayload.add("params",params);
        return requsetPayload;
    }
}
