# MadisonIsland_Automation

This project serves as an example of a test automation framework that I've built in order to aquire experience and knowledge in the field of automation. 

## Tech stack
+ Java as the programming language
+ Selenium WebDriver as the web browser automation framework using the Java binding
+ TestNG as the UnitTest framework to support test creation 
+ Maven as the build and dependancy management tool

## Supported browsers

+ Chrome
+ Edge
+ Firefox

I've used the ```WebDriverManager``` library that carries out the management (download, setup, and maintenance) of the drivers required by Selenium WebDriver (chromedriver, geckodriver, msedgedriver) 
in a fully automated manner.

## Page Object Model
While going through various workflows within a web app, the user interacts with certain pages and webelements. The Page Object Model simply models these as objects within the test code. 
In order to support the Page Object, I've used ```Page Factory``` to initialize the web elements that are defined in the page objects. The initialization is done inside the page class constructor.

```java
public LoginPage(WebDriver driver) {
    super(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
}
```
All the necessary user interactions on these elemets are implemented as methods inside the page classes, this works as an elegant way to implement test routines that are both readable and easier to maintain or update in the future.

```java
 public MyAccountPage loginWith(String email, String password) {
    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    loginButton.click();
    return new MyAccountPage(driver);
}
```

```java
@Test (groups = {"smoke", "regression"})
public void validLoginTest() {
    navigationPage = new NavigationPage(driver);
    loginPage = navigationPage.navigateToLogin();
    myAccountPage = loginPage.loginWith(ConstantUtils.USER, ConstantUtils.PASSWORD);
    Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
}
 ```   

POM helps to deal with one of the most common challange when it comes to web apps automation - keeping your selectors up to date with the latest code version. Because all the selectors belonging to a particular page are stored inside the coresponding page class it is much easier to apply changes since they are made in only one place.

## Data-driven testing

## Reporting

The reporting is implemented with the ```ExtentReports library```. After every successful run, an html file is generated in the target/extent-reports folder. 
The ```ExtentTestListener``` implements the ```ITestListener``` interface and has a role in attaching additional information to the test report through the following overriden methods:

+ ```onStart```: adds the browser information to the test report
+ ```onFinish```: creates the html report 
+ ```onTestSuccess```: logs the status of a passed test
+ ```onTestFailure```: logs the status of a failed test and adds a screenshot to the test report
+ ```onTestSkipped```: logs the status of a skipped test

The listeners are included in every .xml test suite inside a ```<listener>``` tag.

```xml
    <listeners>
        <listener class-name="Utils.ExtentTestListener"></listener>
    </listeners>
```

![reportExample](https://user-images.githubusercontent.com/79747055/216767532-b3a6cd7d-4507-4284-aa66-9411a2807df4.png)

![reportEx](https://user-images.githubusercontent.com/79747055/216765342-196655fe-a813-4151-bd45-c568649ce9e3.png)

![reporte](https://user-images.githubusercontent.com/79747055/216765354-870e818c-db84-4c4a-9ed2-1e6c60703e60.png)

If a test failure occurs, a screen-shot of the UI at the moment of the failure is embedded in the report.

## Utils

## Config

