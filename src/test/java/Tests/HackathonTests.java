package Tests;

import StepDefinitions.ValidationMethods;
import org.testng.annotations.Test;

public class HackathonTests extends ValidationMethods
{

  @Test(description = "Scenario-1")
  public void doubleTap() {
    doubleTapTest();
  }

  @Test(description = "Scenario-3")
  public void dragAndDrop() {
    dragAndDropTest(402, 632, 402, 1591);
  }

  @Test(description = "Scenario-4")
  public void visualTest() {
    visualTesting();
  }

}
