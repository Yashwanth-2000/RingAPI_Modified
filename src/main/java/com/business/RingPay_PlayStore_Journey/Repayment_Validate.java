package com.business.RingPay_PlayStore_Journey;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_PlayStore;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class Repayment_Validate {

	RingPay_TestData_DataProvider_PlayStore dataProvider = new RingPay_TestData_DataProvider_PlayStore();


	public void validate() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.ValidateAPIData("validate");
		ValidatableResponse response = Utilities.PlayStore_ValidateAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"PaymentValidate,Validating 200 Success Response");

		//		Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("validateResponse.decision"),"pass","PaymentValidate,PaymentValidate decision should be pass");

		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//validate_200_schema.json")), response.extract().body().asString(), "PaymentValidate,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'validateAPI'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("ValidateAPI",responseBody, Time);


	}


}
