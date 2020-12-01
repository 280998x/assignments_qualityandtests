package com.mayab.calidad.funcional;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import java.util.List;
import org.junit.*;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCRUD {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  //System.setProperty("webdriver.gecko.driver", "E:\\-Folders\\Universidad\\Calidad y Pruebas de Software\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  baseUrl = "https://www.google.com/";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAdd() throws Exception {
	  String name = "Alan Zuniga";
	  String email = "00331754@anahuac.mx";
	  String age = "22";
	  String sex = "m";
	  
	  driver.get("https://mern-crud.herokuapp.com/");
	  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
	  driver.findElement(By.name("name")).click();
	  driver.findElement(By.name("name")).clear();
	  driver.findElement(By.name("name")).sendKeys(name);
	  driver.findElement(By.name("email")).sendKeys(Keys.DOWN);
      driver.findElement(By.name("email")).sendKeys(Keys.DOWN);
      driver.findElement(By.name("email")).clear();
      driver.findElement(By.name("email")).sendKeys(email);
      driver.findElement(By.name("age")).click();
      driver.findElement(By.name("age")).clear();
      driver.findElement(By.name("age")).sendKeys(age);
      driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
      driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
      driver.findElement(By.xpath("//form/button")).click();
      assertEquals("Successfully added!", new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[4]/div/p"))).getText());
      driver.findElement(By.xpath("//i")).click();
      WebElement Table = driver.findElement(By.xpath("//table/tbody"));
      List<WebElement> tableRows = Table.findElements(By.tagName("tr"));
      for (int row = 0; row < tableRows.size(); row++) {
    	  List<WebElement> rowColumns = tableRows.get(row).findElements(By.tagName("td"));
    	  if(rowColumns.get(0).getText().equals(name) &&
    		 rowColumns.get(1).getText().equals(email) &&
    		 rowColumns.get(2).getText().equals(age) &&
    		 rowColumns.get(3).getText().equals(sex)) {
    		  assertTrue(true);
    		  break;
    	  } else if (row == tableRows.size() - 1) {
    		  assertTrue(false);
    	  }
      }
  }
  
  @Test
  public void testModify() throws Exception {
	  driver.get("https://mern-crud.herokuapp.com/");
	  
	  WebElement editButton = null;
	  WebElement table = driver.findElement(By.xpath("//table/tbody"));
      List<WebElement> tableRows = table.findElements(By.tagName("tr"));
      List<WebElement> rowColumns = tableRows.get(0).findElements(By.tagName("td"));
      List<WebElement> buttons = rowColumns.get(4).findElements(By.tagName("button"));
	  editButton = buttons.get(0);
	  
	  String newName = "Alan";
	  String newEmail = "0034@anahuac.mx";
	  String newAge = "21";
	  String newSex = "m";
	  
	  editButton.click();
	  driver.findElement(By.name("name")).click();
	  driver.findElement(By.name("name")).clear();
	  driver.findElement(By.name("name")).sendKeys(newName);
	  driver.findElement(By.name("email")).click();
	  driver.findElement(By.name("email")).click();
	  driver.findElement(By.name("email")).clear();
	  driver.findElement(By.name("email")).sendKeys(newEmail);
	  driver.findElement(By.name("age")).clear();
	  driver.findElement(By.name("age")).sendKeys(newAge);
	  driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
	  driver.findElement(By.xpath("//div[2]/div[1]")).click();
	  driver.findElement(By.xpath("//form/button")).click();
	  assertEquals("Successfully updated!", new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[4]/div/p"))).getText());
      driver.findElement(By.xpath("//i")).click();
	  driver.get("https://mern-crud.herokuapp.com/");
      table = driver.findElement(By.xpath("//table/tbody"));
      tableRows = table.findElements(By.tagName("tr"));
      for (int row = 0; row < tableRows.size(); row++) {
    	  rowColumns = tableRows.get(row).findElements(By.tagName("td"));
    	  if(rowColumns.get(0).getText().equals(newName) &&
    		 rowColumns.get(1).getText().equals(newEmail) &&
    		 rowColumns.get(2).getText().equals(newAge) &&
    		 rowColumns.get(3).getText().equals(newSex)) {
    		  assertTrue(true);
    		  return;
    	  } else if (row == tableRows.size() - 1) {
    		  assertTrue(false);
    	  }
      }
  }
  
  @Test
  public void testPop() throws Exception {
	  driver.get("https://mern-crud.herokuapp.com/");
	  
	  WebElement table = driver.findElement(By.xpath("//table/tbody"));
      List<WebElement> tableRows = table.findElements(By.tagName("tr"));
	  List<WebElement> rowColumns = tableRows.get(0).findElements(By.tagName("td"));
	  
	  String expectedName = rowColumns.get(0).getText();
	  String expectedEmail = rowColumns.get(1).getText();
	  String expectedAge = rowColumns.get(2).getText();
	  String expectedSex = rowColumns.get(3).getText();
	  
	  List<WebElement> buttons = rowColumns.get(4).findElements(By.tagName("button"));
	  buttons.get(1).click();
	  driver.findElement(By.xpath("//div[3]/button")).click();
      
	  driver.get("https://mern-crud.herokuapp.com/");
      
      table = driver.findElement(By.xpath("//table/tbody"));
      tableRows = table.findElements(By.tagName("tr"));
      
      for (int row = 0; row < tableRows.size(); row++) {
    	  rowColumns = tableRows.get(row).findElements(By.tagName("td"));
    	  if(rowColumns.get(0).getText().equals(expectedName) &&
    		 rowColumns.get(1).getText().equals(expectedEmail) &&
    		 rowColumns.get(2).getText().equals(expectedAge) &&
    		 rowColumns.get(3).getText().equals(expectedSex)) {
    		  assertTrue(false);
    		  return;
    	  } else if (row == tableRows.size() - 1) {
    		  assertTrue(true);
    	  }
      }
  }

  @After
  public void tearDown() throws Exception {
	  addTest();
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  public void addTest() throws Exception {
	  driver.get("https://mern-crud.herokuapp.com/");
	  
	  WebElement editButton = null;
	  WebElement table = driver.findElement(By.xpath("//table/tbody"));
      List<WebElement> tableRows = table.findElements(By.tagName("tr"));
      List<WebElement> rowColumns = tableRows.get(0).findElements(By.tagName("td"));
      List<WebElement> buttons = rowColumns.get(4).findElements(By.tagName("button"));
	  editButton = buttons.get(0);
	  
	  String newName = "test";
	  String newEmail = "test@test.mx";
	  String newAge = "18"; 
	  
	  editButton.click();
	  driver.findElement(By.name("name")).click();
	  driver.findElement(By.name("name")).clear();
	  driver.findElement(By.name("name")).sendKeys(newName);
	  driver.findElement(By.name("email")).click();
	  driver.findElement(By.name("email")).click();
	  driver.findElement(By.name("email")).clear();
	  driver.findElement(By.name("email")).sendKeys(newEmail);
	  driver.findElement(By.name("age")).clear();
	  driver.findElement(By.name("age")).sendKeys(newAge);
	  driver.findElement(By.xpath("//div[3]/div[2]/div")).click();
	  driver.findElement(By.xpath("//div[2]/div[3]")).click();
	  driver.findElement(By.xpath("//form/button")).click();
  }

}
