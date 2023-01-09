package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage {

    @FindBy (how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/div[1]/span[1]")
    WebElement slideshowPreviousButton;

    @FindBy (how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/div[1]")
    WebElement slideshowNextButton;

    @FindBy(how = How.XPATH, using = "//*[@id=\"top\"]/body/div/div[2]/div[2]/div/div/div[2]/ul[1]/li")
    WebElement afterRegisterMessage;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public String getAfterRegisterMessage() {
        return afterRegisterMessage.getText();
    }

}
