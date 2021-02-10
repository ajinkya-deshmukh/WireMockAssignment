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
	
	public static void getStub() {
		stubFor(get(urlEqualTo("/v1/IPL/players")).willReturn(aResponse().withStatus(200)
				.withBodyFile("players.json")
				.withHeader("Content-Type", "Application/json")));
	}

}
