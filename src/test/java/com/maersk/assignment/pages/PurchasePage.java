package com.maersk.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PurchasePage extends Page {

	public PurchasePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="#inputName")
	private WebElement nameField;
	
	@FindBy(css="#address")
	private WebElement addressField;
	
	@FindBy(css="#city")
	private WebElement cityField;
	
	@FindBy(css="#state")
	private WebElement stateField;
	
	@FindBy(css="#zipCode")
	private WebElement zipCodeField;
	
	@FindBy(css="#cardType")
	private WebElement cardTypeField;
	
	@FindBy(css="#creditCardNumber")
	private WebElement creditCardNumberField;
	
	@FindBy(css="#creditCardMonth")
	private WebElement creditCardMonthField;
	
	@FindBy(css="#creditCardYear")
	private WebElement creditCardYearField;
	
	@FindBy(css="#nameOnCard")
	private WebElement nameOnCardField;
	
	@FindBy(css="input[type='submit']")
	private WebElement purchaseFlightButton;
	
	public void enterName(String name) {
		sendText(nameField, name);
	}
	
	public void enterAddress(String address) {
		sendText(addressField, address);
	}
	
	public void enterCity(String city) {
		sendText(cityField, city);
	}
	
	public void enterState(String state) {
		sendText(stateField, state);
	}
	
	public void enterZipCode(String zipCode) {
		sendText(zipCodeField, zipCode);
	}
	
	public void chooseCardType(String cardType) {
		selectDropdownOption(cardTypeField, cardType);
	}
	
	public void enterCreditCardNumber(String creditCardNumber) {
		sendText(creditCardNumberField, creditCardNumber);
	}
	
	public void enterCreditCardMonth(String creditCardMonth) {
		sendText(creditCardMonthField, creditCardMonth);
	}
	
	public void enterCreditCardYear(String creditCardYear) {
		sendText(creditCardYearField, creditCardYear);
	}
	
	public void enterNameOnCard(String nameOnCard) {
		sendText(nameOnCardField, nameOnCard);
	}
	
	public ConfirmationPage purchaseFlight() {
		purchaseFlightButton.click();
		return new ConfirmationPage(getDriver());
	}
	
	public ConfirmationPage purchaseFlight(String name, String address, String city, String state, String zipCode, String cardType, String creditCardNumber, String creditCardMonth, String creditCardYear, String nameOnCard) throws InterruptedException {
		enterName(name);
		enterAddress(address);
		enterCity(city);
		enterState(state);
		enterZipCode(zipCode);
		chooseCardType(cardType);
		enterCreditCardNumber(creditCardNumber);
		enterCreditCardMonth(creditCardMonth);
		enterCreditCardYear(creditCardYear);
		enterNameOnCard(nameOnCard);
		Thread.sleep(5000);
		return purchaseFlight();
	}
}
