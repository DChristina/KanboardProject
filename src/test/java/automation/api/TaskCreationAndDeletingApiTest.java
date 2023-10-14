package automation.api;

import automation.api.kanboard.*;
import automation.base.BaseKanboardTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TaskCreationAndDeletingApiTest extends BaseKanboardTest {

    @Test (groups={"smoke", "regression"})
    public void creationAndDeletingTaskTest() {
        //creation of the project
        String projectData = jsonRpcPayload.project.createProject("Autotest project").toString( );
        ProjectCreationResponse projectCreationResponse = this.request( )
                .contentType(ContentType.JSON)
                .body(projectData)
                .post("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(ProjectCreationResponse.class);
        Assert.assertTrue(projectCreationResponse.getResult( ) > 0);

        //checking that project was created by getting its data
        String getCreatedProjectData = jsonRpcPayload.project.getProject(projectCreationResponse.getResult( ).toString( )).toString( );
        Project gottenProject = this.request( )
                .contentType(ContentType.JSON)
                .body(getCreatedProjectData)
                .get("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).body( ).jsonPath( ).getObject("result", Project.class);

        //creation the task
        String taskData = jsonRpcPayload.task.createTask("Automation test task", Integer.parseInt(gottenProject.getId( ))).toString( );
        TaskCreationResponse taskCreationResponse = this.request( )
                .contentType(ContentType.JSON)
                .body(taskData)
                .post("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(TaskCreationResponse.class);
        Assert.assertEquals(projectCreationResponse.getJsonrpc( ), "2.0");
        Assert.assertTrue(taskCreationResponse.getResult( ) > 0);


        //checking that task was created by getting its data
        String getCreatedTaskData = jsonRpcPayload.task.getTask(taskCreationResponse.getResult( ).toString( )).toString( );
        Task gottenTask = this.request( )
                .contentType(ContentType.JSON)
                .body(getCreatedTaskData)
                .get("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).body( ).jsonPath( ).getObject("result", Task.class);
        Assert.assertEquals(gottenTask.getTitle( ), "Automation test task");


        //deleting  the task
        String deleteCreatedTaskData = jsonRpcPayload.task.deleteTask(taskCreationResponse.getResult( ).toString( )).toString( );
        TaskDeletingResponse deleteTaskData = this.request( )
                .contentType(ContentType.JSON)
                .body(deleteCreatedTaskData)
                .delete("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(TaskDeletingResponse.class);
        Assert.assertEquals(deleteTaskData.getResult( ), true);

        //checking that task was deleted by getting its data
        Response responseOfGettingDeletedTask = this.request()
                .contentType(ContentType.JSON)
                .body(getCreatedTaskData)
                .get("/jsonrpc.php")
                .then().log().all()
                .extract().response().path("result");
        Assert.assertEquals(responseOfGettingDeletedTask, null);

        //deleting  the project
        String deleteCreatedProjectData = jsonRpcPayload.project.deletingProject(projectCreationResponse.getResult().toString()).toString();
        ProjectDeletingResponse deleteProjectData = this.request()
                .contentType(ContentType.JSON)
                .body(deleteCreatedProjectData)
                .delete("/jsonrpc.php")
                .then().log().all()
                .extract().as(ProjectDeletingResponse.class);
        Assert.assertEquals(deleteProjectData.getResult(), true);

        //checking that project was deleted by getting its data
        Response responseOfGettingDeletedProject = this.request()
                .contentType(ContentType.JSON)
                .body(getCreatedProjectData)
                .get("/jsonrpc.php")
                .then().log().all()
                .extract().response().path("result");
        Assert.assertEquals(responseOfGettingDeletedProject, null);
    }

}