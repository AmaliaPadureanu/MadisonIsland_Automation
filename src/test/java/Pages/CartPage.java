package Pages;

import org.openqa.selenium.By;
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
    @FindBy(how = How.CSS, using = "#shopping-cart-table > tbody > tr")
    private List<WebElement> productsInCart;

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

    public void removeProductFromCart(WebElement product) {
        WebElement removeBtn = product.findElement(By.cssSelector("td.a-center.product-cart-remove.last > a"));
        removeBtn.click();
    }

    public int getNoOfProductsInCart() {
        return productsInCart.size();
    }

    public List<WebElement> getProductsInCart() {
        return productsInCart;
    }

    public int getProductSubtotal(WebElement product) {
        String subtotal = product.findElement(By.cssSelector("td.product-cart-total")).getText();
        String substring = subtotal.substring(1);
        String finalValue = substring.substring(0, substring.indexOf("."));

        if (finalValue.indexOf(",") != -1)  {
            String finalValueWithoutComma = finalValue.split(",")[0] + finalValue.split(",")[1];
            return Integer.valueOf(finalValueWithoutComma);
        }

        return Integer.valueOf(finalValue);
    }

    public void editProductQuantity(WebElement product, String newQuantity) {
        WebElement editBox = product.findElement(By.cssSelector("td.product-cart-actions > input"));
        clearAndSendKeys(editBox, newQuantity);
        WebElement updateQuantityButton = product.findElement(By.cssSelector("td.product-cart-actions > button"));
        updateQuantityButton.click();
    }
}
