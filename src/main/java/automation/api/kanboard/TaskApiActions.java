package automation.api.kanboard;

import automation.base.BaseKanboardTest;
import io.restassured.http.ContentType;
import org.testng.Assert;

public class TaskApiActions extends BaseKanboardTest {


    public int  createTaskApiProcess(String title, int project_id) {

        String taskData = jsonRpcPayload.task.createTask(title, project_id).toString( );
        TaskCreationResponse taskCreationResponse = this.request( )
                .contentType(ContentType.JSON)
                .body(taskData)
                .post("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(TaskCreationResponse.class);
        return taskCreationResponse.getResult();
    }
}
