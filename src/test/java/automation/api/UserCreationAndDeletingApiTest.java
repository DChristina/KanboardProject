package automation.api;

import automation.api.kanboard.UserCreationResponse;
import automation.api.kanboard.UserDeletingResponse;
import automation.api.kanboard.User;
import automation.base.BaseKanboardTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCreationAndDeletingApiTest extends BaseKanboardTest {

    @Test(groups={"smoke", "regression","apiTest"},description = "Api test Creation of the user")
    public void createAndDeleteUserTest()  {
        //creation of the user
        String userData = jsonRpcPayload.user.createUser("testUser39","password","app-admin").toString();
        UserCreationResponse userCreationResponse = this.request()
                .contentType(ContentType.JSON)
                .body(userData)
                .post("/jsonrpc.php")
                .then().log().all()
                .extract().as(UserCreationResponse.class);
        Assert.assertEquals(userCreationResponse.getJsonrpc(),"2.0");
        Assert.assertTrue(Integer.parseInt(userCreationResponse.getResult())>0);


        //checking that user was created by getting its data
       String payloadCreatedUser = jsonRpcPayload.user.getUser(Integer.parseInt(userCreationResponse.getResult())).toString();
        User gottenUser =  this.request()
                 .contentType(ContentType.JSON)
                 .body(payloadCreatedUser)
                 .get("/jsonrpc.php")
                 .then().log().all()
                 .extract().body().jsonPath().getObject("result",User.class);
        Assert.assertEquals(gottenUser.getUsername(),"testUser39");

        //deleting the user
        String payloadDeletingdUser = jsonRpcPayload.user.deleteUser(Integer.parseInt(userCreationResponse.getResult())).toString();
        UserDeletingResponse deletedUser = this.request()
                .contentType(ContentType.JSON)
                .body(payloadDeletingdUser)
                .delete("/jsonrpc.php")
                .then().log().all()
                .extract().as(UserDeletingResponse.class);
        Assert.assertEquals(deletedUser.getResult(), true);

        //checking that user was deleted by getting its data
       Response responseOfGettingDeletedUser = this.request()
                .contentType(ContentType.JSON)
                .body(payloadCreatedUser)
                .get("/jsonrpc.php")
                .then().log().all()
                .extract().response().path("result");
       Assert.assertEquals(responseOfGettingDeletedUser, null);
    }
}
