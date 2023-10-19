package automation.api;

import automation.Config;
import automation.api.kanboard.Project;
import automation.api.kanboard.ProjectCreationResponse;
import automation.base.BaseKanboardTest;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CourseWorkTestExample extends BaseKanboardTest {

    @Test(groups={"smoke", "regression","apiTest"}, description = "Api test Creation of the category")
    public void testCreateCategory(){
        //creation of the project
        String projectData = jsonRpcPayload.project.createProject("Autotest project").toString( );
        ProjectCreationResponse projectCreationResponse = this.request( )
                .contentType(ContentType.JSON)
                .body(projectData)
                .post("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(ProjectCreationResponse.class);
        Assert.assertTrue(projectCreationResponse.getResult( ) > 0);

        //creation the category
        String uniqueCategoryName = "Test" + System.currentTimeMillis();
        String payload = jsonRpcPayload.category.createCategory(uniqueCategoryName, projectCreationResponse.getResult()).toString();
        int response = this.request().
                contentType(ContentType.JSON)
                .body(payload)
                .post("/jsonrpc.php")
                .then().log().all()
                .extract().body().jsonPath().getInt("result");

       Assert.assertTrue(response>0);
    }
}
