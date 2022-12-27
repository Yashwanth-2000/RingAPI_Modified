package com.business.RingPay_PlayStore_Journey;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class PinDetailScreen_Get_Pin_Details {

//	static RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();


	public static ValidatableResponse getPinDetails_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		//		Object[][] data = dataProvider.Mock_UserAPIData("user_200");
		ValidatableResponse response = Utilities.PlayStore_GetPinDetailsAPI();


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"getPinDetails_Positive,Validating 200 Success Response");

		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"getPinDetails_Positive,Validating request_id is not null");
		Validation.assertFalse(response.extract().body().jsonPath().get("data.is_pin_present"), "getPinDetails_Positive,Validating is_pin_present Should be false");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","getPinDetails_Positive,Validating message should be success");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.salt"),"","getPinDetails_Positive,Validating message should be success");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.action"),"set_pin","getPinDetails_Positive,Validating action should be set_pin");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.pin_set_method"),"otp","getPinDetails_Positive,Validating pin_set_method should be pin_set_method");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//get_pin_details_200_schema.json")), response.extract().body().asString(), "getPinDetails_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'getPinDetails_Positive'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("GetPinDetailsAPI",responseBody, Time);

		return	response;

	}

	
	public static ValidatableResponse afterResetPin_getPinDetails_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		//		Object[][] data = dataProvider.Mock_UserAPIData("user_200");
		ValidatableResponse response = Utilities.PlayStore_GetPinDetailsAPI();


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"afterResetPin_getPinDetails_Positive,Validating 200 Success Response");

		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"afterResetPin_getPinDetails_Positive,Validating request_id is not null");
		Validation.assertTrue(response.extract().body().jsonPath().get("data.is_pin_present"), "afterResetPin_getPinDetails_Positive,Validating is_pin_present Should be false");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","afterResetPin_getPinDetails_Positive,Validating message should be success");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.salt"),"afterResetPin_getPinDetails_Positive,Validating message should be success");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.pin_set_method"),"otp","afterResetPin_getPinDetails_Positive,Validating pin_set_method should be pin_set_method");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//after_reset_pin_get_pin_details_200_schema.json")), response.extract().body().asString(), "afterResetPin_getPinDetails_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'afterResetPin_getPinDetails_Positive'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("AfterResetPin_GetPinDetailsAPI",responseBody, Time);

		return	response;

	}

	
	
}
