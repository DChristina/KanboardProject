package automation.website;

import automation.base.BaseGUITest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseGUITest {

    @Test(groups={"smoke", "regression"})
    public void sucsesfullAuthorizationTest(){
        IndexPage page = new IndexPage();
        page.setUserName("admin");
        page.setPassword("admin");
        page.pressSignInButton();

        Boolean resultURLIsCorrect =  wd( ).getCurrentUrl( ).equals("http://localhost/");
        Assert.assertTrue(resultURLIsCorrect);
    }

}
