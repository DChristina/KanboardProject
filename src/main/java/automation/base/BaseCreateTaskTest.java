package automation.base;

import automation.api.kanboard.Project;
import automation.api.kanboard.User;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseCreateTaskTest extends BaseKanboardTest{
   public  int user_id;
   public int project_id;
   User newUser = new User();
   Project newProject = new Project();

  /* @BeforeClass(alwaysRun = true)
    public void createUserAndProject(){
        user_id = newUser.createUserFullProcess("Kris","Melnik");
        project_id = newProject.createProjectFullProcess("AutotestProject");
    }

    @AfterClass(alwaysRun = true)
    public void deleteUserAndProject(){
        newUser.deleteUserFullProcess(user_id);
        newProject.deleteProjectFullProcess(project_id);
    }*/

}
