package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class WishlistPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//body/div/div/div/div/div/div/div[1]/ul[1]/li[1]")
    private WebElement wishlistSuccessMessage;
    @FindBy(how = How.XPATH, using = "//tbody//tr")
    private List<WebElement> itemsInWishlist;
    @FindBy(how = How.XPATH, using = "(//a[normalize-space()='Remove item'])[1]")
    private WebElement removeButton;

    public WishlistPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getWishlistSuccessMessage() {
        return wishlistSuccessMessage.getText().toLowerCase();
    }

    public List<WebElement> getItemsInWishlist() {
        return itemsInWishlist;
    }

    public void removeItemFromWishlist() {
        removeButton.click();
        driver.switchTo().alert().accept();
    }
}
