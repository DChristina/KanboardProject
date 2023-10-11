package automation.api;

import automation.Config;
import automation.base.BaseKanboardTest;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CourseWorkTestExample extends BaseKanboardTest {

    @Test
    public void testCreateCategory(){

        String payload = jsonRpcPayload.category.createCategory("Test", 1).toString();
        String response = this.request().
                contentType(ContentType.JSON)
                .body(payload)
                .post("/jsonrpc.php")
                .then().log().all().extract().body().toString();
    }
}
