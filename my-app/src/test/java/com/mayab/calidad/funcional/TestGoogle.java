package com.mayab.calidad.funcional;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestGoogle {
	
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
		  System.setProperty("webdriver.gecko.driver", "E:\\- Folders\\Universidad\\Calidad y Pruebas de Software\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  baseUrl = "https://www.google.com/";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testCase1() throws Exception {
	    driver.get("https://www.google.com/");
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("covid");
	    driver.findElement(By.id("tsf")).submit();
	    driver.findElement(By.xpath("//div[@id='kp-wp-tab-overview']/div[3]/div[2]/div/div/div/div/div/div/div/a/h3/span")).click();
	    assertEquals("COVID-19 Information for U.S. Citizens in Mexico | U.S. Embassy & Consulates in Mexico", driver.getTitle());
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
