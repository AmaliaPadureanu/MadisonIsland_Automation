package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailsPage extends BasePage {

    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//button[@onclick='productAddToCartForm.submit(this)']//span//span[contains(text(),'Add to Cart')]")
    private WebElement addToCartButton;
    @FindBy(how = How.CSS, using = "#product_addtocart_form > div.product-shop > div.product-name")
    private WebElement productName;
    @FindBy(how = How.XPATH, using = "//ul[@id='configurable_swatch_color']//li//a")
    private List<WebElement> colorOptions;
    @FindBy(how = How.XPATH, using = "//ul[@id='configurable_swatch_size']//li//a")
    private List<WebElement> sizeOptions;
    @FindBy(how = How.XPATH, using = "//body[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/form[1]/div[3]/div[3]/p[1]")
    private WebElement availability;
    @FindBy(how = How.ID, using = "qty")
    private WebElement quantityInput;
    @FindBy(how = How.XPATH, using = "//span[@class='regular-price'][1]")
    private WebElement productPrice;
    @FindBy(how = How.XPATH, using = "(//span[contains(text(),'Additional Information')])[1]")
    private WebElement additionalInformationTab;
    @FindBy(how = How.ID, using = "product-attribute-specs-table")
    private WebElement productSpecsTable;
    @FindBy(how = How.XPATH, using = "//a[normalize-space()='Add to Wishlist']")
    private WebElement addToWishlistButton;

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

        if (availability.getText().equalsIgnoreCase("out of stock")) {
            tryAnotherSize();
        }
    }

    private void tryAnotherSize() {
        while (availability.getText().equalsIgnoreCase("out of stock")) {
            sizeOptions.get(getRandomNumber(0, sizeOptions.size())).click();
        }
    }

    public String getProductSpecs() {
        additionalInformationTab.click();
        return productSpecsTable.getText();
    }

    public Integer getProductPrice() {
        return Integer.valueOf(productPrice.getText().split("\\.")[0].substring(1));
    }

    public List<String> getAvailableColors() {
        List<String> availableColors = new ArrayList<>();

        for (WebElement option : colorOptions) {
            availableColors.add(option.getAttribute("title"));
        }
        return availableColors;
    }

    public List<String> getAvailableSizes() {
        List<String> availableSizes = new ArrayList<>();

        for (WebElement option : sizeOptions) {
            availableSizes.add(option.getAttribute("title"));
        }
        return availableSizes;
    }

    public WishlistPage addToWishlist(){
        addToWishlistButton.click();
        return new WishlistPage(driver);
    }
}
