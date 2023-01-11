package com.business.RingPay_MerchantQRCode_Journey_Segment1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import com.Datasheet.RingPay_TestData_DataProvider;
import com.utility.ExtentReporter;
import com.utility.Influxdb;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import com.utility.Validation;

import ch.qos.logback.classic.Logger;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ValidatableResponse;

public class DeleteQuery {
	static LoggingUtils logger = new LoggingUtils();
	public void delete() throws Exception {

		ValidatableResponse responseDelete=Utilities.Merchant_loginAPI_Segment1();

		// fetching loginAPI mobileNumber
		long mobilenumber_login = responseDelete.extract().body().jsonPath().get("data.mobile_number");
		System.out.println("mobilenumber_login : " + mobilenumber_login);

		
		String mobileNumber =String.valueOf(mobilenumber_login);
//		int mobileNumber=mobilenumber_login.intValue();
		
//		int mobileNumber = (int) mobilenumber_login;
		System.out.println("mobileNumber "+mobileNumber);
		
		Utilities.deleteRow("DELETE FROM db_tradofina.users WHERE mobile_number='"+ mobileNumber+"';");
		
		
		// mobile_number For DataBase
		Thread.sleep(5000);
		String mobile_number_dataBase =Utilities.executeQuery("SELECT * FROM db_tradofina.users where mobile_number='"+ mobileNumber+"';",11);
		System.out.println("mobile_number :"+ mobile_number_dataBase);
//		if(mobile_number_dataBase.equalsIgnoreCase("null")) {
			Validation.assertEqualsdatabase(mobile_number_dataBase,null,"mobile_Number_Segment1,Validating DataBase");

			logger.info(mobile_number_dataBase);
			ExtentReporter.extentLogger("Delete", "mobile_Number_Segment1 is null");
//		}

		
	}


}
