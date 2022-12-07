package com.business.RingPay_PromoCode_Journey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.excel.ExcelFunctions;
import com.excel.ExcelWriteData;
import com.utility.ExtentReporter;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.utility.Validation;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class PromoCode_RegisterUser_PromoCode {


	public ValidatableResponse promo_Code() throws Exception {

		//		Start Time
		long startTime=System.currentTimeMillis();

		ValidatableResponse response = Utilities.PromoCodeAPI();


		//Status Code Validation
		int responseBody=response.extract().statusCode();
		Validation.validatingStatusCode(responseBody,200,"promo_Code_Positive,Validating 200 Success Response");

		Validation.assertEquals(response.extract().body().jsonPath().get("data.promocode.status"),"ACTIVE","promo_Code_Positive,Validating message should be success");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.promocode.promo_code_reference_number"),"promo_Code_Positive,Validating promo_code_reference_number is not null");
		Validation.assertRequest_IdNotNullBodyValidation(response.extract().body().jsonPath().get("data.promocode.merchant_reference_number"),"promo_Code_Positive,Validating merchant_reference_number is not null");

		
		//Schema Validation
		Validation.assertSchemaValidation(FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//TestData//promo_code_200_schema.json")), response.extract().body().asString(), "promo_Code_Positive,expectedJsonSchema");

		
		//		End Time
		long endTime=System.currentTimeMillis();
		ExtentReporter.extentLogger("Time Stamp", "API RunTime 'promo_Code_Positive'  : "+(endTime-startTime)+" milliseconds");

		return	response;

	}

}


