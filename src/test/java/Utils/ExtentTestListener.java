package Utils;

import Tests.BaseTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ExtentTestListener extends BaseTest implements ITestListener {

    static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext context) {
        context.setAttribute("WebDriver", this.driver);
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.extentReports.createTest(getTestMethodName(result)).log(Status.PASS, "TEST PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver drv = ((BaseTest) testClass).driver;
        String base64Img = null;
        try {
            base64Img = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File(SeleniumUtils.takeScreenshot(drv))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExtentManager.extentReports.createTest(getTestMethodName(result)).log(Status.FAIL, "TEST FAILED",
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Img).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManager.extentReports.createTest(getTestMethodName(result)).log(Status.SKIP, "TEST SKIPPED");
    }
}
