package com.utility;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.hamcrest.Matchers;

//import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.restassured.module.jsv.JsonSchemaValidator;
//import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class Validation {

	static SoftAssert soft=new SoftAssert();

	static LoggingUtils logger = new LoggingUtils();

	public static void assertSchemaValidation(String key,String Actual,String message) throws Exception {

		try {


			String expectedJsonSchema = key;
			assertThat(Actual, JsonSchemaValidator.matchesJsonSchema(expectedJsonSchema));
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		} 
		catch (Exception e) {
			Utilities.log.error(message);
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		catch (AssertionError e) {
			Utilities.log.error(message);
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}

	}	


	public static void validatingStatusCode (int actualrespcode, int expectedresponsecode, String message) throws Exception {
		if(actualrespcode==expectedresponsecode) {
			soft.assertEquals(actualrespcode, expectedresponsecode);
			Utilities.log.info(message);
			Thread.sleep(1000);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}		 
		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}


	//	public static void validating400StatusCode (int actualrespcode, int expectedresponsecode, String message) throws Exception {
	//		if(actualrespcode==expectedresponsecode) {
	//			Assert.assertEquals(actualrespcode, expectedresponsecode);
	//			Utilities.log.info(message);
	//			ExtentReporter.extentLoggerPass(message+" - Passed");
	//		}		 
	//		else
	//		{
	//			ExtentReporter.extentLoggerFail(message+" - Failed");
	//		}
	//
	//	}
	//	

	public static void assertEquals(String key,String responseValue,String message) throws Exception {


		if(responseValue.equals(key))
		{

			soft.assertEquals(key,responseValue);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}

		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}


	//	assertEqualsdatabase
	public static void assertEqualsdatabase(String key,String responseValue,String message) throws Exception {
		System.out.println("rrrrrrrrrrrr"+responseValue);
		System.out.println("kkkkkkk"+key);
		try {
			//		if(responseValue==key)
			//		{

			soft.assertEquals(key,responseValue);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
			//		}
		}catch (Exception e) {
			e.printStackTrace();
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}




	//assertEqualsDataBase


	public static void assertEqualsDataBase(String key,String responseValue,String message) throws Exception {

		try {


			soft.assertEquals(key,responseValue);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");

		}
		catch (Exception e) {

			ExtentReporter.extentLoggerFail(message+" - Failed");

		}

	}	


	public static void assertEqualsInt(int key,int responseValue,String message) throws Exception {


		if(key==responseValue)
		{
			soft.assertEquals(key,responseValue);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");

		}

		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}


	public static void assertEqualsStatus(String key,String statusInitiated,String statusCOND_APPROVED,String statusFINAL_APPROVED,String message) throws Exception {

		//	String value=responseValue;

		if(statusInitiated.equals(key))

		{
			soft.assertEquals(key,statusInitiated);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}


		else if(statusCOND_APPROVED.equals(key))

		{
			soft.assertEquals(key,statusCOND_APPROVED);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}

		else if(statusFINAL_APPROVED.equals(key))

		{
			soft.assertEquals(key,statusFINAL_APPROVED);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}

		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}


	}

	public static void assertEqualsStage(String key,String statusAddress_details_pending,String statusAcceptance_pending,String statusComplete,String message) throws Exception {

		//	String value=responseValue;
		try {

			if(statusAddress_details_pending.equals(key))

			{
				soft.assertEquals(key,statusAddress_details_pending);
				Utilities.log.info(message);
				ExtentReporter.extentLoggerPass(message+" - Passed");
			}


			else if(statusAcceptance_pending.equals(key))

			{
				soft.assertEquals(key,statusAcceptance_pending);
				Utilities.log.info(message);
				ExtentReporter.extentLoggerPass(message+" - Passed");
			}

			else if(statusComplete.equals(key))

			{
				soft.assertEquals(key,statusComplete);
				Utilities.log.info(message);
				ExtentReporter.extentLoggerPass(message+" - Passed");
			}
		}

		catch (Exception e) {
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

		//		else
		//		{
		//			ExtentReporter.extentLoggerFail(message+" - Failed");
		//		}


	}


	//	public static void assertEqualsValidatingActiveValue (String key,String responseValue,String message) throws Exception {
	//
	//		//		String value=responseValue;
	//
	//		if(responseValue.equals(key)) {
	//
	//			Assert.assertEquals(key, responseValue);
	//			Utilities.log.info(message);
	//			ExtentReporter.extentLoggerPass(message+" - Passed");
	//		}		 
	//		else
	//		{
	//			ExtentReporter.extentLoggerFail(message+" - Failed");
	//		}
	//
	//
	//	}


	public static void assertRequest_IdNotNullBodyValidation(String key,String message) throws Exception {
		//		ValidatableResponse responseBody = Utilities.OnloadAPI();

		System.out.println(key);
		//		String request_id=responseBody.extract().body().jsonPath().get("request_id");
		if(key!=null) 
			//		try
		{

			soft.assertNotNull(key);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}		 
		else
			//		catch (Exception e)
		{
			//			e.printStackTrace();
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}


	}


	public static void assertNull(String key,String message) throws Exception {
		//		ValidatableResponse responseBody = Utilities.OnloadAPI();

		//		System.out.println("+++++++++ :"+key);
		//		String request_id=responseBody.extract().body().jsonPath().get("request_id");


		//		if(key=="") 
		//		{
		try{
			soft.assertNull(key,"");
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
			//		}		 
			//		else
			//		{
			//			logger.info("assertNull: " + message);
			//			ExtentReporter.extentLoggerFail(message+" - Failed");
			//		}

		}
		catch (Exception e) {
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}


	}


	public static void assertEmpty(String key,String message) throws Exception {
		//		ValidatableResponse responseBody = Utilities.OnloadAPI();


		//		System.out.println("+++++++++ :"+key);
		//		String request_id=responseBody.extract().body().jsonPath().get("request_id");


		//				if(key=="") 
		//				{
		try
		{ 
			//			if(key!="") 
			//			{
			//		
			soft.assertNull(key);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");

			//		}
		}
		catch (Exception e) {
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}


	}



	public static void assertIntegerNotNullBodyValidation(Integer key,String message) throws Exception {

		if(Integer.valueOf(key) != null)
		{
			soft.assertNotNull(key);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}		 
		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}


	}

	public static void assertLongNotNullBodyValidation(Long key,String message) throws Exception {

		if(Long.valueOf(key) != null)
		{
			soft.assertNotNull(key);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}		 
		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}


	}

	public static void assertTrue(Boolean key,String message) throws Exception {
		//		if(key==true) {
		try {
			soft.assertTrue(key);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}
		//		}		 
		//		else
		catch (Exception e) 
		{
			e.printStackTrace();
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}

	public static void assertFalse(Boolean key,String message) throws Exception {
		if(key==false) {

			soft.assertFalse(key);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}		 
		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}


	public static void assertEqualsDataBase(Boolean key,String responseValue,String message) throws Exception {


		if(responseValue.equals(key))
		{

			soft.assertEquals(key,responseValue);
			Utilities.log.info(message);
			ExtentReporter.extentLoggerPass(message+" - Passed");
		}

		else
		{
			ExtentReporter.extentLoggerFail(message+" - Failed");
		}

	}




}
