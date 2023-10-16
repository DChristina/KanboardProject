package automation.website;

import automation.api.kanboard.ProjectApiActions;
import automation.api.kanboard.TaskApiActions;
import automation.api.kanboard.UserApiActions;
import automation.base.BaseGUITest;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;

public class CloseTaskTest extends BaseGUITest {
    int user_id;
    int project_id;
    String name  = "TestUser6";
    String password = "TestUser555";
    String role = "app-manager";
    String taskTitle = "Autotest task";
    String taskDescription = "Description for autotest task";
    String taskColor = "Green";

    UserApiActions userApiActions = new UserApiActions();
    ProjectApiActions projectApiActions = new ProjectApiActions();
    TaskApiActions taskApiActions = new TaskApiActions();

    @BeforeTest
    public void beforeCloseTaskTest(){
        user_id =  userApiActions.createUserApiProcess(name,password,role);
        project_id = projectApiActions.createProjectFullProcess("Autotest new Project");
        userApiActions.linkUserAbdProjecrApiProcess(project_id, user_id, "project-manager");
        taskApiActions.createTaskApiProcess(taskTitle,project_id);

    }

    @Test(groups={"smoke", "regression"})
    public void closeTaskTest(){
        IndexPage indexPage = new IndexPage();
        DashboardPage dashboardPage  = new DashboardPage();
        BoardPage  boardPage = new BoardPage();
        TaskPage taskPage = new TaskPage();

        indexPage.authorization(name,password);
        dashboardPage.goToPtojectLink.click();
        boardPage.taskTitle.click();
        //taskPage.closeTask();
        taskPage.closeTaskLink.click();
        taskPage.confirmationPopUP.shouldBe(Condition.visible);
        taskPage.yesButtonConfirmationPopUP.click();
        taskPage.closedTaskStatus.shouldBe(Condition.visible);
        taskPage.activityStreamLink.click();
        String closeActivityTitle = taskPage.taskLastActivityTitle.getText();

        Assert.assertTrue(closeActivityTitle.contains(name + " "+ "closed the task"));

    }
    @AfterTest
    public void afterCloseTaskTest(){
        userApiActions.deleteUserApiProcess(user_id);
        projectApiActions.deleteProjectFullProcess(project_id);
    }
}
