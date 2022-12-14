package com.Datasheet;

import com.utils.Excel;
import org.testng.annotations.DataProvider;

import java.io.IOException;

//import commonFunctions.TestUtil;


public class RingPay_TestData_DataProvider {
	public static String excelPath()
	{
		String os = System.getProperty("os.name");
		String path = System.getProperty("user.dir");
		String filePath;
		filePath = path + "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_" + "stage" + ".xlsx";
		return filePath;
	}

	//	@DataProvider(name = "RingPayAPI")
	//    public static Object[][] RingPayAPIData(String testCaseName) throws IOException{
	//		return Excel.getTestData(excelPath(), "RingPayData", testCaseName);
	//	}

	@DataProvider(name = "MockuserAPI")
	public static Object[][] Mock_UserAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Mock_User", testCaseName);
	}

	@DataProvider(name = "Get_Details_VPA_API")
	public static Object[][] Get_Details_VPA_APIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Get_Details_VPN", testCaseName);
	}

	@DataProvider(name = "SendOtpAPI")
	public static Object[][] SendOtpAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "SendOtp", testCaseName);
	}

	@DataProvider(name = "UserAuthenticateAPI")
	public static Object[][] UserAuthenticateAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "User_Authenticate", testCaseName);
	}


	//	@DataProvider(name = "UpdateUserAPI")
	//	public static Object[][] UpdateUserAPIData(String testCaseName) throws IOException{
	//		return Excel.getTestData(excelPath(), " UpdateUser", testCaseName);
	//	}

	
//	//	PromoCode
//	@DataProvider(name = "PromoCode_BasicDetailsAPI")
//	public static Object[][] UpdateUserAPIData(String testCaseName) throws IOException{
//		return Excel.getTestData(excelPath(), "PromoCode_BasicDetailsAPI", testCaseName);
//	}

	// MerchantQRCode
	@DataProvider(name = "MerchantQRCode_BasicDetailsAPI")
	public static Object[][] BasicDetailsAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "MerchantQRCode_UpdateUser", testCaseName);
	}

	// PlayStore
	@DataProvider(name = "PlayStore_BasicDetailsAPI")
	public static Object[][] PlayStore_BasicDetailsAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "PlayStore_UpdateUser", testCaseName);
	}


	// PromoCodeQRCode
	@DataProvider(name = "Promo_BasicDetailsAPI")
	public static Object[][] Promo_BasicDetailsAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "PromoCode_UpdateUser", testCaseName);
	}

	@DataProvider(name = "RegisterUserAPI")
	public static Object[][] RegisterUserAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "RegisterUser", testCaseName);
	}

	@DataProvider(name = "UserOnboardingAPI")
	public static Object[][] UserOnboardingAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "UserOnboarding", testCaseName);
	}

	@DataProvider(name = "CreateBnplTransactionAPI")
	public static Object[][] CreateBnplTransactionAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Create_Bnpl_Transaction", testCaseName);
	}

	@DataProvider(name = "UpdateUserStatusAPI")
	public static Object[][] UpdateUserStatusAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "UpdateUserStatus", testCaseName);
	}

	@DataProvider(name = "AddAddressAPI")
	public static Object[][] AddAddressAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "AddAddress", testCaseName);
	}

	@DataProvider(name = "PaymentOptionAPI")
	public static Object[][] PaymentOptionAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "PaymentOption", testCaseName);
	}

	@DataProvider(name = "TxnInitiateAPI")
	public static Object[][] TxnInitiateAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Txn_Initiate", testCaseName);
	}

	@DataProvider(name = "TxnCompleteAPI")
	public static Object[][] TxnCompleteAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Txn_Complete", testCaseName);
	}

	@DataProvider(name = "CurrentSpendAPI")
	public static Object[][] CurrentSpendAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Current_Spend", testCaseName);
	}

	@DataProvider(name = "ValidateAPI")
	public static Object[][] ValidateAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Validate", testCaseName);
	}

	@DataProvider(name = "NotifyAPI")
	public static Object[][] NotifyAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "Notify", testCaseName);
	}

	@DataProvider(name = "SendOTPForPinAPI")
	public static Object[][] SendOTPForPinAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "SendOTPForPin", testCaseName);
	}

	@DataProvider(name = "SetResetPinAPI")
	public static Object[][] SetResetPinAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "SetResetPin", testCaseName);
	}

	@DataProvider(name = "RingPolicyAPI")
	public static Object[][] RingPolicyAPIData(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "RingPolicy", testCaseName);
	}


	//	@DataProvider(name = "BC1API")
	//    public static Object[][] BC1APIData(String testCaseName) throws IOException{
	//		return Excel.getTestData(excelPath(), "BC1", testCaseName);
	//	}


}
