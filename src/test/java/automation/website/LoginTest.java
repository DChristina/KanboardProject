package automation.website;

import automation.base.BaseGUITest;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseGUITest {

    @Test(groups={"smoke", "regression"}, description = "Successful authorization of the user")
    public void succesfullAuthorizationTest(){
        IndexPage indexPage = new IndexPage();
        DashboardPage dashboardPage = new DashboardPage();

        indexPage.setUserName("admin");
        indexPage.setPassword("admin");
        indexPage.pressSignInButton();

        Assert.assertTrue(dashboardPage.getDashboardTitle().isDisplayed());
    }

    @Test(groups={"smoke", "regression"}, description = "Unsuccessful authorization of the user, because of wrong password")
    public void unSuccesfullAuthorizationTest(){
        IndexPage indexPage = new IndexPage();

        indexPage.setUserName("admin");
        indexPage.setPassword("password");
        indexPage.pressSignInButton();
        indexPage.getErrorAlert().shouldBe(Condition.visible);

       Assert.assertTrue(indexPage.getErrorAlert().isDisplayed());
       Assert.assertTrue(indexPage.getErrorAlert().getOwnText().toString().equals("Bad username or password"));
    }

    @Test(groups={"smoke", "regression"}, description = "Unsuccessful authorization of the user, because of wrong name")
    public void unSuccesfullAuthorizationTest2(){
        IndexPage indexPage = new IndexPage();

        indexPage.setUserName("name");
        indexPage.setPassword("admin");
        indexPage.pressSignInButton();
        indexPage.getErrorAlert().shouldBe(Condition.visible);

        Assert.assertTrue(indexPage.getErrorAlert().isDisplayed());
        Assert.assertTrue(indexPage.getErrorAlert().getOwnText().toString().equals("Bad username or password"));
    }

}
