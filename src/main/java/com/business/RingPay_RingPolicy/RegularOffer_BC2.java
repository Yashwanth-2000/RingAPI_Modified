package com.business.RingPay_RingPolicy;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_BC2;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class RegularOffer_BC2 {

	static LoggingUtils logger = new LoggingUtils();

	static RingPay_TestData_DataProvider_BC2 dataProvider = new RingPay_TestData_DataProvider_BC2();


	public static ValidatableResponse RegularOffer_BC2() throws Exception {

		
		//	Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.RingPolicyAPIData("bc2");
		ValidatableResponse response = Utilities.RingPolicyAPI_BC2(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"RegularOffer_BC2,Validating 200 Success Response");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","RegularOffer_BC2,Validating message should be success");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//ltbc1_200_schema.json")), response.extract().body().asString(), "RegularOffer_BC2,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'RegularOffer_BC2'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("RegularOffer_BC2",responseBody, Time);

		return	response;

	}


	public static ValidatableResponse RegularOffer_BC2_DataBase() throws Exception {

		//	Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.RingPolicyAPIData("bc2");
		ValidatableResponse response = Utilities.RingPolicyAPI_BC2(data);


		ValidatableResponse userReferenceNumberResponse =Utilities.Merchant_loginAPI_BC2();

		// fetch user_reference_number for DataBase
		String user_reference_number = userReferenceNumberResponse.extract().body().jsonPath().get("data.user_reference_number");
		logger.info("user_reference_number : " + user_reference_number);
		ExtentReporter.extentLogger("user_reference_number ",user_reference_number);


		Thread.sleep(5000);

		String dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.line_application where user_reference_number='"+ user_reference_number+"';",52);
		System.out.println("user_reference_number_DataBase :"+ dataBase);

		//		ValidatableResponse dataBase1=dataBase;

		ExtentReporter.extentLogger("user_reference_number_DataBase",dataBase);
		Validation.assertEqualsDataBase(user_reference_number,dataBase,"user_reference_number_database,RegularOffer_BC2");



		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"RegularOffer_BC2,Validating 200 Success Response");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","RegularOffer_BC2,Validating message should be success");

		//		String to Object
		JSONObject obj = new JSONObject(dataBase);

		//Database Validation
		Validation.assertEqualsDataBase(obj.getString("eligible_source"),"cibil_surrogate","RegularOffer_BC2,Validating DataBase eligible_source Response");
		Validation.assertEquals(obj.getString("model_version"),"FRESH-V17","RegularOffer_BC2,Validating DataBase model_version Response");
		Validation.assertEquals(obj.getString("gb_segment"),"BC2","RegularOffer_BC2,Validating DataBase gb_segment Response");



		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//ltbc1_200_schema.json")), response.extract().body().asString(), "RegularOffer_BC2,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'RegularOffer_BC2'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("RegularOffer_BC2_DataBase",responseBody, Time);

		return	response;

	}


}
