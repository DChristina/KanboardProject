package automation.api.kanboard;

import automation.base.BaseKanboardTest;
import io.opentelemetry.api.trace.StatusCode;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;


public class UserApiActions extends BaseKanboardTest {

    @Step("Api request for creation of the user")
    public int createUserApiProcess(String name, String password, String role) {

        String userData = jsonRpcPayload.user.createUser(name, password, role).toString( );
        UserCreationResponse userCreationResponse = this.request( )
                .contentType(ContentType.JSON)
                .body(userData)
                .post("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract( ).as(UserCreationResponse.class);
        return Integer.parseInt(userCreationResponse.getResult( ));
    }

    @Step("Api request for getting of the user")
    public User getUserApiProcess (Integer user_id){
        String payloadCreatedUser = jsonRpcPayload.user.getUser(user_id).toString();
        User gottenUser =  this.request()
                .contentType(ContentType.JSON)
                .body(payloadCreatedUser)
                .get("/jsonrpc.php")
                .then().log().all()
                .extract().body().jsonPath().getObject("result",User.class);
        return gottenUser;
    }

    @Step("Api request for deleting of the user")
    public Boolean  deleteUserApiProcess (Integer user_id){
        String payloadDeletingdUser = jsonRpcPayload.user.deleteUser(user_id).toString();
        UserDeletingResponse deletedUser = this.request()
                .contentType(ContentType.JSON)
                .body(payloadDeletingdUser)
                .delete("/jsonrpc.php")
                .then().log().all()
                .extract().as(UserDeletingResponse.class);
        return deletedUser.getResult();
    }

    @Step("Api request for linking user and project")
    public void  linkUserAndProjectApiProcess (Integer project_id, Integer user_id,  String role) {
        String linkUserProject = jsonRpcPayload.user.linkUserAndProject(project_id, user_id, role).toString( );
        String response = this.request( )
                .contentType(ContentType.JSON)
                .body(linkUserProject)
                .delete("/jsonrpc.php")
                .then( ).log( ).all( )
                .extract().response().body().toString();

        System.out.println(response );

    }

}
