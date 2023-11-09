package automation.base;

import automation.Config;
import automation.api.KanboardJSONRPC;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseKanboardTest {
    final protected KanboardJSONRPC jsonRpcPayload = new KanboardJSONRPC();

    public RequestSpecification request(){
        return this.request(null,null );
    }
    public RequestSpecification request(String username, String password){
        username = username!=null ? username:Config.API_BASE_NAME.value;
        password = password!=null ? password:Config.API_BASE_PASSWORD.value;

        return RestAssured.
                given().baseUri(Config.getApiBaseURL().toString())
                .auth()
                .basic(username,password);
    }
}
