package automation.website;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class IndexPage {
    private SelenideElement userName = $("#form-username");
    private SelenideElement password = $("#form-password");
    private SelenideElement signInButton = $x("//div[@class=\"form-actions\"]/button");
    private SelenideElement forgotPasswordLink = $x("//div[@class=\"reset-password\"]/a");
    private SelenideElement errorAlert = $x("//div[@class=\"form-login\"]/p");

    //Alert alert = wait.until(ExpectedConditions.alertIsPresent());

    @Step("Set username")
    public void setUserName(String name){
        getUserName().shouldBe(Condition.visible).clear();
        getUserName().sendKeys(name);
    }
    @Step("Set password")
    public void setPassword(String pass){
        getPassword().shouldBe(Condition.visible).clear();
        getPassword().sendKeys(pass);
    }

    @Step("Submit form")
    public void pressSignInButton(){
        getSignInButton().shouldBe(Condition.visible).click();
    }

    @Step("Sign in")
    public void authorization (String name, String password){
        setUserName(name);
        setPassword(password);
        signInButton.click();
    }

    public SelenideElement getUserName() {
        return userName;
    }

    public SelenideElement getPassword() {
        return password;
    }

    public SelenideElement getSignInButton() {
        return signInButton;
    }

    public SelenideElement getForgotPasswordLink() {
        return forgotPasswordLink;
    }

    public SelenideElement getErrorAlert() {
        return errorAlert;
    }
}
