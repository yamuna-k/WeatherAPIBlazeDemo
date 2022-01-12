package com.maersk.assignment.api;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OpenWeatherAPI {
	
	public static Response buildRequest(String baseURL, String endpoint, Map<String, String> queryParams, String contentType) {
		return RestAssured.given()
				.baseUri(baseURL)
				.queryParams(queryParams)
				.header("Content-Type", contentType)
				.when().get(endpoint).then().extract().response();
	}
	
	public static Map<String, String> getQueryParams(String location, String apiKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("q", location);
		map.put("appid", apiKey);
		return map;
	}
}
