package com.wiremock.stubtesting;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import org.junit.Test;

import com.github.tomakehurst.wiremock.http.Response;

import io.restassured.RestAssured;

public class CreateMapping {
	
/*	public void createStub() {
		stubFor(get(urlEqualTo("/api/services"))
				.willReturn(aResponse().withStatus(200)
						.withBody("This is the stub created")
						.withHeader("Content-Type", "application/json")
						));
	}
	
	@Test
	public void verifyStub() {
		createStub();
		
		RestAssured.baseURI = "http://localhost:8080";
		
		io.restassured.response.Response response = RestAssured.given()
				.log().all()
				.when()
				.get("/api/services");
		
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		response.prettyPrint();		
		
		
		
	}
				*/

}
