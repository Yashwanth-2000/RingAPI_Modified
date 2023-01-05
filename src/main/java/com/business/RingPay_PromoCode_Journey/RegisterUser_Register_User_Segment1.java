package com.business.RingPay_PromoCode_Journey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.excel.ExcelWriteData;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class RegisterUser_Register_User_Segment1 {

	static RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();


	public static ValidatableResponse registerUserAfterLogin_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.RegisterUserAPIData("segment1_registeruser_200");
		ValidatableResponse response = Utilities.PromoCode_RegisterUserAPI(data);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"login_Positive,Validating 200 Success Response");


		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"User Added Successfully","registerUserAfterLogin_Positive,Validating message should be success");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.user_reference_number"),"registerUserAfterLogin_Positive,Validating user_reference_number is not null");
		Validation.assertTrue(response.extract().body().jsonPath().get("success"), "registerUserAfterLogin_Positive,Validating success Should be true");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.global_device_id"),"registerUserAfterLogin_Positive,Validating user_id is not null");
		int responseCode=response.extract().body().jsonPath().get("response_code");
		Validation.assertEqualsInt(responseCode,0,"registerUserAfterLogin_Positive,Validating response_code should be 0");


		//Schema Validation

		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//registeruser_200_schema.json")), response.extract().body().asString(), "registerUserAfterLogin_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'registerUser_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("RegisterUserAPI",responseBody, Time);


		return response;

	}

}
