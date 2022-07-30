package CommonFuntions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

public class CommonFunctions
{

  AndroidDriver driver;
  public CommonFunctions(AndroidDriver driver) {
    this.driver = driver;
  }

  public void doubleTapElement(String locatorText) {
    new TouchAction(driver).tap(TapOptions.tapOptions().withTapsCount(2).withElement(ElementOption.element(driver.findElement(By.xpath(locatorText))))).perform();
  }

  public boolean dragAndDrop(int srcXoffset, int srcYoffset, int destXoffset, int destYoffset) {
    new TouchAction(driver).longPress(PointOption.point(srcXoffset, srcYoffset)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).perform().moveTo(PointOption.point(destXoffset, destYoffset)).release().perform();
    int xOffSet = Integer.parseInt(driver.findElement(By.xpath("test")).getAttribute("x"));
    int yOffSet = Integer.parseInt(driver.findElement(By.xpath("test2")).getAttribute("y"));
    return (xOffSet == destXoffset && yOffSet == destYoffset);
  }

  public void takeScreenshot(String fileName)
  {
    File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    try
    {
      FileUtils.copyFile(file, new File(fileName));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

/*  public void writeCircle(double centerX, double centerY, double radius, double startDegree, double degrees, int steps)
  {
    double angle = degrees / steps;
    double prevX = centerX + radius * Math.cos(startDegree * Math.PI / 180F); ;
    double prevY = centerY + radius * Math.sin(startDegree * Math.PI / 180F);

    TouchAction circleTouch = new TouchAction(driver);
    circleTouch.press(prevX, prevY);

    for(int i = 1; i <= steps; ++i)
    {
      double newX = centerX + radius * Math.cos((startDegree + angle * i) * Math.PI / 180F);
      double newY = centerY + radius * Math.sin((startDegree + angle * i) * Math.PI / 180F);

      double difX = newX - prevX;
      double difY = newY - prevY;
      circleTouch.moveTo(difX, difY);

      prevX = newX;
      prevY = newY;
    }

    circleTouch.release();
    circleTouch.perform();
  }*/

}
