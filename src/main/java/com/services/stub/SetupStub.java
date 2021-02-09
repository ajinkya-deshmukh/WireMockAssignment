package com.services.stub;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SetupStub {
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	
	public void getStub() {
		stubFor(get(urlEqualTo("/v1/IPL/players")).willReturn(aResponse().withStatus(200)
				.withBodyFile("players.json")
				.withHeader("Content-Type", "Application/json")));
	}
	
	@Before
	public void setup() {
		wireMockRule.start();
		getStub();
		System.out.println("Inside The Before Class");
	}
	
	/* Can be converted into test to check if stub is available
	public void verifyCreatedStub() {
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response response = RestAssured.given()
				.log().all()
				.when()
				.get("/v1/IPL/players");
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		response.prettyPrint();
		
	} */
	
	@After
	public void tearDown() {
		wireMockRule.stop();
	}

}
