package com.maersk.assignment.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.maersk.assignment.api.OpenWeatherAPI;
import com.maersk.assignment.helper.ReadExcel;
import com.maersk.assignment.model.WeatherData;

import io.restassured.response.Response;

public class OpenWeatherAPITest {
	
	static String baseURL = "http://api.openweathermap.org/data/2.5/";
	static String endpoint = "weather/";
	final static String apiKey = "4eaf40a1a118265877704ecba1569e20";
	static String contentType = "application/json";
	
	@DataProvider
	public Object[][] weatherAPI() throws Exception{
	       Object[][] testObjArray = ReadExcel.getData(System.getProperty("user.dir") + "/src/test/resources/test-data/TestData.xlsx", "WeatherAPI");
	       return (testObjArray);
	}
	
	@Test(dataProvider="weatherAPI")
	public void getWeatherData(String city, String stateCode, String countryCode) {
		String location;
		if(!stateCode.isEmpty() && !countryCode.isEmpty()) {
			System.out.println("if1");
			location = city+","+stateCode+","+countryCode;
		}
		else if(!stateCode.isEmpty() && countryCode.isEmpty()) {
			System.out.println("if2");
			location = city+","+stateCode;
		}
		else {
			System.out.println("else");
			location = city;
		}
		
		Response response = OpenWeatherAPI.buildRequest(baseURL, endpoint, OpenWeatherAPI.getQueryParams(location, apiKey), contentType);
		Assert.assertTrue(response.getStatusCode()==200);
		Gson gson = new Gson();
        WeatherData weatherData
            = gson.fromJson(response.asString(),
            		WeatherData.class);
		assertEquals(weatherData.getName(), city);
	}
	
}
