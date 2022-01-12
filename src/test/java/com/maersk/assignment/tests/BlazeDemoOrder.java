package com.maersk.assignment.tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.maersk.assignment.helper.CaptureScreenshot;
import com.maersk.assignment.helper.ReadExcel;
import com.maersk.assignment.pages.ConfirmationPage;
import com.maersk.assignment.pages.HomePage;
import com.maersk.assignment.pages.PurchasePage;
import com.maersk.assignment.pages.ReserveFlightPage;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Assert;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
public class BlazeDemoOrder {
	
  WebDriver driver;
  HomePage homePage;
  ReserveFlightPage reserveFlightPage;
  PurchasePage purchasePage;
  ConfirmationPage confirmationPage;

  @BeforeTest
  public void launch() {
	  WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--headless");
      options.addArguments("--no-sandbox");
      options.addArguments("--disable-dev-shm-usage");
      driver = new ChromeDriver(options);
	  driver.manage().window().maximize();
	  driver.get("https://blazedemo.com/");
	  homePage = new HomePage(driver);
  }
  
  @DataProvider
  public Object[][] flightBooking() throws Exception{
       Object[][] testObjArray = ReadExcel.getData(System.getProperty("user.dir") + "/src/test/resources/test-data/TestData.xlsx", "BookFlight");
       return (testObjArray);
  }
  
  @Test(dataProvider="flightBooking")
  public void bookFlight(String departureCity, String destinationCity, String chooseFlightBy, String chooseFlightByValue, String name, String address, String city, String state, String zipCode, String cardType, String cardNumber, String cardMonth, String cardYear, String nameOnCard) throws InterruptedException, IOException {
	  homePage.selectDepartureCity(departureCity);
	  homePage.selectDestinationCity(destinationCity);
	  reserveFlightPage = homePage.searchFlights();
	  switch(chooseFlightBy) {
	  	case "Airline": purchasePage = reserveFlightPage.chooseFlightByAirline(chooseFlightByValue);
	  					break;
	  	case "Lowest Price": 
	  	default: purchasePage = reserveFlightPage.chooseFlightByLowestPrice();
	  				break;
	  	case "Flight Number": purchasePage = reserveFlightPage.chooseFlightByNumber(chooseFlightByValue);
								break;					
	  }
	  confirmationPage = purchasePage.purchaseFlight(name, address, city, state, zipCode, cardType, cardNumber, cardMonth, cardYear, nameOnCard);
	  Assert.assertTrue(confirmationPage.isConfirmationIdDisplayed());
	  Assert.assertTrue(!(confirmationPage.getConfirmationId().isEmpty()));
	  CaptureScreenshot cap = new CaptureScreenshot(driver);
	  cap.takeScreenShot("BookFlight");
  }
  
  @AfterTest
  public void close() {
	  driver.close();
  }
}
