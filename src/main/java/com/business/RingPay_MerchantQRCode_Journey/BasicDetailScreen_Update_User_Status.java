package com.business.RingPay_MerchantQRCode_Journey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class BasicDetailScreen_Update_User_Status {


	RingPay_TestData_DataProvider dataProvider = new RingPay_TestData_DataProvider();

	public void updateUserStatus_Positive() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		Object[][] data = dataProvider.UpdateUserStatusAPIData("update_user_200");
		ValidatableResponse response = Utilities.UpdateUserStatusAPI(data);

		//   user_reference_number For DataBase
		String user_reference_number = response.extract().body().jsonPath().get("data.user.user_reference_number");
		System.out.println("user_reference_number: " + user_reference_number);


		//		name_verification_status For DataBase
		String name_verification_status = response.extract().body().jsonPath().get("data.user.name_verification_status");
		System.out.println("name_verification_status: " + name_verification_status);

		Thread.sleep(5000);

		String dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where user_reference_number='"+ user_reference_number+"';",74);
		System.out.println("name_verification_status_DataBase :"+ dataBase);
		Validation.assertEqualsDataBase(name_verification_status,dataBase,"name_verification_status,Validating DataBase");



		//		mobile_verification_status For DataBase
		String mobile_verification_status = response.extract().body().jsonPath().get("data.user.mobile_verification_status");
		System.out.println("mobile_verification_status: " + mobile_verification_status);

		Thread.sleep(5000);

		String mobile_dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where user_reference_number='"+ user_reference_number+"';",75);
		System.out.println("mobile_verification_status_DataBase :"+ mobile_dataBase);
		Validation.assertEqualsDataBase(name_verification_status,mobile_dataBase,"mobile_verification_status,Validating DataBase");


		//		pan_verification_status For DataBase
		String pan_verification_status = response.extract().body().jsonPath().get("data.user.pan_verification_status");
		System.out.println("pan_verification_status: " + pan_verification_status);

		Thread.sleep(5000);

		String pan_dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where user_reference_number='"+ user_reference_number+"';",76);
		System.out.println("pan_verification_status_DataBase :"+ pan_dataBase);
		Validation.assertEqualsDataBase(pan_verification_status,pan_dataBase,"pan_verification_status,Validating DataBase");


		//		dob_verification_status For DataBase
		String dob_verification_status = response.extract().body().jsonPath().get("data.user.dob_verification_status");
		System.out.println("dob_verification_status: " + dob_verification_status);

		Thread.sleep(5000);

		String dob_dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where user_reference_number='"+ user_reference_number+"';",77);
		System.out.println("dob_verification_status :"+ dob_dataBase);
		Validation.assertEqualsDataBase(dob_verification_status,mobile_dataBase,"dob_verification_status,Validating DataBase");


		//		yob_verification_status For DataBase
		String yob_verification_status = response.extract().body().jsonPath().get("data.user.yob_verification_status");
		System.out.println("yob_verification_status: " + yob_verification_status);

		Thread.sleep(5000);

		String yob_dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where user_reference_number='"+ user_reference_number+"';",78);
		System.out.println("yob_verification_status_DataBase :"+ mobile_dataBase);
		Validation.assertEqualsDataBase(yob_verification_status,mobile_dataBase,"yob_verification_status,Validating DataBase");


		//		gender_verification_status For DataBase
		String gender_verification_status = response.extract().body().jsonPath().get("data.user.gender_verification_status");
		System.out.println("gender_verification_status: " + gender_verification_status);

		Thread.sleep(5000);

		String gender_dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where user_reference_number='"+ user_reference_number+"';",79);
		System.out.println("gender_verification_status_DataBase :"+ gender_dataBase);
		Validation.assertEqualsDataBase(gender_verification_status,gender_dataBase,"gender_verification_status,Validating DataBase");


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"updateUserStatus_Positive,Validating 200 Success Response");


		//Body Validation

		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"updateUserStatus_Positive,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","updateUserStatus_Positive,Validating message should be success");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.onboarding_stage"),"VERIFIED","updateUserStatus_Positive,Validating message should be VERIFIED");

		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.name_verification_status"),"VERIFIED","updateUserStatus_Positive,Validating name_verification_status should be VERIFIED");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.mobile_verification_status"),"VERIFIED","updateUserStatus_Positive,Validating mobile_verification_status should be VERIFIED");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.pan_verification_status"),"VERIFIED","updateUserStatus_Positive,Validating pan_verification_status should be VERIFIED");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.dob_verification_status"),"VERIFIED","updateUserStatus_Positive,Validating dob_verification_status should be VERIFIED");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.yob_verification_status"),"VERIFIED","updateUserStatus_Positive,Validating yob_verification_status should be VERIFIED");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.user.gender_verification_status"),"VERIFIED","updateUserStatus_Positive,Validating gender_verification_status should be VERIFIED");


		//Schema Validation

		//		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//update_user_status_200_schema.json")), response.extract().body().asString(), "updateUserStatus_Positive,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'updateUserStatus_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("UpdateUserStatusAPI",responseBody, Time);


	}


}
