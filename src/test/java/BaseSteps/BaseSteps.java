package BaseSteps;

import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseSteps implements Locators
{
  public AndroidDriver driver;
  DesiredCapabilities dc = new DesiredCapabilities();

  //Common steps which needs to be executed before every test
  @BeforeTest
  public void launchApplication(String appName) throws Exception{
    dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Hackathon");
    dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
    dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
    dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
    //Apk location to be updated below
    dc.setCapability(MobileCapabilityType.APP, "");
    URL url = new URL("http://127.0.0.1:4723/wb/hub");
    driver = new AndroidDriver(url, dc);
    driver.findElement(By.xpath(LOGIN_BTN)).click();
    waitForElementToDisappear(LOGIN_BTN);
  }

  //Close the application after completing the execution
  @AfterTest
  public void closeApplication() {
    driver.quit();
  }

  public void waitForElementToDisappear(String locator)
    throws InterruptedException
  {
    for (int i = 0; i < 5; i++)
    {
      if(driver.findElement(By.xpath(locator)).isDisplayed()) {
        Thread.sleep(3000);
      }
      else {
        break;
      }
    }
  }

}
