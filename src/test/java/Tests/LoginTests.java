package Tests;

import org.testng.annotations.Test;

public class LoginTests extends BaseTest{

    @Test
    public void login() {
        driver.get("http://demo-store.seleniumacademy.com");
    }
}
