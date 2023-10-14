package automation.api;

import automation.api.kanboard.*;
import automation.base.BaseKanboardTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("REST API Regression Testing using TestNG")
@Feature("Verify CRUID Operations on User module")
public class ProjectCreationAndDeletingApiTest extends BaseKanboardTest {

    @Test (groups={"smoke", "regression"})
    public void creationAndDeletingProjectTest(){
        //creation of the project
        String projectData = jsonRpcPayload.project.createProject("Autotest project").toString();
        ProjectCreationResponse projectCreationResponse = this.request()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(projectData)
                .filter(new AllureRestAssured())
                .post("/jsonrpc.php")
                .then().log().all()
                .extract().as(ProjectCreationResponse.class);
        Assert.assertEquals(projectCreationResponse.getJsonrpc(),"2.0");
        Assert.assertTrue(projectCreationResponse.getResult()>0);

        //checking that project was created by getting its data
        String getCreatedProjectData = jsonRpcPayload.project.getProject(projectCreationResponse.getResult().toString()).toString();
        Project gottenProject = this.request()
                .contentType(ContentType.JSON)
                .body(getCreatedProjectData)
                .filter(new AllureRestAssured())
                .get("/jsonrpc.php")
                .then().log().all()
                .extract().body().jsonPath().getObject("result", Project.class);
        Assert.assertEquals(gottenProject.getName(),"Autotest project");

        //deleting  the project
        String deleteCreatedProjectData = jsonRpcPayload.project.deletingProject(projectCreationResponse.getResult().toString()).toString();
        ProjectDeletingResponse deleteProjectData = this.request()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(deleteCreatedProjectData)
                .filter(new AllureRestAssured())
                .delete("/jsonrpc.php")
                .then().log().all()
                .extract().as(ProjectDeletingResponse.class);
        Assert.assertEquals(deleteProjectData.getResult(), true);

        //checking that project was deleted by getting its data
        Response responseOfGettingDeletedProject = this.request()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(getCreatedProjectData)
                .filter(new AllureRestAssured())
                .get("/jsonrpc.php")
                .then().log().all()
                .extract().response().path("result");
        Assert.assertEquals(responseOfGettingDeletedProject, null);
    }


}
