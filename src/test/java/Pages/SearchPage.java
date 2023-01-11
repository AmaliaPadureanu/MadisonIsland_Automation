package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.ID, using = "search")
    private WebElement searchBar;

    @FindBy(how = How.XPATH, using = "//*[@id=\"search_mini_form\"]/div[1]/button")
    private WebElement searchButton;

    public SearchPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

}
