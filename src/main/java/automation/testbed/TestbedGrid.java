package automation.testbed;

import automation.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestbedGrid extends BaseTestbed {

    @Override
    public WebDriver createDriver() {
        String gridHost;
        String gridPort;
        DesiredCapabilities caps = new DesiredCapabilities();
        if ("chrome".equalsIgnoreCase(Config.WEB_BROWSER.value)) {
            gridHost = Config.SELENIUM_GRID_CHROME_HOST.value;
            gridPort = Config.SELENIUM_GRID_CHROME_PORT.value;
            caps.setCapability(ChromeOptions.CAPABILITY,this.getCommonChromeOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME);
            caps.setCapability(CapabilityType.BROWSER_VERSION, "117.0");
            caps.setPlatform(Platform.LINUX);
        } else if("firefox".equalsIgnoreCase(Config.WEB_BROWSER.value)){
            gridHost = Config.SELENIUM_GRID_FIREFOX_HOST.value;
            gridPort = Config.SELENIUM_GRID_FIREFOX_PORT.value;
            caps.setCapability(ChromeOptions.CAPABILITY,this.getCommonFirefoxOptions());
            caps.setCapability(CapabilityType.BROWSER_NAME, Browser.FIREFOX);

        } else {
            throw new RuntimeException( "Unsupported browser" + Config.WEB_BROWSER.value );
        }
        String gridURL  = "http://"+ gridHost+":"+ gridPort+ "/wd/hub";
        try{
            URL url = new URL(gridURL);
            return new RemoteWebDriver(url,caps);
        } catch (MalformedURLException e){
            throw new RuntimeException( "Url is not valid:" + gridURL+ e );
        }

    }
}
