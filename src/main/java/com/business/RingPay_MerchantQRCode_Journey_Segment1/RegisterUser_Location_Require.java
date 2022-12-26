package com.business.RingPay_MerchantQRCode_Journey_Segment1;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class RegisterUser_Location_Require {

	public ValidatableResponse location_Require() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		ValidatableResponse response = Utilities.LocationRequireAPI();


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"location_Require_Positive,Validating 200 Success Response");

		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","location_Require_Positive,Validating message should be success");
		Validation.assertTrue(response.extract().body().jsonPath().get("data.location_required"), "location_Require_Positive,Validating success Should be true");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//location_require_200_schema.json")), response.extract().body().asString(), "location_Require_Positive,expectedJsonSchema");


		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'location_Require_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("LocationRequireAPI",responseBody, Time);


		return	response;

	}

}
