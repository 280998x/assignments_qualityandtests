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
		  //System.setProperty("webdriver.gecko.driver", "E:\\-Folders\\Universidad\\Calidad y Pruebas de Software\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  baseUrl = "https://www.google.com/";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testCase1() throws Exception {
	    driver.get("https://www.google.com/");
	    driver.findElement(By.name("q")).clear();
	    driver.findElement(By.name("q")).sendKeys("disneyworld.disney.go.com");
	    driver.findElement(By.id("tsf")).submit();
	    driver.findElement(By.xpath("/html/body/div[7]/div[2]/div[10]/div[1]/div[2]/div/div[2]/div[2]/div/div/div[1]/div/div/div/div[1]/a/h3/span")).click();
	    assertEquals("Walt Disney World Resort in Orlando, Florida", driver.getTitle());
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
