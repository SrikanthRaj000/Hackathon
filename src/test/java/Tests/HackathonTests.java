package Tests;

import StepDefinitions.ValidationMethods;
import org.testng.annotations.Test;

public class HackathonTests extends ValidationMethods
{

  //Double Tab Operation
  @Test(description = "Scenario-1")
  public void doubleTap() {
    doubleTapTest();
  }

  //Drag and drop operations
  @Test(description = "Scenario-3")
  public void dragAndDrop() {
    dragAndDropTest(402, 632, 402, 1591);
  }

  //Visual Testing
  @Test(description = "Scenario-4")
  public void visualTest() {
    visualTesting();
  }

}
