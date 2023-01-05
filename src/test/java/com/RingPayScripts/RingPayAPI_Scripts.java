package com.RingPayScripts;

import org.testng.annotations.Test;

import com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Initiate;
import com.utility.ExtentReporter;

public class RingPayAPI_Scripts {

	
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Mock_User mockuser;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Onload registerUser_Onload;

	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Get_Details_VPA getvpa;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_OTPSend sendotp;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate userauthenticate;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_BasicDetails basicdetails;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Login login;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Register_User registeruser;
	private com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Location_Require locaterequire;
	private com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Get_User_Detail getuserdetails;
	private com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_User_Onboarding useronboarding;
	private com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction createbnpl;
	private com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Update_User_Status updateuserstatus;
	private com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Add_Address addaddress;
	private com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Check_Application_Eligibility basic_eligibility;
	private com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_Get_Offer getoffer ;
	private com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_User_Concent userconcent  ;
	private com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_Check_Application_Eligibility offer_eligibility;
	private com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Bnpl_Lines bnpl_lines;
	private com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Payment_Option payment_option;
	private com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Initiate txn_initiated;
	private com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Complete txn_complete;
	private com.business.RingPay_MerchantQRCode_Journey.Repayment_Home_Screen_For_Current_Spends current_Spends;
	private com.business.RingPay_MerchantQRCode_Journey.Repayment_Validate validate;
	private com.business.RingPay_MerchantQRCode_Journey.Repayment_Notify notify;
	private com.business.RingPay_MerchantQRCode_Journey.TransactionDetails_Get_Settlement_Status getsettlement;
	private com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Get_Pin_Details get_pin_detais;
	private com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Send_Otp_For_Pin sendotpforpin;
	private com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Set_Reset_Pin resetpin;

	
//	-------------------------------------------------------------------------------------------------------------
	
	private com.business.RingPay_PromoCode_Journey.RegisterUser_Mock_User promo_mockuser;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_Onload promo_onload;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_OTPSend promo_sendotp;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate promo_userauthenticate;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_PromoCode promo_promocode;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_BasicDetails promo_basicdetails;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_Login promo_login;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_Register_User promo_registeruser;
	private com.business.RingPay_PromoCode_Journey.RegisterUser_Location_Require promo_locaterequire;

	private com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Get_User_Detail promo_getuserdetails;
	private com.business.RingPay_PromoCode_Journey.BasicDetailScreen_User_Onboarding promo_useronboarding;
	private com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction promo_createbnpl;
	private com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Update_User_Status promo_updateuserstatus;
	private com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Add_Address promo_addaddress;
	private com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Check_Application_Eligibility promo_basic_eligibility;
	private com.business.RingPay_PromoCode_Journey.OfferDetailsScreen_Get_Offer promo_getoffer ;
	private com.business.RingPay_PromoCode_Journey.OfferDetailsScreen_User_Concent promo_userconcent  ;
	private com.business.RingPay_PromoCode_Journey.OfferDetailsScreen_Check_Application_Eligibility promo_offer_eligibility;
	private com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Bnpl_Lines promo_bnpl_lines;
	private com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Payment_Option promo_payment_option;
	private com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Transaction_Initiate promo_txn_initiated;
	private com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Transaction_Complete promo_txn_complete;
	private com.business.RingPay_PromoCode_Journey.Repayment_Home_Screen_For_Current_Spends promo_current_Spends;
	private com.business.RingPay_PromoCode_Journey.Repayment_Validate promo_validate;
	private com.business.RingPay_PromoCode_Journey.Repayment_Notify promo_notify;
	private com.business.RingPay_PromoCode_Journey.TransactionDetails_Get_Settlement_Status promo_getsettlement;
	private com.business.RingPay_PromoCode_Journey.PinDetailScreen_Get_Pin_Details promo_get_pin_detais;
	private com.business.RingPay_PromoCode_Journey.PinDetailScreen_Send_Otp_For_Pin promo_sendotpforpin;
	private com.business.RingPay_PromoCode_Journey.PinDetailScreen_Set_Reset_Pin promo_resetpin;

	
//	------------------------------------------------------------------------------------------------------------------
	
	
	private com.business.RingPay_PlayStore_Journey.RegisterUser_Mock_User play_mockuser;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_Onload play_onload;

	//	private com.business.RingPay_PlayStore_Journey.RegisterUser_Get_Details_VPA getvpa;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_OTPSend play_sendotp;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate play_userauthenticate;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_BasicDetails play_basicdetails;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_Login play_login;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_Register_User play_registeruser;
	private com.business.RingPay_PlayStore_Journey.RegisterUser_Location_Require play_locaterequire;
	private com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Get_User_Detail play_getuserdetails;
	private com.business.RingPay_PlayStore_Journey.BasicDetailScreen_User_Onboarding play_useronboarding;
	private com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Create_Bnpl_Transaction play_createbnpl;
	private com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Update_User_Status play_updateuserstatus;
	private com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Add_Address play_addaddress;
	private com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Check_Application_Eligibility play_basic_eligibility;
	private com.business.RingPay_PlayStore_Journey.OfferDetailsScreen_Get_Offer play_getoffer ;
	private com.business.RingPay_PlayStore_Journey.OfferDetailsScreen_User_Concent play_userconcent  ;
	private com.business.RingPay_PlayStore_Journey.OfferDetailsScreen_Check_Application_Eligibility play_offer_eligibility;
	private com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Bnpl_Lines play_bnpl_lines;
	private com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Payment_Option play_payment_option;
	private com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Transaction_Initiate play_txn_initiated;
	private com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Transaction_Complete play_txn_complete;
	private com.business.RingPay_PlayStore_Journey.Repayment_Home_Screen_For_Current_Spends play_current_Spends;
	private com.business.RingPay_PlayStore_Journey.Repayment_Validate play_validate;
	private com.business.RingPay_PlayStore_Journey.Repayment_Notify play_notify;
	private com.business.RingPay_PlayStore_Journey.TransactionDetails_Get_Settlement_Status play_getsettlement;
	private com.business.RingPay_PlayStore_Journey.PinDetailScreen_Get_Pin_Details play_get_pin_detais;
	private com.business.RingPay_PlayStore_Journey.PinDetailScreen_Send_Otp_For_Pin play_sendotpforpin;
	private com.business.RingPay_PlayStore_Journey.PinDetailScreen_Set_Reset_Pin play_resetpin;

	
//	-----------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Mock_User mockuser_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Onload onload_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Get_Details_VPA getvpa_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_OTPSend sendotp_segment1_qrcode;
	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate userauthenticate_segment1_qrcode;

	private com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Login login_segment1_qrcode;

	private com.business.RingPay_RingPolicy.Segment1 s1_segment1_qrcode;
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
	
//	@Test(enabled = false)
	
	public void MerChantQRCode_Journey() throws Exception {

		
		//		mockuser
		ExtentReporter.HeaderChildNode("MockUser");

		mockuser=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Mock_User();
		mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");


		//		Onload
		ExtentReporter.HeaderChildNode("Onload");

		registerUser_Onload=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Onload();
		registerUser_Onload.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");


		//		Get_VPA
		ExtentReporter.HeaderChildNode("Get_VPA");

		getvpa=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Get_Details_VPA();
		getvpa.get_Details_Vpa_Positive();
		System.out.println("getVPADetails,Validation is Done");


		//		sendOtp
		ExtentReporter.HeaderChildNode("SendOtp");

		sendotp=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_OTPSend();

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
		ExtentReporter.HeaderChildNode("UserAuthenticate");

		userauthenticate=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate();

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
		userauthenticate.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login");

		login=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Login();
		login.login_Positive();
		System.out.println("loginUser,validation is Done");



		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser");

		registeruser=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Register_User();
		registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");

		

		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails");

		basicdetails=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_BasicDetails();


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
		ExtentReporter.HeaderChildNode("LocateRequire");

		locaterequire=new com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Location_Require();

		locaterequire.location_Require();
		System.out.println("locationRequire,validation is Done");




		//	 ====================== Basic_Detail_Screen =================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails");

		getuserdetails=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Get_User_Detail();

		getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");



		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding");

		useronboarding=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_User_Onboarding();

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
		ExtentReporter.HeaderChildNode("CreateBnplTransaction");

		createbnpl=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction();

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
		ExtentReporter.HeaderChildNode("UpdateUserStatus");

		updateuserstatus=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Update_User_Status();

		updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");



		//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====


		//		CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility");

		basic_eligibility=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Check_Application_Eligibility();
		addaddress=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Add_Address();

		basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		addaddress.addAddress_Positive();
		basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");



		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress");

		addaddress=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Add_Address();

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
		ExtentReporter.HeaderChildNode("get_Offer_Details");

		getoffer=new com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_Get_Offer();
		getoffer.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");



		//		accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer");

		userconcent=new com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_User_Concent();
		userconcent.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility");

		offer_eligibility=new com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_Check_Application_Eligibility();

		offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais");

		get_pin_detais=new com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Get_Pin_Details();

		get_pin_detais.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin");

		sendotpforpin=new com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Send_Otp_For_Pin();

		sendotpforpin.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//		resetpin
		ExtentReporter.HeaderChildNode("resetpin");

		resetpin=new com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Set_Reset_Pin();
		get_pin_detais=new com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Get_Pin_Details();

		resetpin.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais");

		get_pin_detais=new com.business.RingPay_MerchantQRCode_Journey.PinDetailScreen_Get_Pin_Details();

		get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines");

		bnpl_lines=new com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Bnpl_Lines();

		bnpl_lines.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");



		// payment_option
		ExtentReporter.HeaderChildNode("payment_option");

		payment_option=new com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Payment_Option();

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
		ExtentReporter.HeaderChildNode("txn_initiated");

		txn_initiated=new com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Initiate();

		Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();
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
		ExtentReporter.HeaderChildNode("txn_complete");
		txn_complete=new com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Complete();

		txn_complete.transactionComplete();
		System.out.println("transactionComplete,validation is Done");



		// ==================================  Repayment  ==============================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends");

		current_Spends=new com.business.RingPay_MerchantQRCode_Journey.Repayment_Home_Screen_For_Current_Spends();
		current_Spends.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");



		// validate
		ExtentReporter.HeaderChildNode("validate");

		validate=new com.business.RingPay_MerchantQRCode_Journey.Repayment_Validate();
		validate.validate();
		System.out.println("paymentValidate,validation is Done");



		//		notify
		ExtentReporter.HeaderChildNode("notify");

		notify=new com.business.RingPay_MerchantQRCode_Journey.Repayment_Notify();

		notify.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");



		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement");

		getsettlement=new com.business.RingPay_MerchantQRCode_Journey.TransactionDetails_Get_Settlement_Status();
		getsettlement.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");



	}
	
	
//	=========================================================================================================================
	
	
	@Test(priority = 1)
	
//	@Test(enabled = false)
	
	public void PromoCode_Journey() throws Exception {

		
		//		mockuser
		ExtentReporter.HeaderChildNode("MockUser");

		promo_mockuser=new com.business.RingPay_PromoCode_Journey.RegisterUser_Mock_User();
		promo_mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");


		//		Onload
		ExtentReporter.HeaderChildNode("Onload");

		promo_onload=new com.business.RingPay_PromoCode_Journey.RegisterUser_Onload();
		promo_onload.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");


//		//		Get_VPA
//		ExtentReporter.HeaderChildNode("Get_VPA");
//
//		getvpa=new com.business.RingPay_PromoCode_Journey.RegisterUser_Get_Details_VPA();
//		getvpa.get_Details_Vpa_Positive();
//		System.out.println("getVPADetails,Validation is Done");


		//		sendOtp
		ExtentReporter.HeaderChildNode("SendOtp");

		promo_sendotp=new com.business.RingPay_PromoCode_Journey.RegisterUser_OTPSend();

		promo_sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive is Done");

		promo_sendotp.mobileNoLessThan10Digit_Negative();
		System.out.println("mobileNoLessThan10Digit_Negative is Done");
		promo_sendotp.mobileNoMoreThan10Digit_Negative();
		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
		promo_sendotp.specialCharacterInMobileNoField_Negative();
		System.out.println("specialCharacterInMobileNoField_Negative is Done");
		promo_sendotp.alphabetsInMobileNoField_Negative();
		System.out.println("alphabetsInMobileNoField_Negative is Done");
		promo_sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive Repeated is Done");



		//		UserAuthenticate
		ExtentReporter.HeaderChildNode("UserAuthenticate");

		promo_userauthenticate=new com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate();

		promo_userauthenticate.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");

		promo_userauthenticate.invalidOtp_Negative();
		System.out.println("invalidOtp_Negative is Done");

		promo_userauthenticate.expiredOtp_Negative();
		System.out.println("expiredOtp_Negative is Done");
		promo_userauthenticate.alphabetInOtpField_Negative();
		System.out.println("alphabetInOtpField_Negative is Done");
		promo_userauthenticate.lessThan6DigitsNoInOtpField_Negative();
		System.out.println("lessThan6DigitsNoInOtpField_Negative is Done");
		promo_userauthenticate.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login");

		promo_login=new com.business.RingPay_PromoCode_Journey.RegisterUser_Login();
		promo_login.login_Positive();
		System.out.println("loginUser,validation is Done");



		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser");

		promo_registeruser=new com.business.RingPay_PromoCode_Journey.RegisterUser_Register_User();
		promo_registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");

		

		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails");

		promo_basicdetails=new com.business.RingPay_PromoCode_Journey.RegisterUser_BasicDetails();


		promo_basicdetails.basicDetails_Positive_SchemaValiadtion();
		System.out.println("ubasicdetails_Positive_SchemaValiadtion,Schema validation");
		promo_basicdetails.alphaNumericInFirstNameField_Negative();
		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
		promo_basicdetails.specialCharacterInFirstNameField_Negative();
		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");

		promo_basicdetails.spaceInFirstNameField_Negative();
		System.out.println("spaceInFirstNameField_Negative,validation is Done");

		promo_basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");

		promo_basicdetails.alphaNumericInLastNameField_Negative();
		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");

		promo_basicdetails.specialCharacterInLastNameField_Negative();
		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");

		promo_basicdetails.spaceInLastNameField_Negative();
		System.out.println("spaceInLastNameField_Negative,validation is Done");

		promo_basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");

		promo_basicdetails.invalidEmailId_Negative();
		System.out.println("invalidEmailId_Negative,validation is Done");

		promo_basicdetails.spaceInEmailIdField_Negative();
		System.out.println("spaceInEmailIdField_Negative,validation is Done");

		promo_basicdetails.basicDetails_Positive();
		System.out.println("updateUser_Positive,validation is Done");



		//		LocateRequire
		ExtentReporter.HeaderChildNode("LocateRequire");

		promo_locaterequire=new com.business.RingPay_PromoCode_Journey.RegisterUser_Location_Require();

		promo_locaterequire.location_Require();
		System.out.println("locationRequire,validation is Done");




		//	 ====================== Basic_Detail_Screen =================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails");

		promo_getuserdetails=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Get_User_Detail();

		promo_getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");



		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding");

		promo_useronboarding=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_User_Onboarding();

		promo_useronboarding.userOnbording_Positive();
		System.out.println("userOnbording_Positive,validation is Done");

		promo_useronboarding.latitudeFieldEmpty_Negative();
		System.out.println("latitudeFieldEmpty_Negative,validation is Done");

		promo_useronboarding.longitudeFieldEmpty_Negative();
		System.out.println("longitudeFieldEmpty_Negative,validation is Done");

		promo_useronboarding.advertisingIdFieldEmpty_Negative();
		System.out.println("advertisingIdFieldEmpty_Negative,validation is Done");

		promo_useronboarding.androidIdFieldEmpty_Negative();
		System.out.println("androidIdFieldEmpty_Negative,validation is Done");

		promo_useronboarding.globalDeviceIdFieldEmpty_Negative();
		System.out.println("globalDeviceIdFieldEmpty_Negative,validation is Done");

		promo_useronboarding.latitudeAndLongitudeFieldEmpty_Negative();
		System.out.println("latitudeAndLongitudeFieldEmpty_Negative,validation is Done");

		promo_useronboarding.latitudeFieldWithAlphaNumericKeywords_Negative();
		System.out.println("latitudeFieldWithAlphaNumericKeywords_Negative,validation is Done");

		promo_useronboarding.userOnbording_Positive();
		System.out.println("userOnbordingWithValidField_Positive,validation is Done");


		//		CreateBnplTransaction
		ExtentReporter.HeaderChildNode("CreateBnplTransaction");

		promo_createbnpl=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction();

		promo_createbnpl.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");
		promo_createbnpl.sourceFieldEmptyBnpl_Negative();
		System.out.println("sourceFieldEmptyBnpl_Negative,validation is Done");
		promo_createbnpl.globalDeviceIdFieldEmptyBnpl_Negative();
		System.out.println("globalDeviceIdFieldEmptyBnpl_Negative,validation is Done");
		promo_createbnpl.productNameFieldEmptyBnpl_Negative();
		System.out.println("productNameFieldEmptyBnpl_Negative,validation is Done");
		promo_createbnpl.getApplicationToken_Positive();
		System.out.println("getApplicationToken_Positive,validation is Done");



		//		UpdateUserStatus
		ExtentReporter.HeaderChildNode("UpdateUserStatus");

		promo_updateuserstatus=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Update_User_Status();

		promo_updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");



		//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====


		//		CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility");

		promo_basic_eligibility=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Check_Application_Eligibility();
		promo_addaddress=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Add_Address();

		promo_basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		promo_basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		promo_addaddress.addAddress_Positive();
		promo_basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");



		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress");

		promo_addaddress=new com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Add_Address();

		promo_addaddress.addAddress_Positive();
		System.out.println("addAddress_Positive,validation is Done");
		promo_addaddress.line1FieldIsEmpty_Negative();
		System.out.println("line1FieldIsEmpty_Negative,validation is Done");
		promo_addaddress.pincodeFieldIsEmpty_Negative();
		System.out.println("pincodeFieldIsEmpty_Negative,validation is Done");
		promo_addaddress.labelFieldIsEmpty_Negative();
		System.out.println("labelFieldIsEmpty_Negative,validation is Done");
		promo_addaddress.tagFieldIsEmpty_Negative();
		System.out.println("tagFieldIsEmpty_Negative,validation is Done");
		promo_addaddress.sourceFieldIsEmpty_Negative();
		System.out.println("sourceFieldIsEmpty_Negative,validation is Done");
		promo_addaddress.invalidSourceField_Negative();
		System.out.println("invalidSourceField_Negative,validation is Done");
		promo_addaddress.productNameFieldIsEmpty_Negative();
		System.out.println("productNameFieldIsEmpty_Negative,validation is Done");



		//	===================================== OFFER_DETAILS_SCREEN =======================================


		//		get_Offer_Details
		ExtentReporter.HeaderChildNode("get_Offer_Details");

		promo_getoffer=new com.business.RingPay_PromoCode_Journey.OfferDetailsScreen_Get_Offer();
		promo_getoffer.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");



		//		accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer");

		promo_userconcent=new com.business.RingPay_PromoCode_Journey.OfferDetailsScreen_User_Concent();
		promo_userconcent.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility");

		promo_offer_eligibility=new com.business.RingPay_PromoCode_Journey.OfferDetailsScreen_Check_Application_Eligibility();

		promo_offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais");

		promo_get_pin_detais=new com.business.RingPay_PromoCode_Journey.PinDetailScreen_Get_Pin_Details();

		promo_get_pin_detais.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin");

		promo_sendotpforpin=new com.business.RingPay_PromoCode_Journey.PinDetailScreen_Send_Otp_For_Pin();

		promo_sendotpforpin.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//		resetpin
		ExtentReporter.HeaderChildNode("resetpin");

		promo_resetpin=new com.business.RingPay_PromoCode_Journey.PinDetailScreen_Set_Reset_Pin();
		promo_get_pin_detais=new com.business.RingPay_PromoCode_Journey.PinDetailScreen_Get_Pin_Details();

		promo_resetpin.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		promo_get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais");

		promo_get_pin_detais=new com.business.RingPay_PromoCode_Journey.PinDetailScreen_Get_Pin_Details();

		promo_get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines");

		promo_bnpl_lines=new com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Bnpl_Lines();

		promo_bnpl_lines.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");



		// payment_option
		ExtentReporter.HeaderChildNode("payment_option");

		promo_payment_option=new com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Payment_Option();

		promo_payment_option.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");
		promo_payment_option.reasonFieldIsEmpty_Negative();
		System.out.println("reasonFieldIsEmpty_Negative,validation is Done");
		promo_payment_option.actualAmountFieldIsEmpty_Negative();
		System.out.println("actualAmountFieldIsEmpty_Negative,validation is Done");
		promo_payment_option.qr_CodeFieldIsEmpty_Negative();
		System.out.println("qr_CodeFieldIsEmpty_Negative,validation is Done");
		promo_payment_option.qr_CodeFieldWithIncorrectVPA_Negative();
		System.out.println("qr_CodeFieldWithIncorrectVPA_Negative,validation is Done");
		promo_payment_option.qr_CodeFieldWithInvalidCode_Negative();
		System.out.println("qr_CodeFieldWithInvalidCode_Negative,validation is Done");
		promo_payment_option.paymentOption_Positive();
		System.out.println("payment_option,validation is Done");



		//		txn_initiated
		ExtentReporter.HeaderChildNode("txn_initiated");

		promo_txn_initiated=new com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Transaction_Initiate();

		promo_txn_initiated.transactionInitiate_Positive();
		System.out.println("transaction_Initiate,validation is Done");

		promo_txn_initiated.productValueEmptyField_Negative();
		System.out.println("productValueEmptyField_Negative,validation is Done");

		promo_txn_initiated.productValueFieldWithAlphaNumericCharacters_Negative();
		System.out.println("productValueFieldWithAlphaNumericCharacters_Negative,validation is Done");

		promo_txn_initiated.transactionTypeFieldEmpty_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");

		promo_txn_initiated.transactionTypeFieldWithInvalidValue_Negative();
		System.out.println("transactionTypeFieldEmpty_Negative,validation is Done");

		promo_txn_initiated.merchantOrder_Id_FieldEmpty_Negative();
		System.out.println("merchantOrder_Id_FieldEmpty_Negative,validation is Done");

		promo_txn_initiated.upiHandleReferenceNumberFieldEmpty_Negative();
		System.out.println("upiHandleReferenceNumberFieldEmpty_Negative,validation is Done");

		promo_txn_initiated.latitudeField_Empty_Negative();
		System.out.println("latitudeField_Empty_Negative,validation is Done");

		promo_txn_initiated.longitudeField_Empty_Negative();
		System.out.println("longitudeField_Empty_Negative,validation is Done");

		promo_txn_initiated.latitudeAndLongitudeField_Empty_Negative();
		System.out.println("latitudeAndLongitudeField_Empty_Negative,validation is Done");




		// txn_complete
		ExtentReporter.HeaderChildNode("txn_complete");
		promo_txn_complete=new com.business.RingPay_PromoCode_Journey.Bnpl_Txn_Transaction_Complete();

		promo_txn_complete.transactionComplete();
		System.out.println("transactionComplete,validation is Done");



		// ==================================  Repayment  ==============================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends");

		promo_current_Spends=new com.business.RingPay_PromoCode_Journey.Repayment_Home_Screen_For_Current_Spends();
		promo_current_Spends.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");



		// validate
		ExtentReporter.HeaderChildNode("validate");

		promo_validate=new com.business.RingPay_PromoCode_Journey.Repayment_Validate();
		promo_validate.validate();
		System.out.println("paymentValidate,validation is Done");



		//		notify
		ExtentReporter.HeaderChildNode("notify");

		promo_notify=new com.business.RingPay_PromoCode_Journey.Repayment_Notify();

		promo_notify.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");



		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement");

		promo_getsettlement=new com.business.RingPay_PromoCode_Journey.TransactionDetails_Get_Settlement_Status();
		promo_getsettlement.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");



	}
	
	
//	==================================================================================================================
	
	
	
	@Test(priority = 2)
	
//	@Test(enabled = false)
	
	public void PlayStore_Journey() throws Exception {

		//		MockUser
		ExtentReporter.HeaderChildNode("MockUser");

		play_mockuser=new com.business.RingPay_PlayStore_Journey.RegisterUser_Mock_User();
		play_mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");

		//		Onload
		ExtentReporter.HeaderChildNode("Onload");

		play_onload=new com.business.RingPay_PlayStore_Journey.RegisterUser_Onload();
		play_onload.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");


		//		SendOtp
		ExtentReporter.HeaderChildNode("SendOtp");

		play_sendotp=new com.business.RingPay_PlayStore_Journey.RegisterUser_OTPSend();

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
		ExtentReporter.HeaderChildNode("UserAuthenticate");

		play_userauthenticate=new com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate();

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
		play_userauthenticate.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login");

		play_login=new com.business.RingPay_PlayStore_Journey.RegisterUser_Login();

		play_login.login_Positive();
		System.out.println("loginUser,validation is Done");


		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser");

		play_registeruser=new com.business.RingPay_PlayStore_Journey.RegisterUser_Register_User();

		play_registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");


		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails");

		play_basicdetails=new com.business.RingPay_PlayStore_Journey.RegisterUser_BasicDetails();

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
		ExtentReporter.HeaderChildNode("LocateRequire");

		play_locaterequire=new com.business.RingPay_PlayStore_Journey.RegisterUser_Location_Require();

		play_locaterequire.location_Require();
		System.out.println("locationRequire,validation is Done");


		//	=========================== Basic_Detail_Screen ================================

		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails");

		play_getuserdetails=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Get_User_Detail();

		play_getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");


		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding");

		play_useronboarding=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_User_Onboarding();

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
		ExtentReporter.HeaderChildNode("CreateBnplTransaction");

		play_createbnpl=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Create_Bnpl_Transaction();

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
		ExtentReporter.HeaderChildNode("UpdateUserStatus");

		play_updateuserstatus=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Update_User_Status();

		play_updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");


		//	CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility");

		play_basic_eligibility=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Check_Application_Eligibility();
		play_addaddress=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Add_Address();

		play_basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		play_basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		play_addaddress.addAddress_Positive();
		play_basic_eligibility.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");


		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress");

		play_addaddress=new com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Add_Address();

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
		ExtentReporter.HeaderChildNode("get_Offer_Details");
		
		play_getoffer=new com.business.RingPay_PlayStore_Journey.OfferDetailsScreen_Get_Offer();

		play_getoffer.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");


		//			accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer");
		
		play_userconcent=new com.business.RingPay_PlayStore_Journey.OfferDetailsScreen_User_Concent();

		play_userconcent.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility");
		
		play_offer_eligibility=new com.business.RingPay_PlayStore_Journey.OfferDetailsScreen_Check_Application_Eligibility();

		play_offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais");
		play_get_pin_detais=new com.business.RingPay_PlayStore_Journey.PinDetailScreen_Get_Pin_Details();

		play_get_pin_detais.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin");
		play_sendotpforpin=new com.business.RingPay_PlayStore_Journey.PinDetailScreen_Send_Otp_For_Pin();

		play_sendotpforpin.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//	resetpin
		ExtentReporter.HeaderChildNode("resetpin");
		play_resetpin=new com.business.RingPay_PlayStore_Journey.PinDetailScreen_Set_Reset_Pin();
		play_get_pin_detais=new com.business.RingPay_PlayStore_Journey.PinDetailScreen_Get_Pin_Details();

		play_resetpin.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		play_get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");


		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais");
		play_get_pin_detais=new com.business.RingPay_PlayStore_Journey.PinDetailScreen_Get_Pin_Details();

		play_get_pin_detais.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines");
		play_bnpl_lines=new com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Bnpl_Lines();

		play_bnpl_lines.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");


		// payment_option
		ExtentReporter.HeaderChildNode("payment_option");
		play_payment_option=new com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Payment_Option();

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
		ExtentReporter.HeaderChildNode("txn_initiated");
		play_txn_initiated=new com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Transaction_Initiate();

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
		ExtentReporter.HeaderChildNode("txn_complete");
		play_txn_complete=new com.business.RingPay_PlayStore_Journey.Bnpl_Txn_Transaction_Complete();

		play_txn_complete.transactionComplete();
		System.out.println("transactionComplete,validation is Done");




		//	============================== Repayment ======================================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends");
		play_current_Spends=new com.business.RingPay_PlayStore_Journey.Repayment_Home_Screen_For_Current_Spends();
		play_current_Spends.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");


		// validate
		ExtentReporter.HeaderChildNode("validate");
		play_validate=new com.business.RingPay_PlayStore_Journey.Repayment_Validate();
		play_validate.validate();
		System.out.println("paymentValidate,validation is Done");


		//		notify
		ExtentReporter.HeaderChildNode("notify");
		play_notify=new com.business.RingPay_PlayStore_Journey.Repayment_Notify();
		play_notify.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");


		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement");
		play_getsettlement=new com.business.RingPay_PlayStore_Journey.TransactionDetails_Get_Settlement_Status();
		play_getsettlement.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");


	}
	
	
//	=====================================================================================================================
	
	
	@Test(priority = 3)
	
//	@Test(enabled = false)
	
	public void MerChantQRCodeJourney_Segment1() throws Exception {

		//		mockuser
		ExtentReporter.HeaderChildNode("MockUser");

		mockuser_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Mock_User();
		mockuser_segment1_qrcode.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");


		//		Onload
		ExtentReporter.HeaderChildNode("Onload");

		onload_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Onload();
		onload_segment1_qrcode.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");


		//		Get_VPA
		ExtentReporter.HeaderChildNode("Get_VPA");

		getvpa_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Get_Details_VPA();
		getvpa_segment1_qrcode.get_Details_Vpa_Positive();
		System.out.println("getVPADetails,Validation is Done");


		//		sendOtp
		ExtentReporter.HeaderChildNode("SendOtp");

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
		ExtentReporter.HeaderChildNode("UserAuthenticate");

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
		userauthenticate_segment1_qrcode.userToken_Positive();
		System.out.println("userToken_Positive,validation is Done");


		//		Login
		ExtentReporter.HeaderChildNode("Login");

		login_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Login();
		login_segment1_qrcode.login_Positive();
		System.out.println("loginUser,validation is Done");


		//		Segment1
		ExtentReporter.HeaderChildNode("Segment1");

		s1_segment1_qrcode=new com.business.RingPay_RingPolicy.Segment1();
		s1_segment1_qrcode.Segment1();
		System.out.println("RingPolicy_Segment1,validation is Done");



		//		RegisterUser
		ExtentReporter.HeaderChildNode("RegisterUser");

		registeruser_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Register_User();
		System.out.println("registeruser,validation is Done");

		registeruser_segment1_qrcode.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");


		//		BasicDetails
		ExtentReporter.HeaderChildNode("BasicDetails");

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
		ExtentReporter.HeaderChildNode("LocateRequire");

		locaterequire_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Location_Require();

		locaterequire_segment1_qrcode.location_Require();
		System.out.println("locationRequire,validation is Done");




		//	 ====================== Basic_Detail_Screen =================================


		//		Getuserdetails
		ExtentReporter.HeaderChildNode("GetUserDetails");

		getuserdetails_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Get_User_Detail();

		getuserdetails_segment1_qrcode.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");



		//		UserOnboarding
		ExtentReporter.HeaderChildNode("UserOnboarding");

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
		ExtentReporter.HeaderChildNode("CreateBnplTransaction");

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
		ExtentReporter.HeaderChildNode("UpdateUserStatus");

		updateuserstatus_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Update_User_Status();

		updateuserstatus_segment1_qrcode.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");



		//// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====


		//		CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("CheckApplicationEligibility");

		basic_eligibility_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Check_Application_Eligibility();
		addaddress_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Add_Address();

		basic_eligibility_segment1_qrcode.checkApplicationEligibility_Positive();
		System.out.println("checkApplicationEligibility_Positive,validation is Done");
		basic_eligibility_segment1_qrcode.checkApplicationEligibilitySchemaValidation_Positive();
		System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");

		addaddress_segment1_qrcode.addAddress_Positive();
		basic_eligibility_segment1_qrcode.checkApplicationEligibility_Positive();
		System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");



		//		AddAddress
		ExtentReporter.HeaderChildNode("AddAddress");

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
		ExtentReporter.HeaderChildNode("get_Offer_Details");

		getoffer_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Get_Offer();
		getoffer_segment1_qrcode.get_Offer();
		System.out.println("get_Offer_Details,validation is Done");



		//		accept_Offer
		ExtentReporter.HeaderChildNode("accept_Offer");

		userconcent_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_User_Concent();
		userconcent_segment1_qrcode.acceptOffer();
		System.out.println("accept_Offer,validation is Done");


		//  OfferDetailsScreen_CheckApplicationEligibility
		ExtentReporter.HeaderChildNode("OfferDetailsScreen_CheckApplicationEligibility");

		offer_eligibility_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.OfferDetailsScreen_Check_Application_Eligibility();

		offer_eligibility_segment1_qrcode.OfferDetailsScreen_CheckApplicationEligibility_Positive();
		System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");



		//	===================================== PIN_DETAILS_SCREEN =======================================


		// get_pin_detais
		ExtentReporter.HeaderChildNode("get_pin_detais");

		get_pin_detais_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		get_pin_detais_segment1_qrcode.getPinDetails_Positive();
		System.out.println("getPinDetails,validation is Done");


		// sendotpforpin
		ExtentReporter.HeaderChildNode("sendotpforpin");

		sendotpforpin_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Send_Otp_For_Pin();

		sendotpforpin_segment1_qrcode.sendOtpForPin_Positive();
		System.out.println("sendOtpForPin,validation is Done");


		//		resetpin
		ExtentReporter.HeaderChildNode("resetpin");

		resetpin_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Set_Reset_Pin();
		get_pin_detais_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		resetpin_segment1_qrcode.setResetPin_Positive();
		System.out.println("setResetPin,validation is Done");

		get_pin_detais_segment1_qrcode.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		// afterResetPin_get_pin_detais 
		ExtentReporter.HeaderChildNode("afterResetPin_get_pin_detais");

		get_pin_detais_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.PinDetailScreen_Get_Pin_Details();

		get_pin_detais_segment1_qrcode.afterResetPin_getPinDetails_Positive();
		System.out.println("AfterResetAPI_SetResetPin,validation is Done");



		//	====================================== BNPL_TXN =======================================


		// bnpl_lines
		ExtentReporter.HeaderChildNode("bnpl_lines");

		bnpl_lines_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Bnpl_Lines();

		bnpl_lines_segment1_qrcode.bnpl_Lines();
		System.out.println("bnpl_lines,validation is Done");



		// payment_option
		ExtentReporter.HeaderChildNode("payment_option");

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
		ExtentReporter.HeaderChildNode("txn_initiated");

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
		ExtentReporter.HeaderChildNode("txn_complete");
		txn_complete_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Bnpl_Txn_Transaction_Complete();

		txn_complete_segment1_qrcode.transactionComplete();
		System.out.println("transactionComplete,validation is Done");




		// ==================================  Repayment  ==============================


		// current_Spends
		ExtentReporter.HeaderChildNode("current_Spends");

		current_Spends_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Home_Screen_For_Current_Spends();

		current_Spends_segment1_qrcode.current_Spent();
		System.out.println("homeScreenForCurrentSpends,validation is Done");



		// validate
		ExtentReporter.HeaderChildNode("validate");

		validate_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Validate();

		validate_segment1_qrcode.validate();
		System.out.println("paymentValidate,validation is Done");



		//		notify
		ExtentReporter.HeaderChildNode("notify");

		notify_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.Repayment_Notify();

		notify_segment1_qrcode.notifyPaymentDone();
		System.out.println("notifyPaymentDone,validation is Done");



		// getsettlement
		ExtentReporter.HeaderChildNode("getsettlement");

		getsettlement_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.TransactionDetails_Get_Settlement_Status();
		getsettlement_segment1_qrcode.getSettlementStatus();
		System.out.println("getSettlementStatus,validation is Done");


		// delete
		ExtentReporter.HeaderChildNode("Delete_MobileNumbe");

		delete_segment1_qrcode=new com.business.RingPay_MerchantQRCode_Journey_Segment1.DeleteQuery();
		delete_segment1_qrcode.delete();
		System.out.println("deleteMobileNumber,validation is Done");


	}
	
	
	
	
	
	
	
	
}
