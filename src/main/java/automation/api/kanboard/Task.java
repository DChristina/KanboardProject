package automation.api.kanboard;

import automation.api.KanboardJSONRPC;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({
        "description", "date_creation", "color_id",
        "project_id", "column_id", "owner_id",
        "position", "is_active", "date_completed",
        "score", "date_due", "category_id",
        "creator_id", "date_modification", "reference",
        "date_started", "time_spent", "time_estimated", "swimlane_id",
        "date_moved", "recurrence_status", "recurrence_trigger",
        "recurrence_factor", "recurrence_timeframe", "recurrence_basedate",
        "recurrence_parent", "recurrence_child", "url",
        "color", "priority","tags", "external_provider","external_uri"
})
public class Task {
    public Integer id;
    public String title;
    public String description;
    public String date_creation;
    public String color_id;
    public String project_id;
    public String column_id;
    public String owner_id;
    public String position;
    public String is_active;
    public Object date_completed;
    public String score;
    public String date_due;
    public String category_id;
    public String creator_id;
    public String date_modification;
    public String reference;
    public Object date_started;
    public String time_spent;
    public String time_estimated;
    public String swimlane_id;
    public String date_moved;
    public String recurrence_status;
    public String recurrence_trigger;
    public String recurrence_factor;
    public String recurrence_timeframe;
    public String recurrence_basedate;
    public Object recurrence_parent;
    public Object recurrence_child;
    public String url;
    public Color color;
    public Integer priority;
    public String[] tags;
    public String external_provider;
    public String external_uri;

    public class Color{
        public String name;
        public String background;
        public String border;
    }

    public Task(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    public Task() {
        super();
    }

    @Step("Usage a template for body requests during the task creation process.")
    public JsonObject createTask(String taskTitle, Integer project_id){
        Map<String, Object> params = new HashMap<>(  );
        params.put("title",taskTitle);
        params.put("project_id",project_id);
        return KanboardJSONRPC.baseRequest("createTask",params) ;
    }

    @Step("Usage a template for body requests during the task getting process.")
    public JsonObject getTask(String taskId){
        Map<String, Object> params = new HashMap<>(  );
        params.put("task_id",taskId);
        return KanboardJSONRPC.baseRequest("getTask",params) ;
    }

    @Step("Usage a template for body requests during the task deleting process.")
    public JsonObject deleteTask(String taskId){
        Map<String, Object> params = new HashMap<>(  );
        params.put("task_id",taskId);
        return KanboardJSONRPC.baseRequest("removeTask",params) ;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
