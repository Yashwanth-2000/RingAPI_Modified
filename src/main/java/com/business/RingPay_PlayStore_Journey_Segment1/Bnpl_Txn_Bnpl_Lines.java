package com.business.RingPay_PlayStore_Journey_Segment1;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.response.ValidatableResponse;

public class Bnpl_Txn_Bnpl_Lines {


	public void bnpl_Lines() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		ValidatableResponse response = Utilities.PlayStore_bnplLinesAPI_Segment1();

		//		line_reference_number For DataBase
		String line_reference_number = response.extract().body().jsonPath().get("data.bnpl.line.line_reference_number");
		System.out.println("line_reference_number: " + line_reference_number);

		Thread.sleep(5000);

		String line_reference_number_dataBase =Utilities.executeQuery("SELECT * FROM bnpl_transactions where line_reference_number='"+ line_reference_number+"';",4);
		System.out.println("line_reference_number_DataBase :"+ line_reference_number_dataBase);
		Validation.assertEqualsDataBase(line_reference_number,line_reference_number_dataBase,"line_reference_number,Validating DataBase");


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"bnpl_Lines,Validating 200 Success Response");

		//		Body Validation
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"bnpl_Lines,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","bnpl_Lines,Validating message should be success");
		Validation.assertTrue(response.extract().body().jsonPath().get("data.bnpl.is_line_present"), "bnpl_Lines,Validating is_line_present should be true");
		Validation.assertEquals(response.extract().body().jsonPath().get("data.bnpl.line.status"),"ACTIVE","bnpl_Lines,Validating status should be ACTIVE");



		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//bnpl_lines_200_schema.json")), response.extract().body().asString(), "bnpl_Lines,expectedJsonSchema");

		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'bnpl_Lines'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("BnplLinesAPI",responseBody, Time);


	}

}
