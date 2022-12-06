package com.RingPayScripts;

import org.testng.annotations.Test;

import com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Initiate;

public class PromoCode_Scripts {

	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Mock_User promo_mockuser;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Onload promo_registerUser_Onload;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_OTPSend promo_sendotp;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UserAuthenticate promo_userauthenticate;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_PromoCode promo_promocode;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UpdateUser promo_updateuser;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Login promo_login;
	private com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Register_User promo_registeruser;
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

	
	

	@Test(priority = 0)
	//	@Parameters({"MOCK_USER-URI"})
	public void userDetailsAPI() throws Exception {

		promo_mockuser=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Mock_User();
		promo_mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");

	}


	@Test(priority = 1)
	//	@Parameters({"ONLOAD-URI"})
	public void onloadAndroidVersionCheckAPI() throws Exception {
		promo_registerUser_Onload=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Onload();
		promo_registerUser_Onload.onload_Positive();
		System.out.println("onloadAndroidVersionCheckAPI,Validation is Done");

	}


	@Test(priority = 2)
	//	@Parameters({"OTP-URI"})
	public void sendOtp_Positive() throws Exception {

		promo_sendotp=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_OTPSend();

		promo_sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive is Done");

		promo_sendotp.mobileNoLessThan10Digit_Negative();
		System.out.println("mobileNoLessThan10Digit_Negative is Done");
		promo_sendotp.mobileNoMoreThan10Digit_Negative();
		System.out.println("mobileNoMoreThan10Digit_Negative is Done");
		promo_sendotp.specialCharacterInMobileNoField_Negative();
		System.out.println("specialCharacterInMobileNoField_Negative is Done");
		promo_sendotp.validMobileNo_Positive();
		System.out.println("validMobileNo_Positive Repeated is Done");
	}



	@Test(priority = 3)
	public void userToken() throws Exception {

		promo_userauthenticate=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UserAuthenticate();

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
	}


	@Test(priority = 4)
	public void promoCode() throws Exception {

		promo_promocode=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_PromoCode();

		promo_promocode.promo_Code();
		System.out.println("promoCode,Validation is Done");
		
	}

	
	
	@Test(priority = 5)
	public void updateUserDetils_200() throws Exception {

		promo_updateuser = new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_UpdateUser();


		promo_updateuser.updateUser_Positive_SchemaValiadtion();
		System.out.println("updateUser_Positive_SchemaValiadtion,Schema validation");
		promo_updateuser.alphaNumericInFirstNameField_Negative();
		System.out.println("alphaNumericInFirstNameField_Negative,validation is Done");
		promo_updateuser.specialCharacterInFirstNameField_Negative();
		System.out.println("specialCharacterInFirstNameField_Negative,validation is Done");

		promo_updateuser.spaceInFirstNameField_Negative();
		System.out.println("spaceInFirstNameField_Negative,validation is Done");

		promo_updateuser.updateUser_Positive();
		System.out.println("updateUser_Positive,validation is Done");

		promo_updateuser.alphaNumericInLastNameField_Negative();
		System.out.println("alphaNumericInLastNameField_Negative,validation is Done");

		promo_updateuser.specialCharacterInLastNameField_Negative();
		System.out.println("specialCharacterInLastNameField_Negative,validation is Done");

		promo_updateuser.spaceInLastNameField_Negative();
		System.out.println("spaceInLastNameField_Negative,validation is Done");

		promo_updateuser.updateUser_Positive();
		System.out.println("updateUser_Positive,validation is Done");

		promo_updateuser.invalidEmailId_Negative();
		System.out.println("invalidEmailId_Negative,validation is Done");

		promo_updateuser.spaceInEmailIdField_Negative();
		System.out.println("spaceInEmailIdField_Negative,validation is Done");

		promo_updateuser.updateUser_Positive();
		System.out.println("updateUser_Positive,validation is Done");


	}

	@Test(priority = 6)
	public void loginUser() throws Exception{
		promo_login=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Login();

		promo_login.login_Positive();
		System.out.println("loginUser,validation is Done");
	}


	@Test(priority = 7)
	public void registeruser() throws Exception {

		promo_registeruser=new com.business.RingPay_PromoCode_Journey.PromoCode_RegisterUser_Register_User();
		System.out.println("registeruser,validation is Done");

		promo_registeruser.registerUserAfterLogin_Positive();
		System.out.println("registeruser,validation is Done");

	}

//	 ====================== Basic_Detail_Screen =================================
	 
	@Test(priority = 8)
	public void getUserDetails_Positive() throws Exception {

		getuserdetails=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Get_User_Detail();
		System.out.println("getUserDetails_Positive,validation is Done");

		getuserdetails.getUserDetails_Positive();
		System.out.println("getUserDetails_Positive,validation is Done");

		
	}
	

	@Test(priority = 9)
	public void user_onboarding_200() throws Exception {

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
		
	}

	@Test(priority = 10)
	public void create_bnpl_transaction() throws Exception {

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
		
	}

	@Test(priority = 11)
	public void upadate_user_status_200() throws Exception {

		updateuserstatus=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Update_User_Status();
		
		updateuserstatus.updateUserStatus_Positive();
		System.out.println("updateuserstatus,validation is Done");

	}
	
	// ======	 PreCondition for  Basic_Details_Screen - Check Application Eligibility  =====
	
		@Test(priority = 12)
		public void check_Application_Eligibility() throws Exception {

			basic_eligibility=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Check_Application_Eligibility();
			addaddress=new com.business.RingPay_MerchantQRCode_Journey.BasicDetailScreen_Add_Address();
			
			basic_eligibility.checkApplicationEligibility_Positive();
			System.out.println("checkApplicationEligibility_Positive,validation is Done");
			basic_eligibility.checkApplicationEligibilitySchemaValidation_Positive();
			System.out.println("checkApplicationEligibilitySchemaValidation_Positive,validation is Done");
			
			addaddress.addAddress();
			basic_eligibility.checkApplicationEligibilityAfterAddAddress_Positive();
			System.out.println("check_Application_Eligibility_After_add_addressAPI,validation is Done");

			
		}
		

		
//		===================================== OFFER_DETAILS_SCREEN =======================================
		
		@Test(priority = 13)
		public void get_Offer_Details() throws Exception {

			getoffer=new com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_Get_Offer();
			
			getoffer.get_Offer();
			System.out.println("Get_Offer_Details,validation is Done");

		}
		
		@Test(priority = 14)
		public void accept_Offer() throws Exception {

			userconcent=new com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_User_Concent();
			
			userconcent.acceptOffer();
			System.out.println("Accept_Offer,validation is Done");

		}
		
		@Test(priority = 15)
		public void offerDetailsScreen_CheckApplicationEligibility() throws Exception {

			offer_eligibility=new com.business.RingPay_MerchantQRCode_Journey.OfferDetailsScreen_Check_Application_Eligibility();
			
			offer_eligibility.OfferDetailsScreen_CheckApplicationEligibility_Positive();
			System.out.println("offerDetailsScreen_CheckApplicationEligibility,validation is Done");

		}
		
//		===================================== BNPL_TXN =======================================
		
		@Test(priority = 16)
		public void bnplLines() throws Exception {

			bnpl_lines=new com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Bnpl_Lines();
			
			bnpl_lines.bnpl_Lines();
			System.out.println("bnpl_lines,validation is Done");
			

		}
		
		@Test(priority = 17)
		public void paymentOption() throws Exception {

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
		}
		
		@Test(priority = 18)
		public void transaction_Initiate() throws Exception {

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

			
		}
		
		
		@Test(priority = 19)
		public void transactionComplete() throws Exception {

			txn_complete=new com.business.RingPay_MerchantQRCode_Journey.Bnpl_Txn_Transaction_Complete();
			
			txn_complete.transactionComplete();
			System.out.println("transactionComplete,validation is Done");
			

		}
		
		@Test(priority = 20)
		public void homeScreenForCurrentSpends() throws Exception {

			current_Spends=new com.business.RingPay_MerchantQRCode_Journey.Repayment_Home_Screen_For_Current_Spends();
			
			current_Spends.current_Spent();
			System.out.println("homeScreenForCurrentSpends,validation is Done");

		}
		
		@Test(priority = 21)
		public void paymentValidate() throws Exception {

			validate=new com.business.RingPay_MerchantQRCode_Journey.Repayment_Validate();
			
			validate.validate();
			System.out.println("paymentValidate,validation is Done");

		}

		
		@Test(priority = 22)
		public void notifyPaymentDone() throws Exception {

			notify=new com.business.RingPay_MerchantQRCode_Journey.Repayment_Notify();
			
			notify.notifyPaymentDone();
			System.out.println("notifyPaymentDone,validation is Done");

		}
		
		
		@Test(priority = 23)
		public void getSettlementStatus() throws Exception {

			getsettlement=new com.business.RingPay_MerchantQRCode_Journey.TransactionDetails_Get_Settlement_Status();
			
			getsettlement.getSettlementStatus();
			System.out.println("getSettlementStatus,validation is Done");

		}

//

}
