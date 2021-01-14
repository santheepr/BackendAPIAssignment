package com.promotionsapi.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.promotionsapi.pojo.erroresponse.InvalidResponse;
import com.promotionsapi.utils.Config;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PromotionsInvalidAPITest extends TestBase {
	private Response response;
	private RequestSpecification requestspec;
	private InvalidResponse invalidresponse;

	public static Logger log = Logger.getLogger(PromotionsInvalidAPITest.class.getName());

	@BeforeTest
	public void initializeRequest() {		
		// Extract the Base URI from the request URL in Config file
		RestAssured.baseURI = Config.getBaseURI();
		requestspec = RestAssured.given();
		requestspec.queryParam("apikey", Config.getInvalidAPIKey());
		response = requestspec.get();
		invalidresponse = response.getBody().as(InvalidResponse.class);
	}

	@Test(priority = 1, description = "Verify that request body is not null")
	public void validateResponseBody() {
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody != null);
		log.info("Response is not null");
		log.info("Response Body : "+responsebody);
	}

	@Test(priority = 2, description = "Verify that status response code 403 is received")
	public void validateStatusCode() {
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 403);
		log.info("Status code is : " + statuscode);
	}

	@Test(priority = 3, description = "Verify the properties exist in the response and the properties are not NULL for each object")
	public void validateAllPropertiesExists() {
		Assert.assertNotNull(invalidresponse.getError().getCode());
		Assert.assertNotNull(invalidresponse.getError().getMessage());
		Assert.assertNotNull(invalidresponse.getError().getRequestId());
	}

	@Test(priority = 4, description = "Verify that error code 8001 is received")
	public void validateErrorCode() {
		String errorCode = invalidresponse.getError().getCode();
		Assert.assertEquals(errorCode, "8001");
		log.info("Error Code is : " + errorCode);
	}

	@Test(priority = 5, description = "Verify the error message value is invalid api key")
	public void validateErrorMessage() {
		String errorMessage = invalidresponse.getError().getMessage();
		Assert.assertEquals(errorMessage, "invalid api key");
		log.info("Error Message is : " + errorMessage);
	}

	@Test(priority = 6, description = "Verify the error request id is not null")
	public void validateRequestId() {
		String errorRequestId = invalidresponse.getError().getRequestId();
		Assert.assertNotNull(errorRequestId);
		log.info("Error RequestId is NOT NULL");
		log.info("*****************End Test******************");
	}
}