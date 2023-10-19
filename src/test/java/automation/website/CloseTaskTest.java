package automation.website;

import automation.api.kanboard.ProjectApiActions;
import automation.api.kanboard.TaskApiActions;
import automation.api.kanboard.UserApiActions;
import automation.base.BaseGUITest;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.*;


public class CloseTaskTest extends BaseGUITest {
    int user_id;
    int project_id;
    String name  = "TestUser6";
    String password = "TestUser555";
    String role = "app-manager";
    String taskTitle = "Autotest task";

    UserApiActions userApiActions = new UserApiActions();
    ProjectApiActions projectApiActions = new ProjectApiActions();
    TaskApiActions taskApiActions = new TaskApiActions();

    @BeforeMethod(alwaysRun = true)
    public void beforeCloseTaskTest(){
        user_id =  userApiActions.createUserApiProcess(name,password,role);
        project_id = projectApiActions.createProjectFullProcess("Autotest new Project");
        userApiActions.linkUserAndProjectApiProcess(project_id, user_id, "project-manager");
        taskApiActions.createTaskApiProcess(taskTitle,project_id);

    }

    @Test(groups={"smoke", "regression","UITest"},description = "UI test for closing task process")
    public void closeTaskTest(){
        IndexPage indexPage = new IndexPage();
        DashboardPage dashboardPage  = new DashboardPage();
        BoardPage  boardPage = new BoardPage();
        TaskPage taskPage = new TaskPage();

        indexPage.authorization(name,password);
        dashboardPage.goToPtojectLink.click();
        boardPage.taskTitle.click();
        taskPage.closeTask();
        taskPage.closedTaskStatus.shouldBe(Condition.visible);
        taskPage.activityStreamLink.click();
        String closeActivityTitle = taskPage.taskLastActivityTitle.getText();

        Assert.assertTrue(closeActivityTitle.contains(name + " "+ "closed the task"));

    }
    @AfterMethod(alwaysRun = true)
    public void afterCloseTaskTest(){
        userApiActions.deleteUserApiProcess(user_id);
        projectApiActions.deleteProjectFullProcess(project_id);
    }
}
