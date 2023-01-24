package com.business.RingPay_RingPolicy_BC1;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_BC1;
import com.Datasheet.RingPay_TestData_DataProvider_LTBC1;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class OfferDetailsScreen_User_Concent {

	RingPay_TestData_DataProvider_BC1 dataProvider = new RingPay_TestData_DataProvider_BC1();


	public void acceptOffer() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();


		//		Object[][] data = dataProvider.Mock_UserAPIData("user_200");
		ValidatableResponse response = Utilities.Merchant_User_ConcentAPI_BC1();


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"acceptOffer,Validating 200 Success Response");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","acceptOffer,Validating message should be success");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//user_concent_200_schema.json")), response.extract().body().asString(), "acceptOffer,expectedJsonSchema");


		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'acceptOfferAPI'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("AcceptOfferAPI",responseBody, Time);


	}


}
