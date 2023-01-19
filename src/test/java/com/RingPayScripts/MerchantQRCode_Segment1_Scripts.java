package com.RingPayScripts;

import java.io.IOException;
//
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.business.RingPay.URI.RingPay_BaseURL;
import com.business.RingPay.URI.RingPay_Endpoints;
import com.business.RingPay_MerchantQRCode_Journey_Segment1.*;
import com.utility.ExtentReporter;
import com.utility.Utilities;

public class MerchantQRCode_Segment1_Scripts  {


	//	
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Mock_User mockuser_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Onload onload_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Get_Details_VPA getvpa_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_OTPSend sendotp_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate userauthenticate_segment1_qrcode;

	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Login login_segment1_qrcode;

	private com.business.RingPay_RingPolicy.Segment1_MerchantQRCode s1_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Register_User registeruser_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_BasicDetails basicdetails_segment1_qrcode;

	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Location_Require locaterequire_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Get_User_Detail getuserdetails_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_User_Onboarding useronboarding_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction createbnpl_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Update_User_Status updateuserstatus_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Add_Address addaddress_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility basic_eligibility_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Get_Offer getoffer_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_User_Concent userconcent_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility offer_eligibility_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Bnpl_Lines bnpl_lines_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Payment_Option payment_option_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Initiate txn_initiated_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Complete txn_complete_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends current_Spends_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Validate validate_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Notify notify_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.TransactionDetails_Get_Settlement_Status getsettlement_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details get_pin_detais_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin sendotpforpin_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Set_Reset_Pin resetpin_segment1_qrcode;

	private com.business.RingPay_MerchantQRCode_Journey_Segment1.DeleteQuery delete_segment1_qrcode;



	@Test(priority = 0)
	public void MerChantQRCodeJourney_Segment1() throws Exception {

		
		//		mockuser
		ExtentReporter.HeaderChildNode("MockUser (testing-service)");

		mockuser_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Mock_User();
		mockuser_segment1_qrcode.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");


		//		Onload
		ExtentReporter.HeaderChildNode("Onload (user-gateway)");

		onload_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Onload();
		onload_segment1_qrcode.onload_Positive();
		System.out.println("onloadAPI,Validation is Done");


		//		Get_VPA
		ExtentReporter.HeaderChildNode("Get_VPA (user-gateway)");

		getvpa_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Get_Details_VPA();
		getvpa_segment1_qrcode.get_Details_Vpa_Positive();
		System.out.println("getVPADetails,Validation is Done");


		//		sendOtp
		ExtentReporter.HeaderChildNode("SendOtp (user-gateway)");

		sendotp_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_OTPSend();

		sendotp_segment1_qrcode.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive is Done");
		sendotp_segment1_qrcode.mobileNoLessThan10Digit_Negative();
		System.out.println("mobileNoLessThan10Digit_Negative is Done");
		sendotp_segment1_qrcode.mobileNoMoreThan10Digit_Negative();
		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
		sendotp_segment1_qrcode.specialCharacterInMobileNoField_Negative();
		System.out.println("specialCharacterInMobileNoField_Negative is Done");
		sendotp_segment1_qrcode.alphabetsInMobileNoField_Negative();
		System.out.println("alphabetsInMobileNoField_Negative is Done");
		sendotp_segment1_qrcode.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive Repeated is Done");



		//		UserAuthenticate
		ExtentReporter.HeaderChildNode("UserAuthenticate (user-gateway)");

		userauthenticate_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate();

		userauthenticate_segment1_qrcode.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");
		userauthenticate_segment1_qrcode.invalidOtp_Negative();
		System.out.println("invalidOtp_Negative is Done");
		userauthenticate_segment1_qrcode.expiredOtp_Negative();
		System.out.println("expiredOtp_Negative is Done");
		userauthenticate_segment1_qrcode.alphabetInOtpField_Negative();
		System.out.println("alphabetInOtpField_Negative is Done");
		userauthenticate_segment1_qrcode.lessThan6DigitsNoInOtpField_Negative();
		System.out.println("lessThan6DigitsNoInOtpField_Negative is Done");
		userauthenticate_segment1_qrcode.userToken_Positive_Repeat();
		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login (user-gateway)");

		login_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Login();
		login_segment1_qrcode.login_Positive();
		System.out.println("loginUser,validation is Done");


		//		Segment1
		ExtentReporter.HeaderChildNode("Segment1 (testing-service)");

		s1_segment1_qrcode=new com.business.RingPay_RingPolicy.Segment1_MerchantQRCode();
		s1_segment1_qrcode.Segment1();
		System.out.println("RingPolicy_Segment1,validation is Done");



		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser (big-data-python)");

		registeruser_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Register_User();

		registeruser_segment1_qrcode.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");


		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails (user-gateway)");

		basicdetails_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_BasicDetails();

		basicdetails_segment1_qrcode.basicDetails_Positive_SchemaValiadtion();
		System.out.println("ubasicdetails_Positive_SchemaValiadtion,Schema validation");
		basicdetails_segment1_qrcode.alphaNumericInFirstNameField_Negative();
		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
		basicdetails_segment1_qrcode.specialCharacterInFirstNameField_Negative();
		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");
		basicdetails_segment1_qrcode.spaceInFirstNameField_Negative();
		System.out.println("spaceInFirstNameField_Negative,validation is Done");
		basicdetails_segment1_qrcode.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");
		basicdetails_segment1_qrcode.alphaNumericInLastNameField_Negative();
		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");
		basicdetails_segment1_qrcode.specialCharacterInLastNameField_Negative();
		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");
		basicdetails_segment1_qrcode.spaceInLastNameField_Negative();
		System.out.println("spaceInLastNameField_Negative,validation is Done");
		basicdetails_segment1_qrcode.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");
		basicdetails_segment1_qrcode.invalidEmailId_Negative();
		System.out.println("invalidEmailId_Negative,validation is Done");
		basicdetails_segment1_qrcode.spaceInEmailIdField_Negative();
		System.out.println("spaceInEmailIdField_Negative,validation is Done");
		basicdetails_segment1_qrcode.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");



		//		LocateRequire
		ExtentReporter.HeaderChildNode("LocateRequire (user-gateway)");

		locaterequire_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Location_Require();

		locaterequire_segment1_qrcode.location_Require();
		System.out.println("locationRequire,validation is Done");




		//	 ====================== Basic_Detail_Screen =================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails (user-gateway)");

		getuserdetails_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Get_User_Detail();

		getuserdetails_segment1_qrcode.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");



		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding (user-gateway)");

		useronboarding_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_User_Onboarding();

		useronboarding_segment1_qrcode.userOnbording_Positive();
		System.out.println("userOnbording_Positive,validation is Done");

		useronboarding_segment1_qrcode.latitudeFieldEmpty_Negative();
		System.out.println("latitudeFieldEmpty_Negative,validation is Done");

		useronboarding_segment1_qrcode.longitudeFieldEmpty_Negative();
		System.out.println("longitudeFieldEmpty_Negative,validation is Done");

		useronboarding_segment1_qrcode.advertisingIdFieldEmpty_Negative();
		System.out.println("advertisingIdFieldEmpty_Negative,validation is Done");

		useronboarding_segment1_qrcode.androidIdFieldEmpty_Negative();
		System.out.println("androidIdFieldEmpty_Negative,validation is Done");

		useronboarding_segment1_qrcode.globalDeviceIdFieldEmpty_Negative();
		System.out.println("globalDeviceIdFieldEmpty_Negative,validation is Done");

		useronboarding_segment1_qrcode.latitudeAndLongitudeFieldEmpty_Negative();
		System.out.println("latitudeAndLongitudeFieldEmpty_Negative,validation is Done");

		useronboarding_segment1_qrcode.latitudeFieldWithAlphaNumericKeywords_Negative();
		System.out.println("latitudeFieldWithAlphaNumericKeywords_Negative,validation is Done");

		useronboarding_segment1_qrcode.userOnbording_Positive();
		System.out.println("userOnbordingWithValidField_Positive,validation is Done");


		//		CreateBnplTransaction
		ExtentReporter.HeaderChildNode("CreateBnplTransaction (user-gateway)");

		createbnpl_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction();

		createbnpl_segment1_qrcode.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");
		createbnpl_segment1_qrcode.sourceFieldEmptyBnpl_Negative();
		System.out.println("sourceFieldEmptyBnpl_Negative,validation is Done");
		createbnpl_segment1_qrcode.globalDeviceIdFieldEmptyBnpl_Negative();
		System.out.println("globalDeviceIdFieldEmptyBnpl_Negative,validation is Done");
		createbnpl_segment1_qrcode.productNameFieldEmptyBnpl_Negative();
		System.out.println("productNameFieldEmptyBnpl_Negative,validation is Done");
		createbnpl_segment1_qrcode.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");



		//		UpdateUserStatus
		ExtentReporter.HeaderChildNode("UpdateUserStatus (user-gateway)");

		updateuserstatus_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Update_User_Status();

		updateuserstatus_segment1_qrcode.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");



		//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====


		//		CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility (txn-gateway)");

		basic_eligibility_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
		addaddress_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Add_Address();

//		basic_eligibility_segment1_qrcode.checkApplicationEligibility_Positive();
//		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		basic_eligibility_segment1_qrcode.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		addaddress_segment1_qrcode.addAddress_Positive();
		basic_eligibility_segment1_qrcode.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");



		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress (user-gateway)");

		addaddress_segment1_qrcode= new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Add_Address();

		addaddress_segment1_qrcode.addAddress_Positive();
		System.out.println("addAddress_Positive,validation is Done");
		addaddress_segment1_qrcode.line1FieldIsEmpty_Negative();
		System.out.println("line1FieldIsEmpty_Negative,validation is Done");
		addaddress_segment1_qrcode.pincodeFieldIsEmpty_Negative();
		System.out.println("pincodeFieldIsEmpty_Negative,validation is Done");
		addaddress_segment1_qrcode.labelFieldIsEmpty_Negative();
		System.out.println("labelFieldIsEmpty_Negative,validation is Done");
		addaddress_segment1_qrcode.tagFieldIsEmpty_Negative();
		System.out.println("tagFieldIsEmpty_Negative,validation is Done");
		addaddress_segment1_qrcode.sourceFieldIsEmpty_Negative();
		System.out.println("sourceFieldIsEmpty_Negative,validation is Done");
		addaddress_segment1_qrcode.invalidSourceField_Negative();
		System.out.println("invalidSourceField_Negative,validation is Done");
		addaddress_segment1_qrcode.productNameFieldIsEmpty_Negative();
		System.out.println("productNameFieldIsEmpty_Negative,validation is Done");




		//	===================================== OFFER_DETAILS_SCREEN =======================================


		//		get_Offer_Details
		ExtentReporter.HeaderChildNode("get_Offer_Details (txn-gateway)");

		getoffer_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Get_Offer();
		getoffer_segment1_qrcode.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");



		//		accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer (txn-gateway)");

		userconcent_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_User_Concent();
		userconcent_segment1_qrcode.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility (txn-gateway)");

		offer_eligibility_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility();

		offer_eligibility_segment1_qrcode.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais (user-gateway)");

		get_pin_detais_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		get_pin_detais_segment1_qrcode.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin (user-gateway)");

		sendotpforpin_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin();

		sendotpforpin_segment1_qrcode.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//		resetpin
		ExtentReporter.HeaderChildNode("resetpin (user-gateway)");

		resetpin_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Set_Reset_Pin();
		get_pin_detais_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		resetpin_segment1_qrcode.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		get_pin_detais_segment1_qrcode.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais (user-gateway)");

		get_pin_detais_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		get_pin_detais_segment1_qrcode.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines (user-gateway)");

		bnpl_lines_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Bnpl_Lines();

		bnpl_lines_segment1_qrcode.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");



		// payment_option
		ExtentReporter.HeaderChildNode("payment_option (user-gateway)");

		payment_option_segment1_qrcode= new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Payment_Option();

		payment_option_segment1_qrcode.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");
		payment_option_segment1_qrcode.reasonFieldIsEmpty_Negative();
		System.out.println("reasonFieldIsEmpty_Negative,validation is Done");
		payment_option_segment1_qrcode.actualAmountFieldIsEmpty_Negative();
		System.out.println("actualAmountFieldIsEmpty_Negative,validation is Done");
		payment_option_segment1_qrcode.qr_CodeFieldIsEmpty_Negative();
		System.out.println("qr_CodeFieldIsEmpty_Negative,validation is Done");
		payment_option_segment1_qrcode.qr_CodeFieldWithIncorrectVPA_Negative();
		System.out.println("qr_CodeFieldWithIncorrectVPA_Negative,validation is Done");
		payment_option_segment1_qrcode.qr_CodeFieldWithInvalidCode_Negative();
		System.out.println("qr_CodeFieldWithInvalidCode_Negative,validation is Done");
		payment_option_segment1_qrcode.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");



		//		txn_initiated
		ExtentReporter.HeaderChildNode("txn_initiated (user-gateway)");

		txn_initiated_segment1_qrcode= new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Initiate();

		txn_initiated_segment1_qrcode.transactionInitiate_Positive();
		System.out.println("transaction_Initiate,validation is Done");

		txn_initiated_segment1_qrcode.productValueEmptyField_Negative();
		System.out.println("productValueEmptyField_Negative,validation is Done");

		txn_initiated_segment1_qrcode.productValueFieldWithAlphaNumericCharacters_Negative();
		System.out.println("productValueFieldWithAlphaNumericCharacters_Negative,validation is Done");

		txn_initiated_segment1_qrcode.transactionTypeFieldEmpty_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");

		txn_initiated_segment1_qrcode.transactionTypeFieldWithInvalidValue_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");

		txn_initiated_segment1_qrcode.merchantOrder_Id_FieldEmpty_Negative();
		System.out.println("merchantOrder_Id_FieldEmpty_Negative,validation is Done");

		txn_initiated_segment1_qrcode.upiHandleReferenceNumberFieldEmpty_Negative();
		System.out.println("upiHandleReferenceNumberFieldEmpty_Negative,validation is Done");

		txn_initiated_segment1_qrcode.latitudeField_Empty_Negative();
		System.out.println("latitudeField_Empty_Negative,validation is Done");

		txn_initiated_segment1_qrcode.longitudeField_Empty_Negative();
		System.out.println("longitudeField_Empty_Negative,validation is Done");

		txn_initiated_segment1_qrcode.latitudeAndLongitudeField_Empty_Negative();
		System.out.println("latitudeAndLongitudeField_Empty_Negative,validation is Done");




		// txn_complete
		ExtentReporter.HeaderChildNode("txn_complete (user-gateway)");
		txn_complete_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Complete();

		txn_complete_segment1_qrcode.transactionComplete();
		System.out.println("transactionComplete,validation is Done");




		// ==================================  Repayment  ==============================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends (user-gateway)");

		current_Spends_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends();

		current_Spends_segment1_qrcode.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");



		// validate
		ExtentReporter.HeaderChildNode("validate (external-gateway)");

		validate_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Validate();

		validate_segment1_qrcode.validate();
		System.out.println("paymentValidate,validation is Done");



		//		notify
		ExtentReporter.HeaderChildNode("notify (external-gateway)");

		notify_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Notify();

		notify_segment1_qrcode.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");



		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement (user-gateway)");

		getsettlement_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.TransactionDetails_Get_Settlement_Status();
		getsettlement_segment1_qrcode.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");


		// delete
		ExtentReporter.HeaderChildNode("Delete_MobileNumber");

		delete_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.DeleteQuery();
		delete_segment1_qrcode.delete();
		System.out.println("deleteMobileNumber,validation is Done");




	}



	//	================================================================================================
	//	================================================================================================
	//	================================================================================================



	//	@Test(priority = 1)
	//	public void onloadAndroidVersionCheckAPI() throws Exception {
	//		registerUser_Onload=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Onload();
	//		registerUser_Onload.onload_Positive();
	//		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");
	//
	//	}
	//
	//
	//	@Test(priority = 2)
	//	public void getVPADetails() throws Exception {
	//
	//		getvpa=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Get_Details_VPA();
	//
	//		getvpa.get_Details_Vpa_Positive();
	//		System.out.println("getVPADetails,Validation is Done");
	//	}
	//
	//
	//	@Test(priority = 3)
	//	public void sendOtp_Positive() throws Exception {
	//
	//		sendotp=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_OTPSend();
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
	//	public void userAuthenticate() throws Exception {
	//
	//		userauthenticate=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate();
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
	//		login=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Login();
	//
	//		login.login_Positive();
	//		System.out.println("loginUser,validation is Done");
	//	}
	//
	//
	//	@Test(priority = 6)
	//	public void RingPolicy() throws Exception{
	//		s1=new com.business.RingPay_RingPolicy.Segment1();
	//		s1.Segment1();
	//		System.out.println("RingPolicy_Segment1,validation is Done");
	//	}
	//
	//
	//
	//	@Test(priority = 7)
	//	public void registeruser() throws Exception {
	//
	//		registeruser=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Register_User();
	//		System.out.println("registeruser,validation is Done");
	//
	//		registeruser.registerUserAfterLogin_Positive();
	//		System.out.println("registeruser,validation is Done");
	//
	//	}
	//
	//	@Test(priority = 8)
	//	public void basicDetils() throws Exception {
	//
	//		basicdetails=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_BasicDetails();
	//
	//
	//		basicdetails.basicDetails_Positive_SchemaValiadtion();
	//		System.out.println("ubasicdetails_Positive_SchemaValiadtion,Schema validation");
	//		//		basicdetails.alphaNumericInFirstNameField_Negative();
	//		//		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
	//		//		basicdetails.specialCharacterInFirstNameField_Negative();
	//		//		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");
	//		//
	//		//		basicdetails.spaceInFirstNameField_Negative();
	//		//		System.out.println("spaceInFirstNameField_Negative,validation is Done");
	//		//
	//		//		basicdetails.basicDetails_Positive();
	//		//		System.out.println("updateUser_Positive,validation is Done");
	//		//
	//		//		basicdetails.alphaNumericInLastNameField_Negative();
	//		//		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");
	//		//
	//		//		basicdetails.specialCharacterInLastNameField_Negative();
	//		//		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");
	//		//
	//		//		basicdetails.spaceInLastNameField_Negative();
	//		//		System.out.println("spaceInLastNameField_Negative,validation is Done");
	//		//
	//		//		basicdetails.basicDetails_Positive();
	//		//		System.out.println("updateUser_Positive,validation is Done");
	//		//
	//		//		basicdetails.invalidEmailId_Negative();
	//		//		System.out.println("invalidEmailId_Negative,validation is Done");
	//		//
	//		//		basicdetails.spaceInEmailIdField_Negative();
	//		//		System.out.println("spaceInEmailIdField_Negative,validation is Done");
	//		//
	//		//		basicdetails.basicDetails_Positive();
	//		//		System.out.println("updateUser_Positive,validation is Done");
	//
	//
	//	}
	//
	//
	//	//	@Test(priority = 9)
	//	//	public void locationRequire() throws Exception {
	//	//
	//	//		locaterequire=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Location_Require();
	//	//
	//	//		locaterequire.location_Require();
	//	//		System.out.println("locationRequire,validation is Done");
	//	//
	//	//
	//	//	}
	//	//
	//	//
	//	//		//	 ====================== Basic_Detail_Screen =================================
	//	//
	//	//	@Test(priority = 10)
	//	//	public void getUserDetails_Positive() throws Exception {
	//	//
	//	//		getuserdetails=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Get_User_Detail();
	//	//
	//	//		getuserdetails.getUserDetails_Positive();
	//	//		System.out.println("getUserDetails_Positive,validation is Done");
	//	//
	//	//
	//	//	}
	//	//
	//	//
	//	//	@Test(priority = 11)
	//	//	public void user_onboarding_200() throws Exception {
	//	//
	//	//		useronboarding=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_User_Onboarding();
	//	//
	//	//		useronboarding.userOnbording_Positive();
	//	//		System.out.println("userOnbording_Positive,validation is Done");
	//	//
	//	////		useronboarding.latitudeFieldEmpty_Negative();
	//	////		System.out.println("latitudeFieldEmpty_Negative,validation is Done");
	//	////
	//	////		useronboarding.longitudeFieldEmpty_Negative();
	//	////		System.out.println("longitudeFieldEmpty_Negative,validation is Done");
	//	////
	//	////		useronboarding.advertisingIdFieldEmpty_Negative();
	//	////		System.out.println("advertisingIdFieldEmpty_Negative,validation is Done");
	//	////
	//	////		useronboarding.androidIdFieldEmpty_Negative();
	//	////		System.out.println("androidIdFieldEmpty_Negative,validation is Done");
	//	////
	//	////		useronboarding.globalDeviceIdFieldEmpty_Negative();
	//	////		System.out.println("globalDeviceIdFieldEmpty_Negative,validation is Done");
	//	////
	//	////		useronboarding.latitudeAndLongitudeFieldEmpty_Negative();
	//	////		System.out.println("latitudeAndLongitudeFieldEmpty_Negative,validation is Done");
	//	////
	//	////		useronboarding.latitudeFieldWithAlphaNumericKeywords_Negative();
	//	////		System.out.println("latitudeFieldWithAlphaNumericKeywords_Negative,validation is Done");
	//	////
	//	////		useronboarding.userOnbording_Positive();
	//	////		System.out.println("userOnbordingWithValidField_Positive,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 12)
	//	//	public void create_bnpl_transaction() throws Exception {
	//	//
	//	//		createbnpl=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction();
	//	//
	//	//		createbnpl.getApplicationToken_Positive();
	//	//		System.out.println("getApplicationToken_Positive,validation is Done");
	//	////		createbnpl.sourceFieldEmptyBnpl_Negative();
	//	////		System.out.println("sourceFieldEmptyBnpl_Negative,validation is Done");
	//	////		createbnpl.globalDeviceIdFieldEmptyBnpl_Negative();
	//	////		System.out.println("globalDeviceIdFieldEmptyBnpl_Negative,validation is Done");
	//	////		createbnpl.productNameFieldEmptyBnpl_Negative();
	//	////		System.out.println("productNameFieldEmptyBnpl_Negative,validation is Done");
	//	////		createbnpl.getApplicationToken_Positive();
	//	////		System.out.println("getApplicationToken_Positive,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 13)
	//	//	public void update_user_status_200() throws Exception {
	//	//
	//	//		updateuserstatus=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Update_User_Status();
	//	//
	//	//		updateuserstatus.updateUserStatus_Positive();
	//	//		System.out.println("updateuserstatus,validation is Done");
	//	//
	//	//	}
	//	//
	//	//
	//	//	//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====
	//	//
	//	//	@Test(priority = 14)
	//	//	public void check_Application_Eligibility() throws Exception {
	//	//
	//	//		basic_eligibility=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
	//	//		addaddress=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Add_Address();
	//	//
	//	//		basic_eligibility.checkApplicationEligibility_Positive();
	//	//		System.out.println("checkApplicationEligibility_Positive,validation is Done");
	//	//		basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
	//	//		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");
	//	//
	//	//		addaddress.addAddress_Positive();
	//	//		basic_eligibility.checkApplicationEligibility_Positive();
	//	//		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");
	//	//
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 15)
	//	//	public void add_addressAPI() throws Exception {
	//	//
	//	//		addaddress=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Add_Address();
	//	//		//		basic_eligibility=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
	//	//		//		addaddress.addAddress_Positive();
	//	//
	//	//		addaddress.addAddress_Positive();
	//	//		System.out.println("addAddress_Positive,validation is Done");
	//	////		addaddress.line1FieldIsEmpty_Negative();
	//	////		System.out.println("line1FieldIsEmpty_Negative,validation is Done");
	//	////		addaddress.pincodeFieldIsEmpty_Negative();
	//	////		System.out.println("pincodeFieldIsEmpty_Negative,validation is Done");
	//	////		addaddress.labelFieldIsEmpty_Negative();
	//	////		System.out.println("labelFieldIsEmpty_Negative,validation is Done");
	//	////		addaddress.tagFieldIsEmpty_Negative();
	//	////		System.out.println("tagFieldIsEmpty_Negative,validation is Done");
	//	////		addaddress.sourceFieldIsEmpty_Negative();
	//	////		System.out.println("sourceFieldIsEmpty_Negative,validation is Done");
	//	////		addaddress.invalidSourceField_Negative();
	//	////		System.out.println("invalidSourceField_Negative,validation is Done");
	//	////		addaddress.productNameFieldIsEmpty_Negative();
	//	////		System.out.println("productNameFieldIsEmpty_Negative,validation is Done");
	//	//
	//	//
	//	//	}
	//
	//
	//	//	//	===================================== OFFER_DETAILS_SCREEN =======================================
	//	//
	//	//	@Test(priority = 16)
	//	//	public void get_Offer_Details() throws Exception {
	//	//
	//	//		getoffer=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Get_Offer();
	//	//
	//	//		getoffer.get_Offer();
	//	//		System.out.println("get_Offer_Details,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 17)
	//	//	public void accept_Offer() throws Exception {
	//	//
	//	//		userconcent=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_User_Concent();
	//	//
	//	//		userconcent.acceptOffer();
	//	//		System.out.println("accept_Offer,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 18)
	//	//	public void offerDetailsScreen_CheckApplicationEligibility() throws Exception {
	//	//
	//	//		offer_eligibility=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility();
	//	//
	//	//		offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
	//	//		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");
	//	//
	//	//	}
	//	//
	//	//
	//	//	//	===================================== PIN_DETAILS_SCREEN =======================================
	//	//
	//	//	@Test(priority = 19)
	//	//	public void getPinDetails() throws Exception {
	//	//
	//	//		get_pin_detais=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();
	//	//
	//	//		get_pin_detais.getPinDetails_Positive();
	//	//		System.out.println("getPinDetails,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 20)
	//	//	public void sendOtpForPin() throws Exception {
	//	//
	//	//		sendotpforpin=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin();
	//	//
	//	//		sendotpforpin.sendOtpForPin_Positive();
	//	//		System.out.println("sendOtpForPin,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 21)
	//	//	public void setResetPin() throws Exception {
	//	//
	//	//		resetpin=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Set_Reset_Pin();
	//	//		get_pin_detais=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();
	//	//
	//	//		resetpin.setResetPin_Positive();
	//	//		System.out.println("setResetPin,validation is Done");
	//	//
	//	//		get_pin_detais.afterResetPin_getPinDetails_Positive();
	//	//		System.out.println("AfterResetAPI_SetResetPin,validation is Done");
	//	//
	//	//	}
	//	//
	//	//
	//	//	@Test(priority = 22)
	//	//	public void afterResetPin_getPinDetails() throws Exception {
	//	//
	//	//		get_pin_detais=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();
	//	//
	//	//		get_pin_detais.afterResetPin_getPinDetails_Positive();
	//	//		System.out.println("AfterResetAPI_SetResetPin,validation is Done");
	//	//
	//	//	}
	//	//
	//	//
	//	//	//	====================================== BNPL_TXN =======================================
	//	//
	//	//	@Test(priority = 23)
	//	//	public void bnplLines() throws Exception {
	//	//
	//	//		bnpl_lines=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Bnpl_Lines();
	//	//
	//	//		bnpl_lines.bnpl_Lines();
	//	//		System.out.println("bnpl_lines,validation is Done");
	//	//
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 24)
	//	//	public void paymentOption() throws Exception {
	//	//
	//	//		payment_option=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Payment_Option();
	//	//
	//	//		payment_option.paymentOption_Positive();
	//	//		System.out.println("payment_option,validation is Done");
	//	////		payment_option.reasonFieldIsEmpty_Negative();
	//	////		System.out.println("reasonFieldIsEmpty_Negative,validation is Done");
	//	////		payment_option.actualAmountFieldIsEmpty_Negative();
	//	////		System.out.println("actualAmountFieldIsEmpty_Negative,validation is Done");
	//	////		payment_option.qr_CodeFieldIsEmpty_Negative();
	//	////		System.out.println("qr_CodeFieldIsEmpty_Negative,validation is Done");
	//	////		payment_option.qr_CodeFieldWithIncorrectVPA_Negative();
	//	////		System.out.println("qr_CodeFieldWithIncorrectVPA_Negative,validation is Done");
	//	////		payment_option.qr_CodeFieldWithInvalidCode_Negative();
	//	////		System.out.println("qr_CodeFieldWithInvalidCode_Negative,validation is Done");
	//	////		payment_option.paymentOption_Positive();
	//	////		System.out.println("payment_option,validation is Done");
	//	//	}
	//	//
	//	//	@Test(priority = 25)
	//	//	public void transaction_Initiate() throws Exception {
	//	//
	//	//		txn_initiated=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Initiate();
	//	//
	//	//		Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();
	//	//		System.out.println("transaction_Initiate,validation is Done");
	//	//
	//	////		txn_initiated.productValueEmptyField_Negative();
	//	////		System.out.println("productValueEmptyField_Negative,validation is Done");
	//	////
	//	////		txn_initiated.productValueFieldWithAlphaNumericCharacters_Negative();
	//	////		System.out.println("productValueFieldWithAlphaNumericCharacters_Negative,validation is Done");
	//	////
	//	////		txn_initiated.transactionTypeFieldEmpty_Negative();
	//	////		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");
	//	////
	//	////		txn_initiated.transactionTypeFieldWithInvalidValue_Negative();
	//	////		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");
	//	////
	//	////		txn_initiated.merchantOrder_Id_FieldEmpty_Negative();
	//	////		System.out.println("merchantOrder_Id_FieldEmpty_Negative,validation is Done");
	//	////
	//	////		txn_initiated.upiHandleReferenceNumberFieldEmpty_Negative();
	//	////		System.out.println("upiHandleReferenceNumberFieldEmpty_Negative,validation is Done");
	//	////
	//	////		txn_initiated.latitudeField_Empty_Negative();
	//	////		System.out.println("latitudeField_Empty_Negative,validation is Done");
	//	////
	//	////		txn_initiated.longitudeField_Empty_Negative();
	//	////		System.out.println("longitudeField_Empty_Negative,validation is Done");
	//	////
	//	////		txn_initiated.latitudeAndLongitudeField_Empty_Negative();
	//	////		System.out.println("latitudeAndLongitudeField_Empty_Negative,validation is Done");
	//	//
	//	//
	//	//	}
	//	//
	//	//
	//	//	@Test(priority = 26)
	//	//	public void transactionComplete() throws Exception {
	//	//
	//	//		txn_complete=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Complete();
	//	//
	//	//		txn_complete.transactionComplete();
	//	//		System.out.println("transactionComplete,validation is Done");
	//	//
	//	//
	//	//	}
	//	//	
	//	//	
	//	//// ==================================  Repayment  ==============================
	//	//	
	//	//	@Test(priority = 27)
	//	//	public void homeScreenForCurrentSpends() throws Exception {
	//	//
	//	//		current_Spends=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends();
	//	//
	//	//		current_Spends.current_Spent();
	//	//		System.out.println("homeScreenForCurrentSpends,validation is Done");
	//	//
	//	//	}
	//	//
	//	//	@Test(priority = 28)
	//	//	public void paymentValidate() throws Exception {
	//	//
	//	//		validate=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Validate();
	//	//
	//	//		validate.validate();
	//	//		System.out.println("paymentValidate,validation is Done");
	//	//
	//	//	}
	//	//
	//	//
	//	//	@Test(priority = 29)
	//	//	public void notifyPaymentDone() throws Exception {
	//	//
	//	//		notify=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Notify();
	//	//
	//	//		notify.notifyPaymentDone();
	//	//		System.out.println("notifyPaymentDone,validation is Done");
	//	//
	//	//	}
	//	//
	//	//
	//	//	@Test(priority = 30)
	//	//	public void getSettlementStatus() throws Exception {
	//	//
	//	//		getsettlement=new com.business.RingPay_MerchantQRCode_Journey_Segment1.TransactionDetails_Get_Settlement_Status();
	//	//		getsettlement.getSettlementStatus();
	//	//		System.out.println("getSettlementStatus,validation is Done");
	//	//
	//	//	}
	//
	//
	//	@Test(priority = 31)
	//	public void deleteMobileNumber() throws Exception {
	//
	//		delete=new com.business.RingPay_MerchantQRCode_Journey_Segment1.DeleteQuery();
	//		delete.delete();
	//		System.out.println("deleteMobileNumber,validation is Done");
	//
	//	}



}

