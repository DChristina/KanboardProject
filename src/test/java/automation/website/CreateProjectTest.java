package automation.website;

import automation.base.BaseGUITest;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateProjectTest extends BaseGUITest {

    @Test(groups={"smoke", "regression"}, description = "Creation of the project")
    public void createProjectTest() throws InterruptedException {
        IndexPage indexPage = new IndexPage();
        DashboardPage dashboardPage = new DashboardPage();
        ProjectPage createdProjectPage = new ProjectPage();

        indexPage.authorization("admin", "admin");

        dashboardPage.createProject("MyNewAdminProject");
        Thread.sleep(5000);

        createdProjectPage.getSidebar().shouldBe(Condition.visible);
        createdProjectPage.getSummaryContent().shouldBe(Condition.visible);


        Assert.assertEquals(createdProjectPage.getMainTitle().getText(),"KB MyNewAdminProject" );
        Assert.assertTrue(WebDriverRunner.url().contains("http://localhost/project/"));


    }


}
