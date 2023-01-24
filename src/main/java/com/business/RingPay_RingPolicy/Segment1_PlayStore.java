package com.business.RingPay_RingPolicy;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_PlayStore_Segment1;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class Segment1_PlayStore {

	static LoggingUtils logger = new LoggingUtils();

	static RingPay_TestData_DataProvider_PlayStore_Segment1 dataProvider = new RingPay_TestData_DataProvider_PlayStore_Segment1();


	public static ValidatableResponse Segment1() throws Exception {

		
		//	Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.RingPolicyAPIData("segment1");
		ValidatableResponse response = Utilities.RingPolicyAPI_PlayStore_S1(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"Segment1,Validating 200 Success Response");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","Segment1,Validating message should be success");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//ltbc1_200_schema.json")), response.extract().body().asString(), "Segment1,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'Segment1'  : "+(endTime-startTime)+" milliseconds");

		//	Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("Segment1_API",responseBody, Time);

		return	response;

	}


//	public static ValidatableResponse Segment1_DataBase() throws Exception {
//
//		//	Start Time
//		long startTime=System.currentTimeMillis();
//
//		Object[][] data = dataProvider.RingPolicyAPIData("segment1");
//		ValidatableResponse response = Utilities.RingPolicyAPI(data);
//
//
//		ValidatableResponse userReferenceNumberResponse =Utilities.loginAPI();
//
//		// fetch user_reference_number for DataBase
//		String user_reference_number = userReferenceNumberResponse.extract().body().jsonPath().get("data.user_reference_number");
//		logger.info("user_reference_number : " + user_reference_number);
//		ExtentReporter.extentLogger("user_reference_number ",user_reference_number);
//
//
//		Thread.sleep(5000);
//
//		String dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.line_application where user_reference_number='"+ user_reference_number+"';",52);
//		System.out.println("user_reference_number_DataBase :"+ dataBase);
//
//		//		ValidatableResponse dataBase1=dataBase;
//
//		ExtentReporter.extentLogger("user_reference_number_DataBase",dataBase);
//		Validation.assertEqualsDataBase(user_reference_number,dataBase,"user_reference_number_database,RegularOffer_BC1API");
//
//
//
//		//Status Code Validation
//		int responseBody=response.extract().statusCode();
//		Validation.validatingStatusCode(responseBody,200,"Segment1_DataBase,Validating 200 Success Response");
//
//		//Body Validation
//		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","Segment1_DataBase,Validating message should be success");
//
//		//		String to Object
//		JSONObject obj = new JSONObject(dataBase);
//
//		//Database Validation
//		Validation.assertEqualsDataBase(obj.getString("eligible_source"),"cibil_surrogate","Segment1_DataBase,Validating DataBase eligible_source Response");
//		Validation.assertEquals(obj.getString("model_version"),"FRESH-V17","Segment1_DataBase,Validating DataBase model_version Response");
//		Validation.assertEquals(obj.getString("gb_segment"),"L2","Segment1_DataBase,Validating DataBase gb_segment Response");
//
//
//
//		//Schema Validation
//		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//ltbc1_200_schema.json")), response.extract().body().asString(), "Segment1_DataBase,expectedJsonSchema");
//
//		//		End Time
//		long endTime=System.currentTimeMillis();
//		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'Segment1_DataBase'  : "+(endTime-startTime)+" milliseconds");
//
//		//	Dashboard
//		long Time = response.extract().time();
//		String ResponseTime = String.valueOf(Time+" ms");
//		System.out.println("responseTime :"+ResponseTime);
//
//		Influxdb.passbyval("Segment1_DataBase",responseBody, Time);
//
//		return	response;
//
//	}


}
