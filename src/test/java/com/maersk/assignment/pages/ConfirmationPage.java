package com.maersk.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends Page {
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="table[class='table'] tbody tr:first-child td:nth-child(2)")
	private WebElement confirmationId;
	
	public boolean isConfirmationIdDisplayed() {
		return confirmationId.isDisplayed();
	}
	
	public String getConfirmationId() {
		return confirmationId.getText();
	}
}
