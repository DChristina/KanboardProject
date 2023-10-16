package automation.api.kanboard;

import automation.api.KanboardJSONRPC;
import automation.base.BaseKanboardTest;
import automation.website.DashboardPage;
import automation.website.IndexPage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties({"is_ldap_user","name","email","google_id","github_id",
        "filter","token","theme","api_access_token","avatar_path","is_active","gitlab_id",
        "lock_expiration_date","notifications_enabled","nb_failed_login","notifications_filter",
        "timezone","language","disable_login_form","twofactor_activated","twofactor_secret",})
public class User {
    private String id;
    private String username;
    private String password;
    private String role;
    private String is_ldap_user;
    private String name;
    private String email;
    private String google_id;
    private String github_id;
    private String notifications_enabled;
    private String timezone;
    private String language;
    private Integer disable_login_form;
    private Integer twofactor_activated;
    private Boolean twofactor_secret;
    private String token;
    private Integer notifications_filter;
    private Integer nb_failed_login;
    private Boolean lock_expiration_date;
    private String gitlab_id;
    private Integer is_active;
    private String avatar_path;
    private String api_access_token;
    private String  theme;
    private String  filter;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(){
        super();
    }

    public JsonObject createUser(String username,String password, String role){
        Map<String, Object> params = new HashMap<>(  );
        params.put("username",username);
        params.put("password",password);
        params.put("role", role);
        return KanboardJSONRPC.baseRequest("createUser",params) ;
    }

    public JsonObject getUser( Integer user_id){
        Map<String, Object> params = new HashMap<>(  );
        params.put("user_id",user_id);
        return KanboardJSONRPC.baseRequest("getUser",params) ;
    }
    public JsonObject deleteUser( Integer user_id){
        Map<String, Object> params = new HashMap<>(  );
        params.put("user_id",user_id);
        return KanboardJSONRPC.baseRequest("removeUser",params) ;
    }
    public JsonObject linkUserAndProject( Integer project_id, Integer user_id, String role){
        Map<String, Object> params = new HashMap<>(  );
        params.put("user_id",user_id);
        params.put("project_id",project_id);
        params.put("role",role);
        return KanboardJSONRPC.baseRequest("addProjectUser",params) ;
    }
    public void  LoginUser (String name, String password){
        IndexPage indexPage = new IndexPage();
        indexPage.setUserName(name);
        indexPage.setPassword("password");
        indexPage.pressSignInButton();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getIs_ldap_user() {
        return is_ldap_user;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGoogle_id() {
        return google_id;
    }

    public String getGithub_id() {
        return github_id;
    }

    public String getNotifications_enabled() {
        return notifications_enabled;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIs_ldap_user(String is_ldap_user) {
        this.is_ldap_user = is_ldap_user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGoogle_id(String google_id) {
        this.google_id = google_id;
    }

    public void setGithub_id(String github_id) {
        this.github_id = github_id;
    }

    public void setNotifications_enabled(String notifications_enabled) {
        this.notifications_enabled = notifications_enabled;
    }
}
