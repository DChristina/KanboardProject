package automation.base;

import automation.Config;
import com.codeborne.selenide.WebDriverRunner;
import automation.Session;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseGUITest extends BaseTestNG
{
    @BeforeMethod(alwaysRun = true)
    public void before() {
        this.wd().get(String.format("http://%s:%s",
                Config.API_BASE_HOST.value,
                Config.API_BASE_PORT.value
        ));
        WebDriverRunner.setWebDriver(this.wd());
    }

    @AfterMethod(alwaysRun = true)
    public void after() {
        Session.get().close();
    }

    protected WebDriver wd() {
        return Session.get().webdriver();
    }
}
