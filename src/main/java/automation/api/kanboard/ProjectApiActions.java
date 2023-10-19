package automation.api.kanboard;

import automation.base.BaseKanboardTest;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

public class ProjectApiActions extends BaseKanboardTest {
    @Step("Api request for creation of the project")
    public int createProjectFullProcess (String projectName){
        String projectData = jsonRpcPayload.project.createProject(projectName).toString();
        ProjectCreationResponse projectCreationResponse = this.request()
                .contentType(ContentType.JSON)
                .body(projectData)
                .post("/jsonrpc.php")
                .then().log().all()
                .extract().as(ProjectCreationResponse.class);
        return projectCreationResponse.getResult();
    }

    @Step("Api request for deleting of the project")
    public Boolean  deleteProjectFullProcess (Integer project_id){
        String deleteCreatedProjectData = jsonRpcPayload.project.deletingProject(project_id.toString()).toString();
        ProjectDeletingResponse deleteProjectData = this.request()
                .contentType(ContentType.JSON)
                .body(deleteCreatedProjectData)
                .delete("/jsonrpc.php")
                .then().log().all()
                .extract().as(ProjectDeletingResponse.class);
        return deleteProjectData.getResult();
    }
}
