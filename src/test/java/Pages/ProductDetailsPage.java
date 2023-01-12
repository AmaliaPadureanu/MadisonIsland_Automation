package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//button[@onclick='productAddToCartForm.submit(this)']//span//span[contains(text(),'Add to Cart')]")
    private WebElement addToCartButton;

    @FindBy(how = How.CSS, using = "#product_addtocart_form > div.product-shop > div.product-name")
    private WebElement productName;

    @FindBy(how = How.XPATH, using = "//ul[@id='configurable_swatch_color']//li")
    private List<WebElement> colorOptions;

    @FindBy(how = How.XPATH, using = "//ul[@id='configurable_swatch_size']//li")
    private List<WebElement> sizeOptions;

    @FindBy(how = How.XPATH, using = "//body[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/form[1]/div[3]/div[3]/p[1]")
    private WebElement availability;

    @FindBy(how = How.ID, using = "qty")
    private WebElement quantityInput;

    @FindBy(how = How.XPATH, using = "//span[@class='regular-price'][1]")
    private WebElement productPrice;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public CartPage addToCart() {
        addToCartButton.click();
        return new CartPage(driver);
    }

    public String getProductName() {
        return productName.getText().toLowerCase();
    }

    public void chooseRandomColor() {
        colorOptions.get(getRandomNumber(0, colorOptions.size())).click();
    }

    public void chooseRandomSize() {
        sizeOptions.get(getRandomNumber(0, sizeOptions.size())).click();
        System.out.println(availability.getText());
        if (availability.getText().equalsIgnoreCase("out of stock")) {
            tryAnotherSize();
        }
    }

    private void tryAnotherSize() {
        while (availability.getText().equalsIgnoreCase("out of stock")) {
            sizeOptions.get(getRandomNumber(0, sizeOptions.size())).click();
            System.out.println("tried again");
        }
    }

    public void changeQuantity(String quantity) {
        clearAndSendKeys(quantityInput, quantity);
    }
}
