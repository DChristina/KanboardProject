package automation.website;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.ByAttribute;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DashboardPage {

    private SelenideElement dashboardTitle = $x("//div[@class=\"title-container\"]/h1");
    private SelenideElement newProjectLink = $(By.linkText("New project"));
    private SelenideElement createProjectForm = $x("//div[@id=\"modal-box\"]");
    private SelenideElement nameInputProjectForm = createProjectForm.find(By.name("name"));
    private SelenideElement IdentifierInputProjectForm = createProjectForm.find(By.name("identifier"));
    private SelenideElement saveButtonProjectForm = $x("//div[@class=\"form-actions\"]/button");
    public SelenideElement goToPtojectLink = $x("//span[@class=\"table-list-title \"]/a");

    @Step("Creation of the project")
    public void createProject(String name){
        newProjectLink.click();
        createProjectForm.shouldBe(Condition.visible);
        nameInputProjectForm.setValue(name);
        saveButtonProjectForm.click();
    }

    @Step("Getting main title on the page")
    public SelenideElement getDashboardTitle() {
        return dashboardTitle;
    }

    public SelenideElement getNewProjectLink() {
        return newProjectLink;
    }

    public SelenideElement getCreateProjectForm() {
        return createProjectForm;
    }

    public SelenideElement getNameInputProjectForm() {
        return nameInputProjectForm;
    }

    public SelenideElement getIdentifierInputProjectForm() {
        return IdentifierInputProjectForm;
    }

    public SelenideElement getSaveButtonProjectForm() {
        return saveButtonProjectForm;
    }
}
