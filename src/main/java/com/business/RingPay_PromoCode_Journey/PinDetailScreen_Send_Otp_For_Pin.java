package com.business.RingPay_PromoCode_Journey;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_PromoCode;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class PinDetailScreen_Send_Otp_For_Pin {

	static RingPay_TestData_DataProvider_PromoCode dataProvider = new RingPay_TestData_DataProvider_PromoCode();

	public static ValidatableResponse sendOtpForPin_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.SendOTPForPinAPIData("sendotpforpin");
		ValidatableResponse response = Utilities.PromoCode_SendOtpForPinAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"SendOtpForPin_Positive,Validating 200 Success Response");

		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"SendOtpForPin_Positive,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"OTP Sent Successfully","SendOtpForPin_Positive,Validating message should be OTP Sent Successfully");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//send_otp_for_pin_200_schema.json")), response.extract().body().asString(), "SendOtpForPin_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'SendOtpForPin_Positive'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("SendOtpForPinAPI",responseBody, Time);

		return	response;

	}

}
