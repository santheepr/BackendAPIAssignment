package com.promotionsapi.tests;

import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.promotionsapi.pojo.response.PromotionsList;
import com.promotionsapi.utils.Config;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PromotionsValidAPITest extends TestBase {
	private Response response;
	private RequestSpecification requestspec;
	private PromotionsList promotionslist;

	public static Logger log = Logger.getLogger(PromotionsValidAPITest.class.getName());

	@BeforeTest
	public void initializeRequest() {
		log.info("*****************Start Test******************");
		// Extract the Base URI from the request URL in Config file
		RestAssured.baseURI = Config.getBaseURI();
		requestspec = RestAssured.given();
		requestspec.queryParam("apikey", Config.getAPIKey());
		response = requestspec.get();
		promotionslist = response.getBody().as(PromotionsList.class);
	}

	@Test(priority = 1, description = "Verify that request body is not null")
	public void validateResponseBody() {
		String responsebody = response.getBody().asString();
		Assert.assertTrue(responsebody != null);
		log.info("Response is not null");
		log.info("Response Body : "+responsebody);
	}

	@Test(priority = 2, description = "Verify that status response code 200 is received")
	public void validateStatusCode() {
		int statuscode = response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
		log.info("Status code is : " + statuscode);
	}

	@Test(priority = 3, description = "Verify the properties exist in the response and the properties are not NULL for each object")
	public void validateAllPropertiesExists() {
		Assert.assertNotNull(promotionslist.getPromotions());
		for (int i = 0; i < promotionslist.getPromotions().size(); i++) {
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getPromotionId());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getOrderId());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getPromoArea());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getPromoType());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getShowPrice());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getShowText());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getLocalizedTexts().getAr());
			Assert.assertNotNull(promotionslist.getPromotions().get(i).getLocalizedTexts().getEn());
		}
	}

	@Test(priority = 4, description = "Verify the Show Price is of type boolean")
	public void validateShowPriceIsBoolean() {
		for (int i = 0; i < promotionslist.getPromotions().size(); i++) {
			String str = promotionslist.getPromotions().get(i).getShowPrice();
			if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@Test(priority = 5, description = "Verify the Show Text is of type boolean")
	public void validateShowTextIsBoolean() {
		for (int i = 0; i < promotionslist.getPromotions().size(); i++) {
			String str = promotionslist.getPromotions().get(i).getShowText();
			if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}
	}

	@Test(priority = 6, description = "Verify the PromotionId is a String in each object")
	public void validatePromotionIdIsString() {
		for (int i = 0; i < promotionslist.getPromotions().size(); i++) {
			boolean isString;
			if (promotionslist.getPromotions().get(i).getPromotionId() instanceof String) {
				isString = true;
			} else {
				isString = false;
			}
			assertTrue(isString);
		}
	}

	@Test(priority = 8, description = "Verify the Promo Type is either EPISODE or VOD or MOVIE or SERIES or SEASON for each object")
	public void validatePromoTypeValue() {
		for (int i = 0; i < promotionslist.getPromotions().size(); i++) {
			String promoType = "";
			if (promotionslist.getPromotions().get(i).getPromoType().equals("EPISODE")
					|| promotionslist.getPromotions().get(i).getPromoType().equals("VOD")
					|| promotionslist.getPromotions().get(i).getPromoType().equals("MOVIE")
					|| promotionslist.getPromotions().get(i).getPromoType().equals("SERIES")
					|| promotionslist.getPromotions().get(i).getPromoType().equals("SEASON")) {
				promoType = "valid";
			} else {
				promoType = "invalid";
			}
			Assert.assertEquals(promoType, "valid");
		}
	}
}