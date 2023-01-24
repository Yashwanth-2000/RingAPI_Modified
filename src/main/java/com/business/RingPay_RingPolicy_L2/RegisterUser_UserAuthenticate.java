package com.business.RingPay_RingPolicy_L2;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_BC1;
import com.Datasheet.RingPay_TestData_DataProvider_L1;
import com.Datasheet.RingPay_TestData_DataProvider_L2;
import com.Datasheet.RingPay_TestData_DataProvider_LTBC1;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class RegisterUser_UserAuthenticate {


	static	RingPay_TestData_DataProvider_L2 dataProvider = new RingPay_TestData_DataProvider_L2();


	public static ValidatableResponse userToken_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		RegisterUser_OTPSend.valid_MobileNo_UserExist_True_Positive();

		Object[][] data = dataProvider.UserAuthenticateAPIData("auth_200");
		ValidatableResponse response = Utilities.userTokenAPI_Positive_L2(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"userAuthenticate,Validating 200 Success Response");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"userAuthenticate,Validating request_id is not null");
		Validation.assertTrue(response.extract().body().jsonPath().get("success"), "userAuthenticate,Validating success is true");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","userAuthenticate,Validating message should be success");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.user_token"),"userAuthenticate,Validating user_token is not null");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.encrypted_user_reference_number"),"userAuthenticate,Validating encrypted_user_reference_number is not null");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//auth_200_schema.json")), response.extract().body().asString(), "userAuthenticate,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'userToken_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("UserAuthenticateAPI",responseBody, Time);

		return response;

	}

	
	
//	L2
	public static ValidatableResponse userToken_Repeat_L2() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		RegisterUser_OTPSend.valid_MobileNo_UserExist_True_Positive();

		Object[][] data = dataProvider.UserAuthenticateAPIData("auth_200");
		ValidatableResponse response = Utilities.userTokenAPI_Repeat_L2(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"userAuthenticate,Validating 200 Success Response");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"userAuthenticate,Validating request_id is not null");
		Validation.assertTrue(response.extract().body().jsonPath().get("success"), "userAuthenticate,Validating success is true");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","userAuthenticate,Validating message should be success");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.user_token"),"userAuthenticate,Validating user_token is not null");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.encrypted_user_reference_number"),"userAuthenticate,Validating encrypted_user_reference_number is not null");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//auth_200_schema.json")), response.extract().body().asString(), "userAuthenticate,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'userToken_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("UserAuthenticateAPI",responseBody, Time);


		return response;

	}
	


	public void invalidOtp_Negative() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.UserAuthenticateAPIData("invalidotp_400");
		ValidatableResponse response = Utilities.userTokenAPI_Positive_L2(data);

		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"invalidOtp_Negative,Validating 400 Bad Request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"invalidOtp_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Otp does not match or have expired","invalidOtp_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//auth_400_schema.json")), response.extract().body().asString(), "invalidOtp_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'invalidOtp_Negative'  : "+(endTime-startTime)+" milliseconds");


	}


	public void expiredOtp_Negative() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.UserAuthenticateAPIData("expiredotp_400");
		ValidatableResponse response = Utilities.userTokenAPI_Positive_L2(data);

		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"expiredOtp_Negative,Validating 400 Bad Request");



		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"expiredOtp_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Otp does not match or have expired","expiredOtp_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//auth_400_schema.json")), response.extract().body().asString(), "expiredOtp_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'expiredOtp_Negative'  : "+(endTime-startTime)+" milliseconds");


	}


	public void alphabetInOtpField_Negative() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.UserAuthenticateAPIData("alphabetinfield_400");
		ValidatableResponse response = Utilities.userTokenAPI_Positive_L2(data);

		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"alphabetInOtpField_Negative,Validating 400 Bad request");

		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"alphabetInOtpField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Otp does not match or have expired","alphabetInOtpField_Negative,Validating message should be success");

		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//auth_400_schema.json")), response.extract().body().asString(), "alphabetInOtpField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'alphabetInOtpField_Negative'  : "+(endTime-startTime)+" milliseconds");


	}

	
	public void lessThan6DigitsNoInOtpField_Negative() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.UserAuthenticateAPIData("lessthan6digit0tp_400");
		ValidatableResponse response = Utilities.userTokenAPI_Positive_L2(data);

		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"lessThan6DigitsNoInOtpField_Negative,Validating 400 Bad request");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"lessThan6DigitsNoInOtpField_Negative,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Otp does not match or have expired","lessThan6DigitsNoInOtpField_Negative,Validating message should be success");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//auth_400_schema.json")), response.extract().body().asString(), "lessThan6DigitsNoInOtpField_Negative,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'lessThan6DigitsNoInOtpField_Negative'  : "+(endTime-startTime)+" milliseconds");


	}


}
