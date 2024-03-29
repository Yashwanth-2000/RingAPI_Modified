package com.business.RingPay_MerchantQRCode_Journey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class RegisterUser_RingPolicy_BasicDetails {

	static RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();

	public static ValidatableResponse basicDetails_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("update_user_200");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);

		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"basicDetails_Positive,Validating 200 Success Response");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"basicDetails_Positive,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","basicDetails_Positive,Validating message should be success");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'basicDetails_Positive'  : "+(endTime-startTime)+" milliseconds");


		return response;

	}


	// ========================     Schema valiadtion  ===================================================

	public static ValidatableResponse basicDetails_Positive_SchemaValiadtion() throws Exception {


		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("update_user_200");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"basicDetails_Positive_SchemaValiadtion,Validating 200 Success Response");


		//Body Validation
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"basicDetails_Positive_SchemaValiadtion,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","basicDetails_Positive_SchemaValiadtion,Validating message should be success");


		//Schema Validation

		Thread.sleep(2000);

		//		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_200_schema.json")), response.extract().body().asString(), "updateUser_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'basicDetails_Positive_SchemaValiadtion'  : "+(endTime-startTime)+" milliseconds");

		//		DashBoard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("BasicDetailsAPI",responseBody, Time);


		return response;

	}




	//	======================================== First Name ====================================================

	public void alphaNumericInFirstNameField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_AlphaNumericInFirstNameField_Negative");

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("alphanumeric_in_firstname_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"alphaNumericInFirstNameField_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"alphaNumericInFirstNameField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The first name format is invalid.","alphaNumericInFirstNameField_Negative,Validating message should be success");



		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "alphaNumericInFirstNameField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'alphaNumericInFirstNameField_Negative'  : "+(endTime-startTime)+" milliseconds");



	}

	public void specialCharacterInFirstNameField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_SpecialCharacterInFirstNameField_Negative");

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("specialCharacter_in_firstname_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);

		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"specialCharacterInFirstNameField_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"specialCharacterInFirstNameField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The first name format is invalid.","specialCharacterInFirstNameField_Negative,Validating message should be success");



		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "specialCharacterInFirstNameField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'specialCharacterInFirstNameField_Negative'  : "+(endTime-startTime)+" milliseconds");



	}


	public void spaceInFirstNameField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_SpaceInFirstNameField_Negative");

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("space_in_firstname_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"spaceInFirstNameField_Negative,Validating 400 Bad Request");



		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"spaceInFirstNameField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The first name field is required.","spaceInFirstNameField_Negative,Validating message should be success");



		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "spaceInFirstNameField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'spaceInFirstNameField_Negative'  : "+(endTime-startTime)+" milliseconds");



	}



	//	==================================== Last Name ==================================================

	public void alphaNumericInLastNameField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_AlphaNumericInLastNameField_Negative");
	
		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("alphanumeric_in_lastname_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"alphaNumericInLastNameField_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"alphaNumericInLastNameField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The last name format is invalid.","alphaNumericInLastNameField_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "alphaNumericInLastNameField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'alphaNumericInLastNameField_Negative'  : "+(endTime-startTime)+" milliseconds");



	}


	public void specialCharacterInLastNameField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_SpecialCharacterInLastNameField_Negative");

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("specialCharacter_in_lastname_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"specialCharacterInLastNameField_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"specialCharacterInLastNameField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The last name format is invalid.","specialCharacterInLastNameField_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "specialCharacterInLastNameField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'specialCharacterInLastNameField_Negative'  : "+(endTime-startTime)+" milliseconds");



	}


	public void spaceInLastNameField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_SpaceInLastNameField_Negative");

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("space_in_lastname_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"spaceInLastNameField_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"spaceInLastNameField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The last name field is required.","spaceInLastNameField_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "spaceInLastNameField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'spaceInLastNameField_Negative'  : "+(endTime-startTime)+" milliseconds");

	}

	//	==================================== Email Field ====================================

	public void invalidEmailId_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_InvalidEmailId_Negative");
		
		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("invalidformat_in_email_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"invalidEmailId_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"invalidEmailId_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The email must be a valid email address.","invalidEmailId_Negative,Validating message should be success");



		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "invalidEmailId_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'invalidEmailId_Negative'  : "+(endTime-startTime)+" milliseconds");


	}


	public void spaceInEmailIdField_Negative() throws Exception {

//		ExtentReporter.HeaderChildNode("basicDetails_SpaceInEmailIdField_Negative");

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.BasicDetailsAPIData("space_in_email_field_400");
		ValidatableResponse response = Utilities.Merchant_BasicDetailsAPI(data);


		String Resp=response.extract().body().asString();
		System.out.println("Response Body= "+Resp);
		ExtentReporter.extentLogger("", "Response Body= "+Resp);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"spaceInEmailIdField_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"spaceInEmailIdField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The email must be a valid email address.","spaceInEmailIdField_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//updateuser_400_schema.json")), response.extract().body().asString(), "spaceInEmailIdField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'spaceInEmailIdField_Negative'  : "+(endTime-startTime)+" milliseconds");

	}


}


