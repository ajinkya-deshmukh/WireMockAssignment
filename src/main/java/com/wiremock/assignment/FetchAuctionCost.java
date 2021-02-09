package com.wiremock.assignment;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.services.stub.SetupStub;




public class FetchAuctionCost extends SetupStub{

	
	@Test
	public void readJson() {
		
		RestAssured.baseURI = "http://localhost:8080";
		
		Response response = RestAssured.given()
				.log().all()
				.when()
				.get("/v1/IPL/players");
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		System.out.println("This is inside fetch auction");
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		List<IPLPlayers> playersList = jsonPathEvaluator.getList("ipl", IPLPlayers.class);
		
		int totalCost = 0;
		
		int difference = 0;
		int totalCostTillPreviousYear = 0;
		for(IPLPlayers iter : playersList)
		 {
			System.out.println("Players: " + iter.year);
			int totalCostByYear = 0;
		
			for(Players iter2 : iter.players) {
				System.out.println(iter2.auction_cost);
				totalCostByYear = totalCostByYear + iter2.auction_cost;
				
				
			}
			totalCost = totalCost + totalCostByYear;
			
			
			difference = totalCostByYear - totalCostTillPreviousYear;
			
			totalCostTillPreviousYear = totalCostByYear; 
			System.out.println("This is the total Cost til now " + totalCost);
			System.out.println("This is the total cost by year " + totalCostByYear);
			System.out.println("This is the difference = " + difference);
			
			
		 }
		
	/*	for(String players : playersList)
		 {
			System.out.println("Players: " + players);
		 }
		
		*/

		
	}
	
	

}
