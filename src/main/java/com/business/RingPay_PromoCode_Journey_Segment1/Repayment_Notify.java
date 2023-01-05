package com.business.RingPay_PromoCode_Journey_Segment1;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class Repayment_Notify {

	RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();

	public void notifyPaymentDone() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();


		Object[][] data = dataProvider.NotifyAPIData("notify");
		ValidatableResponse response = Utilities.PromoCode_NotifyAPI_Segment1(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"notifyPaymentDone,Validating 200 Success Response");

		//		Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("notifyResult.result"),"ok","NotifyPaymentDone,NotifyPaymentDone result should be ok");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//notify_200_schema.json")), response.extract().body().asString(), "NotifyPaymentDone,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'notifyPaymentDone'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("NotifyPaymentAPI",responseBody, Time);


	}

}
