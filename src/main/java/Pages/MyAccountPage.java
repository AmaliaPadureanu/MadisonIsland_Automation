package Pages;

import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage {

    public MyAccountPage(WebDriver driver) {
        super(driver);
//        if (!driver.getTitle().equals("My Account")) {
//            throw new IllegalStateException("This is not the My Account Page," +
//                    " the current page is: " + driver.getCurrentUrl());
//        }
    }
}
