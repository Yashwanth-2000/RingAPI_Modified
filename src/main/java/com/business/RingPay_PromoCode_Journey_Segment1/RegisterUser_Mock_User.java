package com.business.RingPay_PromoCode_Journey_Segment1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_PromoCode;
import com.excel.ExcelFunctions;
import com.excel.ExcelWriteData;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class RegisterUser_Mock_User {


	static RingPay_TestData_DataProvider_PromoCode dataProvider = new RingPay_TestData_DataProvider_PromoCode();


	public static ValidatableResponse mock_User_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.Mock_UserAPIData("user_200");
		ValidatableResponse response = Utilities.MockuserAPI_PromoCode(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"mock_User_Positive,Validating 200 Success Response");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//mock_user_200_schema.json")), response.extract().body().asString(), "mock_User_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'mock_User_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("MockUserAPI",responseBody, Time);

		return	response;

	}

}


