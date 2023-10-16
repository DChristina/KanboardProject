package automation.api.kanboard;

import automation.base.BaseKanboardTest;
import io.opentelemetry.api.trace.StatusCode;
import io.restassured.http.ContentType;


public class UserApiActions extends BaseKanboardTest {

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

    public void  linkUserAbdProjecrApiProcess (Integer project_id, Integer user_id,  String role) {
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
