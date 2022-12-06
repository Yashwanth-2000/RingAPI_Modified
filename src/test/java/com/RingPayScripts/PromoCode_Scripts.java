package com.RingPayScripts;

import org.testng.annotations.Test;

public class PromoCode_Scripts {

	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Mock_User mockuser;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Onload registerUser_Onload;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Get_Details_VPA getvpa;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_OTPSend sendotp;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UserAuthenticate userauthenticate;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_PromoCode promocode;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UpdateUser updateuser;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Login login;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Register_User registeruser;


	@Test(priority = 0)
	//	@Parameters({"MOCK_USER-URI"})
	public void userDetailsAPI() throws Exception {

		mockuser=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Mock_User();
		mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");

	}


	@Test(priority = 1)
	//	@Parameters({"ONLOAD-URI"})
	public void onloadAndroidVersionCheckAPI() throws Exception {
		registerUser_Onload=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Onload();
		registerUser_Onload.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");

	}


	@Test(priority = 2)
	//	@Parameters({"GET_DETAILS_VPN-URI"})
	public void getVPADetails() throws Exception {

		getvpa=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Get_Details_VPA();

		getvpa.get_Details_Vpa_Positive();
		System.out.println("getVPADetails,Validation is Done");
	}


	@Test(priority = 3)
	//	@Parameters({"OTP-URI"})
	public void sendOtp_Positive() throws Exception {

		sendotp=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_OTPSend();

		sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive is Done");

		sendotp.mobileNoLessThan10Digit_Negative();
		System.out.println("mobileNoLessThan10Digit_Negative is Done");
		sendotp.mobileNoMoreThan10Digit_Negative();
		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
		sendotp.specialCharacterInMobileNoField_Negative();
		System.out.println("specialCharacterInMobileNoField_Negative is Done");
		sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive Repeated is Done");
	}



	@Test(priority = 4)
	public void userToken() throws Exception {

		userauthenticate=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UserAuthenticate();

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
	}


	@Test(priority = 5)
	public void promoCode() throws Exception {

		promocode=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_PromoCode();

		promocode.promo_Code();
		System.out.println("promoCode,Validation is Done");
		
	}

	
	
//	@Test(priority = 5)
//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI"})
//	public void updateUserDetils_200() throws Exception {
//
//		updateuser = new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UpdateUser();
//
//
//		updateuser.updateUser_Positive_SchemaValiadtion();
//		System.out.println("updateUser_Positive_SchemaValiadtion,Schema validation");
//		updateuser.alphaNumericInFirstNameField_Negative();
//		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
//		updateuser.specialCharacterInFirstNameField_Negative();
//		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");
//
//		updateuser.spaceInFirstNameField_Negative();
//		System.out.println("spaceInFirstNameField_Negative,validation is Done");
//
//		updateuser.updateUser_Positive();
//		System.out.println("updateUser_Positive,validation is Done");
//
//		updateuser.alphaNumericInLastNameField_Negative();
//		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");
//
//		updateuser.specialCharacterInLastNameField_Negative();
//		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");
//
//		updateuser.spaceInLastNameField_Negative();
//		System.out.println("spaceInLastNameField_Negative,validation is Done");
//
//		updateuser.updateUser_Positive();
//		System.out.println("updateUser_Positive,validation is Done");
//
//		updateuser.invalidEmailId_Negative();
//		System.out.println("invalidEmailId_Negative,validation is Done");
//
//		updateuser.spaceInEmailIdField_Negative();
//		System.out.println("spaceInEmailIdField_Negative,validation is Done");
//
//		updateuser.updateUser_Positive();
//		System.out.println("updateUser_Positive,validation is Done");
//
//
//	}
//
//	@Test(priority = 6)
//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI","Login-URI"})
//	public void loginUser() throws Exception{
//		login=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Login();
//
//		login.login_Positive();
//		System.out.println("loginUser,validation is Done");
//	}
//
//
//	@Test(priority = 7)
//	//	@Parameters({"OTP-URI","AuthenticationURI","UpdateUser-URI","Register_User-URI"})
//	public void registeruser() throws Exception {
//
//	registeruser=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Register_User();
//		System.out.println("registeruser,validation is Done");
//
//		registeruser.registerUserAfterLogin_Positive();
//		System.out.println("registeruser,validation is Done");
//
//	}



}
