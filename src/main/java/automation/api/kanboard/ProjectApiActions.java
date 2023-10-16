package automation.api.kanboard;

import automation.base.BaseKanboardTest;
import io.restassured.http.ContentType;

public class ProjectApiActions extends BaseKanboardTest {
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
