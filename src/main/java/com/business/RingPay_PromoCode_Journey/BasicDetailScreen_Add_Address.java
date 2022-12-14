package com.business.RingPay_PromoCode_Journey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.Datasheet.RingPay_TestData_DataProvider_PromoCode;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class BasicDetailScreen_Add_Address {


	RingPay_TestData_DataProvider_PromoCode dataProvider = new RingPay_TestData_DataProvider_PromoCode();

	//		Start Time
	long startTime=System.currentTimeMillis();

	public ValidatableResponse addAddress_Positive() throws Exception {
		
		Object[][] data = dataProvider.AddAddressAPIData("add_address");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation

		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"addAddress_Positive,Validating 200 Success Response");

		//Body Validation
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("request_id"),"addAddress_Positive,Validating request_id is not null");
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"Success","addAddress_Positive,Validating message should be success");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_200_schema.json")), response.extract().body().asString(), "addAddress_Positive,expectedJsonSchema");

		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'addAddress_Positive'  : "+(endTime-startTime)+" milliseconds");

		//		Dashboard
		long Time = response.extract().time();
		String ResponseTime = String.valueOf(Time+" ms");
		System.out.println("responseTime :"+ResponseTime);

		Influxdb.passbyval("AddAddressAPI",responseBody, Time);


		return response;

	}


	public void  line1FieldIsEmpty_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("line1fieldisempty");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"addAddress_Positive,Validating 400 Bad Request");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The line 1 field is required.","addAddress_Positive,Validating message should be The line 1 field is required.");


		//			Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "addAddress_Positive,expectedJsonSchema");

		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'line1IsEmpty_Negative'  : "+(endTime-startTime)+" milliseconds");


	}

	public void  pincodeFieldIsEmpty_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("pincodefieldisempty");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"addAddress_Positive,Validating 400 Bad Request");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The pincode field is required.","addAddress_Positive,Validating message should be The pincode field is required.");


		//			Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "addAddress_Positive,expectedJsonSchema");

		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'pincodeIsEmpty_Negative'  : "+(endTime-startTime)+" milliseconds");


	}


	public void  labelFieldIsEmpty_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("labelfieldisempty");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"labelIsEmpty_Negative,Validating 400 Bad Request");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The label field is required.","labelIsEmpty_Negative,Validating message should be The label field is required.");


		//			Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "labelIsEmpty_Negative,expectedJsonSchema");

		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'labelIsEmpty_Negative'  : "+(endTime-startTime)+" milliseconds");


	}


	public void  tagFieldIsEmpty_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("tagfieldisempty");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"tagFieldIsEmpty_Negative,Validating 400 Bad Request");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The tag field is required.","tagFieldIsEmpty_Negative,Validating message should be The tag field is required.");


		//			Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "tagFieldIsEmpty_Negative,expectedJsonSchema");

		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'tagFieldIsEmpty_Negative'  : "+(endTime-startTime)+" milliseconds");

	}


	public void  sourceFieldIsEmpty_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("sourcefieldisempty");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"sourceFieldIsEmpty_Negative,Validating 400 Bad Request");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The source must be a string.,The selected source is invalid.","sourceFieldIsEmpty_Negative,Validating message should be The source must be a string.,The selected source is invalid.");


		//			Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "sourceFieldIsEmpty_Negative,expectedJsonSchema");

		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'sourceFieldIsEmpty_Negative'  : "+(endTime-startTime)+" milliseconds");

	}


	public void  invalidSourceField_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("invalidsourcefield");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"invalidSourceField_Negative,Validating 400 Bad Request");

		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The selected source is invalid.","invalidSourceField_Negative,Validating message should be The selected source is invalid.");

		//			Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "invalidSourceField_Negative,expectedJsonSchema");


		//				End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'invalidSourceField_Negative'  : "+(endTime-startTime)+" milliseconds");

	}


	public void  productNameFieldIsEmpty_Negative() throws Exception {
		Object[][] data = dataProvider.AddAddressAPIData("productnamefieldisempty");
		ValidatableResponse response = Utilities.PromoCode_AddAddressAPI(data);


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,400,"productNameFieldIsEmpty_Negative,Validating 400 Bad Request");


		//Body Validation
		Validation.assertEquals(response.extract().body().jsonPath().get("message"),"The product name field is required.","productNameFieldIsEmpty_Negative,Validating message should be The product name field is required.");


		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//add_address_400_schema.json")), response.extract().body().asString(), "productNameFieldIsEmpty_Negative,expectedJsonSchema");

		//End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'productNameFieldIsEmpty_Negative'  : "+(endTime-startTime)+" milliseconds");

	}


}
