package com.wiremock.assignment;
import com.util.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetPlayersService {
	
	public  Response getPlayers() {
		Response response = RestAssured.given()
				.log().all()
				.when()
				.get(Constants.playersEndpoint);
		return response;
	}
}
