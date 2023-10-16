package automation.website;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BoardPage {
    public SelenideElement menuDropdownIcon = $x("//a[@title=\"Configure this project\"]");
    public SelenideElement addNewTaskLink =  $x("//ul[@class=\"dropdown-submenu-open\"]//*[contains(text(),'Add a new task')]");
    public SelenideElement taskCreationForm = $x("//div[@id=\"modal-box\"]");
    public SelenideElement taskTitleTaskCreationForm  = $x("//input[@id=\"form-title\"]");
    public SelenideElement taskDescriptionTaskCreationForm = $x("//textarea[@name=\"description\"]");
    public SelenideElement colorDropdawnTaskCreationForm = $x("//select[@id=\"form-color_id\"]");
    public SelenideElement saveButtonTaskCreationForm = $x("//button[@type=\"submit\"]");
    public SelenideElement tasksOnBoard = $x( "//div[@class=\"task-board-expanded\"]");
    public SelenideElement taskTitle = tasksOnBoard.$x("div[@class=\"task-board-title\"]/a");



    public SelenideElement getMenuDropdownIcon() {
        return menuDropdownIcon;
    }

    public SelenideElement getAddNewTaskLink() {
        return addNewTaskLink;
    }

    public SelenideElement getTaskCreationForm() {
        return taskCreationForm;
    }

    public SelenideElement getTaskTitleTaskCreationForm() {
        return taskTitleTaskCreationForm;
    }

    public SelenideElement getTaskDescriptionTaskCreationForm() {
        return taskDescriptionTaskCreationForm;
    }

    public SelenideElement getColorDropdawnTaskCreationForm() {
        return colorDropdawnTaskCreationForm;
    }

    public SelenideElement getSaveButtonTaskCreationForm() {
        return saveButtonTaskCreationForm;
    }
}
