package com.wiremock.assignment;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.logging.log4j.core.Logger;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import com.models.IPLPlayers;
import com.models.Players;
import com.services.stub.SetupStub;


public class CalculateIncrease extends SetupStub{
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CalculateIncrease.class);
	
	public static void calculateIncrease(List<IPLPlayers> playersList) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		int totalCost = 0;
		
		int difference = 0;
		int totalCostTillPreviousYear = 0;
		double yoy = 0.0; 
		for(IPLPlayers iter : playersList)
		 {
			int totalCostByYear = 0;
		
			for(Players iter2 : iter.getPlayers()) {
				totalCostByYear = totalCostByYear + iter2.getAuction_cost();
			}
			totalCost = totalCost + totalCostByYear;
			
			difference = totalCostByYear - totalCostTillPreviousYear;
			
			totalCostTillPreviousYear = totalCostByYear; 
			
			yoy = (double)difference / totalCostTillPreviousYear * 100;
		 }
		
		logger.info("This is the total Cost till now = " + totalCost);
		logger.info("The year on year increase in the player price is " + difference);
		logger.info("The year on year increase in the player price is " + df.format(yoy) + "%");
		
	}
}
