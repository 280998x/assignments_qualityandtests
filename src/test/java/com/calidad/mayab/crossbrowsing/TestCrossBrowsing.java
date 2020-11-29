package com.calidad.mayab.crossbrowsing;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestCrossBrowsing {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String URL = "https://aazc28:0ff93f3b-ae76-467c-b3b3-8054bed40b1f@ondemand.us-west-1.saucelabs.com:443/wd/hub";
	  

	  @Before
	  public void setUp() throws Exception {
		  FirefoxOptions caps = new FirefoxOptions();
		  //caps.setCapability("platform", "Windows 10");
		  caps.setCapability("platform", "Linux");
		  //caps.setCapability("platform", "macOS 10.13");
		  caps.setCapability("version", "latest");
		  caps.setCapability("name", "Firefox");
		  caps.setCapability("extendedDebugging", "true");
		  caps.setCapability("buildNumber", "3.0");
		  driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		  driver.get("https://mern-crud.herokuapp.com");
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testAdd_Succesful() throws Exception {
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
	      assertEquals("Successfully added!", new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div[4]/div/p"))).getText());
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
	  public void testModify_Successful() throws Exception {
		  driver.get("https://mern-crud.herokuapp.com/");
		  
		  String expectedName = "Alan Zuniga";
		  String expectedEmail = "00331754@anahuac.mx";
		  String expectedAge = "22";
		  String expectedSex = "m";
		  WebElement editButton = null;
		  
		  WebElement table = driver.findElement(By.xpath("//table/tbody"));
	      List<WebElement> tableRows = table.findElements(By.tagName("tr"));
	      for (int row = 0; row < tableRows.size(); row++) {
	    	  List<WebElement> rowColumns = tableRows.get(row).findElements(By.tagName("td"));
	    	  if(rowColumns.get(0).getText().equals(expectedName) &&
	    		 rowColumns.get(1).getText().equals(expectedEmail) &&
	    		 rowColumns.get(2).getText().equals(expectedAge) &&
	    		 rowColumns.get(3).getText().equals(expectedSex)) {
	    		  List<WebElement> buttons = rowColumns.get(4).findElements(By.tagName("button"));
	    		  editButton = buttons.get(0);
	    		  assertTrue(true);
	    		  break;
	    	  } else if (row == tableRows.size() - 1) {
	    		  assertTrue(false);
	    		  return;
	    	  }
	      }
		  
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
	      table = driver.findElement(By.xpath("//table/tbody"));
	      tableRows = table.findElements(By.tagName("tr"));
	      for (int row = 0; row < tableRows.size(); row++) {
	    	  List<WebElement> rowColumns = tableRows.get(row).findElements(By.tagName("td"));
	    	  if(rowColumns.get(0).getText().equals(newName) &&
	    		 rowColumns.get(1).getText().equals(newEmail) &&
	    		 rowColumns.get(2).getText().equals(newAge) &&
	    		 rowColumns.get(3).getText().equals(newSex)) {
	    		  assertTrue(true);
	    		  break;
	    	  } else if (row == tableRows.size() - 1) {
	    		  assertTrue(false);
	    	  }
	      }
	  }
	  
	  @Test
	  public void testPop_Successful() throws Exception {
		  driver.get("https://mern-crud.herokuapp.com/");
		  
		  String expectedName = "Alan";
		  String expectedEmail = "0034@anahuac.mx";
		  String expectedAge = "21";
		  String expectedSex = "m";
		  
		  WebElement table = driver.findElement(By.xpath("//table/tbody"));
	      List<WebElement> tableRows = table.findElements(By.tagName("tr"));
	      for (int row = 0; row < tableRows.size(); row++) {
	    	  List<WebElement> rowColumns = tableRows.get(row).findElements(By.tagName("td"));
	    	  if(rowColumns.get(0).getText().equals(expectedName) &&
	    		 rowColumns.get(1).getText().equals(expectedEmail) &&
	    		 rowColumns.get(2).getText().equals(expectedAge) &&
	    		 rowColumns.get(3).getText().equals(expectedSex)) {
	    		  List<WebElement> buttons = rowColumns.get(4).findElements(By.tagName("button"));
	    		  buttons.get(1).click();
	    		  driver.findElement(By.xpath("//div[3]/button")).click();
	    		  break;
	    	  } else if (row == tableRows.size() - 1) {
	    		  assertTrue(false);
	    		  return;
	    	  }
	      }
	      
	      table = driver.findElement(By.xpath("//table/tbody"));
	      tableRows = table.findElements(By.tagName("tr"));
	      
	      for (int row = 0; row < tableRows.size(); row++) {
	    	  List<WebElement> rowColumns = tableRows.get(row).findElements(By.tagName("td"));
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
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
}
