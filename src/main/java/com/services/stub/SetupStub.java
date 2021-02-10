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
	
	@After
	public void tearDown() {
		wireMockRule.stop();
	}

}
