package com.business.RingPay_PromoCode_Journey_Segment1;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class PinDetailScreen_Set_Reset_Pin {
	
	static RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();

	public static ValidatableResponse setResetPin_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.SetResetPinAPIData("resetpin");
		ValidatableResponse response = Utilities.PromoCode_SetResetPinAPI_Segment1(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"setResetPin_Positive,Validating 200 Success Response");

		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"sendResetPin_Positive,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Pin set successfully","sendResetPin_Positive,Validating message should be OTP Sent Successfully");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//set_reset_pin_200_schema.json")), response.extract().body().asString(), "sendResetPin_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'sendResetPin_Positive'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("SendResetPinAPI",responseBody, Time);

		return	response;

	}


}
