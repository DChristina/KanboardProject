package automation.website;

import automation.api.kanboard.ProjectApiActions;
import automation.api.kanboard.TaskApiActions;
import automation.api.kanboard.UserApiActions;
import automation.base.BaseGUITest;
import org.testng.Assert;
import org.testng.annotations.*;

public class CommentTaskTest extends BaseGUITest  {

        int user_id;
        int project_id;
        String name  = "TestUser7";
        String password = "TestUser555";
        String role = "app-manager";
        String taskTitle = "Autotest task";
        String comment = "Autotest comment";

        UserApiActions userApiActions = new UserApiActions( );
        ProjectApiActions projectApiActions = new ProjectApiActions( );
        TaskApiActions taskApiActions = new TaskApiActions( );

        @BeforeMethod(alwaysRun = true)
        public void beforeCommentTask() {
            user_id = userApiActions.createUserApiProcess(name, password, role);
            project_id = projectApiActions.createProjectFullProcess("Autotest new Project");
            userApiActions.linkUserAndProjectApiProcess(project_id, user_id, "project-manager");
            taskApiActions.createTaskApiProcess(taskTitle, project_id);
        }

        @Test(groups={"smoke", "regression","UITest"},description = "UI test for commenting task process")
        public void commentTask(){
            IndexPage indexPage = new IndexPage();
            DashboardPage dashboardPage  = new DashboardPage();
            BoardPage  boardPage = new BoardPage();
            TaskPage taskPage = new TaskPage();

            indexPage.authorization(name,password);
            dashboardPage.goToPtojectLink.click();
            boardPage.taskTitle.click();
            taskPage.addCommentInPopUp(comment);
            String createdCommentText = taskPage.lastCommentTaxt.getOwnText();
            Assert.assertTrue( createdCommentText.equals(comment));

        }

        @AfterMethod(alwaysRun = true)
        public void afterCommentTask(){
            userApiActions.deleteUserApiProcess(user_id);
            projectApiActions.deleteProjectFullProcess(project_id);
        }

}
