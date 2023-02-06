package com.RingPayScripts;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.RingPay.URI.RingPay_BaseURL;
import com.business.RingPay.URI.RingPay_Endpoints;
import com.business.RingPay_PlayStore_Journey_Segment1.*;
import com.utility.ExtentReporter;
import com.utility.Utilities;


public class PlayStore_Segment1_Scripts  {

//
//	
//
//	
//	
	
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Mock_User play_mockuser;
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Onload play_onload;

	//	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Get_Details_VPA getvpa;
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_OTPSend play_sendotp;
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate play_userauthenticate;
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_BasicDetails play_basicdetails;
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Login play_login;

//	private com.business.RingPay_RingPolicy.Segment1_PlayStore s1_segment1_playstore;

	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Register_User play_registeruser;
	private com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Location_Require play_locaterequire;
	private com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Get_User_Detail play_getuserdetails;
	private com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_User_Onboarding play_useronboarding;
	private com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction play_createbnpl;
	private com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Update_User_Status play_updateuserstatus;
	private com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Add_Address play_addaddress;
	private com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility play_basic_eligibility;
	private com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_Get_Offer play_getoffer ;
	private com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_User_Concent play_userconcent  ;
	private com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility play_offer_eligibility;
	private com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Bnpl_Lines play_bnpl_lines;
	private com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Payment_Option play_payment_option;
	private com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Transaction_Initiate play_txn_initiated;
	private com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Transaction_Complete play_txn_complete;
	private com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends play_current_Spends;
	private com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Validate play_validate;
	private com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Notify play_notify;
	private com.business.RingPay_PlayStore_Journey_Segment1.TransactionDetails_Get_Settlement_Status play_getsettlement;
	private com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details play_get_pin_detais;
	private com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin play_sendotpforpin;
	private com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Set_Reset_Pin play_resetpin;

	private com.business.RingPay_PlayStore_Journey_Segment1.DeleteQuery delete_segment1_playstore;



	@Test(priority = 0)
	public void PlayStore_Journey_Segment1() throws Exception {

		// delete
				ExtentReporter.HeaderChildNode("Delete_MobileNumber_Before");
		
				delete_segment1_playstore=new com.business.RingPay_PlayStore_Journey_Segment1.DeleteQuery();
				delete_segment1_playstore.delete_before();
				System.out.println("deleteMobileNumber,validation is Done");

				
				
		//		MockUser
		ExtentReporter.HeaderChildNode("MockUser (testing-service)");

		play_mockuser=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Mock_User();
		play_mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");

		//		Onload
		ExtentReporter.HeaderChildNode("Onload (user-gateway)");

		play_onload=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Onload();
		play_onload.onload_Positive();
		System.out.println("onloadAPI,Validation is Done");


		//		SendOtp
		ExtentReporter.HeaderChildNode("SendOtp (user-gateway)");

		play_sendotp=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_OTPSend();

		play_sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive is Done");

		play_sendotp.mobileNoLessThan10Digit_Negative();
		System.out.println("mobileNoLessThan10Digit_Negative is Done");
		play_sendotp.mobileNoMoreThan10Digit_Negative();
		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
		play_sendotp.specialCharacterInMobileNoField_Negative();
		System.out.println("specialCharacterInMobileNoField_Negative is Done");
		play_sendotp.alphabetsInMobileNoField_Negative();
		System.out.println("alphabetsInMobileNoField_Negative is Done");
		play_sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive Repeated is Done");


		//		UserAuthenticate
		ExtentReporter.HeaderChildNode("UserAuthenticate (user-gateway)");

		play_userauthenticate=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate();

		play_userauthenticate.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");
		play_userauthenticate.invalidOtp_Negative();
		System.out.println("invalidOtp_Negative is Done");
		play_userauthenticate.expiredOtp_Negative();
		System.out.println("expiredOtp_Negative is Done");
		play_userauthenticate.alphabetInOtpField_Negative();
		System.out.println("alphabetInOtpField_Negative is Done");
		play_userauthenticate.lessThan6DigitsNoInOtpField_Negative();
		System.out.println("lessThan6DigitsNoInOtpField_Negative is Done");
		play_userauthenticate.userToken_Positive_PlayStore_Repeat_S1();
		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login (user-gateway)");

		play_login=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Login();

		play_login.login_Positive();
		System.out.println("loginUser,validation is Done");



//		//		Segment1
//		ExtentReporter.HeaderChildNode("Segment1 (testing-service)");
//
//		s1_segment1_playstore=new com.business.RingPay_RingPolicy.Segment1_PlayStore();
//		s1_segment1_playstore.Segment1();
//		System.out.println("RingPolicy_Segment1,validation is Done");


		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser (big-data-python)");

		play_registeruser=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Register_User();

		play_registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");


		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails (user-gateway)");

		play_basicdetails=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_BasicDetails();

		play_basicdetails.basicDetails_Positive_SchemaValiadtion();
		System.out.println("ubasicdetails_Positive_SchemaValiadtion,Schema validation");
		play_basicdetails.alphaNumericInFirstNameField_Negative();
		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
		play_basicdetails.specialCharacterInFirstNameField_Negative();
		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");
		play_basicdetails.spaceInFirstNameField_Negative();
		System.out.println("spaceInFirstNameField_Negative,validation is Done");
		play_basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");
		play_basicdetails.alphaNumericInLastNameField_Negative();
		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");
		play_basicdetails.specialCharacterInLastNameField_Negative();
		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");
		play_basicdetails.spaceInLastNameField_Negative();
		System.out.println("spaceInLastNameField_Negative,validation is Done");
		play_basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");
		play_basicdetails.invalidEmailId_Negative();
		System.out.println("invalidEmailId_Negative,validation is Done");
		play_basicdetails.spaceInEmailIdField_Negative();
		System.out.println("spaceInEmailIdField_Negative,validation is Done");
		play_basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");


		//		LocateRequire
		ExtentReporter.HeaderChildNode("LocateRequire (user-gateway)");

		play_locaterequire=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Location_Require();

		play_locaterequire.location_Require();
		System.out.println("locationRequire,validation is Done");


		//	=========================== Basic_Detail_Screen ================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails (user-gateway)");

		play_getuserdetails=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Get_User_Detail();

		play_getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");


		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding (user-gateway)");

		play_useronboarding=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_User_Onboarding();

		play_useronboarding.userOnbording_Positive();
		System.out.println("userOnbording_Positive,validation is Done");
		play_useronboarding.latitudeFieldEmpty_Negative();
		System.out.println("latitudeFieldEmpty_Negative,validation is Done");
		play_useronboarding.longitudeFieldEmpty_Negative();
		System.out.println("longitudeFieldEmpty_Negative,validation is Done");
		play_useronboarding.advertisingIdFieldEmpty_Negative();
		System.out.println("advertisingIdFieldEmpty_Negative,validation is Done");
		play_useronboarding.androidIdFieldEmpty_Negative();
		System.out.println("androidIdFieldEmpty_Negative,validation is Done");
		play_useronboarding.globalDeviceIdFieldEmpty_Negative();
		System.out.println("globalDeviceIdFieldEmpty_Negative,validation is Done");
		play_useronboarding.latitudeAndLongitudeFieldEmpty_Negative();
		System.out.println("latitudeAndLongitudeFieldEmpty_Negative,validation is Done");
		play_useronboarding.latitudeFieldWithAlphaNumericKeywords_Negative();
		System.out.println("latitudeFieldWithAlphaNumericKeywords_Negative,validation is Done");
		play_useronboarding.userOnbording_Positive();
		System.out.println("userOnbordingWithValidField_Positive,validation is Done");


		//		CreateBnplTransaction
		ExtentReporter.HeaderChildNode("CreateBnplTransaction (user-gateway)");

		play_createbnpl=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction();

		play_createbnpl.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");
		play_createbnpl.sourceFieldEmptyBnpl_Negative();
		System.out.println("sourceFieldEmptyBnpl_Negative,validation is Done");
		play_createbnpl.globalDeviceIdFieldEmptyBnpl_Negative();
		System.out.println("globalDeviceIdFieldEmptyBnpl_Negative,validation is Done");
		play_createbnpl.productNameFieldEmptyBnpl_Negative();
		System.out.println("productNameFieldEmptyBnpl_Negative,validation is Done");
		play_createbnpl.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");


		//		UpdateUserStatus
		ExtentReporter.HeaderChildNode("UpdateUserStatus (user-gateway)");

		play_updateuserstatus=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Update_User_Status();

		play_updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");


		//	CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility (txn-gateway)");

		play_basic_eligibility=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
		play_addaddress=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Add_Address();

		//		play_basic_eligibility.checkApplicationEligibility_Positive();
		//		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		play_basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		play_addaddress.addAddress_Positive();
		play_basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");


		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress (user-gateway)");

		play_addaddress=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Add_Address();

		play_addaddress.addAddress_Positive();
		System.out.println("addAddress_Positive,validation is Done");
		play_addaddress.line1FieldIsEmpty_Negative();
		System.out.println("line1FieldIsEmpty_Negative,validation is Done");
		play_addaddress.pincodeFieldIsEmpty_Negative();
		System.out.println("pincodeFieldIsEmpty_Negative,validation is Done");
		play_addaddress.labelFieldIsEmpty_Negative();
		System.out.println("labelFieldIsEmpty_Negative,validation is Done");
		play_addaddress.tagFieldIsEmpty_Negative();
		System.out.println("tagFieldIsEmpty_Negative,validation is Done");
		play_addaddress.sourceFieldIsEmpty_Negative();
		System.out.println("sourceFieldIsEmpty_Negative,validation is Done");
		play_addaddress.invalidSourceField_Negative();
		System.out.println("invalidSourceField_Negative,validation is Done");
		play_addaddress.productNameFieldIsEmpty_Negative();
		System.out.println("productNameFieldIsEmpty_Negative,validation is Done");


		//		===================================== OFFER_DETAILS_SCREEN =======================================

		//		get_Offer_Details
		ExtentReporter.HeaderChildNode("get_Offer_Details (txn-gateway)");

		play_getoffer=new com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_Get_Offer();

		play_getoffer.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");


		//			accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer (txn-gateway)");

		play_userconcent=new com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_User_Concent();

		play_userconcent.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility (txn-gateway)");

		play_offer_eligibility=new com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility();

		play_offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais (user-gateway)");
		play_get_pin_detais=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		play_get_pin_detais.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin (user-gateway)");
		play_sendotpforpin=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin();

		play_sendotpforpin.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//	resetpin
		ExtentReporter.HeaderChildNode("resetpin (user-gateway)");
		play_resetpin=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Set_Reset_Pin();
		play_get_pin_detais=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		play_resetpin.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		play_get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");


		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais (user-gateway)");
		play_get_pin_detais=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		play_get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines  (user-gateway)");
		play_bnpl_lines=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Bnpl_Lines();

		play_bnpl_lines.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");


		// payment_option
		ExtentReporter.HeaderChildNode("payment_option  (user-gateway)");
		play_payment_option=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Payment_Option();

		play_payment_option.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");
		play_payment_option.reasonFieldIsEmpty_Negative();
		System.out.println("reasonFieldIsEmpty_Negative,validation is Done");
		play_payment_option.actualAmountFieldIsEmpty_Negative();
		System.out.println("actualAmountFieldIsEmpty_Negative,validation is Done");
		play_payment_option.qr_CodeFieldIsEmpty_Negative();
		System.out.println("qr_CodeFieldIsEmpty_Negative,validation is Done");
		play_payment_option.qr_CodeFieldWithIncorrectVPA_Negative();
		System.out.println("qr_CodeFieldWithIncorrectVPA_Negative,validation is Done");
		play_payment_option.qr_CodeFieldWithInvalidCode_Negative();
		System.out.println("qr_CodeFieldWithInvalidCode_Negative,validation is Done");
		play_payment_option.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");


		//	txn_initiated
		ExtentReporter.HeaderChildNode("txn_initiated (user-gateway)");
		play_txn_initiated=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Transaction_Initiate();

		play_txn_initiated.transactionInitiate_Positive();
		System.out.println("transaction_Initiate,validation is Done");
		play_txn_initiated.productValueEmptyField_Negative();
		System.out.println("productValueEmptyField_Negative,validation is Done");
		play_txn_initiated.productValueFieldWithAlphaNumericCharacters_Negative();
		System.out.println("productValueFieldWithAlphaNumericCharacters_Negative,validation is Done");
		play_txn_initiated.transactionTypeFieldEmpty_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");
		play_txn_initiated.transactionTypeFieldWithInvalidValue_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");
		play_txn_initiated.merchantOrder_Id_FieldEmpty_Negative();
		System.out.println("merchantOrder_Id_FieldEmpty_Negative,validation is Done");
		play_txn_initiated.upiHandleReferenceNumberFieldEmpty_Negative();
		System.out.println("upiHandleReferenceNumberFieldEmpty_Negative,validation is Done");
		play_txn_initiated.latitudeField_Empty_Negative();
		System.out.println("latitudeField_Empty_Negative,validation is Done");
		play_txn_initiated.longitudeField_Empty_Negative();
		System.out.println("longitudeField_Empty_Negative,validation is Done");
		play_txn_initiated.latitudeAndLongitudeField_Empty_Negative();
		System.out.println("latitudeAndLongitudeField_Empty_Negative,validation is Done");


		// txn_complete
		ExtentReporter.HeaderChildNode("txn_complete (user-gateway)");
		play_txn_complete=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Transaction_Complete();

		play_txn_complete.transactionComplete();
		System.out.println("transactionComplete,validation is Done");



		//	============================== Repayment ======================================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends (user-gateway)");
		play_current_Spends=new com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends();
		play_current_Spends.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");


		// validate
		ExtentReporter.HeaderChildNode("validate (external-gateway)");
		play_validate=new com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Validate();
		play_validate.validate();
		System.out.println("paymentValidate,validation is Done");


		//		notify
		ExtentReporter.HeaderChildNode("notify (external-gateway)");
		play_notify=new com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Notify();
		play_notify.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");


		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement (user-gateway)");
		play_getsettlement=new com.business.RingPay_PlayStore_Journey_Segment1.TransactionDetails_Get_Settlement_Status();
		play_getsettlement.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");


		// delete
		ExtentReporter.HeaderChildNode("Delete_MobileNumber");

		delete_segment1_playstore=new com.business.RingPay_PlayStore_Journey_Segment1.DeleteQuery();
		delete_segment1_playstore.delete();
		System.out.println("deleteMobileNumber,validation is Done");



	}






	//  ====================================================================================================
	//  ====================================================================================================
	//  ====================================================================================================


	//	@Test(priority = 1)
	//	public void onload() throws Exception {
	//		ExtentReporter.HeaderChildNode("PlayStore2");
	//		
	//		registerUser_Onload=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Onload();
	//		registerUser_Onload.onload_Positive();
	//		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");
	//
	//	}
	//


	//	@Test(priority = 2)
	//	public void sendOtp() throws Exception {
	//
	//		sendotp=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_OTPSend();
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
	//	@Test(priority = 3)
	//	public void userToken() throws Exception {
	//
	//		userauthenticate=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate();
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
	//	@Test(priority = 4)
	//	public void loginUser() throws Exception{
	//		login=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Login();
	//
	//		login.login_Positive();
	//		System.out.println("loginUser,validation is Done");
	//	}
	//
	//
	//
	//	@Test(priority = 5)
	//	public void registeruser() throws Exception {
	//
	//		registeruser=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Register_User();
	//
	//		registeruser.registerUserAfterLogin_Positive();
	//		System.out.println("registeruser,validation is Done");
	//
	//	}
	//
	//
	//	@Test(priority = 6)
	//	public void basicDetils() throws Exception {
	//
	//		basicdetails=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_BasicDetails();
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
	//	@Test(priority = 7)
	//	public void locationRequire() throws Exception {
	//
	//		locaterequire=new com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Location_Require();
	//
	//		locaterequire.location_Require();
	//		System.out.println("locationRequire,validation is Done");
	//
	//
	//	}
	//
	//
	//	//	 ====================== Basic_Detail_Screen =================================
	//
	//	@Test(priority = 8)
	//	public void getUserDetails_Positive() throws Exception {
	//
	//		getuserdetails=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Get_User_Detail();
	//
	//		getuserdetails.getUserDetails_Positive();
	//		System.out.println("getUserDetails_Positive,validation is Done");
	//
	//
	//	}
	//
	//
	//	@Test(priority = 9)
	//	public void user_onboarding_200() throws Exception {
	//
	//		useronboarding=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_User_Onboarding();
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
	//		useronboarding.userOnbording_Positive();
	//		System.out.println("userOnbordingWithValidField_Positive,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 10)
	//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI","Basic_CreateBnplTransaction-URI"})
	//	public void create_bnpl_transaction() throws Exception {
	//
	//		createbnpl=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction();
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
	//	@Test(priority = 11)
	//	public void update_user_status_200() throws Exception {
	//
	//		updateuserstatus=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Update_User_Status();
	//
	//		updateuserstatus.updateUserStatus_Positive();
	//		System.out.println("updateuserstatus,validation is Done");
	//
	//	}
	//
	//
	//	//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====
	//
	//	@Test(priority = 12)
	//	public void check_Application_Eligibility() throws Exception {
	//
	//		basic_eligibility=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
	//		addaddress=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Add_Address();
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
	//	@Test(priority = 13)
	//	public void add_addressAPI() throws Exception {
	//
	//		addaddress=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Add_Address();
	//		//		basic_eligibility=new com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
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
	//	@Test(priority = 14)
	//	public void get_Offer_Details() throws Exception {
	//
	//		getoffer=new com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_Get_Offer();
	//
	//		getoffer.get_Offer();
	//		System.out.println("get_Offer_Details,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 15)
	//	public void accept_Offer() throws Exception {
	//
	//		userconcent=new com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_User_Concent();
	//
	//		userconcent.acceptOffer();
	//		System.out.println("accept_Offer,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 16)
	//	public void offerDetailsScreen_CheckApplicationEligibility() throws Exception {
	//
	//		offer_eligibility=new com.business.RingPay_PlayStore_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility();
	//
	//		offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
	//		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");
	//
	//	}
	//
	//
	//	//	===================================== PIN_DETAILS_SCREEN =======================================
	//
	//	@Test(priority = 17)
	//	public void getPinDetails() throws Exception {
	//
	//		get_pin_detais=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details();
	//
	//		get_pin_detais.getPinDetails_Positive();
	//		System.out.println("getPinDetails,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 18)
	//	public void sendOtpForPin() throws Exception {
	//
	//		sendotpforpin=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin();
	//
	//		sendotpforpin.sendOtpForPin_Positive();
	//		System.out.println("sendOtpForPin,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 19)
	//	public void setResetPin() throws Exception {
	//
	//		resetpin=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Set_Reset_Pin();
	//		get_pin_detais=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details();
	//
	//		resetpin.setResetPin_Positive();
	//		System.out.println("setResetPin,validation is Done");
	//
	//		get_pin_detais.afterResetPin_getPinDetails_Positive();
	//		System.out.println("AfterResetAPI_SetResetPin,validation is Done");
	//
	//	}
	//
	//
	//	@Test(priority = 20)
	//	public void afterResetPin_getPinDetails() throws Exception {
	//
	//		get_pin_detais=new com.business.RingPay_PlayStore_Journey_Segment1.PinDetailScreen_Get_Pin_Details();
	//
	//		get_pin_detais.afterResetPin_getPinDetails_Positive();
	//		System.out.println("AfterResetAPI_SetResetPin,validation is Done");
	//
	//	}
	//
	//
	//	//	====================================== BNPL_TXN =======================================
	//
	//	@Test(priority = 21)
	//	public void bnplLines() throws Exception {
	//
	//		bnpl_lines=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Bnpl_Lines();
	//
	//		bnpl_lines.bnpl_Lines();
	//		System.out.println("bnpl_lines,validation is Done");
	//
	//
	//	}
	//
	//	@Test(priority = 22)
	//	public void paymentOption() throws Exception {
	//
	//		payment_option=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Payment_Option();
	//
	//		payment_option.paymentOption_Positive();
	//		System.out.println("payment_option,validation is Done");
	//		payment_option.reasonFieldIsEmpty_Negative();
	//		System.out.println("reasonFieldIsEmpty_Negative,validation is Done");
	//		payment_option.actualAmountFieldIsEmpty_Negative();
	//		System.out.println("actualAmountFieldIsEmpty_Negative,validation is Done");
	//		payment_option.qr_CodeFieldIsEmpty_Negative();
	//		System.out.println("qr_CodeFieldIsEmpty_Negative,validation is Done");
	//		payment_option.qr_CodeFieldWithIncorrectVPA_Negative();
	//		System.out.println("qr_CodeFieldWithIncorrectVPA_Negative,validation is Done");
	//		payment_option.qr_CodeFieldWithInvalidCode_Negative();
	//		System.out.println("qr_CodeFieldWithInvalidCode_Negative,validation is Done");
	//		payment_option.paymentOption_Positive();
	//		System.out.println("payment_option,validation is Done");
	//	}
	//
	//	@Test(priority = 23)
	//	public void transaction_Initiate() throws Exception {
	//
	//		txn_initiated=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Transaction_Initiate();
	//
	//		Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();
	//		System.out.println("transaction_Initiate,validation is Done");
	//
	//		txn_initiated.productValueEmptyField_Negative();
	//		System.out.println("productValueEmptyField_Negative,validation is Done");
	//
	//		txn_initiated.productValueFieldWithAlphaNumericCharacters_Negative();
	//		System.out.println("productValueFieldWithAlphaNumericCharacters_Negative,validation is Done");
	//
	//		txn_initiated.transactionTypeFieldEmpty_Negative();
	//		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");
	//
	//		txn_initiated.transactionTypeFieldWithInvalidValue_Negative();
	//		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");
	//
	//		txn_initiated.merchantOrder_Id_FieldEmpty_Negative();
	//		System.out.println("merchantOrder_Id_FieldEmpty_Negative,validation is Done");
	//
	//		txn_initiated.upiHandleReferenceNumberFieldEmpty_Negative();
	//		System.out.println("upiHandleReferenceNumberFieldEmpty_Negative,validation is Done");
	//
	//		txn_initiated.latitudeField_Empty_Negative();
	//		System.out.println("latitudeField_Empty_Negative,validation is Done");
	//
	//		txn_initiated.longitudeField_Empty_Negative();
	//		System.out.println("longitudeField_Empty_Negative,validation is Done");
	//
	//		txn_initiated.latitudeAndLongitudeField_Empty_Negative();
	//		System.out.println("latitudeAndLongitudeField_Empty_Negative,validation is Done");
	//
	//
	//	}
	//
	//
	//	@Test(priority = 24)
	//	public void transactionComplete() throws Exception {
	//
	//		txn_complete=new com.business.RingPay_PlayStore_Journey_Segment1.Bnpl_Txn_Transaction_Complete();
	//
	//		txn_complete.transactionComplete();
	//		System.out.println("transactionComplete,validation is Done");
	//
	//
	//	}
	//
	//
	//	//	============================== Repayment ======================================
	//
	//
	//	@Test(priority = 25)
	//	public void homeScreenForCurrentSpends() throws Exception {
	//
	//		current_Spends=new com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends();
	//		current_Spends.current_Spent();
	//		System.out.println("homeScreenForCurrentSpends,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 26)
	//	public void paymentValidate() throws Exception {
	//
	//		validate=new com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Validate();
	//		validate.validate();
	//		System.out.println("paymentValidate,validation is Done");
	//
	//	}
	//
	//
	//	@Test(priority = 27)
	//	public void notifyPaymentDone() throws Exception {
	//
	//		notify=new com.business.RingPay_PlayStore_Journey_Segment1.Repayment_Notify();
	//		notify.notifyPaymentDone();
	//		System.out.println("notifyPaymentDone,validation is Done");
	//
	//	}
	//
	//
	//	@Test(priority = 28)
	//	public void getSettlementStatus_PlayStore() throws Exception {
	//
	//		getsettlement=new com.business.RingPay_PlayStore_Journey_Segment1.TransactionDetails_Get_Settlement_Status();
	//		getsettlement.getSettlementStatus();
	//		System.out.println("getSettlementStatus,validation is Done");
	//
	//	}



}

