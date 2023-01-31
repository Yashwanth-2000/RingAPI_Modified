package com.RingPayScripts;

import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.business.RingPay.URI.RingPay_BaseURL;
import com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Transaction_Initiate;
import com.utility.ExtentReporter;
import com.utility.Utilities;

public class PromoCode_Segment1_Scripts {


	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Mock_User mockuser;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Onload onload;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_SendOtp_Segment1 sendotp;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate userauthenticate;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_PromoCode promocode;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_BasicDetails_Segment1 basicdetails;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Login login;
	private com.business.RingPay_RingPolicy.Segment1_PromoCode s1_segment1_promocode;

	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Register_User registeruser;
	private com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Location_Require locaterequire;

	private com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Get_User_Detail getuserdetails;
	private com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_User_Onboarding useronboarding;
	private com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction createbnpl;
	private com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Update_User_Status updateuserstatus;
	private com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Add_Address addaddress;
	private com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility basic_eligibility;
	private com.business.RingPay_PromoCode_Journey_Segment1.OfferDetailsScreen_Get_Offer getoffer ;
	private com.business.RingPay_PromoCode_Journey_Segment1.OfferDetailsScreen_User_Concent userconcent  ;
	private com.business.RingPay_PromoCode_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility offer_eligibility;
	private com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Bnpl_Lines bnpl_lines;
	private com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Payment_Option payment_option;
	private com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Transaction_Initiate txn_initiated;
	private com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Transaction_Complete txn_complete;
	private com.business.RingPay_PromoCode_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends current_Spends;
	private com.business.RingPay_PromoCode_Journey_Segment1.Repayment_Validate validate;
	private com.business.RingPay_PromoCode_Journey_Segment1.Repayment_Notify notify;
	private com.business.RingPay_PromoCode_Journey_Segment1.TransactionDetails_Get_Settlement_Status getsettlement;
	private com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details get_pin_detais;
	private com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin sendotpforpin;
	private com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Set_Reset_Pin resetpin;

	private com.business.RingPay_PromoCode_Journey_Segment1.DeleteQuery_PromoCode delete_segment1_promocode;


	@Test(priority = 0)
	public void PromoCode_Journey_Segment1() throws Exception {

//
				//		Delete
				ExtentReporter.HeaderChildNode("Delete_MobileNumber");
		
				delete_segment1_promocode=new com.business.RingPay_PromoCode_Journey_Segment1.DeleteQuery_PromoCode();
				delete_segment1_promocode.delete_before();
				System.out.println("deleteMobileNumber,validation is Done");


		//		mockuser
		ExtentReporter.HeaderChildNode("MockUser (testing-service)");

		mockuser=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Mock_User();
		mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");


		//		Onload
		ExtentReporter.HeaderChildNode("Onload (user-gateway)");

		onload=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Onload();
		onload.onload_Positive();
		System.out.println("onloadAPI,Validation is Done");



		//		sendOtp
		ExtentReporter.HeaderChildNode("SendOtp (user-gateway)");

		sendotp=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_SendOtp_Segment1();

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

		userauthenticate=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate();

		userauthenticate.userToken_PromoCode_S1();
		System.out.println("userToken_Positive,validation is Done");
		userauthenticate.invalidOtp_Negative();
		System.out.println("invalidOtp_Negative is Done");
		userauthenticate.expiredOtp_Negative();
		System.out.println("expiredOtp_Negative is Done");
		userauthenticate.alphabetInOtpField_Negative();
		System.out.println("alphabetInOtpField_Negative is Done");
		userauthenticate.lessThan6DigitsNoInOtpField_Negative();
		System.out.println("lessThan6DigitsNoInOtpField_Negative is Done");
		userauthenticate.userToken_PromoCode_S1_Repeat();
		System.out.println("userToken_Positive,validation is Done");

		//		promocode
		ExtentReporter.HeaderChildNode("promocode (user-gateway)");

		promocode=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_PromoCode();
		promocode.promo_Code();
		System.out.println("promoCode,Validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login (user-gateway)");

		login=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Login();
		login.login_Positive();
		System.out.println("loginUser,validation is Done");



		//		Segment1
		ExtentReporter.HeaderChildNode("Segment1 (testing-service)");

		s1_segment1_promocode=new com.business.RingPay_RingPolicy.Segment1_PromoCode();
		s1_segment1_promocode.Segment1();
		System.out.println("RingPolicy_Segment1,validation is Done");



		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser (big-data-python)");

		registeruser=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Register_User();
		registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");



		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails (user-gateway)");

		basicdetails=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_BasicDetails_Segment1();


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

		locaterequire=new com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Location_Require();
		locaterequire.location_Require();
		System.out.println("locationRequire,validation is Done");




		//	 ====================== Basic_Detail_Screen =================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails (user-gateway)");

		getuserdetails=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Get_User_Detail();

		getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");



		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding (user-gateway)");

		useronboarding=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_User_Onboarding();

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

		createbnpl=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction();

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

		updateuserstatus=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Update_User_Status();

		updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");



		//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====


		//		CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility (txn-gateway)");

		basic_eligibility=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
		addaddress=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Add_Address();

		//		basic_eligibility.checkApplicationEligibility_Positive();
		//		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		addaddress.addAddress_Positive();
		basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");



		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress (user-gateway)");

		addaddress=new com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Add_Address();

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



		//		//	===================================== OFFER_DETAILS_SCREEN =======================================


		//		get_Offer_Details
		ExtentReporter.HeaderChildNode("get_Offer_Details (txn-gateway)");

		getoffer=new com.business.RingPay_PromoCode_Journey_Segment1.OfferDetailsScreen_Get_Offer();
		getoffer.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");



		//		accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer (txn-gateway)");

		userconcent=new com.business.RingPay_PromoCode_Journey_Segment1.OfferDetailsScreen_User_Concent();
		userconcent.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility (txn-gateway)");

		offer_eligibility=new com.business.RingPay_PromoCode_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility();
		offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais (user-gateway)");

		get_pin_detais=new com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		get_pin_detais.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin (user-gateway)");

		sendotpforpin=new com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin();

		sendotpforpin.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//		resetpin
		ExtentReporter.HeaderChildNode("resetpin (user-gateway)");

		resetpin=new com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Set_Reset_Pin();
		get_pin_detais=new com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		resetpin.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais (user-gateway)");

		get_pin_detais=new com.business.RingPay_PromoCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines (user-gateway)");

		bnpl_lines=new com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Bnpl_Lines();

		bnpl_lines.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");



		// payment_option
		ExtentReporter.HeaderChildNode("payment_option (user-gateway)");

		payment_option=new com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Payment_Option();

		payment_option.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");
		payment_option.reasonFieldIsEmpty_Negative();
		System.out.println("reasonFieldIsEmpty_Negative,validation is Done");
		payment_option.actualAmountFieldIsEmpty_Negative();
		System.out.println("actualAmountFieldIsEmpty_Negative,validation is Done");
		payment_option.qr_CodeFieldIsEmpty_Negative();
		System.out.println("qr_CodeFieldIsEmpty_Negative,validation is Done");
		payment_option.qr_CodeFieldWithIncorrectVPA_Negative();
		System.out.println("qr_CodeFieldWithIncorrectVPA_Negative,validation is Done");
		payment_option.qr_CodeFieldWithInvalidCode_Negative();
		System.out.println("qr_CodeFieldWithInvalidCode_Negative,validation is Done");
		payment_option.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");



		//		txn_initiated
		ExtentReporter.HeaderChildNode("txn_initiated (user-gateway)");

		txn_initiated=new com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Transaction_Initiate();

		txn_initiated.transactionInitiate_Positive();
		System.out.println("transaction_Initiate,validation is Done");

		txn_initiated.productValueEmptyField_Negative();
		System.out.println("productValueEmptyField_Negative,validation is Done");

		txn_initiated.productValueFieldWithAlphaNumericCharacters_Negative();
		System.out.println("productValueFieldWithAlphaNumericCharacters_Negative,validation is Done");

		txn_initiated.transactionTypeFieldEmpty_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");

		txn_initiated.transactionTypeFieldWithInvalidValue_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");

		txn_initiated.merchantOrder_Id_FieldEmpty_Negative();
		System.out.println("merchantOrder_Id_FieldEmpty_Negative,validation is Done");

		txn_initiated.upiHandleReferenceNumberFieldEmpty_Negative();
		System.out.println("upiHandleReferenceNumberFieldEmpty_Negative,validation is Done");

		txn_initiated.latitudeField_Empty_Negative();
		System.out.println("latitudeField_Empty_Negative,validation is Done");

		txn_initiated.longitudeField_Empty_Negative();
		System.out.println("longitudeField_Empty_Negative,validation is Done");

		txn_initiated.latitudeAndLongitudeField_Empty_Negative();
		System.out.println("latitudeAndLongitudeField_Empty_Negative,validation is Done");




		// txn_complete
		ExtentReporter.HeaderChildNode("txn_complete (user-gateway)");
		txn_complete=new com.business.RingPay_PromoCode_Journey_Segment1.Bnpl_Txn_Transaction_Complete();

		txn_complete.transactionComplete();
		System.out.println("transactionComplete,validation is Done");



		// ==================================  Repayment  ==============================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends (user-gateway)");

		current_Spends=new com.business.RingPay_PromoCode_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends();
		current_Spends.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");



		// validate
		ExtentReporter.HeaderChildNode("validate (external-gateway)");

		validate=new com.business.RingPay_PromoCode_Journey_Segment1.Repayment_Validate();
		validate.validate();
		System.out.println("paymentValidate,validation is Done");



		//		notify
		ExtentReporter.HeaderChildNode("notify (external-gateway)");

		notify=new com.business.RingPay_PromoCode_Journey_Segment1.Repayment_Notify();

		notify.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");



		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement (user-gateway)");

		getsettlement=new com.business.RingPay_PromoCode_Journey_Segment1.TransactionDetails_Get_Settlement_Status();
		getsettlement.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");


		//		Delete
		ExtentReporter.HeaderChildNode("Delete_MobileNumber");

		delete_segment1_promocode=new com.business.RingPay_PromoCode_Journey_Segment1.DeleteQuery_PromoCode();
		delete_segment1_promocode.delete();
		System.out.println("deleteMobileNumber,validation is Done");



	}


}
