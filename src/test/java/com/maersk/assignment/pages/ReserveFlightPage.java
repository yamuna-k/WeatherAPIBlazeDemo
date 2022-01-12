package com.maersk.assignment.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReserveFlightPage extends Page {
	
	public ReserveFlightPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="table[class='table'] thead th")
	private List<WebElement> tableHeadings;
	
	@FindBy(css="table[class='table'] tbody tr")
	private List<WebElement> tableRows;
	
	public PurchasePage chooseFlightByNumber(String flightNumber) {
		int index = 0;
		for(WebElement ele: tableHeadings) {
			if(ele.getText().contains("Flight")) {
				index = tableHeadings.indexOf(ele);
				break;
			}
		}
		for(WebElement ele: tableRows) {
			String data = ele.findElements(By.tagName("td")).get(index).getText();
			if(data.equals(flightNumber)) {
				ele.findElements(By.tagName("td")).get(0).click();
				break;
			}
		}
		return new PurchasePage(getDriver());
	}
	
	public PurchasePage chooseFlightByAirline(String airline) {
		int index = 0;
		for(WebElement ele: tableHeadings) {
			if(ele.getText().contains("Airline")) {
				index = tableHeadings.indexOf(ele);
				break;
			}
		}
		for(WebElement ele: tableRows) {
			String data = ele.findElements(By.tagName("td")).get(index).getText();
			if(data.equals(airline)) {
				ele.findElements(By.tagName("td")).get(0).click();
				break;
			}
		}
		return new PurchasePage(getDriver());
	}
	
	public PurchasePage chooseFlightByLowestPrice() {
		int index = 0;
		for(WebElement ele: tableHeadings) {
			if(ele.getText().contains("Price")) {
				index = tableHeadings.indexOf(ele);
				break;
			}
		}
		List<String> priceList = new ArrayList<String>();
		for(WebElement ele: tableRows) {
			priceList.add(ele.findElements(By.tagName("td")).get(index).getText());
		}
		Collections.sort(priceList);
		System.out.println(priceList);
		for(WebElement ele: tableRows) {
			String data = ele.findElements(By.tagName("td")).get(index).getText();
			if(data.equals(priceList.get(0))) {
				ele.findElements(By.tagName("td")).get(0).click();
				break;
			}
		}
		return new PurchasePage(getDriver());
	}
}
