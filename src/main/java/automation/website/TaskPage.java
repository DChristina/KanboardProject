package automation.website;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class TaskPage {

    public SelenideElement closeTaskLink = $x("//div[@class=\"sidebar sidebar-icons\"]//*[contains(text(),'Close this task')]");
    public SelenideElement confirmationPopUP = $x("//div[@id=\"modal-box\"]");
    public SelenideElement yesButtonConfirmationPopUP = $x("//button[@id=\"modal-confirm-button\"]");
    public SelenideElement activityStreamLink = $x("//div[@class=\"sidebar sidebar-icons\"]//*[contains(text(),'Activity stream')]");
    public SelenideElement closedTaskStatus = $x("//div[@class=\"task-summary-column\"][4]//strong[text()=\"Completed:\"]");
    public SelenideElement taskLastActivityTitle = $x("//p[@class=\"activity-title\"]");
    public SelenideElement addCommentLink = $x("//div[@class=\"sidebar sidebar-icons\"]//*[contains(text(),'Add a comment')]");
    public SelenideElement textareaCommentPopUp = $x("//div[@id=\"modal-content\"]//textarea[@name=\"comment\"]");
    public SelenideElement saveButtonCommentPopUp = $x("//div[@id=\"modal-content\"]//button[@type=\"submit\"]");
    public SelenideElement addedLastComment = $x("//div[@class=\"comment \"]");
    public SelenideElement lastCommentTaxt = $x("//div[@class=\"comment \"]//p[1]");

    public void addCommentInPopUp(String comment){
        addCommentLink.click();
        textareaCommentPopUp.setValue(comment);
        saveButtonCommentPopUp.click();
    }
    public void closeTask(){
        closeTaskLink.click();
        confirmationPopUP.shouldBe(Condition.visible);
        yesButtonConfirmationPopUP.click();
    }


}
