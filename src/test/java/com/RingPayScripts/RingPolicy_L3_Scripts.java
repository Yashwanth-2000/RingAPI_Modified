package com.RingPayScripts;

import java.io.IOException;
//
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.RingPay.URI.RingPay_BaseURL;
import com.business.RingPay.URI.RingPay_Endpoints;
import com.business.RingPay_RingPolicy_L3.*;
import com.utility.ExtentReporter;
import com.utility.Utilities;



public class RingPolicy_L3_Scripts  {

	private com.business.RingPay_RingPolicy_L3.RegisterUser_Mock_User mockuser;
	private com.business.RingPay_RingPolicy_L3.RegisterUser_Onload registerUser_Onload;

	private com.business.RingPay_RingPolicy_L3.RegisterUser_Get_Details_VPA getvpa;
	private com.business.RingPay_RingPolicy_L3.RegisterUser_OTPSend sendotp;
	private com.business.RingPay_RingPolicy_L3.RegisterUser_UserAuthenticate userauthenticate;
	private com.business.RingPay_RingPolicy_L3.RegisterUser_BasicDetails basicdetails;
	private com.business.RingPay_RingPolicy_L3.RegisterUser_Login login;
	private com.business.RingPay_RingPolicy.RegularOffer_L3 l3;

	private com.business.RingPay_RingPolicy_L3.RegisterUser_Register_User registeruser;
	private com.business.RingPay_RingPolicy_L3.RegisterUser_Location_Require locaterequire;
	private com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Get_User_Detail getuserdetails;
	private com.business.RingPay_RingPolicy_L3.BasicDetailScreen_User_Onboarding useronboarding;
	private com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Create_Bnpl_Transaction createbnpl;
	private com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Update_User_Status updateuserstatus;
	private com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Add_Address addaddress;
	private com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Check_Application_Eligibility basic_eligibility;
	private com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_Get_Offer getoffer ;
	private com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_User_Concent userconcent  ;
	private com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_Check_Application_Eligibility offer_eligibility;
	private com.business.RingPay_RingPolicy_L3.Bnpl_Txn_Bnpl_Lines bnpl_lines;
	private com.business.RingPay_RingPolicy_L3.Bnpl_Txn_Payment_Option payment_option;
	private com.business.RingPay_RingPolicy_L3.Bnpl_Txn_Transaction_Initiate txn_initiated;
	private com.business.RingPay_RingPolicy_L3.Bnpl_Txn_Transaction_Complete txn_complete;
	private com.business.RingPay_RingPolicy_L3.Repayment_Home_Screen_For_Current_Spends current_Spends;
	private com.business.RingPay_RingPolicy_L3.Repayment_Validate validate;
	private com.business.RingPay_RingPolicy_L3.Repayment_Notify notify;
	private com.business.RingPay_RingPolicy_L3.TransactionDetails_Get_Settlement_Status getsettlement;
	private com.business.RingPay_RingPolicy_L3.PinDetailScreen_Get_Pin_Details get_pin_detais;
	private com.business.RingPay_RingPolicy_L3.PinDetailScreen_Send_Otp_For_Pin sendotpforpin;
	private com.business.RingPay_RingPolicy_L3.PinDetailScreen_Set_Reset_Pin resetpin;


//
	@Test(priority = 0)

	public void Ringpolicy_L3() throws Exception {

		//		mockuser
		ExtentReporter.HeaderChildNode("MockUser (testing-service)");

		mockuser=new com.business.RingPay_RingPolicy_L3.RegisterUser_Mock_User();
		mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");


		//		Onload
		ExtentReporter.HeaderChildNode("Onload (user-gateway)");

		registerUser_Onload=new com.business.RingPay_RingPolicy_L3.RegisterUser_Onload();
		registerUser_Onload.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");



		//		Get_VPA
		ExtentReporter.HeaderChildNode("Get_VPA (user-gateway)");

		getvpa=new com.business.RingPay_RingPolicy_L3.RegisterUser_Get_Details_VPA();

		getvpa.get_Details_Vpa_Positive();
		System.out.println("getVPADetails,Validation is Done");


		//		sendOtp
		ExtentReporter.HeaderChildNode("SendOtp (user-gateway)");

		sendotp=new com.business.RingPay_RingPolicy_L3.RegisterUser_OTPSend();

		sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive is Done");

		sendotp.mobileNoLessThan10Digit_Negative();
		System.out.println("mobileNoLessThan10Digit_Negative is Done");
		sendotp.mobileNoMoreThan10Digit_Negative();
		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
		sendotp.specialCharacterInMobileNoField_Negative();
		System.out.println("specialCharacterInMobileNoField_Negative is Done");
		sendotp.alphabetsInMobileNoField_Negative();
		System.out.println("alphabetsInMobileNoField_Negative is Done");
		sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive Repeated is Done");



		//		UserAuthenticate
		ExtentReporter.HeaderChildNode("UserAuthenticate (user-gateway)");

		userauthenticate=new com.business.RingPay_RingPolicy_L3.RegisterUser_UserAuthenticate();

		userauthenticate.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");
		userauthenticate.invalidOtp_Negative();
		System.out.println("invalidOtp_Negative is Done");
		userauthenticate.expiredOtp_Negative();
		System.out.println("expiredOtp_Negative is Done");
		userauthenticate.alphabetInOtpField_Negative();
		System.out.println("alphabetInOtpField_Negative is Done");
		userauthenticate.lessThan6DigitsNoInOtpField_Negative();
		System.out.println("lessThan6DigitsNoInOtpField_Negative is Done");
//		userauthenticate.userTokenAPI_Repeat_L3();
//		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login (user-gateway)");

		login=new com.business.RingPay_RingPolicy_L3.RegisterUser_Login();

		login.login_Positive();
		System.out.println("loginUser,validation is Done");



		//		RegularOffer_L3
		ExtentReporter.HeaderChildNode("RegularOffer_L3 (testing-service)");

		l3=new com.business.RingPay_RingPolicy.RegularOffer_L3();

		l3.RegularOffer_L3();
		System.out.println("RegularOffer_L3,validation is Done");



		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser (big-data-python)");

		registeruser=new com.business.RingPay_RingPolicy_L3.RegisterUser_Register_User();

		registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");




		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails (user-gateway)");

		basicdetails=new com.business.RingPay_RingPolicy_L3.RegisterUser_BasicDetails();

		basicdetails.basicDetails_Positive_SchemaValiadtion();
		System.out.println("ubasicdetails_Positive_SchemaValiadtion,Schema validation");
		basicdetails.alphaNumericInFirstNameField_Negative();
		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
		basicdetails.specialCharacterInFirstNameField_Negative();
		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");

		basicdetails.spaceInFirstNameField_Negative();
		System.out.println("spaceInFirstNameField_Negative,validation is Done");

		basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");

		basicdetails.alphaNumericInLastNameField_Negative();
		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");

		basicdetails.specialCharacterInLastNameField_Negative();
		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");

		basicdetails.spaceInLastNameField_Negative();
		System.out.println("spaceInLastNameField_Negative,validation is Done");

		basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");

		basicdetails.invalidEmailId_Negative();
		System.out.println("invalidEmailId_Negative,validation is Done");

		basicdetails.spaceInEmailIdField_Negative();
		System.out.println("spaceInEmailIdField_Negative,validation is Done");

		basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");



		//		LocateRequire
		ExtentReporter.HeaderChildNode("LocateRequire (user-gateway)");

		locaterequire=new com.business.RingPay_RingPolicy_L3.RegisterUser_Location_Require();

		locaterequire.location_Require();
		System.out.println("locationRequire,validation is Done");



		//	 ====================== Basic_Detail_Screen =================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails (user-gateway)");

		getuserdetails=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Get_User_Detail();

		getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");



		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding (user-gateway)");

		useronboarding=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_User_Onboarding();

		useronboarding.userOnbording_Positive();
		System.out.println("userOnbording_Positive,validation is Done");

		useronboarding.latitudeFieldEmpty_Negative();
		System.out.println("latitudeFieldEmpty_Negative,validation is Done");

		useronboarding.longitudeFieldEmpty_Negative();
		System.out.println("longitudeFieldEmpty_Negative,validation is Done");

		useronboarding.advertisingIdFieldEmpty_Negative();
		System.out.println("advertisingIdFieldEmpty_Negative,validation is Done");

		useronboarding.androidIdFieldEmpty_Negative();
		System.out.println("androidIdFieldEmpty_Negative,validation is Done");

		useronboarding.globalDeviceIdFieldEmpty_Negative();
		System.out.println("globalDeviceIdFieldEmpty_Negative,validation is Done");

		useronboarding.latitudeAndLongitudeFieldEmpty_Negative();
		System.out.println("latitudeAndLongitudeFieldEmpty_Negative,validation is Done");

		useronboarding.latitudeFieldWithAlphaNumericKeywords_Negative();
		System.out.println("latitudeFieldWithAlphaNumericKeywords_Negative,validation is Done");


		useronboarding.userOnbording_Positive();
		System.out.println("userOnbordingWithValidField_Positive,validation is Done");



		//		CreateBnplTransaction
		ExtentReporter.HeaderChildNode("CreateBnplTransaction (user-gateway)");

		createbnpl=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Create_Bnpl_Transaction();

		createbnpl.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");
		createbnpl.sourceFieldEmptyBnpl_Negative();
		System.out.println("sourceFieldEmptyBnpl_Negative,validation is Done");
		createbnpl.globalDeviceIdFieldEmptyBnpl_Negative();
		System.out.println("globalDeviceIdFieldEmptyBnpl_Negative,validation is Done");
		createbnpl.productNameFieldEmptyBnpl_Negative();
		System.out.println("productNameFieldEmptyBnpl_Negative,validation is Done");
		createbnpl.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");



		//		UpdateUserStatus
		ExtentReporter.HeaderChildNode("UpdateUserStatus (user-gateway)");

		updateuserstatus=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Update_User_Status();

		updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");



		//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====


		//		CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility (txn-gateway)");

		basic_eligibility=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Check_Application_Eligibility();
		addaddress=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Add_Address();

//		basic_eligibility.checkApplicationEligibility_Positive();
//		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		addaddress.addAddress_Positive();
		basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");




		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress (user-gateway)");

		addaddress=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Add_Address();

		addaddress.addAddress_Positive();
		System.out.println("addAddress_Positive,validation is Done");
		addaddress.line1FieldIsEmpty_Negative();
		System.out.println("line1FieldIsEmpty_Negative,validation is Done");
		addaddress.pincodeFieldIsEmpty_Negative();
		System.out.println("pincodeFieldIsEmpty_Negative,validation is Done");
		addaddress.labelFieldIsEmpty_Negative();
		System.out.println("labelFieldIsEmpty_Negative,validation is Done");
		addaddress.tagFieldIsEmpty_Negative();
		System.out.println("tagFieldIsEmpty_Negative,validation is Done");
		addaddress.sourceFieldIsEmpty_Negative();
		System.out.println("sourceFieldIsEmpty_Negative,validation is Done");
		addaddress.invalidSourceField_Negative();
		System.out.println("invalidSourceField_Negative,validation is Done");
		addaddress.productNameFieldIsEmpty_Negative();
		System.out.println("productNameFieldIsEmpty_Negative,validation is Done");




		//	===================================== OFFER_DETAILS_SCREEN =======================================

		//		get_Offer_Details
		ExtentReporter.HeaderChildNode("get_Offer_Details (txn-gateway)");

		getoffer=new com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_Get_Offer();

		getoffer.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");


		//		accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer (txn-gateway)");

		userconcent=new com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_User_Concent();

		userconcent.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility (txn-gateway)");

		offer_eligibility=new com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_Check_Application_Eligibility();

		offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");


		//  RegularOffer_L3_DataBase
		ExtentReporter.HeaderChildNode("RegularOffer_L3_DataBase (testing-service)");

		l3=new com.business.RingPay_RingPolicy.RegularOffer_L3();

		l3.RegularOffer_L3_DataBase();
		System.out.println("RegularOffer_L3_DataBase,validation is Done");


	}



	//	@Test(priority = 1)
	//	public void onloadAndroidVersionCheckAPI() throws Exception {
	//		registerUser_Onload=new com.business.RingPay_RingPolicy_L3.RegisterUser_Onload();
	//		registerUser_Onload.onload_Positive();
	//		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");
	//
	//	}
	//
	//
	//	@Test(priority = 2)
	//	public void getVPADetails() throws Exception {
	//
	//		getvpa=new com.business.RingPay_RingPolicy_L3.RegisterUser_Get_Details_VPA();
	//
	//		getvpa.get_Details_Vpa_Positive();
	//		System.out.println("getVPADetails,Validation is Done");
	//	}
	//
	//
	//	@Test(priority = 3)
	//	public void sendOtp_Positive() throws Exception {
	//
	//		sendotp=new com.business.RingPay_RingPolicy_L3.RegisterUser_OTPSend();
	//
	//		sendotp.validMobileNo_Positive();
	//		System.out.println("validMobileNo_Positive is Done");
	//
	//		sendotp.mobileNoLessThan10Digit_Negative();
	//		System.out.println("mobileNoLessThan10Digit_Negative is Done");
	//		sendotp.mobileNoMoreThan10Digit_Negative();
	//		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
	//		sendotp.specialCharacterInMobileNoField_Negative();
	//		System.out.println("specialCharacterInMobileNoField_Negative is Done");
	//		sendotp.alphabetsInMobileNoField_Negative();
	//		System.out.println("alphabetsInMobileNoField_Negative is Done");
	//		sendotp.validMobileNo_Positive();
	//		System.out.println("validMobileNo_Positive Repeated is Done");
	//	}
	//
	//
	//
	//	@Test(priority = 4)
	//	public void userToken() throws Exception {
	//
	//		userauthenticate=new com.business.RingPay_RingPolicy_L3.RegisterUser_UserAuthenticate();
	//
	//		userauthenticate.userToken_Positive();
	//		System.out.println("userToken_Positive,validation is Done");
	//
	//		userauthenticate.invalidOtp_Negative();
	//		System.out.println("invalidOtp_Negative is Done");
	//
	//		userauthenticate.expiredOtp_Negative();
	//		System.out.println("expiredOtp_Negative is Done");
	//		userauthenticate.alphabetInOtpField_Negative();
	//		System.out.println("alphabetInOtpField_Negative is Done");
	//		userauthenticate.lessThan6DigitsNoInOtpField_Negative();
	//		System.out.println("lessThan6DigitsNoInOtpField_Negative is Done");
	//		userauthenticate.userToken_Positive();
	//		System.out.println("userToken_Positive,validation is Done");
	//	}
	//
	//
	//
	//	@Test(priority = 5)
	//	public void loginUser() throws Exception{
	//		login=new com.business.RingPay_RingPolicy_L3.RegisterUser_Login();
	//
	//		login.login_Positive();
	//		System.out.println("loginUser,validation is Done");
	//	}
	//
	//
	//	@Test(priority = 6)
	//	public void RegularOffer_L3() throws Exception{
	//		l3=new com.business.RingPay_RingPolicy.RegularOffer_L3();
	//
	//		l3.RegularOffer_L3();
	//		System.out.println("RegularOffer_L3,validation is Done");
	//	}
	//
	//
	//	@Test(priority = 7)
	//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI","Register_User-URI"})
	//	public void registeruser() throws Exception {
	//
	//		registeruser=new com.business.RingPay_RingPolicy_L3.RegisterUser_Register_User();
	//		System.out.println("registeruser,validation is Done");
	//
	//		registeruser.registerUserAfterLogin_Positive();
	//		System.out.println("registeruser,validation is Done");
	//
	//	}
	//	
	//	
	//	@Test(priority = 8)
	//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI"})
	//	public void updateUserDetils_200() throws Exception {
	//
	//		basicdetails=new com.business.RingPay_RingPolicy_L3.RegisterUser_BasicDetails();
	//
	//
	//		basicdetails.basicDetails_Positive_SchemaValiadtion();
	//		System.out.println("ubasicdetails_Positive_SchemaValiadtion,Schema validation");
	//		basicdetails.alphaNumericInFirstNameField_Negative();
	//		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
	//		basicdetails.specialCharacterInFirstNameField_Negative();
	//		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");
	//
	//		basicdetails.spaceInFirstNameField_Negative();
	//		System.out.println("spaceInFirstNameField_Negative,validation is Done");
	//
	//		basicdetails.basicDetails_Positive();
	//		System.out.println("updateUser_Positive,validation is Done");
	//
	//		basicdetails.alphaNumericInLastNameField_Negative();
	//		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");
	//
	//		basicdetails.specialCharacterInLastNameField_Negative();
	//		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");
	//
	//		basicdetails.spaceInLastNameField_Negative();
	//		System.out.println("spaceInLastNameField_Negative,validation is Done");
	//
	//		basicdetails.basicDetails_Positive();
	//		System.out.println("updateUser_Positive,validation is Done");
	//
	//		basicdetails.invalidEmailId_Negative();
	//		System.out.println("invalidEmailId_Negative,validation is Done");
	//
	//		basicdetails.spaceInEmailIdField_Negative();
	//		System.out.println("spaceInEmailIdField_Negative,validation is Done");
	//
	//		basicdetails.basicDetails_Positive();
	//		System.out.println("updateUser_Positive,validation is Done");
	//
	//
	//	}
	//	
	//
	//	@Test(priority = 9)
	//	public void locationRequire() throws Exception {
	//
	//		locaterequire=new com.business.RingPay_RingPolicy_L3.RegisterUser_Location_Require();
	//
	//		locaterequire.location_Require();
	//		System.out.println("locationRequire,validation is Done");
	//
	//
	//	}
	//
	//
	//	//	//	 ====================== Basic_Detail_Screen =================================
	//
	//	@Test(priority = 10)
	//	public void getUserDetails_Positive() throws Exception {
	//
	//		getuserdetails=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Get_User_Detail();
	//
	//		getuserdetails.getUserDetails_Positive();
	//		System.out.println("getUserDetails_Positive,validation is Done");
	//
	//
	//	}
	//
	//
	//	@Test(priority = 11)
	//	public void user_onboarding_200() throws Exception {
	//
	//		useronboarding=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_User_Onboarding();
	//
	//		useronboarding.userOnbording_Positive();
	//		System.out.println("userOnbording_Positive,validation is Done");
	//
	//		useronboarding.latitudeFieldEmpty_Negative();
	//		System.out.println("latitudeFieldEmpty_Negative,validation is Done");
	//
	//		useronboarding.longitudeFieldEmpty_Negative();
	//		System.out.println("longitudeFieldEmpty_Negative,validation is Done");
	//
	//		useronboarding.advertisingIdFieldEmpty_Negative();
	//		System.out.println("advertisingIdFieldEmpty_Negative,validation is Done");
	//
	//		useronboarding.androidIdFieldEmpty_Negative();
	//		System.out.println("androidIdFieldEmpty_Negative,validation is Done");
	//
	//		useronboarding.globalDeviceIdFieldEmpty_Negative();
	//		System.out.println("globalDeviceIdFieldEmpty_Negative,validation is Done");
	//
	//		useronboarding.latitudeAndLongitudeFieldEmpty_Negative();
	//		System.out.println("latitudeAndLongitudeFieldEmpty_Negative,validation is Done");
	//
	//		useronboarding.latitudeFieldWithAlphaNumericKeywords_Negative();
	//		System.out.println("latitudeFieldWithAlphaNumericKeywords_Negative,validation is Done");
	//
	//		//		useronboarding.userOnbordingWithValidField_Positive();
	//		//		System.out.println("userOnbordingWithValidField_Positive,validation is Done");
	//
	//		useronboarding.userOnbording_Positive();
	//		System.out.println("userOnbordingWithValidField_Positive,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 12)
	//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI","Basic_CreateBnplTransaction-URI"})
	//	public void create_bnpl_transaction() throws Exception {
	//
	//		createbnpl=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Create_Bnpl_Transaction();
	//
	//		createbnpl.getApplicationToken_Positive();
	//		System.out.println("getApplicationToken_Positive,validation is Done");
	//		createbnpl.sourceFieldEmptyBnpl_Negative();
	//		System.out.println("sourceFieldEmptyBnpl_Negative,validation is Done");
	//		createbnpl.globalDeviceIdFieldEmptyBnpl_Negative();
	//		System.out.println("globalDeviceIdFieldEmptyBnpl_Negative,validation is Done");
	//		createbnpl.productNameFieldEmptyBnpl_Negative();
	//		System.out.println("productNameFieldEmptyBnpl_Negative,validation is Done");
	//		createbnpl.getApplicationToken_Positive();
	//		System.out.println("getApplicationToken_Positive,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 13)
	//	public void update_user_status_200() throws Exception {
	//
	//		updateuserstatus=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Update_User_Status();
	//
	//		updateuserstatus.updateUserStatus_Positive();
	//		System.out.println("updateuserstatus,validation is Done");
	//
	//	}
	//
	//
	//	//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====
	//
	//	@Test(priority = 14)
	//	public void check_Application_Eligibility() throws Exception {
	//
	//		basic_eligibility=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Check_Application_Eligibility();
	//		addaddress=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Add_Address();
	//
	//		basic_eligibility.checkApplicationEligibility_Positive();
	//		System.out.println("checkApplicationEligibility_Positive,validation is Done");
	//		basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
	//		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");
	//
	//		addaddress.addAddress_Positive();
	//		basic_eligibility.checkApplicationEligibility_Positive();
	//		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");
	//
	//
	//	}
	//
	//	@Test(priority = 15)
	//	public void add_addressAPI() throws Exception {
	//
	//		addaddress=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Add_Address();
	//		//		basic_eligibility=new com.business.RingPay_RingPolicy_L3.BasicDetailScreen_Check_Application_Eligibility();
	//		//		addaddress.addAddress_Positive();
	//
	//		addaddress.addAddress_Positive();
	//		System.out.println("addAddress_Positive,validation is Done");
	//		addaddress.line1FieldIsEmpty_Negative();
	//		System.out.println("line1FieldIsEmpty_Negative,validation is Done");
	//		addaddress.pincodeFieldIsEmpty_Negative();
	//		System.out.println("pincodeFieldIsEmpty_Negative,validation is Done");
	//		addaddress.labelFieldIsEmpty_Negative();
	//		System.out.println("labelFieldIsEmpty_Negative,validation is Done");
	//		addaddress.tagFieldIsEmpty_Negative();
	//		System.out.println("tagFieldIsEmpty_Negative,validation is Done");
	//		addaddress.sourceFieldIsEmpty_Negative();
	//		System.out.println("sourceFieldIsEmpty_Negative,validation is Done");
	//		addaddress.invalidSourceField_Negative();
	//		System.out.println("invalidSourceField_Negative,validation is Done");
	//		addaddress.productNameFieldIsEmpty_Negative();
	//		System.out.println("productNameFieldIsEmpty_Negative,validation is Done");
	//
	//
	//	}
	//
	//
	//	//	===================================== OFFER_DETAILS_SCREEN =======================================
	//
	//	@Test(priority = 16)
	//	public void get_Offer_Details() throws Exception {
	//
	//		getoffer=new com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_Get_Offer();
	//
	//		getoffer.get_Offer();
	//		System.out.println("get_Offer_Details,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 17)
	//	public void accept_Offer() throws Exception {
	//
	//		userconcent=new com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_User_Concent();
	//
	//		userconcent.acceptOffer();
	//		System.out.println("accept_Offer,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 18)
	//	public void offerDetailsScreen_CheckApplicationEligibility() throws Exception {
	//
	//		offer_eligibility=new com.business.RingPay_RingPolicy_L3.OfferDetailsScreen_Check_Application_Eligibility();
	//
	//		offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
	//		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 19)
	//	public void RegularOffer_L3_DataBase() throws Exception {
	//
	//		l3=new com.business.RingPay_RingPolicy.RegularOffer_L3();
	//
	//		l3.RegularOffer_L3_DataBase();
	//		System.out.println("RegularOffer_L3_DataBase,validation is Done");
	//
	//	}



}


