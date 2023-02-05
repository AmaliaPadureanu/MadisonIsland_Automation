# MadisonIsland_Automation

:computer: This project serves as an example of a test automation framework that I've built in order to acquire experience and knowledge in the field of automation. 

## Tech stack
:gear: Java as the programming language

:gear: Selenium WebDriver as the web browser automation framework using the Java binding

:gear: TestNG as the UnitTest framework to support test creation 

:gear: Maven as the build and dependancy management tool

:gear: Extent Report as the reporting tool

:gear: JSON and MySQL as the external data sources

## Supported browsers

:pushpin: Chrome

:pushpin: Edge

:pushpin: Firefox

I've used the ```WebDriverManager``` library that carries out the management (download, setup, and maintenance) of the drivers required by Selenium WebDriver (chromedriver, geckodriver, msedgedriver) 
in a fully automated manner.

## Page Object Model
While going through various workflows within a web app, the user interacts with certain pages and webelements. The ```Page Object Model``` simply models these as objects within the test code. 

```java
public LoginPage(WebDriver driver) {
    super(driver);
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
}
```
In order to support the Page Object, I've used ```Page Factory``` to initialize the web elements that are defined in the page objects. The initialization is done inside the page class constructor.

```java
 public MyAccountPage loginWith(String email, String password) {
    emailInput.sendKeys(email);
    passwordInput.sendKeys(password);
    loginButton.click();
    return new MyAccountPage(driver);
}
```

All the necessary user interactions on these elemets are implemented as methods inside the page classes, this works as an elegant way to implement test routines that are both readable and easier to maintain or update in the future.

```java
@Test (groups = {"smoke", "regression"})
public void validLoginTest() {
    navigationPage = new NavigationPage(driver);
    loginPage = navigationPage.navigateToLogin();
    myAccountPage = loginPage.loginWith(ConstantUtils.USER, ConstantUtils.PASSWORD);
    Assert.assertTrue(myAccountPage.getPageTitle().equals("My Account"));
}
 ```   
 
```POM``` helps to deal with one of the most common challange when it comes to web apps automation - keeping your selectors up to date with the latest code version. Because all the selectors belonging to a particular page are stored inside the coresponding page class it is much easier to apply changes since they are made in only one place.

## Data-driven testing

I've used external data sources in order to: 

:point_right: separate the test logic from the test data

:point_right: be able to run the same test method several times with different sets of data, thus reducing the number of test methods 

:point_right: easily modify the data without making changes to the code

I've used ```Jackson Databind``` library to read ```JSON``` data and ```MySQL Connector``` to make SELECT requests to a local ```MySQL``` database in order to retrieve data and parse it into Java Objects through custom Object Models. This proccess takes place inside a method that has the @DataProvider annotation, so the data can be further used by a test method.

```java
    @DataProvider(name = "jsonInvalidRegisterDP")
    public Iterator<Object[]> jsonDPCollectionInvalid() throws IOException {
        Collection<Object[]> dataProvider = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\invalidRegisterData.json");
        RegisterModel[] registerModels = objectMapper.readValue(file, RegisterModel[].class);

        for (RegisterModel registerModel : registerModels) {
            dataProvider.add(new Object[] {registerModel});
        }
        return dataProvider.iterator();
    }
```

The ```ObjectMapper``` class is used to retrieve and parse the JSON data from the ```src\\test\\resources\\Data\\invalidRegisterData.json``` file into ```RegisterModel``` objects that are further added to a ```Collection of Object[]```. An Iterator then loops throgh the Collection and the method returns a ```RegisterModel``` object that is used by the test method. Every object returned represents a different data set, thus the test method runs several times with different data sets, increasing test coverage.

```java
   @DataProvider(name = "invalidEditAccountInformationDP")
   public Iterator<Object[]> SQLDpCollectionInvalid() throws SQLException {
      Collection<Object[]> dataProvider = new ArrayList<>();

         Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
                 + "/" + dbSchema, dbUser, dbPassword);
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery("SELECT * FROM editaccountinformation_negative");

         while (resultSet.next()) {
            EditAccountInformationModel editAccountInformationModel = new EditAccountInformationModel(
                    resultSet.getString("firstname"),
                    resultSet.getString("middlename"),
                    resultSet.getString("lastname"),
                    resultSet.getString("email"),
                    resultSet.getString("firstnameError"),
                    resultSet.getString("lastnameError"),
                    resultSet.getString("emailError"),
                    resultSet.getString("emailErrorPopup"));
            dataProvider.add(new Object[] {editAccountInformationModel});
         }

      return dataProvider.iterator();
   }
```

The ```.getConnection``` method establishes a connection with the database using the database hostname, port, scheme, user and password that are provided in the ```config.properties``` file. 

The ```Statement``` object sends a query to the database that selects data from the ```editaccountinformation_negative``` table and stores it in a ```ResultSet``` object. The ```.getString``` method is called on each row in the ResultSet object and the values from each column are copied in an ```EditAccountInformationModel``` object which is then added to a Collection and returned by the method using an ```Iterator```.

## BasePage

This class is extended by all the page classes and implements some useful methods that can be used in the subclasses to write more concise and clean routines.

```java
 public void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
 }
```    

## BaseTest

The setup and teardown methods run automatically from the BaseTest class using the ```@BeforeTest``` and ```@AfterTest``` annotations. This class is extended by all the test classes.

:point_right: ```@BeforeTest``` creates the browser instance based on the ```browser``` parameter in ```config.properties```, opens the home page of the website, gets the connection information for the MySQL database from ```config.properties``` and initializes the ```ExtentTest``` object

:point_right: ```@AfterTest``` closes the driver instance

## Reporting

The reporting is implemented with the ```ExtentReports library```. After every successful run, an html file is generated in the ```target/extent-reports``` folder and is named with a combination of extentReport + current time in milliseconds in order to avoid overriding. 

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

On test failures screenshots will automatically be taken and embedded in the report. The screenshot files will be named with a combination of screenshot + current time in milliseconds in order to avoid overriding.

## Configuration file

The framework is easily configurable thanks to the ```config.properties``` file, the way in which the tests are ran can be changed by enabling/ disabling certain properties. 

```java
protocol=http
hostname=www.demo-store.seleniumacademy.com
port=80
browser=EDGE
headlessMode=true
startMaximized=true
#............DB CONNECTION.............
dbHostname=localhost
dbPort=3306
dbUser=root
dbPassword=root
dbSchema=automation
```
