package com.wiremock.assignment;
import java.text.DecimalFormat;
import java.util.List;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.models.IPLPlayers;
import com.models.Players;
import com.services.stub.SetupStub;


public class CalculateIncrease extends SetupStub{
	
	@Test
	public void getYearOnYearIncrease() {
		
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		GetPlayersService gpd = new GetPlayersService();
		
		Response response = gpd.getPlayers();
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		List<IPLPlayers> playersList = jsonPathEvaluator.getList("ipl", IPLPlayers.class);
		
		int totalCost = 0;
		
		int difference = 0;
		int totalCostTillPreviousYear = 0;
		double yoy = 0.0; 
		for(IPLPlayers iter : playersList)
		 {
			//System.out.println("Players: " + iter.year);
			int totalCostByYear = 0;
		
			for(Players iter2 : iter.players) {
			//	System.out.println(iter2.auction_cost);
				totalCostByYear = totalCostByYear + iter2.auction_cost;
				
				
			}
			totalCost = totalCost + totalCostByYear;
			
			difference = totalCostByYear - totalCostTillPreviousYear;
			
			totalCostTillPreviousYear = totalCostByYear; 
			
			yoy = (double)difference / totalCostTillPreviousYear * 100;
			

		 }
		System.out.println("This is the total Cost till now = " + totalCost);
		//System.out.println("This is the total cost by year = " + totalCostByYear);
		System.out.println("The year on year increase in the player price is " + difference);
		System.out.println("The year on year increase in the player price is " + df.format(yoy) + "%");
	}
}
