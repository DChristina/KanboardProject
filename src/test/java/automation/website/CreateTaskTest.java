package automation.website;

import automation.api.kanboard.ProjectApiActions;
import automation.api.kanboard.User;
import automation.api.kanboard.UserApiActions;
import automation.base.BaseGUITest;
import com.codeborne.selenide.Condition;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Locale;

public class CreateTaskTest extends BaseGUITest {
    int user_id;
    int project_id;
    String name  = "TestUser2";
    String password = "TestUser555";
    String role = "app-manager";
    String taskTitle = "Autotest task";
    String taskDescription = "Description for autotest task";
    String taskColor = "Green";
    UserApiActions userApiActions = new UserApiActions();
    ProjectApiActions projectApiActions = new ProjectApiActions();

    @BeforeTest
    public void beforeCreateTaskTest(){

        user_id =  userApiActions.createUserApiProcess(name,password,role);
        project_id = projectApiActions.createProjectFullProcess("Autotest new Project");
        userApiActions.linkUserAbdProjecrApiProcess(project_id, user_id, "project-manager");

    }

    @Test(groups={"smoke", "regression"})
    public void CreateTaskTest(){
      IndexPage indexPage = new IndexPage();
      DashboardPage dashboardPage  = new DashboardPage();
      BoardPage  boardPage = new BoardPage();

        indexPage.authorization(name,password);
        dashboardPage.goToPtojectLink.click();

        boardPage.menuDropdownIcon.click();
        boardPage.addNewTaskLink.click();
        boardPage.taskCreationForm.shouldBe(Condition.visible);
        boardPage.taskTitleTaskCreationForm.setValue(taskTitle);
        boardPage.taskDescriptionTaskCreationForm.setValue(taskDescription);
        boardPage.colorDropdawnTaskCreationForm.selectOptionContainingText(taskColor);
        boardPage.saveButtonTaskCreationForm.click();


        Assert.assertEquals(boardPage.taskTitle.getOwnText(), taskTitle);
        Assert.assertTrue(boardPage.tasksOnBoard.parent().attr("class").contains(taskColor.toLowerCase(Locale.ROOT)));

    }

    @AfterTest
    public void afterCreateTaskTest(){
        userApiActions.deleteUserApiProcess(user_id);
        projectApiActions.deleteProjectFullProcess(project_id);
    }
}
