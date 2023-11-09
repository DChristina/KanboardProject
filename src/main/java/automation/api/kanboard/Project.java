package automation.api.kanboard;

import automation.api.KanboardJSONRPC;
import automation.base.BaseKanboardTest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"is_active","token","owner_id","email","enable_global_tags","task_limit",
        "per_swimlane_task_limits","per_swimlane_task_limits","predefined_email_subjects","priority_start",
        "priority_end","priority_default","last_modified","start_date","end_date","is_public","is_private",
        "default_swimlane","show_default_swimlane","description","identifier","url"})
public class Project  {
    public String id;
    public String name;
    public String is_active;
    public String token;
    public String last_modified;
    public String is_public;
    public String is_private;
    public String default_swimlane;
    public String show_default_swimlane;
    public String description;
    public String identifier;
    public Url url;
    public String start_date;
    public String end_date;
    public Integer owner_id;
    public Integer priority_default;
    public Integer priority_start;
    public Integer priority_end;
    public String email;
    public String predefined_email_subjects;
    public Integer per_swimlane_task_limits;
    public Integer task_limit;
    public Integer enable_global_tags;

    public class Url{
        public String board;
        public String calendar;
        public String list;

        public  Url( String board,String calendar,String list){
            this.board = board;
            this.calendar = calendar;
            this.list = list;
        }
        public  Url(){
            super();
        }
    }

    public  Project(String id,String name){
        this.id = id;
        this.name = name;
    }
    public  Project(){
        super();
    }
    @Step("Usage a template for body requests during the project creation process.")
    public JsonObject createProject(String projectName){
        Map<String, Object> params = new HashMap<>(  );
        params.put("name",projectName);
        return KanboardJSONRPC.baseRequest("createProject",params) ;
    }

    @Step("Getting data about the project projectId")
    public JsonObject getProject(String projectId){
        Map<String, Object> params = new HashMap<>(  );
        params.put("project_id",projectId);
        return KanboardJSONRPC.baseRequest("getProjectById",params) ;
    }

    @Step("Deleting of the project projectId")
    public JsonObject deletingProject(String projectId){
        Map<String, Object> params = new HashMap<>(  );
        params.put("project_id",projectId);
        return KanboardJSONRPC.baseRequest("removeProject",params) ;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
