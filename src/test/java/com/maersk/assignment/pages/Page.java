package com.maersk.assignment.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Page {
	private WebDriver driver;
	public Page(WebDriver driver) {
		this.setDriver(driver);
		PageFactory.initElements(driver, this);
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectDropdownOption(WebElement element, String option) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(option);
	}
	
	public void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}
}
