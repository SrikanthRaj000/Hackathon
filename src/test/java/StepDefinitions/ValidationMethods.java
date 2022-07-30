package StepDefinitions;

import java.io.File;
import java.time.Duration;

import BaseSteps.BaseSteps;
import CommonFuntions.CommonFunctions;
import com.asprise.ocr.Ocr;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ValidationMethods
  extends BaseSteps
{

  CommonFunctions commonFunctions = new CommonFunctions(driver);

  public void doubleTapTest()
  {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    commonFunctions.doubleTapElement(DOUBLE_TAP_BTN);
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(CLICK_HERE_BTN))));
    Assert.assertTrue(driver.findElement(By.xpath(CLICK_HERE_BTN)).isDisplayed() && (driver.findElement(By.xpath(TAP_ACTIVITY_LABEL)).isDisplayed()));
  }

  public void dragAndDropTest(int srcXoffset, int srcYoffset, int destXoffset, int destYoffset)
  {
    Assert.assertTrue(commonFunctions.dragAndDrop(srcXoffset, srcYoffset, destXoffset, destYoffset));
  }

  public void visualTesting()
  {
    driver.findElement(By.xpath(BASE_IMAGE_BTN));
    commonFunctions.takeScreenshot("src/main/resources/TestOutput/Baseimage.png");
    Ocr ocr = new Ocr();
    ocr.startEngine("eng", Ocr.SPEED_FASTEST);
    String imageText = ocr.recognize(new File[]{ new File("src/main/resources/TestOutput/Baseimage.png") },
      Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT
    );
    ocr.stopEngine();
    driver.navigate().back();
    driver.findElement(By.xpath(SECONDARY_IMAGE_BTN));
    Assert.assertTrue(imageText.contains("Titans"));
  }

}
