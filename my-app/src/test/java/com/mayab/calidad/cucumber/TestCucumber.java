package com.mayab.calidad.cucumber;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.*;

public class TestCucumber {
	private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();
	  private String URL = "";
	  
	@Given("browser is open")
	  public void setup() throws Exception {
		  System.setProperty("webdriver.gecko.driver", "E:\\-Folders\\Universidad\\Calidad y Pruebas de Software\\geckodriver.exe");
		  driver = new FirefoxDriver();
		  baseUrl = "https://www.google.com/";
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
	@And("user is in login page")
	  public void user_is_in_login_page() throws Exception {		  
		  driver.get("https://example.testproject.io/web/");
	}
	
	@When("user enters correct username and password")
	public void enter_username_and_password() {
		driver.findElement(By.id("name")).sendKeys("username");
		driver.findElement(By.id("password")).sendKeys("12345");
	}
	
	@When("user clicks login button")
	public void click_login() {
		driver.findElement(By.id("login")).click();;
	}
	
	@Then("website shows main page")
	public void shows_main_page() {
		assertEquals("Logout",driver.findElement(By.id("logout")).getText());
	}
	
}
