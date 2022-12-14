package com.business.RingPay_PlayStore_Journey_Segment1;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_PlayStore_Segment1;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
public class OfferDetailsScreen_Get_Offer {

	RingPay_TestData_DataProvider_PlayStore_Segment1 dataProvider = new RingPay_TestData_DataProvider_PlayStore_Segment1();


	public void get_Offer() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();


		//		Object[][] data = dataProvider.Mock_UserAPIData("user_200");
		ValidatableResponse response = Utilities.PlayStore_GetOfferAPI_Segment1();


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"get_Offer,Validating 200 Success Response");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//getoffers_200_schema.json")), response.extract().body().asString(), "get_Offer,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'get_OfferAPI'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("GetOfferAPI",responseBody, Time);


	}

}
