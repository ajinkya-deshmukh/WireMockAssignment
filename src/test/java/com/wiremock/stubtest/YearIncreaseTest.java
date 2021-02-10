package com.wiremock.stubtest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.models.IPLPlayers;
import com.wiremock.assignment.GetPlayersService;
import com.wiremock.assignment.CalculateIncrease;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import com.services.stub.SetupStub;

public class YearIncreaseTest {

	@Rule
	public WireMockRule wireMockRule = new WireMockRule();
	
	@Before
	public void setup() {
		wireMockRule.start();
		SetupStub.getStub();
		System.out.println("Inside The Before Class");
	}
	
	@Test
	public void getYearOnYearIncrease() {
		
		GetPlayersService gpd = new GetPlayersService();
		Response response = gpd.getPlayers();
		
		int statusCode = response.getStatusCode();
		assertEquals(200, statusCode);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		List<IPLPlayers> playersList = jsonPathEvaluator.getList("ipl", IPLPlayers.class);
		CalculateIncrease.calculateIncrease(playersList);
	}
	
	@After
	public void tearDown() {
		wireMockRule.stop();
	}
}
