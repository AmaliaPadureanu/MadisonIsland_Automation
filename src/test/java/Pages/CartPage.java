package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//body/div/div/div/div/div/div/ul/li[1]")
    private WebElement cartSuccessMessage;
    @FindBy(how = How.XPATH, using = "//td[@data-rwd-label='Price']//span//span")
    private WebElement productPrice;
    @FindBy(how = How.XPATH, using = "(//td[@data-rwd-label='Subtotal'])")
    private WebElement subtotal;
    @FindBy(how = How.CSS, using = "#shopping-cart-table > tbody > tr")
    private List<WebElement> productsInCart;
    @FindBy(how = How.CSS, using = "#shopping-cart-table > tbody > tr.first.odd > td.a-center.product-cart-remove.last > a")
    private WebElement removeProductButton;

    public CartPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getCartSuccessMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("http://demo-store.seleniumacademy.com/checkout/cart/"));
        return cartSuccessMessage.getText().toLowerCase();
    }

    public void removeProductFromCart() {
        removeProductButton.click();
    }

    public int getNoOfProductsInCart() {
        return productsInCart.size();
    }
}
