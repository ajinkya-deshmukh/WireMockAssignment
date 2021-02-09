package com.wiremock.assignment;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class Runner {
	
	
	
	@Rule
	public WireMockRule wireMockRule = new WireMockRule();

	
	public void getStub() {
		stubFor(get(urlEqualTo("/v1/IPL/players")).willReturn(aResponse().withStatus(200)
				.withBodyFile("players.json")
				.withHeader("Content-Type", "Application/json")));
	}
	
	@RunWith(Suite.class)
	@SuiteClasses({FetchAuctionCost.class})
	public class RunTestSuite{
		
		@BeforeClass
		public void setup() {
			wireMockRule.start();
			getStub();
			System.out.println("Inside The Before Class");
		}

		@AfterClass
		public void tearDown() {
			wireMockRule.stop();
		}   
	}

}
