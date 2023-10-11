package automation;

import automation.utils.MySQLDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Session
{
    static final private ThreadLocal<Session> _instance = new ThreadLocal<>();

    private Session() {
        Runtime.getRuntime().addShutdownHook(new Thread(Session.this::close));
    }

    static public Session get() {
        if (_instance.get() == null)
            _instance.set(new Session());
        return _instance.get();
    }

    private MySQLDriver _mysql;

    public MySQLDriver mysql() {
        if (this._mysql == null)
            this._mysql = new MySQLDriver();
        return this._mysql;
    }

    private WebDriver _webdriver;

    public WebDriver webdriver() {
        if (this._webdriver == null) {
            if ("chrome".equalsIgnoreCase(Config.WEB_BROWSER.value)) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.setCapability("browserVersion", "116.0.5845.111");
                options.addArguments("wm-window-animations-disabled");
                options.addArguments("ash-disable-smooth-screen-rotation");
                options.addArguments("disable-smooth-scrolling");
                options.addArguments("disable-infobars");
                options.addArguments("disable-default-apps");
                options.addArguments("disable-extensions");
                //options.addArguments("lang=en_US");
                options.setAcceptInsecureCerts(true);
                //options.addArguments("auto-open-devtools-for-tabs");
                Map<String, Object> preferences = new HashMap<>();
                preferences.put("history.saving_disabled", true);
                preferences.put("browser.show_home_button", false);
                preferences.put("credentials_enable_service", false);
                preferences.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", preferences);
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation", "load-extension"});
                if (Config.WEB_BROWSER_NO_GUI.isTrue()) {
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                }
                this._webdriver = new ChromeDriver(options);
                this._webdriver.manage().window().setPosition(new Point(-300, 0));
            }
            //this._webdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            this._webdriver.manage().window().maximize();
            // Use for very small screens
            //this._webdriver.manage().window().setSize(new Dimension(1280, 1024));
        }

        return this._webdriver;
    }

    public void close() {
        if (this._webdriver != null) {
            this._webdriver.quit();
            this._webdriver = null;
        }
    }
}

