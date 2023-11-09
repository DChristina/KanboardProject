package automation.website;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {
    public SelenideElement mainTitle = $x("//div[@class=\"title-container\"]/h1");
    public SelenideElement sidebar = $x("//div[@class=\"sidebar\"]");
    public SelenideElement summaryContent = $x("//div[@class=\"sidebar-content\"]");

    public SelenideElement getMainTitle() {
        return mainTitle;
    }

    public SelenideElement getSidebar() {
        return sidebar;
    }

    public SelenideElement getSummaryContent() {
        return summaryContent;
    }


}
