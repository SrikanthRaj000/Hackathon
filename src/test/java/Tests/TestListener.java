package Tests;

import static Tests.ExtentTestManager.getTest;

import java.util.Objects;
import java.util.logging.Logger;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends HackathonTests implements ITestListener
{
  
    Logger log = Logger.getLogger("Hackathon");

  private static String getTestMethodName(ITestResult iTestResult) {
    return iTestResult.getMethod().getConstructorOrMethod().getName();
  }

  @Override
  public void onStart(ITestContext iTestContext) {
    log.info("I am in onStart method " + iTestContext.getName());
    iTestContext.setAttribute("WebDriver", this.driver);
  }

  @Override
  public void onFinish(ITestContext iTestContext) {
    log.info("I am in onFinish method " + iTestContext.getName());
    //Do tier down operations for ExtentReports reporting!
    ExtentManager.extentReports.flush();
  }

  @Override
  public void onTestStart(ITestResult iTestResult) {
    log.info(getTestMethodName(iTestResult) + " test is starting.");
  }

  @Override
  public void onTestSuccess(ITestResult iTestResult) {
    log.info(getTestMethodName(iTestResult) + " test is succeed.");
    //ExtentReports log operation for passed tests.
    getTest().log(Status.PASS, "Test passed");
  }

  @Override
  public void onTestFailure(ITestResult iTestResult) {
    log.info(getTestMethodName(iTestResult) + " test is failed.");

    //Get driver from BaseTest and assign to local webdriver variable.
    Object testClass = iTestResult.getInstance();
    //Take base64Screenshot screenshot for extent reports
    String base64Screenshot =
      "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);

    //ExtentReports log and screenshot operations for failed tests.
    getTest().log(Status.FAIL, "Test Failed",
      getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
  }

  @Override
  public void onTestSkipped(ITestResult iTestResult) {
    Logger.getLogger("Hackathon").info(getTestMethodName(iTestResult) + " test is skipped.");
    //ExtentReports log operation for skipped tests.
    getTest().log(Status.SKIP, "Test Skipped");
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    log.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
  }
}