package com.maersk.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(name="fromPort")
	private WebElement departureCity;
	
	@FindBy(name="toPort")
	private WebElement destinationCity;
	
	@FindBy(css="input[type='submit']")
	private WebElement findFlightsButton;
	
	public void selectDepartureCity(String cityName) {
		selectDropdownOption(departureCity, cityName);
	}
	
	public void selectDestinationCity(String cityName) {
		selectDropdownOption(destinationCity, cityName);
	}
	
	public ReserveFlightPage searchFlights() {
		findFlightsButton.click();
		return new ReserveFlightPage(getDriver());
	}

}
