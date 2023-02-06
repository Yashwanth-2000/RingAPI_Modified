package com.utility;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import com.excel.ExcelWriteData;
import com.business.RingPay.URI.RingPay_BaseURL;
import com.business.RingPay.URI.RingPay_Endpoints;
import com.business.RingPay_MerchantQRCode_Journey.*;

import org.json.simple.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.google.common.collect.Ordering;
import com.propertyfilereader.PropertyFileReader;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class Utilities extends ExtentReporter {

	private Utilities() {
	}

	public static String user_token;
	public static String user_token_promocode;
	public static String user_token_playstore;
	public static String user_token_S1;
	public static String user_token_promocode_S1;
	public static String user_token_playstore_S1;

	//	Ring Policy
	public static String user_token_LTBC1;
	public static String user_token_BC1;
	public static String user_token_BC2;
	public static String user_token_L1;
	public static String user_token_L2;
	public static String user_token_L3;


	public static String MobileNumber_PromoCode;
	public static String Mobile_Number;
	public static String MobileNumber_PlayStore;
	public static String Mobile_Number_S1;
	public static String MobileNumber_PromoCode_S1;
	public static String MobileNumber_PlayStore_S1;

	//	Ring Policy
	public static String Mobile_Number_LTBC1;
	public static String Mobile_Number_BC1;
	public static String Mobile_Number_BC2;
	public static String Mobile_Number_L1;
	public static String Mobile_Number_L2;
	public static String Mobile_Number_L3;


	public static String applicationToken;
	public static String applicationToken_PromoCode;
	public static String applicationToken_PlayStore;
	public static String applicationToken_S1;
	public static String applicationToken_PromoCode_S1;
	public static String applicationToken_PlayStore_S1;

	//	Ring Policy
	public static String applicationToken_LTBC1;
	public static String applicationToken_BC1;
	public static String applicationToken_BC2;
	public static String applicationToken_L1;
	public static String applicationToken_L2;
	public static String applicationToken_L3;


	//	Ring Policy_user_reference_number
	public static String user_reference_number_LTBC1;
	public static String user_reference_number_BC1;
	public static String user_reference_number_BC2;
	public static String user_reference_number_L1;
	public static String user_reference_number_L2;
	public static String user_reference_number_L3;


	//	public static String user_token_negative;


	/** Time out */
	private static int timeout;

	/** Retry Count */
	private static int retryCount;

	/** Test data property file**/
	public static PropertyFileReader prop = new PropertyFileReader(".\\properties\\testData.properties");

	// public static ExtentReporter extent = new ExtentReporter();

	@SuppressWarnings("rawtypes")
	public static TouchAction touchAction;

	private static SoftAssert softAssert = new SoftAssert();

	public static boolean relaunch = false;

	public static String CTUserName;

	public static String CTPWD;

	public static String setPlatform = "";

	// logging for extent report
	public static LoggingUtils log = new LoggingUtils();

	/** The Constant logger. */
	// final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** window handler */
	static ArrayList<String> win = new ArrayList<>();

	/** The Android driver. */
	public IOSDriver<WebElement> iOSDriver;

	public static int getTimeout() {
		return 30;
	}

	/*
	 * public static void setTimeout(int timeout) { this.timeout = timeout; }
	 */

	public static int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	static WebDriverWait wait;

	public static JavascriptExecutor js;
	public static Actions action;
	public static String username = "";
	public static String password = "";






	/**
	 * @return true if keyboard is displayed
	 * @throws IOException
	 */
	public boolean checkKeyboardDisplayed() throws IOException {
		boolean mInputShown = false;
		try {
			String cmd = "adb shell dumpsys input_method | grep mInputShown";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String outputText = "";
			while ((outputText = br.readLine()) != null) {
				if (!outputText.trim().equals("")) {
					String[] output = outputText.split(" ");
					String[] value = output[output.length - 1].split("=");
					String keyFlag = value[1];
					if (keyFlag.equalsIgnoreCase("True")) {
						mInputShown = true;
					}
				}
			}
			br.close();
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
		return mInputShown;
	}


	public static void waitTime(int x) {
		try {
			Thread.sleep(x);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Finding the duplicate elements in the list
	 *
	 * @param mono
	 * @param content
	 * @param dosechang
	 * @param enteral
	 */
	public List<String> findDuplicateElements(List<String> mono) {

		List<String> duplicate = new ArrayList<String>();
		Set<String> s = new HashSet<String>();
		try {
			if (mono.size() > 0) {
				for (String content : mono) {
					if (s.add(content) == false) {
						int i = 1;
						duplicate.add(content);
						System.out.println("List of duplicate elements is" + i + content);
						i++;
					}
				}
			}
		} catch (Exception e) {
			// System.out.println(e);
		}
		return duplicate;
	}

	/**
	 * @param contents
	 * @return the list without duplicate elements
	 */
	public List<String> removeDuplicateElements(List<String> contents) {

		LinkedHashSet<String> set = new LinkedHashSet<String>(contents);
		ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>();
		try {

			if (contents.size() > 0) {
				listWithoutDuplicateElements = new ArrayList<String>(set);
			}

		} catch (Exception e) {
			// System.out.println(e);
		}
		return listWithoutDuplicateElements;
	}

	/**
	 * @param sorted
	 * @return true if the list is sorted
	 * @return false if the list is not sorted
	 */
	public boolean checkListIsSorted(List<String> ListToSort) {

		boolean isSorted = false;

		if (ListToSort.size() > 0) {
			try {
				if (Ordering.natural().isOrdered(ListToSort)) {
					ExtentReporter.extentLogger("Check sorting", "List is sorted");
					logger.info("List is sorted");
					isSorted = true;
					return isSorted;
				} else {
					ExtentReporter.extentLogger("Check sorting", ListToSort + " " + "List is not sorted");
					logger.info(ListToSort + "List is notsorted");
					isSorted = false;
				}
			} catch (Exception e) {
				// System.out.println(e);
			}
		} else {
			logger.info("The size of the list is zero");
			ExtentReporter.extentLogger("",
					ListToSort + " " + "There are no elements in the list to check the sort order");
		}
		return isSorted;
	}


	/**
	 * Function for hard sleep
	 *
	 * @param seconds
	 */
	public void sleep(int seconds) {
		try {
			int ms = 1000 * seconds;
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Function to generate random integer of specified maxValue
	 *
	 * @param maxValue
	 * @return
	 */
	public String generateRandomInt(int maxValue) {
		Random rand = new Random();
		int x = rand.nextInt(maxValue);
		String randomInt = Integer.toString(x);
		return randomInt;
	}

	public static String RandomIntegerGenerator(int n) {
		String number = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (number.length() * Math.random());

			sb.append(number.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * Function to generate Random String of length 4
	 *
	 * @return
	 */
	public String generateRandomString(int size) {
		String strNumbers = "abcdefghijklmnopqrstuvwxyzacvbe";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(9);
		strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		String s1 = strRandomNumber.toString().toUpperCase();
		for (int i = 1; i < size; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		return s1 + strRandomNumber.toString();
	}

	/**
	 * Function to generate Random characters of specified length
	 *
	 * @param candidateChars
	 * @param length
	 * @return
	 */
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}

	/**
	 * Function to generate Random Integer between range
	 *
	 * @param maxValue
	 * @param minValue
	 * @return
	 * @throws InterruptedException
	 */
	public String generateRandomIntwithrange(int maxValue, int minValue) throws InterruptedException {
		Thread.sleep(2000);
		Random rand = new Random();
		int x = rand.nextInt(maxValue - minValue) + 1;
		String randomInt = Integer.toString(x);
		System.out.println("Auto generate of number from generic method : " + randomInt);
		return randomInt;
	}

	/**
	 * Function Convert from String to Integer @param(String)
	 */
	public int convertToInt(String string) {
		int i = Integer.parseInt(string);
		return i;
	}

	/**
	 * Function Convert from Integer to String @param(integer)
	 */
	public String convertToString(int i) {
		String string = Integer.toString(i);
		return string;
	}

	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		System.out.println(t.length);

		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}

		return num;
	}

	public static String RandomStringGenerator(int n) {
		{
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());

				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		}
	}

	public static String getParameterFromXML(String param) {
		return ExtentReporter.testContext.getCurrentXmlTest().getParameter(param);
	}



	//	// execute query from DB
	//	public static String executeQuery1(String dbTable,int column_no) throws SQLException, ClassNotFoundException {
	//		// Setting the driver
	//		String ref_no = null;
	//		Class.forName("com.mysql.cj.jdbc.Driver");
	//		try {
	//			// Open a connection to the database
	//
	//			java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
	//			java.sql.Statement st = con.createStatement();
	//
	//
	//			java.sql.ResultSet rs = st.executeQuery(dbTable);
	//
	//			System.out.println("=========================================");
	//			while (rs.next()) {
	//				
	//				ref_no = rs.getString(column_no);
	//				System.out.println("Database Value: " + ref_no);
	//			}
	//				System.out.println("=========================================");
	//
	//			con.close();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return ref_no;
	//	}


	// execute query from DB
	public static String executeQuery(String dbTable,int column_no) throws SQLException, ClassNotFoundException {
		// Setting the driver
		String ref_no = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			// Open a connection to the database

			java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
			java.sql.Statement st = con.createStatement();


			java.sql.ResultSet rs = st.executeQuery(dbTable);


			while (rs.next()) {
				System.out.println("=========================================");
				ref_no = rs.getString(column_no);
				System.out.println("Database Value: " + ref_no);
				System.out.println("=========================================");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ref_no;
	}




	//	public static String deleteQuery() throws SQLException, ClassNotFoundException {
	//
	//
	//		//	Connection conn = null;
	//		//	   Statement stmt = null;
	//		try{
	//			Class.forName("com.mysql.cj.jdbc.Driver");
	//			Connection con = DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
	//			Statement st = con.createStatement();
	//
	//			//		   DELETE FROM db_tradofina.users
	//			//		   WHERE mobile_number=9029698425
	//
	//			String sql = "DELETE FROM tablename WHERE id = idno;";
	//			st.executeUpdate(sql);
	//
	//			st.close();
	//
	//
	//		}
	//		catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//
	//	}



	public static void deleteRow(String query) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		Statement st = con.createStatement();
		String sql = query ;         
		st.executeUpdate(sql);
		st.close();
	}



	public void executeUpdate(String updateExecutionQuery , String dbTable) throws ClassNotFoundException, SQLException {



		// Setting the driver
		Class.forName("com.mysql.cj.jdbc.Driver");



		// Open a connection to the database
		java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		java.sql.Statement st = con.createStatement();



		// Executing the update query
		st.executeUpdate(updateExecutionQuery);



		java.sql.ResultSet rs = st.executeQuery(dbTable);
		System.out.println("=================================================================================================");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
			+ " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
			+ rs.getString(8));
		}
		con.close();
	}

	public void executeInsert(String dbTable/*String refNo, String eligibilityType, String approved_amount,String offer_id,String repeat_offer_id*/) throws ClassNotFoundException, SQLException {



		// Setting the driver
		Class.forName("com.mysql.cj.jdbc.Driver");



		// Open a connection to the database
		java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		java.sql.Statement st = con.createStatement();



		java.sql.PreparedStatement ps = con.prepareStatement(dbTable /*"Insert into db_tradofina.instaloan_whitelisted_users(user_reference_number,eligible_type,approved_amount,offer_id,repeat_offer_id)VALUES('" +refNo+ "','" +eligibilityType+ "','" +approved_amount+ "','" +offer_id+ "','" +repeat_offer_id+ "')"*/);
		ps.executeUpdate();
		con.close();
	}

	/**
	 * 
	 * @param data
	 * @param url
	 * @return
	 * @throws Exception 
	 */

	public static ValidatableResponse SendOtpAPI(Object[][] data) throws Exception {

		try {
			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sendOtpEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source_app", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source_app", req_body.get("source_app"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="SendOtpAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}


	//	public static ValidatableResponse LessThan10DigitAPI(Object[][] data) {
	//
	//		Random rand = new Random();
	//
	//		String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sendOtpEndPoint);
	//		System.out.println("Url :" + url);
	//
	//		HashMap<String, String> req_body = new HashMap<>();
	//		// System.out.println((String) data[0][3]);
	//		req_body.put("source_app", (String) data[0][0]);
	//		req_body.put("mobile_number", (String) data[0][1]);
	//
	//		JSONObject Myrequestbody = new JSONObject();
	//
	//		Myrequestbody.put("source_app", req_body.get("source_app"));
	//		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
	//
	//		HashMap<String, Object> headers = new HashMap<>();
	//		headers.put("x-request-id", rand.nextInt(1001));
	//		headers.put("X-Client-App", "android");
	//		headers.put("X-Client-Version", 4.9);
	//		headers.put("X-Client-OS-Type", "android");
	//		headers.put("X-Client-OS-Version", 10);
	//		headers.put("x-login-token",
	//				"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
	//		headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
	//		headers.put("x-login-timestamp", "1636960116339");
	//
	//		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
	//				.body(Myrequestbody.toJSONString()).when().post().then();
	//
	//		System.out.println("Request Url -->" + url);
	//		ExtentReporter.extentLogger("", "Request Url -->" + url);
	//		System.out.println("Request :" + Myrequestbody);
	//		ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
	//		String Resp = response.extract().body().asString();
	//		System.out.println("Response Body= " + Resp);
	//		ExtentReporter.extentLogger("", "Response Body= " + Resp);
	//
	//		return response;
	//	}
	//
	//	public static ValidatableResponse MoreThan10DigitAPI(Object[][] data) {
	//
	//		Random rand = new Random();
	//
	//		String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sendOtpEndPoint);
	//		System.out.println("Url :" + url);
	//
	//		HashMap<String, String> req_body = new HashMap<>();
	//		// System.out.println((String) data[0][3]);
	//		req_body.put("source_app", (String) data[0][0]);
	//		req_body.put("mobile_number", (String) data[0][1]);
	//
	//		JSONObject Myrequestbody = new JSONObject();
	//
	//		Myrequestbody.put("source_app", req_body.get("source_app"));
	//		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
	//
	//		HashMap<String, Object> headers = new HashMap<>();
	//		headers.put("x-request-id", rand.nextInt(1001));
	//		headers.put("X-Client-App", "android");
	//		headers.put("X-Client-Version", 4.9);
	//		headers.put("X-Client-OS-Type", "android");
	//		headers.put("X-Client-OS-Version", 10);
	//		headers.put("x-login-token",
	//				"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
	//		headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
	//		headers.put("x-login-timestamp", "1636960116339");
	//
	//		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
	//				.body(Myrequestbody.toJSONString()).when().post().then();
	//
	//		System.out.println("Request Url -->" + url);
	//		ExtentReporter.extentLogger("", "Request Url -->" + url);
	//		System.out.println("Request :" + Myrequestbody);
	//		ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
	//		String Resp = response.extract().body().asString();
	//		System.out.println("Response Body= " + Resp);
	//		ExtentReporter.extentLogger("", "Response Body= " + Resp);
	//
	//		return response;
	//	}
	//
	//	public static ValidatableResponse SpecialCharacterInFieldAPI(Object[][] data) {
	//
	//		Random rand = new Random();
	//
	//		String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sendOtpEndPoint);
	//		System.out.println("Url :" + url);
	//
	//		HashMap<String, String> req_body = new HashMap<>();
	//		// System.out.println((String) data[0][3]);
	//		req_body.put("source_app", (String) data[0][0]);
	//		req_body.put("mobile_number", (String) data[0][1]);
	//
	//		JSONObject Myrequestbody = new JSONObject();
	//
	//		Myrequestbody.put("source_app", req_body.get("source_app"));
	//		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
	//
	//		HashMap<String, Object> headers = new HashMap<>();
	//		headers.put("x-request-id", rand.nextInt(1001));
	//		headers.put("X-Client-App", "android");
	//		headers.put("X-Client-Version", 4.9);
	//		headers.put("X-Client-OS-Type", "android");
	//		headers.put("X-Client-OS-Version", 10);
	//		headers.put("x-login-token",
	//				"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
	//		headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
	//		headers.put("x-login-timestamp", "1636960116339");
	//
	//		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
	//				.body(Myrequestbody.toJSONString()).when().post().then();
	//
	//		System.out.println("Request Url -->" + url);
	//		ExtentReporter.extentLogger("", "Request Url -->" + url);
	//		System.out.println("Request :" + Myrequestbody);
	//		ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
	//		String Resp = response.extract().body().asString();
	//		System.out.println("Response Body= " + Resp);
	//		ExtentReporter.extentLogger("", "Response Body= " + Resp);
	//
	//		return response;
	//	}

	/**
	 * 
	 * @param data
	 * @param url
	 * @return
	 */

	public static ValidatableResponse API(Object[][] data, String url) {
		Random rand = new Random();

		HashMap<String, String> req_body = new HashMap<>();
		// System.out.println((String) data[0][3]);
		req_body.put("source_app", (String) data[0][0]);
		req_body.put("mobile_number", (String) data[0][1]);
		// try{
		// req_body.put("otp", (String) data[0][2]);
		// req_body.put("client_id", (String) data[0][3]);
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		JSONObject Myrequestbody = new JSONObject();

		Myrequestbody.put("source_app", req_body.get("source_app"));
		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
		// Myrequestbody.put("otp", req_body.get("otp"));
		// Myrequestbody.put("client_id", req_body.get("client_id"));

		HashMap<String, Object> headers = new HashMap<>();
		headers.put("x-request-id", rand.nextInt(1001));
		headers.put("X-Client-App", "android");
		headers.put("X-Client-Version", 4.9);
		headers.put("X-Client-OS-Type", "android");
		headers.put("X-Client-OS-Version", 10);
		headers.put("x-login-token",
				"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
		headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
		headers.put("x-login-timestamp", "1636960116339");

		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
				.body(Myrequestbody.toJSONString()).when().post().then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);
		System.out.println("Request :" + Myrequestbody);
		ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
		String Resp = response.extract().body().asString();
		System.out.println("Response Body= " + Resp);
		ExtentReporter.extentLogger("", "Response Body= " + Resp);
		return response;
	}

	// New ====================================

	/**
	 * 
	 * @param data
	 * @param url
	 * @return
	 * @throws IOException
	 */

	// ============== Post Response ======================

	public static ValidatableResponse postMethodAPI(HashMap<String, Object> headers, JSONObject Myrequestbody,
			String url) throws IOException {

		ValidatableResponse response = RestAssured.given()
				.baseUri(url)
				.contentType(ContentType.JSON)
				.headers(headers)
				.body(Myrequestbody)
				.when()
				.post()
				.then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	// ============== Post Response ======================

	public static ValidatableResponse postMethodAuthAPI(HashMap<String, Object> headers, String request,
			String url) throws IOException {

		ValidatableResponse response = RestAssured.given()
				.auth().basic("tslG1aMU12HlAuBY8PiS6vkVtxyWKDk5", "26E1foPXLUtbiFwkizn09LnnCL9ZQS5f")
				.baseUri(url)
				.contentType(ContentType.JSON)
				.headers(headers)
				.body(request)
				.when()
				.post()
				.then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	public static ValidatableResponse postMethodWithHeadersAPI(HashMap<String, Object> headers, String url)
			throws IOException {

		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
				.when().post().then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	// ============== Get Response ======================

	public static ValidatableResponse getMethodAPI(String url) throws IOException {

		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).when().get()
				.then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	public static ValidatableResponse getMethodWithHeaderAPI(HashMap<String, Object> headers, String url)
			throws IOException {

		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
				.when().get().then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	public static ValidatableResponse patchMethodAPI(HashMap<String, Object> headers, JSONObject Myrequestbody,
			String url) throws IOException {

		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
				.body(Myrequestbody.toJSONString()).when().patch().then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	public static ValidatableResponse patchMethodWithHeaderAPI(HashMap<String, Object> headers, String url)
			throws IOException {

		ValidatableResponse response = RestAssured.given().baseUri(url).contentType(ContentType.JSON).headers(headers)
				.when().patch().then();

		System.out.println("Request Url -->" + url);
		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}



	public static ValidatableResponse MockuserAPI_LTBC1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			Mobile_Number = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.excelWrite(filePath, "SendOtp", Mobile_Number, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 1, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 2, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 3, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 4, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);

			//			PromoCode_UpdateUser
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 1, 5);


			//			PromoCode_UpdateUser-Segment1
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 10, 3);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 10, 4);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 10, 5);



			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);

			//			Segment 1
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 10, 3);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 10, 4);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 10, 5);


			// Data to Register_user
			ExcelWriteData.excelWrite(filePath, "RegisterUser", Mobile_Number, 1, 3);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.excelWrite(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.excelWrite(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	// MockuserAPI Merchant
	public static ValidatableResponse MockuserAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			Mobile_Number = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.excelWrite(filePath, "SendOtp", Mobile_Number, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 1, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 2, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 3, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 4, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);

			//			PromoCode_UpdateUser
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 1, 5);


			//			PromoCode_UpdateUser-Segment1
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 10, 3);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 10, 4);
			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 10, 5);



			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);

			//			Segment 1
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 10, 3);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 10, 4);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 10, 5);


			// Data to Register_user
			ExcelWriteData.excelWrite(filePath, "RegisterUser", Mobile_Number, 1, 3);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.excelWrite(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.excelWrite(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	MockuserAPI_Merchant_S1
	public static ValidatableResponse MockuserAPI_Merchant_S1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			Mobile_Number_S1 = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number_S1);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.excelWrite(filePath, "SendOtp", Mobile_Number_S1, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number_S1, 1, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number_S1, 2, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number_S1, 3, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number_S1, 4, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", Mobile_Number_S1, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);
//
//			//			PromoCode_UpdateUser
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 1, 4);
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 1, 5);
//
//
//			//			PromoCode_UpdateUser-Segment1
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 10, 3);
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 10, 4);
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 10, 5);



			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

//			//			MerchantQrCodeUpdateuser
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);

			//			Segment 1
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);


			// Data to Register_user
			ExcelWriteData.excelWrite(filePath, "RegisterUser", Mobile_Number_S1, 1, 3);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 1, 8);

//			//			Segment1
//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.excelWrite(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.excelWrite(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	MockuserAPI_PromoCode
	public static ValidatableResponse MockuserAPI_PromoCode(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			MobileNumber_PromoCode = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + MobileNumber_PromoCode);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "SendOtp", MobileNumber_PromoCode, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode, 2, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode, 3, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode, 4, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

			//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
			//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);

			//			PromoCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", dob, 1, 5);


//			//			PromoCode_UpdateUser-Segment1
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 10, 3);
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 10, 4);
//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 10, 5);



			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

//			//			MerchantQrCodeUpdateuser
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);
//
//			//			Segment 1
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 10, 3);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 10, 4);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 10, 5);


			// Data to Register_user
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", MobileNumber_PromoCode, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

//			//			Segment1
//			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 2, 4);
//			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);


			//		ExtentReporter.extentLoggerPass(message+" - Passed");

			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	MockuserAPI_PromoCode_S1
	public static ValidatableResponse MockuserAPI_PromoCode_Segment1(Object[][] data) throws Exception {

		//		try
		//		{

		String filePath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

		String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
		logger.info("Url :" + url);

		ExtentReporter.extentLogger("url", url);

		Random rand = new Random();

		HashMap<String, String> req_body = new HashMap<>();
		req_body.put("gender", (String) data[0][0]);
		req_body.put("encrypted_name", (String) data[0][1]);


		JSONObject Myrequestbody = new JSONObject();

		Myrequestbody.put("gender", req_body.get("gender"));
		Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

		String req=String.valueOf(Myrequestbody);
		ExtentReporter.extentLogger("req_body", "Request :"+req);

		HashMap<String, Object> headers = new HashMap<>();
		headers.put("client-id", "zx2789");

		String header=String.valueOf(headers);
		ExtentReporter.extentLogger("headers","Headers :"+ header);


		ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

		logger.info("Request :" + Myrequestbody);

		ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

		String Resp = response.extract().body().asString();

		logger.info("Response Body= " + Resp);

		ExtentReporter.extentLogger("", "Response Body= " + Resp);

		// fetching Mobileno
		MobileNumber_PromoCode_S1 = response.extract().body().jsonPath().get("data.data.mobile_number");
		logger.info("MobileNumber : " + MobileNumber_PromoCode_S1);

		// fetching Otp
		Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
		logger.info("OTP : " + Otp);


		// fetching motherName
		String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
		logger.info("MotherName : " + motherName);

		// fetching Email
		String email = response.extract().body().jsonPath().get("data.data.email");
		logger.info("Email : " + email);

		// fetching Dob
		String dob = response.extract().body().jsonPath().get("data.data.dob");
		logger.info("Dob : " + dob);

		// fetching imei
		String imei = response.extract().body().jsonPath().get("data.data.imei");
		logger.info("imei : " + imei);


		// fetching motherName
		String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
		logger.info("motherName : " + mother_name);


		// fetching androidId
		String androidId = response.extract().body().jsonPath().get("data.data.android_id");
		logger.info("androidId : " + androidId);


		// fetching advertisingId
		String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
		logger.info("advertisingId : " + advertisingId);


		//			// fetching gender
		//			String gender = response.extract().body().jsonPath().get("data.data.gender");
		//			logger.info("gender : " + gender);


		// ================== Write Excel =======================

		// MobileNo to SentOtp
//		ExcelWriteData.excelWrite(filePath, "SendOtp", MobileNumber_PromoCode_S1, 1, 2);


		// MobileNo to User_Authenticate
//		ExcelWriteData.excelWrite(filePath, "User_Authenticate", MobileNumber_PromoCode_S1, 1, 2);
		ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode_S1, 2, 2);
		ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode_S1, 3, 2);
		ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode_S1, 4, 2);
		ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PromoCode_S1, 5, 2);

		// Data to UpdateUser
		// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
		// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
		// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

		//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
		//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
		//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);

		//			PromoCode_UpdateUser-Segment1
		ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
		ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", email, 1, 4);
		ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", dob, 1, 5);



		//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

		//			//			MerchantQrCodeUpdateuser
		//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
		//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
		//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);
		//
		//			//			Segment 1
		//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 10, 3);
		//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 10, 4);
		//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 10, 5);


		// Data to Register_user
//		ExcelWriteData.excelWrite(filePath, "RegisterUser", MobileNumber_PromoCode_S1, 1, 3);
		ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
		ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);


		// Data to User_Onboarding
		ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

		// Data to Create_Bnpl_transaction
		ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

		// Data to Home_Screen_for_Current_Spends

		ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
		ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
		ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


		// Data to Update User
		ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

		// Data to gender
		ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);


		//		ExtentReporter.extentLoggerPass(message+" - Passed");

		return response;


	}
	//		catch(Exception e)
	//		{
	//			String message="MockuserAPI";
	//			ExtentReporter.extentLogger("",message);
	//			ExtentReporter.extentLoggerFail(e.getMessage());
	//			return null;
	//		}
	//
	//	}






	//	MockuserAPI_PlayStore
	public static ValidatableResponse MockuserAPI_PlayStore(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			MobileNumber_PlayStore = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + MobileNumber_PlayStore);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.excelWrite(filePath, "SendOtp", MobileNumber_PlayStore, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", MobileNumber_PlayStore, 1, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", MobileNumber_PlayStore, 2, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", MobileNumber_PlayStore, 3, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", MobileNumber_PlayStore, 4, 2);
			ExcelWriteData.excelWrite(filePath, "User_Authenticate", MobileNumber_PlayStore, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);

			//			//			PromoCode_UpdateUser
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 1, 5);


			//			//			PromoCode_UpdateUser-Segment1
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 10, 3);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 10, 4);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 10, 5);



			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);
			//
			//			//			Segment 1
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 10, 3);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 10, 4);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 10, 5);


			// Data to Register_user
			ExcelWriteData.excelWrite(filePath, "RegisterUser", MobileNumber_PlayStore, 1, 3);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.excelWrite(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.excelWrite(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.excelWrite(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.excelWrite(filePath, "UpdateUserStatus", mother_name, 1, 2);


			//		ExtentReporter.extentLoggerPass(message+" - Passed");

			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	MockuserAPI_PlayStore_S1
	public static ValidatableResponse MockuserAPI_PlayStore_Segment1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			MobileNumber_PlayStore_S1 = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + MobileNumber_PlayStore_S1);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "SendOtp", MobileNumber_PlayStore_S1, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PlayStore_S1, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PlayStore_S1, 2, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PlayStore_S1, 3, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PlayStore_S1, 4, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", MobileNumber_PlayStore_S1, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);

			//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
			//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", email, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "PlayStore_UpdateUser", dob, 1, 5);

			//			PlayStore_Segment1
			ExcelWriteData.DemoExcel(filePath, "PlayStore_UpdateUser", motherName, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "PlayStore_UpdateUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "PlayStore_UpdateUser", dob, 1, 5);




			//			//			PromoCode_UpdateUser
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 1, 3);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 1, 5);
			//
			//
			//			//			PromoCode_UpdateUser-Segment1
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", motherName, 10, 3);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", email, 10, 4);
			//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", dob, 10, 5);
			//
			//
			//
			//			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);
			//
			//			//			MerchantQrCodeUpdateuser
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);
			//
			//			//			Segment 1
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", motherName, 10, 3);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", email, 10, 4);
			//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", dob, 10, 5);


			// Data to Register_user
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", MobileNumber_PlayStore_S1, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);


			//		ExtentReporter.extentLoggerPass(message+" - Passed");

			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	// MockuserAPI Merchant LTBC1
	public static ValidatableResponse MockuserAPI_RingPolicy_LTBC1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			Mobile_Number_LTBC1 = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number_LTBC1);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "SendOtp", Mobile_Number_LTBC1, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_LTBC1, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_LTBC1, 2, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_LTBC1, 3, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_LTBC1, 4, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_LTBC1, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);


			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);



			// Data to Register_user
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", Mobile_Number_LTBC1, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	// MockuserAPI Merchant BC1
	public static ValidatableResponse MockuserAPI_RingPolicy_BC1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			Mobile_Number_BC1 = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number_BC1);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "SendOtp", Mobile_Number_BC1, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC1, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC1, 2, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC1, 3, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC1, 4, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC1, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);


			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);



			// Data to Register_user
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", Mobile_Number_BC1, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	// MockuserAPI Merchant L1
	public static ValidatableResponse MockuserAPI_RingPolicy_L1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			Mobile_Number_L1 = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number_L1);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "SendOtp", Mobile_Number_L1, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L1, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L1, 2, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L1, 3, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L1, 4, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L1, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);


			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);



			// Data to Register_user
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", Mobile_Number_L1, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	// MockuserAPI Merchant L2
		public static ValidatableResponse MockuserAPI_RingPolicy_L2(Object[][] data) throws Exception {

			try
			{

				String filePath = System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L2_stage.xlsx";

				String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
				logger.info("Url :" + url);

				ExtentReporter.extentLogger("url", url);

				Random rand = new Random();

				HashMap<String, String> req_body = new HashMap<>();
				req_body.put("gender", (String) data[0][0]);
				req_body.put("encrypted_name", (String) data[0][1]);


				JSONObject Myrequestbody = new JSONObject();

				Myrequestbody.put("gender", req_body.get("gender"));
				Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

				String req=String.valueOf(Myrequestbody);
				ExtentReporter.extentLogger("req_body", "Request :"+req);

				HashMap<String, Object> headers = new HashMap<>();
				headers.put("client-id", "zx2789");

				String header=String.valueOf(headers);
				ExtentReporter.extentLogger("headers","Headers :"+ header);


				ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

				logger.info("Request :" + Myrequestbody);

				ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

				String Resp = response.extract().body().asString();

				logger.info("Response Body= " + Resp);

				ExtentReporter.extentLogger("", "Response Body= " + Resp);

				// fetching Mobileno
				//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
				//			logger.info("MobileNumber : " + mobileNumber);

				Mobile_Number_L2 = response.extract().body().jsonPath().get("data.data.mobile_number");
				logger.info("MobileNumber : " + Mobile_Number_L2);

				// fetching Otp
				Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
				logger.info("OTP : " + Otp);


				// fetching motherName
				String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
				logger.info("MotherName : " + motherName);

				// fetching Email
				String email = response.extract().body().jsonPath().get("data.data.email");
				logger.info("Email : " + email);

				// fetching Dob
				String dob = response.extract().body().jsonPath().get("data.data.dob");
				logger.info("Dob : " + dob);

				// fetching imei
				String imei = response.extract().body().jsonPath().get("data.data.imei");
				logger.info("imei : " + imei);


				// fetching motherName
				String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
				logger.info("motherName : " + mother_name);


				// fetching androidId
				String androidId = response.extract().body().jsonPath().get("data.data.android_id");
				logger.info("androidId : " + androidId);


				// fetching advertisingId
				String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
				logger.info("advertisingId : " + advertisingId);


				//			// fetching gender
				//			String gender = response.extract().body().jsonPath().get("data.data.gender");
				//			logger.info("gender : " + gender);


				// ================== Write Excel =======================

				// MobileNo to SentOtp
				ExcelWriteData.DemoExcel(filePath, "SendOtp", Mobile_Number_L2, 1, 2);


				// MobileNo to User_Authenticate
				ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L2, 1, 2);
				ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L2, 2, 2);
				ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L2, 3, 2);
				ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L2, 4, 2);
				ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L2, 5, 2);

				// Data to UpdateUser
				// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
				// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
				// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);


				//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

				//			MerchantQrCodeUpdateuser
				ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
				ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
				ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);



				// Data to Register_user
				ExcelWriteData.DemoExcel(filePath, "RegisterUser", Mobile_Number_L2, 1, 3);
				ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
				ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

				//			//			Segment1
				//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
				//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

				// Data to User_Onboarding
				ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

				// Data to Create_Bnpl_transaction
				ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

				// Data to Home_Screen_for_Current_Spends

				ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
				ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
				ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


				// Data to Update User
				ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

				// Data to gender
				ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

				//	===============================================================================================


				//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
				//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


				return response;


			}
			catch(Exception e)
			{
				String message="MockuserAPI";
				ExtentReporter.extentLogger("",message);
				ExtentReporter.extentLoggerFail(e.getMessage());
				return null;
			}

		}
	
		
		
		// MockuserAPI Merchant L3
				public static ValidatableResponse MockuserAPI_RingPolicy_L3(Object[][] data) throws Exception {

					try
					{

						String filePath = System.getProperty("user.dir")
								+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L3_stage.xlsx";

						String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
						logger.info("Url :" + url);

						ExtentReporter.extentLogger("url", url);

						Random rand = new Random();

						HashMap<String, String> req_body = new HashMap<>();
						req_body.put("gender", (String) data[0][0]);
						req_body.put("encrypted_name", (String) data[0][1]);


						JSONObject Myrequestbody = new JSONObject();

						Myrequestbody.put("gender", req_body.get("gender"));
						Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

						String req=String.valueOf(Myrequestbody);
						ExtentReporter.extentLogger("req_body", "Request :"+req);

						HashMap<String, Object> headers = new HashMap<>();
						headers.put("client-id", "zx2789");

						String header=String.valueOf(headers);
						ExtentReporter.extentLogger("headers","Headers :"+ header);


						ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

						logger.info("Request :" + Myrequestbody);

						ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

						String Resp = response.extract().body().asString();

						logger.info("Response Body= " + Resp);

						ExtentReporter.extentLogger("", "Response Body= " + Resp);

						// fetching Mobileno
						//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
						//			logger.info("MobileNumber : " + mobileNumber);

						Mobile_Number_L3 = response.extract().body().jsonPath().get("data.data.mobile_number");
						logger.info("MobileNumber : " + Mobile_Number_L3);

						// fetching Otp
						Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
						logger.info("OTP : " + Otp);


						// fetching motherName
						String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
						logger.info("MotherName : " + motherName);

						// fetching Email
						String email = response.extract().body().jsonPath().get("data.data.email");
						logger.info("Email : " + email);

						// fetching Dob
						String dob = response.extract().body().jsonPath().get("data.data.dob");
						logger.info("Dob : " + dob);

						// fetching imei
						String imei = response.extract().body().jsonPath().get("data.data.imei");
						logger.info("imei : " + imei);


						// fetching motherName
						String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
						logger.info("motherName : " + mother_name);


						// fetching androidId
						String androidId = response.extract().body().jsonPath().get("data.data.android_id");
						logger.info("androidId : " + androidId);


						// fetching advertisingId
						String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
						logger.info("advertisingId : " + advertisingId);


						//			// fetching gender
						//			String gender = response.extract().body().jsonPath().get("data.data.gender");
						//			logger.info("gender : " + gender);


						// ================== Write Excel =======================

						// MobileNo to SentOtp
						ExcelWriteData.DemoExcel(filePath, "SendOtp", Mobile_Number_L3, 1, 2);


						// MobileNo to User_Authenticate
						ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L3, 1, 2);
						ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L3, 2, 2);
						ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L3, 3, 2);
						ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L3, 4, 2);
						ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_L3, 5, 2);

						// Data to UpdateUser
						// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
						// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
						// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);


						//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

						//			MerchantQrCodeUpdateuser
						ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
						ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
						ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);



						// Data to Register_user
						ExcelWriteData.DemoExcel(filePath, "RegisterUser", Mobile_Number_L3, 1, 3);
						ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
						ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

						//			//			Segment1
						//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
						//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

						// Data to User_Onboarding
						ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

						// Data to Create_Bnpl_transaction
						ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

						// Data to Home_Screen_for_Current_Spends

						ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
						ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
						ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


						// Data to Update User
						ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

						// Data to gender
						ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

						//	===============================================================================================


						//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
						//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


						return response;


					}
					catch(Exception e)
					{
						String message="MockuserAPI";
						ExtentReporter.extentLogger("",message);
						ExtentReporter.extentLoggerFail(e.getMessage());
						return null;
					}

				}
		
	
	// MockuserAPI Merchant BC2
	public static ValidatableResponse MockuserAPI_RingPolicy_BC2(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC2_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.mockUserEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("gender", (String) data[0][0]);
			req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);

			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);

			String Resp = response.extract().body().asString();

			logger.info("Response Body= " + Resp);

			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching Mobileno
			//			String mobileNumber = response.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			Mobile_Number_BC2 = response.extract().body().jsonPath().get("data.data.mobile_number");
			logger.info("MobileNumber : " + Mobile_Number_BC2);

			// fetching Otp
			Integer Otp = response.extract().body().jsonPath().get("data.data.otp");
			logger.info("OTP : " + Otp);


			// fetching motherName
			String motherName = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("MotherName : " + motherName);

			// fetching Email
			String email = response.extract().body().jsonPath().get("data.data.email");
			logger.info("Email : " + email);

			// fetching Dob
			String dob = response.extract().body().jsonPath().get("data.data.dob");
			logger.info("Dob : " + dob);

			// fetching imei
			String imei = response.extract().body().jsonPath().get("data.data.imei");
			logger.info("imei : " + imei);


			// fetching motherName
			String mother_name = response.extract().body().jsonPath().get("data.data.mother_name");
			logger.info("motherName : " + mother_name);


			// fetching androidId
			String androidId = response.extract().body().jsonPath().get("data.data.android_id");
			logger.info("androidId : " + androidId);


			// fetching advertisingId
			String advertisingId = response.extract().body().jsonPath().get("data.data.advertising_id");
			logger.info("advertisingId : " + advertisingId);


			//			// fetching gender
			//			String gender = response.extract().body().jsonPath().get("data.data.gender");
			//			logger.info("gender : " + gender);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "SendOtp", Mobile_Number_BC2, 1, 2);


			// MobileNo to User_Authenticate
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC2, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC2, 2, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC2, 3, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC2, 4, 2);
			ExcelWriteData.DemoExcel(filePath, "User_Authenticate", Mobile_Number_BC2, 5, 2);

			// Data to UpdateUser
			// ExcelWriteData.excelWrite(filePath," UpdateUser",firstName,1,1);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",middleName,1,2);
			// ExcelWriteData.excelWrite(filePath," UpdateUser",lastName,1,3);


			//			ExcelWriteData.excelWrite(filePath, " UpdateUser", gender, 1, 6);

			//			MerchantQrCodeUpdateuser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", motherName, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", dob, 1, 5);



			// Data to Register_user
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", Mobile_Number_BC2, 1, 3);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", email, 1, 4);
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", imei, 1, 8);

			//			//			Segment1
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", email, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RegisterUser", imei, 2, 8);

			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", imei, 1, 4);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", imei, 1, 6);

			// Data to Home_Screen_for_Current_Spends

			ExcelWriteData.DemoExcel(filePath, "Current_Spend", androidId, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", advertisingId, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", imei, 1, 4);


			// Data to Update User
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			// Data to gender
			ExcelWriteData.DemoExcel(filePath, "UpdateUserStatus", mother_name, 1, 2);

			//	===============================================================================================


			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 2, 1);
			//			ExcelWriteData.DemoExcel(filePath, "Get_Details_VPN", mother_name, 3, 1);


			return response;


		}
		catch(Exception e)
		{
			String message="MockuserAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




	/**
	 * 
	 * @param data
	 * @param url
	 * @return
	 * @throws Exception 
	 */

	public static ValidatableResponse OnloadAPI() throws Exception {


		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.onloadEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);
			Random rand = new Random();


			ValidatableResponse response = Utilities.getMethodAPI(url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="OnloadAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	} 



	public static ValidatableResponse Get_Details_VPA_API(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			//		HashMap<String, String> req_queryparam = new HashMap<>();
			//		req_queryparam.put("vpa", (String) data[0][0]);
			//
			//		JSONObject Myrequestqueryparam = new JSONObject();
			//
			//		Myrequestqueryparam.put("vpa", req_queryparam.get("vpa"));
			//		System.out.println("hellloo: " + Myrequestqueryparam);
			//
			//
			//		String req=String.valueOf(Myrequestqueryparam);
			//		ExtentReporter.extentLogger("req_body", "Request :"+req);


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			//		logger.info("Request :" + Myrequestqueryparam);
			//		ExtentReporter.extentLogger("", "Request :" + Myrequestqueryparam);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	public static ValidatableResponse Get_Details_VPA_API_Merchant_Segment1(Object[][] data) throws Exception {

		
		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_queryparam = new HashMap<>();
			req_queryparam.put("vpa", (String) data[0][0]);

			JSONObject Myrequestqueryparam = new JSONObject();

			Myrequestqueryparam.put("vpa", req_queryparam.get("vpa"));
			System.out.println("hellloo: " + Myrequestqueryparam);
			//
			//
			//			String req=String.valueOf(Myrequestqueryparam);
			//			ExtentReporter.extentLogger("req_body", "Request :"+req);


			ValidatableResponse response = RestAssured.given()
					//					.baseUri(url)
					.contentType(ContentType.JSON)
					.when()
					//					.body(Myrequestqueryparam.toJSONString())
					//					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.queryParams(Myrequestqueryparam)	
					.baseUri(url)
					.get().then();


			//			logger.info("Request :" + Myrequestqueryparam);
			//			ExtentReporter.extentLogger("", "Request :" + Myrequestqueryparam);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
//			ExcelWriteData.excelWrite(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	public static ValidatableResponse Get_Details_VPA_API_RingPolicy_LTBC1(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			//		HashMap<String, String> req_queryparam = new HashMap<>();
			//		req_queryparam.put("vpa", (String) data[0][0]);
			//
			//		JSONObject Myrequestqueryparam = new JSONObject();
			//
			//		Myrequestqueryparam.put("vpa", req_queryparam.get("vpa"));
			//		System.out.println("hellloo: " + Myrequestqueryparam);
			//
			//
			//		String req=String.valueOf(Myrequestqueryparam);
			//		ExtentReporter.extentLogger("req_body", "Request :"+req);


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			//		logger.info("Request :" + Myrequestqueryparam);
			//		ExtentReporter.extentLogger("", "Request :" + Myrequestqueryparam);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			//		ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	RingPolicy_BC1
	public static ValidatableResponse Get_Details_VPA_API_RingPolicy_BC1(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			//		ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	RingPolicy_L1
	public static ValidatableResponse Get_Details_VPA_API_RingPolicy_L1(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			//		ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}

	
//	RingPolicy_L2
	public static ValidatableResponse Get_Details_VPA_API_RingPolicy_L2(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L2_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			//		ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}

	
//	RingPolicy_L3
	public static ValidatableResponse Get_Details_VPA_API_RingPolicy_L3(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L3_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			//		ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}
	

	//	RingPolicy_BC2
	public static ValidatableResponse Get_Details_VPA_API_RingPolicy_BC2(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC2_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getdetailsVPAEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();


			ValidatableResponse response = RestAssured.given()
					//				.baseUri(url)
					.contentType(ContentType.JSON).when()
					//				.body(Myrequestqueryparam.toJSONString())
					.queryParam("vpa", "rpy.ipmerchant1234193158@icici")
					.baseUri(url)
					.get().then();


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//  fetching merchant_reference_number

			String merchant_Reference_Number = response.extract().body().jsonPath().get("data.merchant_details.merchant_reference_number");
			logger.info("Request :" + merchant_Reference_Number);


			//  Write Excel

			//			Data to MerchantQRCode_UpdateUser
			ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 1, 8);
			//		ExcelWriteData.DemoExcel(filePath, "MerchantQRCode_UpdateUser", merchant_Reference_Number, 10, 8);

			return response;

		}
		catch (Exception e) {
			String message="Get_Details_VPA_API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	// userTokenAPI
	public static ValidatableResponse userTokenAPI(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			//			user_token = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	userTokenAPI_Repeat_Merchant
	public static ValidatableResponse userTokenAPI_Repeat(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			user_token = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token);
			ExtentReporter.extentLogger("user_token", user_token);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	// userTokenAPI_S1
	public static ValidatableResponse userTokenAPI_S1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			//			user_token = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//		userTokenAPI_Repeat_Merchant_S1
	public static ValidatableResponse userTokenAPI_Repeat_S1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			user_token_S1 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_S1);
			ExtentReporter.extentLogger("user_token", user_token_S1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	//	userTokenAPI_Repeat_PromoCode
	public static ValidatableResponse userTokenAPI_Repeat_PromoCode(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			user_token_promocode = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_promocode);
			ExtentReporter.extentLogger("user_token", user_token_promocode);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}

		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	// userTokenAPI_playstore
	public static ValidatableResponse userTokenAPI_PlayStore(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			//			user_token = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}	



	//		userTokenAPI_Repeat_PlayStore
	public static ValidatableResponse userTokenAPI_Repeat_PlayStore(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			user_token_playstore = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_playstore);
			ExtentReporter.extentLogger("user_token", user_token_playstore);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	// userTokenAPI_playstore_S1
	public static ValidatableResponse userTokenAPI_PlayStore_S1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			//			user_token = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}	



	//	userTokenAPI_Repeat_PlayStore_S1
	public static ValidatableResponse userTokenAPI_Repeat_PlayStore_S1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			user_token_playstore_S1 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_playstore_S1);
			ExtentReporter.extentLogger("user_token", user_token_playstore_S1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	userTokenAPI_RingPolicy_LTBC1
	public static ValidatableResponse userTokenAPI_RingPolicy_LTBC1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			////						UserToken
			//			user_token_LTBC1 = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token_LTBC1);
			//			ExtentReporter.extentLogger("user_token", user_token_LTBC1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	userTokenAPI_RingPolicy_BC1
	public static ValidatableResponse userTokenAPI_Positive_BC1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			////						UserToken
			//			user_token_LTBC1 = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token_LTBC1);
			//			ExtentReporter.extentLogger("user_token", user_token_LTBC1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	userTokenAPI_RingPolicy_BC2
	public static ValidatableResponse userTokenAPI_RingPolicy_BC2(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			////						UserToken
			//			user_token_LTBC1 = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token_LTBC1);
			//			ExtentReporter.extentLogger("user_token", user_token_LTBC1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}
	

	//	userTokenAPI_RingPolicy_L1
	public static ValidatableResponse userTokenAPI_Positive_L1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			////						UserToken
			//			user_token_LTBC1 = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token_LTBC1);
			//			ExtentReporter.extentLogger("user_token", user_token_LTBC1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


//	userTokenAPI_RingPolicy_L2
	public static ValidatableResponse userTokenAPI_Positive_L2(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	

	//	userTokenAPI_RingPolicy_Repeat_BC1
	public static ValidatableResponse userTokenAPI_Repeat_BC1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////		UserToken
			user_token_BC1 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_BC1);
			ExtentReporter.extentLogger("user_token", user_token_BC1);
			ExtentReporter.extentLogger("user_token", user_token_BC1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	userTokenAPI_RingPolicy_L3
	public static ValidatableResponse userTokenAPI_Positive_L3(Object[][] data) throws Exception {
		
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}	
	

	//	userTokenAPI_RingPolicy_Repeat_L1
	public static ValidatableResponse userTokenAPI_Repeat_L1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////		UserToken
			user_token_L1 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_L1);
			ExtentReporter.extentLogger("user_token", user_token_L1);
			ExtentReporter.extentLogger("user_token", user_token_L1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	userTokenAPI_RingPolicy_Repeat_L2
	public static ValidatableResponse userTokenAPI_Repeat_L2(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////		UserToken
			user_token_L2 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_L2);
			ExtentReporter.extentLogger("user_token", user_token_L2);
			ExtentReporter.extentLogger("user_token", user_token_L2);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}
	
	
//	userTokenAPI_RingPolicy_Repeat_L3
	public static ValidatableResponse userTokenAPI_Repeat_L3(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////		UserToken
			user_token_L3 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_L3);
			ExtentReporter.extentLogger("user_token", user_token_L3);
			ExtentReporter.extentLogger("user_token", user_token_L3);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
	

	//	userTokenAPI_RingPolicy_BC2
	public static ValidatableResponse userTokenAPI_Repeat_BC2(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			user_token_BC2 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_BC2);
			ExtentReporter.extentLogger("user_token", user_token_BC2);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	userTokenAPI_Repeat_RingPolicy
	public static ValidatableResponse userTokenAPI_Repeat_RingPolicy(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////		UserToken
			user_token_LTBC1 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_LTBC1);
			ExtentReporter.extentLogger("user_token", user_token_LTBC1);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	//	UserToken_Negative 


	public static ValidatableResponse userTokenAPI_Negative(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			//			UserToken
			//			user_token_negative = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token_negative :" + user_token_negative);
			//			ExtentReporter.extentLogger("user_token_negative", user_token_negative);



			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	// userTokenAPI_PromoCode_Segment1
	public static ValidatableResponse userTokenAPI_PromoCode_S1(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			////						UserToken
			//			user_token = response.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	// userTokenAPI_PromoCode_S1_Repeat
	public static ValidatableResponse userTokenAPI_PromoCode_S1_Repeat(Object[][] data) throws Exception {
		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("otp", (String) data[0][0]);
			req_body.put("mobile_number", (String) data[0][1]);
			req_body.put("client_id", (String) data[0][2]);
			req_body.put("source_app", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("client_id", req_body.get("client_id"));
			Myrequestbody.put("source_app", req_body.get("source_app"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", 4.9);
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 10);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
			headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
			headers.put("x-login-timestamp", "1636960116339");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//						UserToken
			user_token_promocode_S1 = response.extract().body().jsonPath().get("data.user_token");
			logger.info("user_token :" + user_token_promocode_S1);
			ExtentReporter.extentLogger("user_token", user_token_promocode_S1);



			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger(" ", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="userTokenAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	public static ValidatableResponse invalidOtpAPI(Object[][] data) throws IOException {
	//
	//		String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userAuthenticateEndPoint);
	//		System.out.println("Url :" + url);
	//
	//		Random rand = new Random();
	//
	//		HashMap<String, String> req_body = new HashMap<>();
	//
	//		req_body.put("otp", (String) data[0][0]);
	//		req_body.put("mobile_number", (String) data[0][1]);
	//		req_body.put("client_id", (String) data[0][2]);
	//		req_body.put("source_app", (String) data[0][3]);
	//
	//		JSONObject Myrequestbody = new JSONObject();
	//
	//		Myrequestbody.put("otp", req_body.get("otp"));
	//		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
	//		Myrequestbody.put("client_id", req_body.get("client_id"));
	//		Myrequestbody.put("source_app", req_body.get("source_app"));
	//
	//		HashMap<String, Object> headers = new HashMap<>();
	//		headers.put("x-request-id", rand.nextInt(1001));
	//		headers.put("X-Client-App", "android");
	//		headers.put("X-Client-Version", 4.9);
	//		headers.put("X-Client-OS-Type", "android");
	//		headers.put("X-Client-OS-Version", 10);
	//		headers.put("x-login-token",
	//				"eyJhbGciOiJSUzI1NiIsIng1YyI6WyJNSUlGWVRDQ0JFbWdBd0lCQWdJUkFQaEtkUXdrSUFNRENRQUFBQUM4QzZvd0RRWUpLb1pJaHZjTkFRRUxCUUF3UmpFTE1Ba0dBMVVFQmhNQ1ZWTXhJakFnQmdOVkJBb1RHVWR2YjJkc1pTQlVjblZ6ZENCVFpYSjJhV05sY3lCTVRFTXhFekFSQmdOVkJBTVRDa2RVVXlCRFFTQXhSRFF3SGhjTk1qRXhNREUzTVRjd05qQTNXaGNOTWpJd01URTFNVGN3TmpBMldqQWRNUnN3R1FZRFZRUURFeEpoZEhSbGMzUXVZVzVrY205cFpDNWpiMjB3Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLQW9JQkFRQ3ZnU2VHM3JTVlcwSVBpWkJGVmJoMktjYjNoTnl3R2VJOUZmaVgyUXZRQnBmUkIvT0xiUUFwZGdDWTZJL1dqNEw0aHVNQzRMVHA3OFZXbmhtZGJ3Y1NxbXJzNkpDM3kwWnVmVm4ydzhsV0NYODNsYytFUmdRVHhmaGUwTVNIakhlWk9mWGROQ3dqejZrTXJkZEVPUlJ5T3V3SWdjcXcrNGoycS9mSktHbkUyNXQ5NndOTDgrUDg1V294ZXhaZEROR1pzMmkzNmRvZkdVTGR1YTZaWFI1YjFlODJkd0dra0Rkd3RFMjZCeDRhTTl4VDEwK3A0S3FKNXZ0MWpvY1N0K2tTWHFRaEowQlJjS082OWhGUTRDSUdKYk5EYlRIMENGYlMvanJsNThGWnhVTUVwaUNHbG9JdmJyZ20xSlFzRDE2UmtIZlQ0NVM5UERNc3k5WFI4bjVqQWdNQkFBR2pnZ0p4TUlJQ2JUQU9CZ05WSFE4QkFmOEVCQU1DQmFBd0V3WURWUjBsQkF3d0NnWUlLd1lCQlFVSEF3RXdEQVlEVlIwVEFRSC9CQUl3QURBZEJnTlZIUTRFRmdRVUJ0M1lUWkFYZ3pGYXdpV2FXN3hmaStYRDhnZ3dId1lEVlIwakJCZ3dGb0FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd2JRWUlLd1lCQlFVSEFRRUVZVEJmTUNvR0NDc0dBUVVGQnpBQmhoNW9kSFJ3T2k4dmIyTnpjQzV3YTJrdVoyOXZaeTluZEhNeFpEUnBiblF3TVFZSUt3WUJCUVVITUFLR0pXaDBkSEE2THk5d2Eya3VaMjl2Wnk5eVpYQnZMMk5sY25SekwyZDBjekZrTkM1a1pYSXdIUVlEVlIwUkJCWXdGSUlTWVhSMFpYTjBMbUZ1WkhKdmFXUXVZMjl0TUNFR0ExVWRJQVFhTUJnd0NBWUdaNEVNQVFJQk1Bd0dDaXNHQVFRQjFua0NCUU13UHdZRFZSMGZCRGd3TmpBMG9ES2dNSVl1YUhSMGNEb3ZMMk55YkhNdWNHdHBMbWR2YjJjdlozUnpNV1EwYVc1MEwxZ3lTakpJY2w4M1VHbE5MbU55YkRDQ0FRUUdDaXNHQVFRQjFua0NCQUlFZ2ZVRWdmSUE4QUIxQUZHanNQWDlBWG1jVm0yNE4zaVBES1I2ekJzbnkvZWVpRUthRGY3VWl3WGxBQUFCZkk5dXVqSUFBQVFEQUVZd1JBSWdYd3JxbEEvV21IRFVySVpSWDIrS24raldjRVlsQjliVCtsRk9HT3RaTEtNQ0lGUzRXYU14Q09GaVAxTnhVN3hMcVBQVGlwR2dlaFgwS0IwTFgrTXhkdEl0QUhjQUtYbSs4SjQ1T1NId1ZuT2ZZNlYzNWI1WGZaeGdDdmo1VFYwbVhDVmR4NFFBQUFGOGoyNjZLUUFBQkFNQVNEQkdBaUVBNDdRNldJYmVnQUZuL0liUUM5OEFoR0dlY0xGVWowcjRCMnlrSkFlN2tzd0NJUURiQ2RNNFdzQ2JVUHJsSDhIV3M1ZGpqQWluKy9jWDZPNHpDTldMbzJxakhEQU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FRRUFMWHlhOUhVVm5rZURkUFgyd0tzQ2QybDhNcGpTeW5iVWVKWGI5Um04dXRsczRjRzkvdXEzRzZ3clRGWkNhdldJMnE5SmxlUnA1Q21DeCtrcElPVVh3T0dPQUZ3SVFrUFhCRnFrOGJscmE1MmhGTTluMUROYzY1bmNVRHkybXFYbjNXaVByN0crZEdSNlkzRnFKMjQ3K0VySlllbTZnM28rR3ZVcERxbWpkZ01SdHFFTXlmTVZIa0xoN3ZucWlXdnYzQ2VlU1ViRjkvMFdxUklNdTdPSFZyTkVET1ZUUEZuWENVczgyUk1OVVd0dVJTS1Njelh3QXFNN0JFWGR4TjNYcXE1Z1dOUDdUeFowczZzRTZGOHovWmN0OFVLdHRkNVBidGhrdGdFMmVvUmFaYTB1alNWVmtUeTVGb1pvMWJ1ZXhjbnM5WjlEWDFCUy9RU1JXbjNBUHc9PSIsIk1JSUZqRENDQTNTZ0F3SUJBZ0lOQWdDT3NnSXpObVdMWk0zYm16QU5CZ2txaGtpRzl3MEJBUXNGQURCSE1Rc3dDUVlEVlFRR0V3SlZVekVpTUNBR0ExVUVDaE1aUjI5dloyeGxJRlJ5ZFhOMElGTmxjblpwWTJWeklFeE1RekVVTUJJR0ExVUVBeE1MUjFSVElGSnZiM1FnVWpFd0hoY05NakF3T0RFek1EQXdNRFF5V2hjTk1qY3dPVE13TURBd01EUXlXakJHTVFzd0NRWURWUVFHRXdKVlV6RWlNQ0FHQTFVRUNoTVpSMjl2WjJ4bElGUnlkWE4wSUZObGNuWnBZMlZ6SUV4TVF6RVRNQkVHQTFVRUF4TUtSMVJUSUVOQklERkVORENDQVNJd0RRWUpLb1pJaHZjTkFRRUJCUUFEZ2dFUEFEQ0NBUW9DZ2dFQkFLdkFxcVBDRTI3bDB3OXpDOGRUUElFODliQSt4VG1EYUc3eTdWZlE0YyttT1dobFVlYlVRcEsweXYycjY3OFJKRXhLMEhXRGplcStuTElITjFFbTVqNnJBUlppeG15UlNqaElSMEtPUVBHQk1VbGRzYXp0SUlKN08wZy84MnFqL3ZHRGwvLzN0NHRUcXhpUmhMUW5UTFhKZGVCKzJEaGtkVTZJSWd4NndON0U1TmNVSDNSY3NlamNxajhwNVNqMTl2Qm02aTFGaHFMR3ltaE1Gcm9XVlVHTzN4dElIOTFkc2d5NGVGS2NmS1ZMV0szbzIxOTBRMExtL1NpS21MYlJKNUF1NHkxZXVGSm0ySk05ZUI4NEZrcWEzaXZyWFdVZVZ0eWUwQ1FkS3ZzWTJGa2F6dnh0eHZ1c0xKekxXWUhrNTV6Y1JBYWNEQTJTZUV0QmJRZkQxcXNDQXdFQUFhT0NBWFl3Z2dGeU1BNEdBMVVkRHdFQi93UUVBd0lCaGpBZEJnTlZIU1VFRmpBVUJnZ3JCZ0VGQlFjREFRWUlLd1lCQlFVSEF3SXdFZ1lEVlIwVEFRSC9CQWd3QmdFQi93SUJBREFkQmdOVkhRNEVGZ1FVSmVJWURySlhrWlFxNWRSZGhwQ0QzbE96dUpJd0h3WURWUjBqQkJnd0ZvQVU1SzhySm5FYUswZ25oUzlTWml6djhJa1RjVDR3YUFZSUt3WUJCUVVIQVFFRVhEQmFNQ1lHQ0NzR0FRVUZCekFCaGhwb2RIUndPaTh2YjJOemNDNXdhMmt1WjI5dlp5OW5kSE55TVRBd0JnZ3JCZ0VGQlFjd0FvWWthSFIwY0RvdkwzQnJhUzVuYjI5bkwzSmxjRzh2WTJWeWRITXZaM1J6Y2pFdVpHVnlNRFFHQTFVZEh3UXRNQ3N3S2FBbm9DV0dJMmgwZEhBNkx5OWpjbXd1Y0d0cExtZHZiMmN2WjNSemNqRXZaM1J6Y2pFdVkzSnNNRTBHQTFVZElBUkdNRVF3Q0FZR1o0RU1BUUlCTURnR0Npc0dBUVFCMW5rQ0JRTXdLakFvQmdnckJnRUZCUWNDQVJZY2FIUjBjSE02THk5d2Eya3VaMjl2Wnk5eVpYQnZjMmwwYjNKNUx6QU5CZ2txaGtpRzl3MEJBUXNGQUFPQ0FnRUFJVlRveTI0andYVXIwckFQYzkyNHZ1U1ZiS1F1WXczbkxmbExmTGg1QVlXRWVWbC9EdTE4UUFXVU1kY0o2by9xRlpiaFhrQkgwUE5jdzk3dGhhZjJCZW9EWVk5Q2svYitVR2x1aHgwNnpkNEVCZjdIOVA4NG5ucndwUis0R0JEWksrWGgzSTB0cUp5MnJnT3FORGZscjVJTVE4WlRXQTN5bHRha3pTQktaNlhwRjBQcHF5Q1J2cC9OQ0d2MktYMlR1UENKdnNjcDEvbTJwVlR0eUJqWVBSUStRdUNRR0FKS2p0TjdSNURGcmZUcU1XdllnVmxwQ0pCa3dsdTcrN0tZM2NUSWZ6RTdjbUFMc2tNS05MdUR6K1J6Q2NzWVRzVmFVN1ZwM3hMNjBPWWhxRmt1QU9PeERaNnBIT2o5K09KbVlnUG1PVDRYMys3TDUxZlhKeVJIOUtmTFJQNm5UMzFENW5tc0dBT2daMjYvOFQ5aHNCVzF1bzlqdTVmWkxaWFZWUzVIMEh5SUJNRUt5R01JUGhGV3JsdC9oRlMyOE4xemFLSTBaQkdEM2dZZ0RMYmlEVDlmR1hzdHBrK0ZtYzRvbFZsV1B6WGU4MXZkb0VuRmJyNU0yNzJIZGdKV28rV2hUOUJZTTBKaSt3ZFZtblJmZlhnbG9Fb2x1VE5jV3pjNDFkRnBnSnU4ZkYzTEcwZ2wyaWJTWWlDaTlhNmh2VTBUcHBqSnlJV1hoa0pUY01KbFByV3gxVnl0RVVHclgybDBKRHdSalcvNjU2cjBLVkIwMnhIUkt2bTJaS0kwM1RnbExJcG1WQ0sza0JLa0tOcEJOa0Z0OHJoYWZjQ0tPYjlKeC85dHBORmxRVGw3QjM5ckpsSldrUjE3UW5acVZwdEZlUEZPUm9abUZ6TT0iLCJNSUlGWWpDQ0JFcWdBd0lCQWdJUWQ3ME5iTnMyK1JycUlRL0U4RmpURFRBTkJna3Foa2lHOXcwQkFRc0ZBREJYTVFzd0NRWURWUVFHRXdKQ1JURVpNQmNHQTFVRUNoTVFSMnh2WW1Gc1UybG5iaUJ1ZGkxellURVFNQTRHQTFVRUN4TUhVbTl2ZENCRFFURWJNQmtHQTFVRUF4TVNSMnh2WW1Gc1UybG5iaUJTYjI5MElFTkJNQjRYRFRJd01EWXhPVEF3TURBME1sb1hEVEk0TURFeU9EQXdNREEwTWxvd1J6RUxNQWtHQTFVRUJoTUNWVk14SWpBZ0JnTlZCQW9UR1VkdmIyZHNaU0JVY25WemRDQlRaWEoyYVdObGN5Qk1URU14RkRBU0JnTlZCQU1UQzBkVVV5QlNiMjkwSUZJeE1JSUNJakFOQmdrcWhraUc5dzBCQVFFRkFBT0NBZzhBTUlJQ0NnS0NBZ0VBdGhFQ2l4N2pvWGViTzl5L2xENjNsYWRBUEtIOWd2bDlNZ2FDY2ZiMmpILzc2TnU4YWk2WGw2T01TL2tyOXJINXpvUWRzZm5GbDk3dnVmS2o2YndTaVY2bnFsS3IrQ01ueTZTeG5HUGIxNWwrOEFwZTYyaW05TVphUncxTkVEUGpUckVUbzhnWWJFdnMvQW1RMzUxa0tTVWpCNkcwMGowdVlPRFAwZ21IdTgxSThFM0N3bnFJaXJ1Nnoxa1oxcStQc0Fld25qSHhnc0hBM3k2bWJXd1pEclhZZmlZYVJRTTlzSG1rbENpdEQzOG01YWdJL3Bib1BHaVVVKzZET29nckZaWUpzdUI2akM1MTFwenJwMVprajVaUGFLNDlsOEtFajhDOFFNQUxYTDMyaDdNMWJLd1lVSCtFNEV6Tmt0TWc2VE84VXBtdk1yVXBzeVVxdEVqNWN1SEtaUGZtZ2hDTjZKM0Npb2o2T0dhSy9HUDVBZmw0L1h0Y2QvcDJoL3JzMzdFT2VaVlh0TDBtNzlZQjBlc1dDcnVPQzdYRnhZcFZxOU9zNnBGTEtjd1pwRElsVGlyeFpVVFFBczZxemttMDZwOThnN0JBZStkRHE2ZHNvNDk5aVlINlRLWC8xWTdEemt2Z3RkaXpqa1hQZHNEdFFDdjlVdyt3cDlVN0RiR0tvZ1BlTWEzTWQrcHZlejdXMzVFaUV1YSsrdGd5L0JCakZGRnkzbDNXRnBPOUtXZ3o3enBtN0FlS0p0OFQxMWRsZUNmZVhra1VBS0lBZjVxb0liYXBzWld3cGJrTkZoSGF4MnhJUEVEZ2ZnMWF6Vlk4MFpjRnVjdEw3VGxMbk1RLzBsVVRiaVN3MW5INjlNRzZ6TzBiOWY2QlFkZ0FtRDA2eUs1Nm1EY1lCWlVDQXdFQUFhT0NBVGd3Z2dFME1BNEdBMVVkRHdFQi93UUVBd0lCaGpBUEJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUa3J5c21jUm9yU0NlRkwxSm1MTy93aVJOeFBqQWZCZ05WSFNNRUdEQVdnQlJnZTJZYVJRMlh5b2xRTDMwRXpUU28vL3o5U3pCZ0JnZ3JCZ0VGQlFjQkFRUlVNRkl3SlFZSUt3WUJCUVVITUFHR0dXaDBkSEE2THk5dlkzTndMbkJyYVM1bmIyOW5MMmR6Y2pFd0tRWUlLd1lCQlFVSE1BS0dIV2gwZEhBNkx5OXdhMmt1WjI5dlp5OW5jM0l4TDJkemNqRXVZM0owTURJR0ExVWRId1FyTUNrd0o2QWxvQ09HSVdoMGRIQTZMeTlqY213dWNHdHBMbWR2YjJjdlozTnlNUzluYzNJeExtTnliREE3QmdOVkhTQUVOREF5TUFnR0JtZUJEQUVDQVRBSUJnWm5nUXdCQWdJd0RRWUxLd1lCQkFIV2VRSUZBd0l3RFFZTEt3WUJCQUhXZVFJRkF3TXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnRUJBRFNrSHJFb285QzBkaGVtTVhvaDZkRlNQc2piZEJaQmlMZzlOUjN0NVArVDRWeGZxN3ZxZk0vYjVBM1JpMWZ5Sm05YnZoZEdhSlEzYjJ0NnlNQVlOL29sVWF6c2FMK3l5RW45V3ByS0FTT3NoSUFyQW95WmwrdEphb3gxMThmZXNzbVhuMWhJVnc0MW9lUWExdjF2ZzRGdjc0elBsNi9BaFNydzlVNXBDWkV0NFdpNHdTdHo2ZFRaL0NMQU54OExaaDFKN1FKVmoyZmhNdGZUSnI5dzR6MzBaMjA5Zk9VMGlPTXkrcWR1Qm1wdnZZdVI3aFpMNkR1cHN6Zm53MFNrZnRoczE4ZEc5WktiNTlVaHZtYVNHWlJWYk5RcHNnM0JabHZpZDBsSUtPMmQxeG96Y2xPemdqWFBZb3ZKSkl1bHR6a011MzRxUWI5U3oveWlscmJDZ2o4PSJdfQ.eyJub25jZSI6IlBvSEJNR1FXVTZMTHZuQ21tQUlqUkt4dTJ4ND0iLCJ0aW1lc3RhbXBNcyI6MTYzNzc1MTY1NTE2OSwiYXBrUGFja2FnZU5hbWUiOiJjb20uZmFzdGJhbmtpbmcuZGVidWciLCJhcGtEaWdlc3RTaGEyNTYiOiJsRHF1bDJxejdyd2owRDFJSzBkcTZwTnNaUmR0QW9BbUNNOVh5MGg2bkNjPSIsImN0c1Byb2ZpbGVNYXRjaCI6dHJ1ZSwiYXBrQ2VydGlmaWNhdGVEaWdlc3RTaGEyNTYiOlsiR3k3N1doNFRkR0ZXd3NoaS9VVXdDdUJIL0NBZ2V4VFFLdmJzbW5pWHFpTT0iXSwiYmFzaWNJbnRlZ3JpdHkiOnRydWUsImV2YWx1YXRpb25UeXBlIjoiQkFTSUMsSEFSRFdBUkVfQkFDS0VEIn0.ShOvWqQ_5i-T1ixx59sbk0-6LMo8oKiC5PfZCt9dVJrnfeap8JMQ9x8v19-Yh-M07y54BjQPXFGU-Y602uFc_V7TKHonDqjaEOsx6VfRwiQeZmtaO-Hhmlr2g-xRHFoDOnXy2wHYGfDkMbir50EraIyny3xfs-guIDMwg5qAzQaN999KRsrbHXX-a6wwoQ0qyUSVKGN57T_qOcXaq9X5bI1B3nD1m5Inu7TW0xrCb0sfUn8GDimAtnXELKf048S4iaXBObbgtiNyVQtTEfqHA8WdfhANIZWcV4XQDHbv69wcvrmUTDeZJienIfkmesfYnFDngW2NfR9A9m_Q5sorig");
	//		headers.put("x-login-nonce", "B6B667EB514890789F56F9B78BFA509AB41B673B");
	//		headers.put("x-login-timestamp", "1636960116339");
	//
	//		ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);
	//
	//		System.out.println("Request :" + Myrequestbody);
	//		ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
	//		String Resp = response.extract().body().asString();
	//		System.out.println("Response Body= " + Resp);
	//		ExtentReporter.extentLogger("", "Response Body= " + Resp);
	//		return response;
	//	}

	//PlayStore
	public static ValidatableResponse playStore_BasicDetailsAPI(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("has_tnc_accepted", (String) data[0][7]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][8]);



			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="playStore_BasicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//PlayStore_BasicDetails_S1
	public static ValidatableResponse playStore_BasicDetailsAPI_Segment1(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("has_tnc_accepted", (String) data[0][7]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][8]);



			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="playStore_BasicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	// BasicDetailsAPI_Merchant_Segment1 
	public static ValidatableResponse BasicDetailsAPI_Merchant_Segment1(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);



			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_BasicDetailsAPI(Object[][] data) throws Exception {

		try {


			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	// PromoCode API
	public static ValidatableResponse promoCodeBasicDetailsAPI(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("promo_code_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);



			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("promo_code_reference_number", req_body.get("promo_code_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	// promoCodeBasicDetailsAPI_Segment1

	public static ValidatableResponse promoCodeBasicDetailsAPI_Segment1(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("promo_code_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);



			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("promo_code_reference_number", req_body.get("promo_code_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	Merchant_LTBC1
	public static ValidatableResponse Merchant_BasicDetailsAPI_LTBC1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	Merchant_BC1
	public static ValidatableResponse Merchant_BasicDetailsAPI_BC1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant_L1
	public static ValidatableResponse Merchant_BasicDetailsAPI_L1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


//	Merchant_L2
	public static ValidatableResponse Merchant_BasicDetailsAPI_L2(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

//	Merchant_L3
	public static ValidatableResponse Merchant_BasicDetailsAPI_L3(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L3);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	//	Merchant_BC2
	public static ValidatableResponse Merchant_BasicDetailsAPI_BC2(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.basicDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mother_name", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("dob", (String) data[0][4]);
			req_body.put("gender", (String) data[0][5]);
			req_body.put("is_native_merchant", (String) data[0][6]);
			req_body.put("merchant_reference_number", (String) data[0][7]);
			req_body.put("has_tnc_accepted", (String) data[0][8]);
			req_body.put("has_ckyc_consent_accepted", (String) data[0][9]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("dob", req_body.get("dob"));
			Myrequestbody.put("gender", req_body.get("gender"));
			Myrequestbody.put("is_native_merchant", req_body.get("is_native_merchant"));
			Myrequestbody.put("merchant_reference_number", req_body.get("merchant_reference_number"));
			Myrequestbody.put("has_tnc_accepted", req_body.get("has_tnc_accepted"));
			Myrequestbody.put("has_ckyc_consent_accepted", req_body.get("has_ckyc_consent_accepted"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="basicDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//MerchantQRCode_Segment 1
	public static ValidatableResponse Merchant_loginAPI_Segment1() throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			String user_reference_number = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number);

			// fetch first_name
			String first_name = response.extract().body().jsonPath().get("data.first_name");
			logger.info("first_name : " + first_name);
			ExtentReporter.extentLogger("first_name ",first_name);

			// fetch last_name
			String last_name = response.extract().body().jsonPath().get("data.last_name");
			logger.info("last_name : " + last_name);
			ExtentReporter.extentLogger("last_name ",last_name);


			//	fetch first_name & last_name
			String cibil_user_name= first_name+" "+last_name;
			logger.info("cibil_user_name : " + cibil_user_name);



			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number, 1, 1);

//			// Segment 1
//			ExcelWriteData.excelWrite(filePath, "RegisterUser", user_reference_number, 2, 11);


			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 7, 1);

			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number, 1, 1);




			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

//			// Segment 1
//			ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}





	//	Merchant
	public static ValidatableResponse Merchant_loginAPI() throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);


			//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			String user_reference_number = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number);

			// fetch first_name
			//			String first_name = response.extract().body().jsonPath().get("data.first_name");
			//			logger.info("first_name : " + first_name);
			//			ExtentReporter.extentLogger("first_name ",first_name);
			//
			//			// fetch last_name
			//			String last_name = response.extract().body().jsonPath().get("data.last_name");
			//			logger.info("last_name : " + last_name);
			//			ExtentReporter.extentLogger("last_name ",last_name);
			//
			//
			//			//	fetch first_name & last_name
			//			String cibil_user_name= first_name+" "+last_name;
			//			logger.info("cibil_user_name : " + cibil_user_name);



			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number, 1, 1);

			// Segment 1
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 2, 11);


			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 7, 1);


			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number, 1, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//		// Segment 1
			//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_loginAPI() throws Exception {

		//		try {

		//		ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
		//
		//		String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
		//		logger.info("user_token :" + user_token);
		//		ExtentReporter.extentLogger("user_token", user_token);


		String filePath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

		Random rand = new Random();

		String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
		logger.info("Url :" + url);
		ExtentReporter.extentLogger("url", url);


		HashMap<String, Object> headers = new HashMap<>();
		headers.put("x-request-id", rand.nextInt(1001));
		headers.put("Authorization", "Bearer " + user_token_promocode);

		String header=String.valueOf(headers);
		ExtentReporter.extentLogger("header","Headers :"+ header);


		ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


		String Resp = response.extract().body().asString();
		logger.info("Resp :" + Resp);
		ExtentReporter.extentLogger("", "Response Body= " + Resp);

		// fetch user_reference_number
		String user_reference_number = response.extract().body().jsonPath().get("data.user_reference_number");
		logger.info("user_reference_number : " + user_reference_number);
		ExtentReporter.extentLogger("user_reference_number ",user_reference_number);

		// fetch first_name
		//			String first_name = response.extract().body().jsonPath().get("data.first_name");
		//			logger.info("first_name : " + first_name);
		//			ExtentReporter.extentLogger("first_name ",first_name);
		//
		//			// fetch last_name
		//			String last_name = response.extract().body().jsonPath().get("data.last_name");
		//			logger.info("last_name : " + last_name);
		//			ExtentReporter.extentLogger("last_name ",last_name);
		//
		//
		//			//	fetch first_name & last_name
		//			String cibil_user_name= first_name+" "+last_name;
		//			logger.info("cibil_user_name : " + cibil_user_name);



		// fetch user_id
		Integer user_id = response.extract().body().jsonPath().get("data.user_id");
		logger.info("user_id : " + user_id);

		String User_id=String.valueOf(user_id);
		ExtentReporter.extentLogger("User_id", User_id);


		// Write Excel
		ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 1, 11);
		ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number, 1, 1);

//		// Segment 1
//		ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 2, 11);


		// Write LTBC1 from Excel
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 1, 1);
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 2, 1);
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 3, 1);
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 4, 1);
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 5, 1);
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 6, 1);
		ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 7, 1);


		// Write AddAddress from Excel
		ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number, 1, 1);


		// Write RegisterUser from Excel
		ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

//		// Segment 1
//		ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 2, 12);


		// Write LTBC1 from Excel
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


		// Write LTBC1 from Excel
		//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


		return response;

	}
	//		catch (Exception e) {
	//			String message="loginAPI";
	//			ExtentReporter.extentLoggerFail(message+" - Failed");	
	//		}
	//		return null;
	//
	//	}



	//	PromoCode_loginAPI_Segment1
	public static ValidatableResponse PromoCode_loginAPI_Segment1() throws Exception {

//		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			String user_reference_number = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number);

			// fetch first_name
			//			String first_name = response.extract().body().jsonPath().get("data.first_name");
			//			logger.info("first_name : " + first_name);
			//			ExtentReporter.extentLogger("first_name ",first_name);
			//
			//			// fetch last_name
			//			String last_name = response.extract().body().jsonPath().get("data.last_name");
			//			logger.info("last_name : " + last_name);
			//			ExtentReporter.extentLogger("last_name ",last_name);
			//
			//
			//			//	fetch first_name & last_name
			//			String cibil_user_name= first_name+" "+last_name;
			//			logger.info("cibil_user_name : " + cibil_user_name);



			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number, 1, 1);

			//			// Segment 1
			//			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 2, 11);


			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 7, 1);


			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number, 1, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//			// Segment 1
			//			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
//		catch (Exception e) {
//			String message="loginAPI";
//			ExtentReporter.extentLoggerFail(message+" - Failed");	
//		}
//		return null;
//
//	}





	//	PlayStore
	public static ValidatableResponse playStore_LoginAPI() throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			String user_reference_number = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number);

			// fetch first_name
			//			String first_name = response.extract().body().jsonPath().get("data.first_name");
			//			logger.info("first_name : " + first_name);
			//			ExtentReporter.extentLogger("first_name ",first_name);
			//
			//			// fetch last_name
			//			String last_name = response.extract().body().jsonPath().get("data.last_name");
			//			logger.info("last_name : " + last_name);
			//			ExtentReporter.extentLogger("last_name ",last_name);
			//
			//
			//			//	fetch first_name & last_name
			//			String cibil_user_name= first_name+" "+last_name;
			//			logger.info("cibil_user_name : " + cibil_user_name);


			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number, 1, 1);

			//			// Segment 1
			//			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 2, 11);


			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 7, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//			// Segment 1
			//			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PlayStore_Segment1

	public static ValidatableResponse playStore_LoginAPI_Segment1() throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			String user_reference_number = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number);

			//			// fetch first_name
			//			String first_name = response.extract().body().jsonPath().get("data.first_name");
			//			logger.info("first_name : " + first_name);
			//			ExtentReporter.extentLogger("first_name ",first_name);
			//
			//			// fetch last_name
			//			String last_name = response.extract().body().jsonPath().get("data.last_name");
			//			logger.info("last_name : " + last_name);
			//			ExtentReporter.extentLogger("last_name ",last_name);
			//
			//
			//			//	fetch first_name & last_name
			//			String cibil_user_name= first_name+" "+last_name;
			//			logger.info("cibil_user_name : " + cibil_user_name);



			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number, 1, 1);


			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number, 7, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			// Segment 1
			//			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_loginAPI_LTBC1() throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);


			//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			user_reference_number_LTBC1 = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number_LTBC1);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number_LTBC1);

			// fetch first_name
			//			String first_name = response.extract().body().jsonPath().get("data.first_name");
			//			logger.info("first_name : " + first_name);
			//			ExtentReporter.extentLogger("first_name ",first_name);
			//
			//			// fetch last_name
			//			String last_name = response.extract().body().jsonPath().get("data.last_name");
			//			logger.info("last_name : " + last_name);
			//			ExtentReporter.extentLogger("last_name ",last_name);
			//
			//
			//			//	fetch first_name & last_name
			//			String cibil_user_name= first_name+" "+last_name;
			//			logger.info("cibil_user_name : " + cibil_user_name);



			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number_LTBC1, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number_LTBC1, 1, 1);



			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_LTBC1, 7, 1);


			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number_LTBC1, 1, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//		// Segment 1
			//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	// login_BC1
	public static ValidatableResponse Merchant_loginAPI_BC1() throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC1_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);


			//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			user_reference_number_BC1 = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number_BC1);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number_BC1);


			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number_BC1, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number_BC1, 1, 1);



			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC1, 7, 1);


			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number_BC1, 1, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//		// Segment 1
			//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	// login_L1
	public static ValidatableResponse Merchant_loginAPI_L1() throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L1_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);


			//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			user_reference_number_L1 = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number_L1);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number_L1);


			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number_L1, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number_L1, 1, 1);



			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L1, 7, 1);


			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number_L1, 1, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//		// Segment 1
			//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
	// login_L2
		public static ValidatableResponse Merchant_loginAPI_L2() throws Exception {

			try {

				String filePath = System.getProperty("user.dir")
						+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L2_stage.xlsx";

				Random rand = new Random();

				String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
				logger.info("Url :" + url);
				ExtentReporter.extentLogger("url", url);


				HashMap<String, Object> headers = new HashMap<>();
				headers.put("x-request-id", rand.nextInt(1001));
				headers.put("Authorization", "Bearer " + user_token_L2);


				//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


				String header=String.valueOf(headers);
				ExtentReporter.extentLogger("header","Headers :"+ header);


				ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


				String Resp = response.extract().body().asString();
				logger.info("Resp :" + Resp);
				ExtentReporter.extentLogger("", "Response Body= " + Resp);

				// fetch user_reference_number
				user_reference_number_L2 = response.extract().body().jsonPath().get("data.user_reference_number");
				logger.info("user_reference_number : " + user_reference_number_L2);
				ExtentReporter.extentLogger("user_reference_number ",user_reference_number_L2);


				// fetch user_id
				Integer user_id = response.extract().body().jsonPath().get("data.user_id");
				logger.info("user_id : " + user_id);

				String User_id=String.valueOf(user_id);
				ExtentReporter.extentLogger("User_id", User_id);


				// Write Excel
				ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number_L2, 1, 11);
				ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number_L2, 1, 1);



				// Write LTBC1 from Excel
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 1, 1);
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 2, 1);
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 3, 1);
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 4, 1);
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 5, 1);
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 6, 1);
				ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L2, 7, 1);


				// Write AddAddress from Excel
				ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number_L2, 1, 1);


				// Write RegisterUser from Excel
				ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

				//		// Segment 1
				//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


				// Write LTBC1 from Excel
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


				// Write LTBC1 from Excel
				//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


				return response;

			}
			catch (Exception e) {
				String message="loginAPI";
				ExtentReporter.extentLoggerFail(message+" - Failed");	
			}
			return null;

		}

	
		// login_L3
				public static ValidatableResponse Merchant_loginAPI_L3() throws Exception {

					try {

						String filePath = System.getProperty("user.dir")
								+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L3_stage.xlsx";

						Random rand = new Random();

						String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
						logger.info("Url :" + url);
						ExtentReporter.extentLogger("url", url);


						HashMap<String, Object> headers = new HashMap<>();
						headers.put("x-request-id", rand.nextInt(1001));
						headers.put("Authorization", "Bearer " + user_token_L3);


						//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


						String header=String.valueOf(headers);
						ExtentReporter.extentLogger("header","Headers :"+ header);


						ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


						String Resp = response.extract().body().asString();
						logger.info("Resp :" + Resp);
						ExtentReporter.extentLogger("", "Response Body= " + Resp);

						// fetch user_reference_number
						user_reference_number_L3 = response.extract().body().jsonPath().get("data.user_reference_number");
						logger.info("user_reference_number : " + user_reference_number_L3);
						ExtentReporter.extentLogger("user_reference_number ",user_reference_number_L3);


						// fetch user_id
						Integer user_id = response.extract().body().jsonPath().get("data.user_id");
						logger.info("user_id : " + user_id);

						String User_id=String.valueOf(user_id);
						ExtentReporter.extentLogger("User_id", User_id);


						// Write Excel
						ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number_L3, 1, 11);
						ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number_L3, 1, 1);



						// Write LTBC1 from Excel
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 1, 1);
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 2, 1);
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 3, 1);
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 4, 1);
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 5, 1);
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 6, 1);
						ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_L3, 7, 1);


						// Write AddAddress from Excel
						ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number_L3, 1, 1);


						// Write RegisterUser from Excel
						ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

						//		// Segment 1
						//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


						// Write LTBC1 from Excel
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


						// Write LTBC1 from Excel
						//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


						return response;

					}
					catch (Exception e) {
						String message="loginAPI";
						ExtentReporter.extentLoggerFail(message+" - Failed");	
					}
					return null;

				}
		

	// login_BC2
	public static ValidatableResponse Merchant_loginAPI_BC2() throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC2_stage.xlsx";

			Random rand = new Random();

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.loginEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);


			//		logger.info("Urldddddddddddddddddddddddd :" + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);


			String Resp = response.extract().body().asString();
			logger.info("Resp :" + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetch user_reference_number
			user_reference_number_BC2 = response.extract().body().jsonPath().get("data.user_reference_number");
			logger.info("user_reference_number : " + user_reference_number_BC2);
			ExtentReporter.extentLogger("user_reference_number ",user_reference_number_BC2);


			// fetch user_id
			Integer user_id = response.extract().body().jsonPath().get("data.user_id");
			logger.info("user_id : " + user_id);

			String User_id=String.valueOf(user_id);
			ExtentReporter.extentLogger("User_id", User_id);


			// Write Excel
			ExcelWriteData.DemoExcel(filePath, "RegisterUser", user_reference_number_BC2, 1, 11);
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", user_reference_number_BC2, 1, 1);



			// Write LTBC1 from Excel
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 1, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 2, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 3, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 4, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 5, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 6, 1);
			ExcelWriteData.DemoExcel(filePath, "RingPolicy", user_reference_number_BC2, 7, 1);


			// Write AddAddress from Excel
			ExcelWriteData.DemoExcel(filePath, "AddAddress", user_reference_number_BC2, 1, 1);


			// Write RegisterUser from Excel
			ExcelWriteData.DemoExcelInteger(filePath, "RegisterUser", user_id, 1, 12);

			//		// Segment 1
			//		ExcelWriteData.IntegerExcelWrite(filePath, "RegisterUser", user_id, 2, 12);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 1, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 3, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 4, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 5, 4);
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 6, 4);


			// Write LTBC1 from Excel
			//			ExcelWriteData.excelWrite(filePath, "RingPolicy", cibil_user_name, 2, 4);


			return response;

		}
		catch (Exception e) {
			String message="loginAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	// Merchant_Segment 1
	public static ValidatableResponse RegisterUserAPI_Merchant_Segment1(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_RegisterUserAPI(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token);



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.excelWrite(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_RegisterUserAPI(Object[][] data) throws Exception {

//		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
//		catch (Exception e) {
//			String message="RegisterUserAPI";
//			ExtentReporter.extentLoggerFail(message+" - Failed");	
//		}
//		return null;
//
//	}



	//	PromoCode_RegisterUserAPI_Segment1

	public static ValidatableResponse PromoCode_RegisterUserAPI_Segment1(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.excelWrite(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	//	Playstore
	public static ValidatableResponse PlayStore_RegisterUserAPI(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	//	Playstore_Register_S1
	public static ValidatableResponse PlayStore_RegisterUserAPI_Segment1(Object[][] data) throws Exception {

		try {

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);



			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	RingPolicy_LTBC1
	public static ValidatableResponse Merchant_RegisterUserAPI_LTBC1(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_LTBC1);



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	RingPolicy_BC1
	public static ValidatableResponse Merchant_RegisterUserAPI_BC1(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC1_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_BC1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	RingPolicy_L1
	public static ValidatableResponse Merchant_RegisterUserAPI_L1(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L1_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_L1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	RingPolicy_L2
	public static ValidatableResponse Merchant_RegisterUserAPI_L2(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L2_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_L2);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	RingPolicy_L3
	public static ValidatableResponse Merchant_RegisterUserAPI_L3(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L3_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_L3);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	

	//	RingPolicy_BC2
	public static ValidatableResponse Merchant_RegisterUserAPI_BC2(Object[][] data) throws Exception {

		try {


			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC2_stage.xlsx";

			String url = RingPay_BaseURL.bigDataPythonURL.concat(RingPay_Endpoints.registerUserEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("first_name", (String) data[0][0]);
			req_body.put("last_name", (String) data[0][1]);
			req_body.put("mobile_number", (String) data[0][2]);
			req_body.put("email", (String) data[0][3]);
			req_body.put("os_info", (String) data[0][4]);
			req_body.put("device_model", (String) data[0][5]);
			req_body.put("device_brand", (String) data[0][6]);
			req_body.put("imei", (String) data[0][7]);
			req_body.put("android_id", (String) data[0][8]);
			req_body.put("advertising_id", (String) data[0][9]);
			req_body.put("user_reference_number", (String) data[0][10]);
			req_body.put("user_id", (String) data[0][11]);
			req_body.put("request_id", (String) data[0][12]);
			req_body.put("approximate_contacts", (String) data[0][13]);
			req_body.put("approximate_sms", (String) data[0][14]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("first_name", req_body.get("first_name"));
			Myrequestbody.put("last_name", req_body.get("last_name"));
			Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			Myrequestbody.put("email", req_body.get("email"));
			Myrequestbody.put("os_info", req_body.get("os_info"));
			Myrequestbody.put("device_model", req_body.get("device_model"));
			Myrequestbody.put("device_brand", req_body.get("device_brand"));
			Myrequestbody.put("imei", req_body.get("imei"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("user_id", req_body.get("user_id"));
			Myrequestbody.put("request_id", req_body.get("request_id"));
			Myrequestbody.put("approximate_contacts", req_body.get("approximate_contacts"));
			Myrequestbody.put("approximate_sms", req_body.get("approximate_sms"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-Version", "1.1.7");
			//			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_negative);
			headers.put("Authorization", "client_id=MCUMMlBD7gs98HlHL3Py9Syk3xRsvvU5,token=" + user_token_BC2);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header","Headers :"+ header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String global_device_id = response.extract().body().jsonPath().get("data.global_device_id");
			logger.info("Global_Device_id: " + global_device_id);
			ExtentReporter.extentLogger("global_device_id", global_device_id);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "UserOnboarding", global_device_id, 1, 3);

			// Data to Create_Bnpl_transaction
			ExcelWriteData.DemoExcel(filePath, "Create_Bnpl_Transaction", global_device_id, 1, 5);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", global_device_id, 1, 9);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Current_Spend", global_device_id, 1, 3);

			return response;

		}
		catch (Exception e) {
			String message="RegisterUserAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//Get_User_DetailsAPI_Segment 1
	public static ValidatableResponse Get_User_DetailsAPI_Merchant_Segment1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant
	public static ValidatableResponse Merchant_Get_User_DetailsAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	PromoCode
	public static ValidatableResponse PromoCode_Get_User_DetailsAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	//	PromoCode_Get_User_DetailsAPI_Segment1
	public static ValidatableResponse PromoCode_Get_User_DetailsAPI_Segment1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore
	public static ValidatableResponse PlayStore_Get_User_DetailsAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_Get_User_DetailsAPI_Segment1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	RingPolicy_LTBC1
	public static ValidatableResponse Merchant_Get_User_DetailsAPI_LTBC1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	RingPolicy_BC1
	public static ValidatableResponse Merchant_Get_User_DetailsAPI_BC1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}

	
	//	RingPolicy_L1
	public static ValidatableResponse Merchant_Get_User_DetailsAPI_L1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}

	
	//	RingPolicy_L2
	public static ValidatableResponse Merchant_Get_User_DetailsAPI_L2() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	RingPolicy_L3
	public static ValidatableResponse Merchant_Get_User_DetailsAPI_L3() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L3);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	

	//	RingPolicy_BC2
	public static ValidatableResponse Merchant_Get_User_DetailsAPI_BC2() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getDetailsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Get_User_DetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	// Useronboarding_Segment 1
	public static ValidatableResponse UserOnboarding_Segment1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant
	public static ValidatableResponse Merchant_User_OnboardingAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	PromoCode
	public static ValidatableResponse PromoCode_User_OnboardingAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PromoCode_User_OnboardingAPI_Segment1

	public static ValidatableResponse PromoCode_User_OnboardingAPI_Segment1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}





	// PlayStore
	public static ValidatableResponse PlayStore_User_OnboardingAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	// PlayStore_S1
	public static ValidatableResponse PlayStore_User_OnboardingAPI_Segment1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	Merchant_LTBC1
	public static ValidatableResponse Merchant_User_OnboardingAPI_LTBC1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant_BC1
	public static ValidatableResponse Merchant_User_OnboardingAPI_BC1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	
//	Merchant_L1
	public static ValidatableResponse Merchant_User_OnboardingAPI_L1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}
	
	
//	Merchant_L2
	public static ValidatableResponse Merchant_User_OnboardingAPI_L2(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_L3
	public static ValidatableResponse Merchant_User_OnboardingAPI_L3(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L3);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
	//	Merchant_BC2
	public static ValidatableResponse Merchant_User_OnboardingAPI_BC2(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userOnbordingEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="User_OnboardingAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	Merchant_Segment 1
	public static ValidatableResponse Create_Bnpl_TransactionAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			Global Variable
			applicationToken_S1 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_S1);
			ExtentReporter.extentLogger("applicationToken", applicationToken_S1);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			Global Variable

			applicationToken = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken);
			ExtentReporter.extentLogger("applicationToken", applicationToken);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PromoCode
	public static ValidatableResponse PromoCode_Create_Bnpl_TransactionAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			Global Variable

			applicationToken_PromoCode = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_PromoCode);
			ExtentReporter.extentLogger("applicationToken", applicationToken_PromoCode);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	//	PromoCode_Create_Bnpl_TransactionAPI_Segment1

	public static ValidatableResponse PromoCode_Create_Bnpl_TransactionAPI_Segment1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			Global Variable

			applicationToken_PromoCode_S1 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_PromoCode_S1);
			ExtentReporter.extentLogger("applicationToken", applicationToken_PromoCode_S1);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore
	public static ValidatableResponse PlayStore_Create_Bnpl_TransactionAPI(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			Global Variable

			applicationToken_PlayStore = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_PlayStore);
			ExtentReporter.extentLogger("applicationToken", applicationToken_PlayStore);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_Create_Bnpl_TransactionAPI_Segment1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);


			//			Global Variable

			applicationToken_PlayStore_S1 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_PlayStore_S1);
			ExtentReporter.extentLogger("applicationToken", applicationToken_PlayStore_S1);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	Merchant_RingPolicy_LTBC1
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_LTBC1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			Global Variable

			applicationToken_LTBC1 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_LTBC1);
			ExtentReporter.extentLogger("applicationToken", applicationToken_LTBC1);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	Merchant_RingPolicy_BC1
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_BC1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			Global Variable

			applicationToken_BC1 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_BC1);
			ExtentReporter.extentLogger("applicationToken", applicationToken_BC1);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


//	Merchant_RingPolicy_L1
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_L1(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			Global Variable

			applicationToken_L1 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_L1);
			ExtentReporter.extentLogger("applicationToken", applicationToken_L1);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}
	
	
//	Merchant_RingPolicy_L2
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_L2(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

//			//			Global Variable
//
//			applicationToken_L2 = response.extract().body().jsonPath().get("data.application_token");
//			logger.info("ApplicationToken: " + applicationToken_L2);
//			ExtentReporter.extentLogger("applicationToken", applicationToken_L2);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_RingPolicy_L3
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_L3(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L3);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

//			//			Global Variable
//
//			applicationToken_L2 = response.extract().body().jsonPath().get("data.application_token");
//			logger.info("ApplicationToken: " + applicationToken_L2);
//			ExtentReporter.extentLogger("applicationToken", applicationToken_L2);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_RingPolicy_L2_Token
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_L2_Token(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			Global Variable

			applicationToken_L2 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_L2);
			ExtentReporter.extentLogger("applicationToken", applicationToken_L2);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
	//	Merchant_RingPolicy_BC2
	public static ValidatableResponse Merchant_Create_Bnpl_TransactionAPI_BC2(Object[][] data) throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.createBnplTransactionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("source", (String) data[0][0]);
			req_body.put("advertising_id", (String) data[0][1]);
			req_body.put("latitude", (String) data[0][2]);
			req_body.put("longitude", (String) data[0][3]);
			req_body.put("global_device_id", (String) data[0][4]);
			req_body.put("imei_number", (String) data[0][5]);
			req_body.put("android_id", (String) data[0][6]);
			req_body.put("product_name", (String) data[0][7]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("source", req_body.get("source"));
			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("product_name", req_body.get("product_name"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			//			Global Variable

			applicationToken_BC2 = response.extract().body().jsonPath().get("data.application_token");
			logger.info("ApplicationToken: " + applicationToken_BC2);
			ExtentReporter.extentLogger("applicationToken", applicationToken_BC2);


			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="Create_Bnpl_TransactionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//Segment 1
	public static ValidatableResponse UpdateUserStatusAPI(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	 Merchant
	public static ValidatableResponse Merchant_UpdateUserStatusAPI(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	 PromoCode
	public static ValidatableResponse PromoCode_UpdateUserStatusAPI(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	//	 PromoCode_UpdateUserStatusAPI_Segment1

	public static ValidatableResponse PromoCode_UpdateUserStatusAPI_Segment1(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	//	PlayStore
	public static ValidatableResponse PlayStore_UpdateUserStatusAPI(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_UpdateUserStatusAPI_Segment1(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	 Merchant_RingPolicy_LTBC1
	public static ValidatableResponse Merchant_UpdateUserStatusAPI_LTBC1(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	 Merchant_RingPolicy_BC1
	public static ValidatableResponse Merchant_UpdateUserStatusAPI_BC1(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}

	
//	 Merchant_RingPolicy_L1
	public static ValidatableResponse Merchant_UpdateUserStatusAPI_L1(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}

	
//	 Merchant_RingPolicy_L2
	public static ValidatableResponse Merchant_UpdateUserStatusAPI_L2(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	 Merchant_RingPolicy_L3
	public static ValidatableResponse Merchant_UpdateUserStatusAPI_L3(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L3);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
	//	 Merchant_RingPolicy_BC2
	public static ValidatableResponse Merchant_UpdateUserStatusAPI_BC2(Object[][] data) throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.updateUserStatusEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("Gender", (String) data[0][0]);
			req_body.put("mother_name", (String) data[0][1]);
			req_body.put("name_verification_status", (String) data[0][2]);
			req_body.put("dob_verification_status", (String) data[0][3]);
			req_body.put("pan_verification_status", (String) data[0][4]);
			req_body.put("mobile_verification_status", (String) data[0][5]);
			req_body.put("gender_verification_status", (String) data[0][6]);
			req_body.put("yob_verification_status", (String) data[0][7]);
			req_body.put("onboarding_stage", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("Gender", req_body.get("Gender"));
			Myrequestbody.put("mother_name", req_body.get("mother_name"));
			Myrequestbody.put("name_verification_status", req_body.get("name_verification_status"));
			Myrequestbody.put("dob_verification_status", req_body.get("dob_verification_status"));
			Myrequestbody.put("pan_verification_status", req_body.get("pan_verification_status"));
			Myrequestbody.put("mobile_verification_status", req_body.get("mobile_verification_status"));
			Myrequestbody.put("gender_verification_status", req_body.get("gender_verification_status"));
			Myrequestbody.put("yob_verification_status", req_body.get("yob_verification_status"));
			Myrequestbody.put("onboarding_stage", req_body.get("onboarding_stage"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodAPI(headers, Myrequestbody, url);

			////		System.out.println("Request Url -->" + url);
			//		logger.info("Url :" + url);
			//		ExtentReporter.extentLogger("", "Request Url -->" + url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="UpdateUserStatusAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	// Segment 1
	public static ValidatableResponse CheckApplicationEligibilityAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);
			
			
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PromoCode
	public static ValidatableResponse PromoCode_CheckApplicationEligibilityAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			//			headers.put("Authorization",applicationToken);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PromoCode_CheckApplicationEligibilityAPI_Segment1

	public static ValidatableResponse PromoCode_CheckApplicationEligibilityAPI_Segment1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			//			headers.put("Authorization",applicationToken);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	//	PlayStore
	public static ValidatableResponse PlayStore_CheckApplicationEligibilityAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_CheckApplicationEligibilityAPI_Segment1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("user_token :" + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	Merchant_RingPolicy_LTBC1
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI_LTBC1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_LTBC1);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}

	//	Merchant_RingPolicy_BC1
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI_BC1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC1);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


//	Merchant_RingPolicy_L1
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI_L1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L1);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_RingPolicy_L2
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI_L2() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L2);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_RingPolicy_L3
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI_L3() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L3);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	

	//	Merchant_RingPolicy_BC2
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAPI_BC2() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC2);
			headers.put("X-Client-Version", "1.1.7");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}




	// Merchant_Segment 1
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_CheckApplicationEligibilityAfterAddAddressAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_CheckApplicationEligibilityAfterAddAddressAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_CheckApplicationEligibilityAfterAddAddressAPI_Segment1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}




	//	PlayStore
	public static ValidatableResponse PlayStore_CheckApplicationEligibilityAfterAddAddressAPI() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_CheckApplicationEligibilityAfterAddAddressAPI_Segment1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			// Create_Bnpl_Transaction for Application_Token
			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}




	//	Merchant_LTBC1
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI_LTBC1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_LTBC1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}


	//	Merchant_BC1
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI_BC1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}


//	Merchant_L1
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI_L1() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L1);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}

	
//	Merchant_L2
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI_L2() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L2);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}
	
	
//	Merchant_L3
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI_L3() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L3);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}
	
	
	//	Merchant_BC2
	public static ValidatableResponse CheckApplicationEligibilityAfterAddAddressAPI_BC2() throws Exception {

		try {
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.checkApplicationEligibilityEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC2);
			headers.put("X-Client-Version", "1.1.7");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CheckApplicationEligibilityAfterAddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}

	}


	// ========================= PreConditon for Check Application Eligibility
	// ===============

	//	Segment 1
	public static ValidatableResponse AddAddressAPI(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));


			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_S1);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant
	public static ValidatableResponse Merchant_AddAddressAPI(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PromoCode
	public static ValidatableResponse PromoCode_AddAddressAPI(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PromoCode_AddAddressAPI_Segment1

	public static ValidatableResponse PromoCode_AddAddressAPI_Segment1(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore
	public static ValidatableResponse PlayStore_AddAddressAPI(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}



	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_AddAddressAPI_Segment1(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			// Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	Merchant_LTBC1
	public static ValidatableResponse Merchant_AddAddressAPI_LTBC1(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	//	Merchant_BC1
	public static ValidatableResponse Merchant_AddAddressAPI_BC1(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC1);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}

	
//	Merchant_L1
	public static ValidatableResponse Merchant_AddAddressAPI_L1(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L1);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_L2
	public static ValidatableResponse Merchant_AddAddressAPI_L2(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L2);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	
	
//	Merchant_L3
	public static ValidatableResponse Merchant_AddAddressAPI_L3(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_L3);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}
	

	//	Merchant_BC2
	public static ValidatableResponse Merchant_AddAddressAPI_BC2(Object[][] data) throws Exception {
		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.addAddressEndPoint);
			System.out.println("Url :" + url);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("room_number", (String) data[0][1]);
			req_body.put("line_1", (String) data[0][2]);
			req_body.put("line_2", (String) data[0][3]);
			//			req_body.put("landmark", (String) data[0][3]);
			req_body.put("pincode", (String) data[0][4]);
			req_body.put("label", (String) data[0][5]);
			req_body.put("tag", (String) data[0][6]);
			//			req_body.put("residence_type", (String) data[0][8]);
			req_body.put("product_name", (String) data[0][7]);
			req_body.put("source", (String) data[0][8]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("room_number", req_body.get("room_number"));
			Myrequestbody.put("line_1", req_body.get("line_1"));
			Myrequestbody.put("line_2", req_body.get("line_2"));
			Myrequestbody.put("pincode", req_body.get("pincode"));
			Myrequestbody.put("label", req_body.get("label"));
			Myrequestbody.put("tag", req_body.get("tag"));
			Myrequestbody.put("product_name", req_body.get("product_name"));
			Myrequestbody.put("source", req_body.get("source"));

			HashMap<String, Object> headers = new HashMap<>();
			// headers.put("x-request-id", rand.nextInt(1001));
			headers.put("Authorization", "Bearer " + user_token_BC2);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			System.out.println("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			//		ExtentReporter.extentLogger("", message);

			return response;
		}

		catch (Exception e) {
			String message="AddAddressAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	// ========================= OFFER_DETAILS_SCREEN
	// ===================================

	//	Segment 1
	public static ValidatableResponse getOfferAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			//		System.out.println("ApplicationToken: " + applicationToken);
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_getOfferAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PromoCode
	public static ValidatableResponse PromoCode_getOfferAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			//		System.out.println("ApplicationToken: " + applicationToken);
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_getOfferAPI_Segment1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			//		System.out.println("ApplicationToken: " + applicationToken);
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	//	PlayStore

	public static ValidatableResponse PlayStore_GetOfferAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			//		System.out.println("ApplicationToken: " + applicationToken);
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_GetOfferAPI_Segment1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			//		System.out.println("ApplicationToken: " + applicationToken);
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}	



	//	Merchant_LTBC1
	public static ValidatableResponse Merchant_getOfferAPI_LTBC1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant_BC1
	public static ValidatableResponse Merchant_getOfferAPI_BC1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	Merchant_L1
	public static ValidatableResponse Merchant_getOfferAPI_L1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	Merchant_L2
	public static ValidatableResponse Merchant_getOfferAPI_L2() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}

	
//	Merchant_L3
	public static ValidatableResponse Merchant_getOfferAPI_L3() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L3);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}
	
	
	//	Merchant_BC2
	public static ValidatableResponse Merchant_getOfferAPI_BC2() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getOffersEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}

		catch (Exception e) {
			String message="getOfferAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	Segment 1
	public static ValidatableResponse User_ConcentAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_User_ConcentAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_User_ConcentAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_User_ConcentAPI_Segment1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PromoCode_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			
			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}




	//	PlayStore
	public static ValidatableResponse PlayStore_User_ConcentAPI() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_User_ConcentAPI_Segment1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse bnplResponse = com.business.RingPay_PlayStore_Journey_Segment1.BasicDetailScreen_Create_Bnpl_Transaction.getApplicationToken_Positive();
			//
			//			String applicationToken = bnplResponse.extract().body().jsonPath().get("data.application_token");
			//			logger.info("ApplicationToken: " + applicationToken);
			//			ExtentReporter.extentLogger("applicationToken", applicationToken);

			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant_LTBC1
	public static ValidatableResponse Merchant_User_ConcentAPI_LTBC1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant_BC1
	public static ValidatableResponse Merchant_User_ConcentAPI_BC1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


//	Merchant_L1
	public static ValidatableResponse Merchant_User_ConcentAPI_L1() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}
	
	
//	Merchant_L2
	public static ValidatableResponse Merchant_User_ConcentAPI_L2() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}
	
	
//	Merchant_L3
	public static ValidatableResponse Merchant_User_ConcentAPI_L3() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L3);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}
	
	
	//	Merchant_BC2
	public static ValidatableResponse Merchant_User_ConcentAPI_BC2() throws Exception {

		try {

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.userConcentEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.patchMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="User_ConcentAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	// Segment 1
	public static ValidatableResponse bnplLinesAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.bnplLinesEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			ValidatableResponse response = Utilities.postMethodWithHeadersAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;
		}
		catch (Exception e) {
			String message="bnplLinesAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_bnplLinesAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.bnplLinesEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			ValidatableResponse response = Utilities.postMethodWithHeadersAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;
		}
		catch (Exception e) {
			String message="bnplLinesAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PromoCode
	public static ValidatableResponse PromoCode_bnplLinesAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.bnplLinesEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			ValidatableResponse response = Utilities.postMethodWithHeadersAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;
		}
		catch (Exception e) {
			String message="bnplLinesAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_bnplLinesAPI_Segment1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.bnplLinesEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			ValidatableResponse response = Utilities.postMethodWithHeadersAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;
		}
		catch (Exception e) {
			String message="bnplLinesAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PlayStore
	public static ValidatableResponse PlayStore_bnplLinesAPI() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.bnplLinesEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			ValidatableResponse response = Utilities.postMethodWithHeadersAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;
		}
		catch (Exception e) {
			String message="bnplLinesAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	PlayStore_S1
	public static ValidatableResponse PlayStore_bnplLinesAPI_Segment1() throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.bnplLinesEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			ValidatableResponse response = Utilities.postMethodWithHeadersAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;
		}
		catch (Exception e) {
			String message="bnplLinesAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	// Random Product Value

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	//	Segment 1
	public static ValidatableResponse PaymentOptionAPI(Object[][] data) throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.paymentOptionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			int nProductValue = getRandomNumber(100, 200);
			String sProductValue = String.valueOf(nProductValue);
			System.out.println("productValue: " + sProductValue);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 2, 2);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sProductValue, 1, 2);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("reason", (String) data[0][0]);
			req_body.put("actual_amount", (String) data[0][1]);
			req_body.put("qr_code", (String) data[0][2]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("reason", req_body.get("reason"));
			Myrequestbody.put("actual_amount", req_body.get("actual_amount"));
			Myrequestbody.put("qr_code", req_body.get("qr_code"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String upi_handle_reference_number = response.extract().body().jsonPath().get("data.upi_details.upi_handle_reference_number");
			logger.info("upi_handle_reference_number: " + upi_handle_reference_number);
			ExtentReporter.extentLogger("upi_handle_reference_number", upi_handle_reference_number);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", upi_handle_reference_number, 1, 5);

			return response;

		}
		catch (Exception e) {
			String message="PaymentOptionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}


	// Merchant
	public static ValidatableResponse Merchant_PaymentOptionAPI(Object[][] data) throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.paymentOptionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			int nProductValue = getRandomNumber(100, 200);
			String sProductValue = String.valueOf(nProductValue);
			System.out.println("productValue: " + sProductValue);


			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "PaymentOption", sProductValue, 1, 2);
			ExcelWriteData.excelWrite(filePath, "PaymentOption", sProductValue, 2, 2);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", sProductValue, 1, 2);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("reason", (String) data[0][0]);
			req_body.put("actual_amount", (String) data[0][1]);
			req_body.put("qr_code", (String) data[0][2]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("reason", req_body.get("reason"));
			Myrequestbody.put("actual_amount", req_body.get("actual_amount"));
			Myrequestbody.put("qr_code", req_body.get("qr_code"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String upi_handle_reference_number = response.extract().body().jsonPath().get("data.upi_details.upi_handle_reference_number");
			logger.info("upi_handle_reference_number: " + upi_handle_reference_number);
			ExtentReporter.extentLogger("upi_handle_reference_number", upi_handle_reference_number);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", upi_handle_reference_number, 1, 5);

			return response;

		}
		catch (Exception e) {
			String message="PaymentOptionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	// PromoCode
	public static ValidatableResponse PromoCode_PaymentOptionAPI(Object[][] data) throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.paymentOptionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			int nProductValue = getRandomNumber(100, 200);
			String sProductValue = String.valueOf(nProductValue);
			System.out.println("productValue: " + sProductValue);


			// Data to User_Onboarding
			ExcelWriteData.excelWrite(filePath, "PaymentOption", sProductValue, 1, 2);
			ExcelWriteData.excelWrite(filePath, "PaymentOption", sProductValue, 2, 2);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", sProductValue, 1, 2);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("reason", (String) data[0][0]);
			req_body.put("actual_amount", (String) data[0][1]);
			req_body.put("qr_code", (String) data[0][2]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("reason", req_body.get("reason"));
			Myrequestbody.put("actual_amount", req_body.get("actual_amount"));
			Myrequestbody.put("qr_code", req_body.get("qr_code"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String upi_handle_reference_number = response.extract().body().jsonPath().get("data.upi_details.upi_handle_reference_number");
			logger.info("upi_handle_reference_number: " + upi_handle_reference_number);
			ExtentReporter.extentLogger("upi_handle_reference_number", upi_handle_reference_number);

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", upi_handle_reference_number, 1, 5);

			return response;

		}
		catch (Exception e) {
			String message="PaymentOptionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	// PromoCode_Segment1
	public static ValidatableResponse PromoCode_PaymentOptionAPI_Segment1(Object[][] data) throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.paymentOptionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			int nProductValue = getRandomNumber(100, 200);
			String sProductValue = String.valueOf(nProductValue);
			System.out.println("productValue: " + sProductValue);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 2, 2);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sProductValue, 1, 2);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("reason", (String) data[0][0]);
			req_body.put("actual_amount", (String) data[0][1]);
			req_body.put("qr_code", (String) data[0][2]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("reason", req_body.get("reason"));
			Myrequestbody.put("actual_amount", req_body.get("actual_amount"));
			Myrequestbody.put("qr_code", req_body.get("qr_code"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String upi_handle_reference_number = response.extract().body().jsonPath().get("data.upi_details.upi_handle_reference_number");
			logger.info("upi_handle_reference_number: " + upi_handle_reference_number);
			ExtentReporter.extentLogger("upi_handle_reference_number", upi_handle_reference_number);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", upi_handle_reference_number, 1, 5);

			return response;

		}
		catch (Exception e) {
			String message="PaymentOptionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore
	public static ValidatableResponse PlayStore_PaymentOptionAPI(Object[][] data) throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.paymentOptionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			int nProductValue = getRandomNumber(100, 200);
			String sProductValue = String.valueOf(nProductValue);
			System.out.println("productValue: " + sProductValue);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 2, 2);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sProductValue, 1, 2);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("reason", (String) data[0][0]);
			req_body.put("actual_amount", (String) data[0][1]);
			req_body.put("qr_code", (String) data[0][2]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("reason", req_body.get("reason"));
			Myrequestbody.put("actual_amount", req_body.get("actual_amount"));
			Myrequestbody.put("qr_code", req_body.get("qr_code"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String upi_handle_reference_number = response.extract().body().jsonPath().get("data.upi_details.upi_handle_reference_number");
			logger.info("upi_handle_reference_number: " + upi_handle_reference_number);
			ExtentReporter.extentLogger("upi_handle_reference_number", upi_handle_reference_number);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", upi_handle_reference_number, 1, 5);

			return response;

		}
		catch (Exception e) {
			String message="PaymentOptionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_PaymentOptionAPI_Segment1(Object[][] data) throws Exception {

		try {

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.paymentOptionEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);

			int nProductValue = getRandomNumber(100, 200);
			String sProductValue = String.valueOf(nProductValue);
			System.out.println("productValue: " + sProductValue);


			// Data to User_Onboarding
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 1, 2);
			ExcelWriteData.DemoExcel(filePath, "PaymentOption", sProductValue, 2, 2);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sProductValue, 1, 2);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("reason", (String) data[0][0]);
			req_body.put("actual_amount", (String) data[0][1]);
			req_body.put("qr_code", (String) data[0][2]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("reason", req_body.get("reason"));
			Myrequestbody.put("actual_amount", req_body.get("actual_amount"));
			Myrequestbody.put("qr_code", req_body.get("qr_code"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			// fetching data
			String upi_handle_reference_number = response.extract().body().jsonPath().get("data.upi_details.upi_handle_reference_number");
			logger.info("upi_handle_reference_number: " + upi_handle_reference_number);
			ExtentReporter.extentLogger("upi_handle_reference_number", upi_handle_reference_number);

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", upi_handle_reference_number, 1, 5);

			return response;

		}
		catch (Exception e) {
			String message="PaymentOptionAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
			return null;
		}


	}



	// Random merchant Order

	public static long merchant_order_id(long min, long max) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextLong(min, max);
	}
	
	
	
	public static long merchant_order_id_PromoCode_S1(long min, long max) {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		return random.nextLong(min, max);
	}
	

	public static String sku_description(int len) {
		String chars = "ABCDEFGHIJKLMNOPQRSTU";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}


	//	Segment 1
	public static ValidatableResponse TransactionInitiateAPI(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnInitiateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

//			long id = merchant_order_id(59999000001L, 59999999999L);
			
			String sMerchantOrder = "59999"+RandomIntegerGenerator(6);
//			String sMerchantOrder = String.valueOf(id);
			logger.info("MerchantOrder: " + sMerchantOrder);

			
			String sku_Description = sku_description(6);
			logger.info("sku_Description :" + sku_Description);

			// fetching data

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", "bnpl"+sMerchantOrder, 1, 4);


			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sku_Description, 1, 6);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("product_value", (String) data[0][1]);
			req_body.put("transaction_type", (String) data[0][2]);
			req_body.put("merchant_order_id", (String) data[0][3]);
			req_body.put("upi_handle_reference_number", (String) data[0][4]);
			req_body.put("sku_description", (String) data[0][5]);
			req_body.put("latitude", (String) data[0][6]);
			req_body.put("longitude", (String) data[0][7]);
			req_body.put("global_device_id", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("product_value", req_body.get("product_value"));
			Myrequestbody.put("transaction_type", req_body.get("transaction_type"));
			Myrequestbody.put("merchant_order_id", req_body.get("merchant_order_id"));
			Myrequestbody.put("upi_handle_reference_number", req_body.get("upi_handle_reference_number"));
			Myrequestbody.put("sku_description", req_body.get("sku_description"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;


		}
		catch (Exception e) {
			String message="TransactionInitiateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}


	// Merchant
	public static ValidatableResponse Merchant_TransactionInitiateAPI(Object[][] data) throws Exception {

//		try {
		
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnInitiateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

//			long id = merchant_order_id(59999000001L, 59999999999L);
//			String sMerchantOrder = String.valueOf(id);
//			logger.info("MerchantOrder: " + sMerchantOrder);
			
			String sMerchantOrder = "59999"+RandomIntegerGenerator(6);
			logger.info("MerchantOrder: " + sMerchantOrder);
			

//			String MerchantOrder_bnpl="bnpl"+sMerchantOrder;
//			ExtentReporter.extentLogger("MerchantOrder", MerchantOrder_bnpl);
//			logger.info("MerchantOrder :" + MerchantOrder_bnpl);
			
			String sku_Description = sku_description(6);
			logger.info("sku_Description :" + sku_Description);

			// fetching data

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", "bnpl"+sMerchantOrder, 1, 4);


			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sku_Description, 1, 6);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("product_value", (String) data[0][1]);
			req_body.put("transaction_type", (String) data[0][2]);
			req_body.put("merchant_order_id", (String) data[0][3]);
			req_body.put("upi_handle_reference_number", (String) data[0][4]);
			req_body.put("sku_description", (String) data[0][5]);
			req_body.put("latitude", (String) data[0][6]);
			req_body.put("longitude", (String) data[0][7]);
			req_body.put("global_device_id", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("product_value", req_body.get("product_value"));
			Myrequestbody.put("transaction_type", req_body.get("transaction_type"));
			Myrequestbody.put("merchant_order_id", req_body.get("merchant_order_id"));
			Myrequestbody.put("upi_handle_reference_number", req_body.get("upi_handle_reference_number"));
			Myrequestbody.put("sku_description", req_body.get("sku_description"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;


//		}
//		catch (Exception e) {
//			String message="TransactionInitiateAPI";
//			ExtentReporter.extentLoggerFail(message+" - Failed");
//			return null;
//		}

	}



	// PromoCode
	public static ValidatableResponse PromoCode_TransactionInitiateAPI(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnInitiateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

//			long id = merchant_order_id(59999000001L, 59999999999L);
//			String sMerchantOrder = String.valueOf(id);
			
			String sMerchantOrder = "59999"+RandomIntegerGenerator(6);
			logger.info("MerchantOrder: " + sMerchantOrder);

			String sku_Description = sku_description(6);
			logger.info("sku_Description :" + sku_Description);

			// fetching data

			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", "bnpl"+sMerchantOrder, 1, 4);


			// Data to Txn_Initiated
			ExcelWriteData.excelWrite(filePath, "Txn_Initiate", sku_Description, 1, 6);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("product_value", (String) data[0][1]);
			req_body.put("transaction_type", (String) data[0][2]);
			req_body.put("merchant_order_id", (String) data[0][3]);
			req_body.put("upi_handle_reference_number", (String) data[0][4]);
			req_body.put("sku_description", (String) data[0][5]);
			req_body.put("latitude", (String) data[0][6]);
			req_body.put("longitude", (String) data[0][7]);
			req_body.put("global_device_id", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("product_value", req_body.get("product_value"));
			Myrequestbody.put("transaction_type", req_body.get("transaction_type"));
			Myrequestbody.put("merchant_order_id", req_body.get("merchant_order_id"));
			Myrequestbody.put("upi_handle_reference_number", req_body.get("upi_handle_reference_number"));
			Myrequestbody.put("sku_description", req_body.get("sku_description"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;


		}
		catch (Exception e) {
			String message="TransactionInitiateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	// PromoCode_Segment1
	public static ValidatableResponse PromoCode_TransactionInitiateAPI_Segment1(Object[][] data) throws Exception {

//		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnInitiateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

			
//			long id = merchant_order_id_PromoCode_S1(59999000001L, 59999999999L);
			String sMerchantOrder = "59999"+RandomIntegerGenerator(6);
			logger.info("MerchantOrder: " + sMerchantOrder);

			String sku_Description = sku_description(6);
			logger.info("sku_Description :" + sku_Description);

			// fetching data

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", "bnpl"+sMerchantOrder, 1, 4);


			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sku_Description, 1, 6);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("product_value", (String) data[0][1]);
			req_body.put("transaction_type", (String) data[0][2]);
			req_body.put("merchant_order_id", (String) data[0][3]);
			req_body.put("upi_handle_reference_number", (String) data[0][4]);
			req_body.put("sku_description", (String) data[0][5]);
			req_body.put("latitude", (String) data[0][6]);
			req_body.put("longitude", (String) data[0][7]);
			req_body.put("global_device_id", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("product_value", req_body.get("product_value"));
			Myrequestbody.put("transaction_type", req_body.get("transaction_type"));
			Myrequestbody.put("merchant_order_id", req_body.get("merchant_order_id"));
			Myrequestbody.put("upi_handle_reference_number", req_body.get("upi_handle_reference_number"));
			Myrequestbody.put("sku_description", req_body.get("sku_description"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;


		}
//		catch (Exception e) {
//			String message="TransactionInitiateAPI";
//			ExtentReporter.extentLoggerFail(message+" - Failed");
//			return null;
//		}
//
//	}



	//	PlayStore
	public static ValidatableResponse PlayStore_TransactionInitiateAPI(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnInitiateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

//			long id = merchant_order_id(59999000001L, 59999999999L);
//			String sMerchantOrder = String.valueOf(id);
//			logger.info("MerchantOrder: " + sMerchantOrder);

			String sMerchantOrder = "59999"+RandomIntegerGenerator(6);
			logger.info("MerchantOrder: " + sMerchantOrder);
			
			ExtentReporter.extentLogger("MerchantOrder", sMerchantOrder);
			
			String MerchantID="bnpl"+sMerchantOrder;
			
			ExtentReporter.extentLogger("MerchantID", MerchantID);
			
			String sku_Description = sku_description(6);
			logger.info("sku_Description :" + sku_Description);

			// fetching data

			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", MerchantID, 1, 4);


			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sku_Description, 1, 6);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("product_value", (String) data[0][1]);
			req_body.put("transaction_type", (String) data[0][2]);
			req_body.put("merchant_order_id", (String) data[0][3]);
			req_body.put("upi_handle_reference_number", (String) data[0][4]);
			req_body.put("sku_description", (String) data[0][5]);
			req_body.put("latitude", (String) data[0][6]);
			req_body.put("longitude", (String) data[0][7]);
			req_body.put("global_device_id", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("product_value", req_body.get("product_value"));
			Myrequestbody.put("transaction_type", req_body.get("transaction_type"));
			Myrequestbody.put("merchant_order_id", req_body.get("merchant_order_id"));
			Myrequestbody.put("upi_handle_reference_number", req_body.get("upi_handle_reference_number"));
			Myrequestbody.put("sku_description", req_body.get("sku_description"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;


		}
		catch (Exception e) {
			String message="TransactionInitiateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_TransactionInitiateAPI_Segment1(Object[][] data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnInitiateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			// Random rand = new Random();

//			long id = merchant_order_id(59999000001L, 59999999999L);
//			String sMerchantOrder = String.valueOf(id);
			
			String sMerchantOrder = "59999"+RandomIntegerGenerator(6);
			logger.info("MerchantOrder: " + sMerchantOrder);

//			ExtentReporter.extentLogger("MerchantOrder", sMerchantOrder);
			
			String sku_Description = sku_description(6);
			logger.info("sku_Description :" + sku_Description);

			// fetching data
			String sMerchantOrderempty = "";
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sMerchantOrderempty, 1, 4);
			
			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", "bnpl"+sMerchantOrder, 1, 4);


			// Data to Txn_Initiated
			ExcelWriteData.DemoExcel(filePath, "Txn_Initiate", sku_Description, 1, 6);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("product_value", (String) data[0][1]);
			req_body.put("transaction_type", (String) data[0][2]);
			req_body.put("merchant_order_id", (String) data[0][3]);
			req_body.put("upi_handle_reference_number", (String) data[0][4]);
			req_body.put("sku_description", (String) data[0][5]);
			req_body.put("latitude", (String) data[0][6]);
			req_body.put("longitude", (String) data[0][7]);
			req_body.put("global_device_id", (String) data[0][8]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("product_value", req_body.get("product_value"));
			Myrequestbody.put("transaction_type", req_body.get("transaction_type"));
			Myrequestbody.put("merchant_order_id", req_body.get("merchant_order_id"));
			Myrequestbody.put("upi_handle_reference_number", req_body.get("upi_handle_reference_number"));
			Myrequestbody.put("sku_description", req_body.get("sku_description"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;


		}
		catch (Exception e) {
			String message="TransactionInitiateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	//	Segment 1
	public static ValidatableResponse TransactionCompleteAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnCompleteEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("transaction_reference_number", (String) data[0][0]);
			req_body.put("transaction_pin_hash", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("transaction_reference_number", req_body.get("transaction_reference_number"));
			Myrequestbody.put("transaction_pin_hash", req_body.get("transaction_pin_hash"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="TransactionCompleteAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_TransactionCompleteAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnCompleteEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("transaction_reference_number", (String) data[0][0]);
			req_body.put("transaction_pin_hash", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("transaction_reference_number", req_body.get("transaction_reference_number"));
			Myrequestbody.put("transaction_pin_hash", req_body.get("transaction_pin_hash"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="TransactionCompleteAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_TransactionCompleteAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnCompleteEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("transaction_reference_number", (String) data[0][0]);
			req_body.put("transaction_pin_hash", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("transaction_reference_number", req_body.get("transaction_reference_number"));
			Myrequestbody.put("transaction_pin_hash", req_body.get("transaction_pin_hash"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="TransactionCompleteAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_TransactionCompleteAPI_Segment1(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnCompleteEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("transaction_reference_number", (String) data[0][0]);
			req_body.put("transaction_pin_hash", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("transaction_reference_number", req_body.get("transaction_reference_number"));
			Myrequestbody.put("transaction_pin_hash", req_body.get("transaction_pin_hash"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="TransactionCompleteAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	PlayStore
	public static ValidatableResponse PlayStore_TransactionCompleteAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnCompleteEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("transaction_reference_number", (String) data[0][0]);
			req_body.put("transaction_pin_hash", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("transaction_reference_number", req_body.get("transaction_reference_number"));
			Myrequestbody.put("transaction_pin_hash", req_body.get("transaction_pin_hash"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="TransactionCompleteAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	//	PlayStore_S1
	public static ValidatableResponse PlayStore_TransactionCompleteAPI_Segment1(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.txnCompleteEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			//		System.out.println("UserToken: " + user_token);
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("user_token", user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("transaction_reference_number", (String) data[0][0]);
			req_body.put("transaction_pin_hash", (String) data[0][1]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("transaction_reference_number", req_body.get("transaction_reference_number"));
			Myrequestbody.put("transaction_pin_hash", req_body.get("transaction_pin_hash"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="TransactionCompleteAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	Segment1
	public static ValidatableResponse CurrentSpendsAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.currentSpendsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);


			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);
			req_body.put("client_id", (String) data[0][6]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("client_id", req_body.get("client_id"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CurrentSpendsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_CurrentSpendsAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.currentSpendsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);
			req_body.put("client_id", (String) data[0][6]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("client_id", req_body.get("client_id"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CurrentSpendsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	PromoCode
	public static ValidatableResponse PromoCode_CurrentSpendsAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.currentSpendsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);
			req_body.put("client_id", (String) data[0][6]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("client_id", req_body.get("client_id"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CurrentSpendsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_CurrentSpendsAPI_Segment1(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.currentSpendsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);
			req_body.put("client_id", (String) data[0][6]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("client_id", req_body.get("client_id"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CurrentSpendsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	// PlayStore
	public static ValidatableResponse PlayStore_CurrentSpendsAPI(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.currentSpendsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);
			req_body.put("client_id", (String) data[0][6]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("client_id", req_body.get("client_id"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CurrentSpendsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	// PlayStore_S1
	public static ValidatableResponse PlayStore_CurrentSpendsAPI_Segment1(Object[][] data) throws Exception {

		try {
			// String filePath=
			// System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.currentSpendsEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("advertising_id", (String) data[0][0]);
			req_body.put("android_id", (String) data[0][1]);
			req_body.put("global_device_id", (String) data[0][2]);
			req_body.put("imei_number", (String) data[0][3]);
			req_body.put("latitude", (String) data[0][4]);
			req_body.put("longitude", (String) data[0][5]);
			req_body.put("client_id", (String) data[0][6]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("advertising_id", req_body.get("advertising_id"));
			Myrequestbody.put("android_id", req_body.get("android_id"));
			Myrequestbody.put("global_device_id", req_body.get("global_device_id"));
			Myrequestbody.put("imei_number", req_body.get("imei_number"));
			Myrequestbody.put("latitude", req_body.get("latitude"));
			Myrequestbody.put("longitude", req_body.get("longitude"));
			Myrequestbody.put("client_id", req_body.get("client_id"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body_getoffer= " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="CurrentSpendsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	// Segment 1
	public static ValidatableResponse ValidateAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.validateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			String bene_account_no="33557701"+Mobile_Number_S1;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Validate", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Validate", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_full_name", (String) data[0][11]);
			req_body.put("rmtr_address", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);



			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_address", req_body.get("rmtr_address"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			//		System.out.println("\"validate\"");

			String requestValidate="{"+"\"validate\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="ValidateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_ValidateAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.validateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Mock_User.mock_User_Positive();

			// fetching Mobileno
			//			 Mobile_Number = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			String bene_account_no="33557701"+Mobile_Number;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Validate", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Validate", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_full_name", (String) data[0][11]);
			req_body.put("rmtr_address", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);



			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_address", req_body.get("rmtr_address"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			//		System.out.println("\"validate\"");

			String requestValidate="{"+"\"validate\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="ValidateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	PromoCode
	public static ValidatableResponse PromoCode_ValidateAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.validateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PromoCode_Journey.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			String bene_account_no="33557701"+MobileNumber_PromoCode;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Validate", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Validate", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_full_name", (String) data[0][11]);
			req_body.put("rmtr_address", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);



			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_address", req_body.get("rmtr_address"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			//		System.out.println("\"validate\"");

			String requestValidate="{"+"\"validate\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="ValidateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_ValidateAPI_Segment1(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.validateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			String bene_account_no="33557701"+MobileNumber_PromoCode_S1;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Validate", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.DemoExcel(filePath, "Validate", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_full_name", (String) data[0][11]);
			req_body.put("rmtr_address", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);



			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_address", req_body.get("rmtr_address"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			//		System.out.println("\"validate\"");

			String requestValidate="{"+"\"validate\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="ValidateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	//	PlayStore
	public static ValidatableResponse PlayStore_ValidateAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.validateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PlayStore_Journey.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			String bene_account_no="33557701"+MobileNumber_PlayStore;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Validate", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Validate", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_full_name", (String) data[0][11]);
			req_body.put("rmtr_address", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);



			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_address", req_body.get("rmtr_address"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			//		System.out.println("\"validate\"");

			String requestValidate="{"+"\"validate\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="ValidateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	//	PlayStore_S1
	public static ValidatableResponse PlayStore_ValidateAPI_Segment1(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.validateEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);

			String bene_account_no="33557701"+MobileNumber_PlayStore_S1;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Validate", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Validate", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_full_name", (String) data[0][11]);
			req_body.put("rmtr_address", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);



			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_address", req_body.get("rmtr_address"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			//		System.out.println("\"validate\"");

			String requestValidate="{"+"\"validate\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="ValidateAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}

	}




	// Segment 1
	public static ValidatableResponse NotifyAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.notifyEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);


			String bene_account_no="33557701"+Mobile_Number_S1;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Notify", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Notify", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_account_type", (String) data[0][11]);
			req_body.put("rmtr_full_name", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);
			req_body.put("status", (String) data[0][15]);
			req_body.put("credit_acct_no", (String) data[0][16]);
			req_body.put("credited_at", (String) data[0][17]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_account_type", req_body.get("rmtr_account_type"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));
			Myrequestbody.put("status", req_body.get("status"));
			Myrequestbody.put("credit_acct_no", req_body.get("credit_acct_no"));
			Myrequestbody.put("credited_at", "2022-07-14 18:30:25");

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			String requestValidate="{"+"\"notify\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}


	//	Merchant
	public static ValidatableResponse Merchant_NotifyAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.notifyEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_MerchantQRCode_Journey.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);


			String bene_account_no="33557701"+Mobile_Number;

			// Data to bene_account_no
			ExcelWriteData.DemoExcel(filePath, "Notify", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Notify", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_account_type", (String) data[0][11]);
			req_body.put("rmtr_full_name", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);
			req_body.put("status", (String) data[0][15]);
			req_body.put("credit_acct_no", (String) data[0][16]);
			req_body.put("credited_at", (String) data[0][17]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_account_type", req_body.get("rmtr_account_type"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));
			Myrequestbody.put("status", req_body.get("status"));
			Myrequestbody.put("credit_acct_no", req_body.get("credit_acct_no"));
			Myrequestbody.put("credited_at", "2022-07-14 18:30:25");

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			String requestValidate="{"+"\"notify\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}



	//	PromoCode
	public static ValidatableResponse PromoCode_NotifyAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.notifyEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PromoCode_Journey.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);


			String bene_account_no="33557701"+MobileNumber_PromoCode;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Notify", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Notify", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_account_type", (String) data[0][11]);
			req_body.put("rmtr_full_name", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);
			req_body.put("status", (String) data[0][15]);
			req_body.put("credit_acct_no", (String) data[0][16]);
			req_body.put("credited_at", (String) data[0][17]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_account_type", req_body.get("rmtr_account_type"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));
			Myrequestbody.put("status", req_body.get("status"));
			Myrequestbody.put("credit_acct_no", req_body.get("credit_acct_no"));
			Myrequestbody.put("credited_at", "2022-07-14 18:30:25");

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			String requestValidate="{"+"\"notify\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_NotifyAPI_Segment1(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.notifyEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);


			String bene_account_no="33557701"+MobileNumber_PromoCode_S1;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Notify", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.DemoExcel(filePath, "Notify", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_account_type", (String) data[0][11]);
			req_body.put("rmtr_full_name", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);
			req_body.put("status", (String) data[0][15]);
			req_body.put("credit_acct_no", (String) data[0][16]);
			req_body.put("credited_at", (String) data[0][17]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_account_type", req_body.get("rmtr_account_type"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));
			Myrequestbody.put("status", req_body.get("status"));
			Myrequestbody.put("credit_acct_no", req_body.get("credit_acct_no"));
			Myrequestbody.put("credited_at", "2022-07-14 18:30:25");

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			String requestValidate="{"+"\"notify\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}	



	//	PlayStore
	public static ValidatableResponse PlayStore_NotifyAPI(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.notifyEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PlayStore_Journey.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);


			String bene_account_no="33557701"+MobileNumber_PlayStore;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Notify", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Notify", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_account_type", (String) data[0][11]);
			req_body.put("rmtr_full_name", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);
			req_body.put("status", (String) data[0][15]);
			req_body.put("credit_acct_no", (String) data[0][16]);
			req_body.put("credited_at", (String) data[0][17]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_account_type", req_body.get("rmtr_account_type"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));
			Myrequestbody.put("status", req_body.get("status"));
			Myrequestbody.put("credit_acct_no", req_body.get("credit_acct_no"));
			Myrequestbody.put("credited_at", "2022-07-14 18:30:25");

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			String requestValidate="{"+"\"notify\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_NotifyAPI_Segment1(Object[][] data) throws Exception {

		try {
			String filePath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.externalURL.concat(RingPay_Endpoints.notifyEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse mockUserResponse= com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_Mock_User.mock_User_Positive();
			//
			//			// fetching Mobileno
			//			String mobileNumber = mockUserResponse.extract().body().jsonPath().get("data.data.mobile_number");
			//			logger.info("MobileNumber : " + mobileNumber);


			String bene_account_no="33557701"+MobileNumber_PlayStore_S1;

			// Data to bene_account_no
			ExcelWriteData.excelWrite(filePath, "Notify", bene_account_no, 1, 2);


			long id = merchant_order_id(80000000001L, 99999999999L);
			String stransfer_unique_no = String.valueOf(id);
			logger.info("MerchantOrder: " + stransfer_unique_no);

			String transfer_unique_no="ICIC"+stransfer_unique_no;


			// Data to transfer_unique_no
			ExcelWriteData.excelWrite(filePath, "Notify", transfer_unique_no, 1, 6);


			HashMap<String, String> req_body = new HashMap<>();

			req_body.put("customer_code", (String) data[0][0]);
			req_body.put("bene_account_no", (String) data[0][1]);
			req_body.put("bene_account_ifsc", (String) data[0][2]);
			req_body.put("bene_full_name", (String) data[0][3]);
			req_body.put("transfer_type", (String) data[0][4]);
			req_body.put("transfer_unique_no", (String) data[0][5]);
			req_body.put("transfer_timestamp", (String) data[0][6]);
			req_body.put("transfer_ccy", (String) data[0][7]);
			req_body.put("transfer_amt", (String) data[0][8]);
			req_body.put("rmtr_account_no", (String) data[0][9]);
			req_body.put("rmtr_account_ifsc", (String) data[0][10]);
			req_body.put("rmtr_account_type", (String) data[0][11]);
			req_body.put("rmtr_full_name", (String) data[0][12]);
			req_body.put("rmtr_to_bene_note", (String) data[0][13]);
			req_body.put("attempt_no", (String) data[0][14]);
			req_body.put("status", (String) data[0][15]);
			req_body.put("credit_acct_no", (String) data[0][16]);
			req_body.put("credited_at", (String) data[0][17]);


			JSONObject Myrequestbody = new JSONObject();


			Myrequestbody.put("customer_code", req_body.get("customer_code"));
			Myrequestbody.put("bene_account_no", req_body.get("bene_account_no"));
			Myrequestbody.put("bene_account_ifsc", req_body.get("bene_account_ifsc"));
			Myrequestbody.put("bene_full_name", req_body.get("bene_full_name"));
			Myrequestbody.put("transfer_type", req_body.get("transfer_type"));
			Myrequestbody.put("transfer_unique_no", req_body.get("transfer_unique_no"));
			Myrequestbody.put("transfer_timestamp", "2022-08-25 15:08:24");
			Myrequestbody.put("transfer_ccy", req_body.get("transfer_ccy"));
			Myrequestbody.put("transfer_amt", req_body.get("transfer_amt"));
			Myrequestbody.put("rmtr_account_no", req_body.get("rmtr_account_no"));
			Myrequestbody.put("rmtr_account_ifsc", req_body.get("rmtr_account_ifsc"));
			Myrequestbody.put("rmtr_account_type", req_body.get("rmtr_account_type"));
			Myrequestbody.put("rmtr_full_name", req_body.get("rmtr_full_name"));
			Myrequestbody.put("rmtr_to_bene_note", req_body.get("rmtr_to_bene_note"));
			Myrequestbody.put("attempt_no", req_body.get("attempt_no"));
			Myrequestbody.put("status", req_body.get("status"));
			Myrequestbody.put("credit_acct_no", req_body.get("credit_acct_no"));
			Myrequestbody.put("credited_at", "2022-07-14 18:30:25");

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Basic dHNsRzFhTVUxMkhsQXVCWThQaVM2dmtWdHh5V0tEazU6MjZFMWZvUFhMVXRiaUZ3a2l6bjA5TG5uQ0w5WlFTNWY=");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			String requestValidate="{"+"\"notify\""+":"+Myrequestbody+"}";
			logger.info("request :"+requestValidate);


			ValidatableResponse response = Utilities.postMethodAuthAPI(headers, requestValidate, url);


			logger.info("Request :" + requestValidate);
			ExtentReporter.extentLogger("", "Request :" + requestValidate);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body " + Resp);

			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}




	// Segment 1
	public static ValidatableResponse GetSettlementAPI(Object[][]data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			Thread.sleep(3000);

			//		ValidatableResponse Response = Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();

			HashMap<String, Object> transaction_reference_number = new HashMap<>();
			transaction_reference_number.put("transaction_reference_number", (String) data[0][0]);

			String Number=String.valueOf(transaction_reference_number);
			System.out.println("number ====="+Number);

			String reference_number = Number.substring(30, 50);
			System.out.println("reference_number :"+reference_number);

			//		 String split[] = Number.split("=", 18);   
			//		 for (String s: split)           
			//			 System.out.println("split :" +s); 
			//		
			//		 String reference_number = Arrays.toString(split[1]);
			//		 System.out.println("reference_number :" +reference_number); 


			//		String transaction_reference_number = Response.extract().body().jsonPath().get("data.transaction.transaction_reference_number");
			//		System.out.println("transaction_reference_number: " + transaction_reference_number);

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getSettlementEndPoint+reference_number+"/settlement-status");
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);



			String Resp = response.extract().body().asString();
			//		System.out.println("Response Body= " + Resp);
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}


	//	Merchant
	public static ValidatableResponse Merchant_GetSettlementAPI(Object[][]data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			Thread.sleep(3000);

			//		ValidatableResponse Response = Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();

			HashMap<String, Object> transaction_reference_number = new HashMap<>();
			transaction_reference_number.put("transaction_reference_number", (String) data[0][0]);

			String Number=String.valueOf(transaction_reference_number);
			System.out.println("number ====="+Number);

			String reference_number = Number.substring(30, 50);
			System.out.println("reference_number :"+reference_number);

			//		 String split[] = Number.split("=", 18);   
			//		 for (String s: split)           
			//			 System.out.println("split :" +s); 
			//		
			//		 String reference_number = Arrays.toString(split[1]);
			//		 System.out.println("reference_number :" +reference_number); 


			//		String transaction_reference_number = Response.extract().body().jsonPath().get("data.transaction.transaction_reference_number");
			//		System.out.println("transaction_reference_number: " + transaction_reference_number);

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getSettlementEndPoint+reference_number+"/settlement-status");
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);



			String Resp = response.extract().body().asString();
			//		System.out.println("Response Body= " + Resp);
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}



	//	PromoCode
	public static ValidatableResponse PromoCode_GetSettlementAPI(Object[][]data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			Thread.sleep(3000);

			//		ValidatableResponse Response = Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();

			HashMap<String, Object> transaction_reference_number = new HashMap<>();
			transaction_reference_number.put("transaction_reference_number", (String) data[0][0]);

			String Number=String.valueOf(transaction_reference_number);
			System.out.println("number ====="+Number);

			String reference_number = Number.substring(30, 50);
			System.out.println("reference_number :"+reference_number);

			//		 String split[] = Number.split("=", 18);   
			//		 for (String s: split)           
			//			 System.out.println("split :" +s); 
			//		
			//		 String reference_number = Arrays.toString(split[1]);
			//		 System.out.println("reference_number :" +reference_number); 


			//		String transaction_reference_number = Response.extract().body().jsonPath().get("data.transaction.transaction_reference_number");
			//		System.out.println("transaction_reference_number: " + transaction_reference_number);

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getSettlementEndPoint+reference_number+"/settlement-status");
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);



			String Resp = response.extract().body().asString();
			//		System.out.println("Response Body= " + Resp);
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="GetSettlementAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}


	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_GetSettlementAPI_Segment1(Object[][]data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			Thread.sleep(3000);

			//		ValidatableResponse Response = Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();

			HashMap<String, Object> transaction_reference_number = new HashMap<>();
			transaction_reference_number.put("transaction_reference_number", (String) data[0][0]);

			String Number=String.valueOf(transaction_reference_number);
			System.out.println("number ====="+Number);

			String reference_number = Number.substring(30, 50);
			System.out.println("reference_number :"+reference_number);

			//		 String split[] = Number.split("=", 18);   
			//		 for (String s: split)           
			//			 System.out.println("split :" +s); 
			//		
			//		 String reference_number = Arrays.toString(split[1]);
			//		 System.out.println("reference_number :" +reference_number); 


			//		String transaction_reference_number = Response.extract().body().jsonPath().get("data.transaction.transaction_reference_number");
			//		System.out.println("transaction_reference_number: " + transaction_reference_number);

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getSettlementEndPoint+reference_number+"/settlement-status");
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);



			String Resp = response.extract().body().asString();
			//		System.out.println("Response Body= " + Resp);
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}




	//	PlayStore
	public static ValidatableResponse PlayStore_GetSettlementAPI(Object[][]data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);
			//
			//			Thread.sleep(3000);

			//		ValidatableResponse Response = Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();

			HashMap<String, Object> transaction_reference_number = new HashMap<>();
			transaction_reference_number.put("transaction_reference_number", (String) data[0][0]);

			String Number=String.valueOf(transaction_reference_number);
			System.out.println("number ====="+Number);

			String reference_number = Number.substring(30, 50);
			System.out.println("reference_number :"+reference_number);

			//		 String split[] = Number.split("=", 18);   
			//		 for (String s: split)           
			//			 System.out.println("split :" +s); 
			//		
			//		 String reference_number = Arrays.toString(split[1]);
			//		 System.out.println("reference_number :" +reference_number); 


			//		String transaction_reference_number = Response.extract().body().jsonPath().get("data.transaction.transaction_reference_number");
			//		System.out.println("transaction_reference_number: " + transaction_reference_number);

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getSettlementEndPoint+reference_number+"/settlement-status");
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);



			String Resp = response.extract().body().asString();
			//		System.out.println("Response Body= " + Resp);
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="GetSettlementAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}


	//	PlayStore_S1
	public static ValidatableResponse PlayStore_GetSettlementAPI_Segment1(Object[][]data) throws Exception {

		try {
			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			System.out.println("UserToken: " + user_token);

			//			Thread.sleep(3000);

			//		ValidatableResponse Response = Bnpl_Txn_Transaction_Initiate.transactionInitiate_Positive();

			HashMap<String, Object> transaction_reference_number = new HashMap<>();
			transaction_reference_number.put("transaction_reference_number", (String) data[0][0]);

			String Number=String.valueOf(transaction_reference_number);
			System.out.println("number ====="+Number);

			String reference_number = Number.substring(30, 50);
			System.out.println("reference_number :"+reference_number);

			//		 String split[] = Number.split("=", 18);   
			//		 for (String s: split)           
			//			 System.out.println("split :" +s); 
			//		
			//		 String reference_number = Arrays.toString(split[1]);
			//		 System.out.println("reference_number :" +reference_number); 


			//		String transaction_reference_number = Response.extract().body().jsonPath().get("data.transaction.transaction_reference_number");
			//		System.out.println("transaction_reference_number: " + transaction_reference_number);

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getSettlementEndPoint+reference_number+"/settlement-status");
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer "+user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);

			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);



			String Resp = response.extract().body().asString();
			//		System.out.println("Response Body= " + Resp);
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch (Exception e) {
			String message="NotifyAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");
			return null;
		}
	}



	// ============================================ PromoCode ============================================


	public static ValidatableResponse PromoCodeAPI() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.promoCodeEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);

			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			// fetching gender
			String promo_code_reference_number = response.extract().body().jsonPath().get("data.promocode.promo_code_reference_number");
			logger.info("promo_code_reference_number : " + promo_code_reference_number);


			// ================== Write Excel =======================

			// MobileNo to SentOtp
			ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", promo_code_reference_number, 1, 8);

			ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", promo_code_reference_number, 10, 8);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="PromoCodeAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	PromoCode_Segment1_API

	public static ValidatableResponse PromoCode_Segment1_API() throws Exception {

		//		try
		//		{

		String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

		String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.promoCodeEndPoint);
		logger.info("Url :" + url);
		ExtentReporter.extentLogger("url", url);

		//		ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
		//
		//		String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
		//		logger.info("UserToken: " + user_token);
		//		ExtentReporter.extentLogger("UserToken: ",user_token);

		Random rand = new Random();


		HashMap<String, Object> headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + user_token_promocode_S1);

		String header=String.valueOf(headers);
		ExtentReporter.extentLogger("headers","Headers :"+ header);


		ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


		// fetching gender
		String promo_code_reference_number = response.extract().body().jsonPath().get("data.promocode.promo_code_reference_number");
		logger.info("promo_code_reference_number : " + promo_code_reference_number);


		// ================== Write Excel =======================

		// MobileNo to SentOtp
		ExcelWriteData.DemoExcel(filePath, "PromoCode_UpdateUser", promo_code_reference_number, 1, 8);

		//			ExcelWriteData.excelWrite(filePath, "PromoCode_UpdateUser", promo_code_reference_number, 10, 8);


		String Resp = response.extract().body().asString();
		logger.info("Response Body= " + Resp);
		ExtentReporter.extentLogger("", "Response Body= " + Resp);


		return response;

	}

	//		catch(Exception e)
	//		{
	//			String message="PromoCodeAPI";
	//			ExtentReporter.extentLogger("",message);
	//			ExtentReporter.extentLoggerFail(e.getMessage());
	//			return null;
	//		}
	//
	//	}




	//LocationRequireAPI_Segment 1
	public static ValidatableResponse LocationRequireAPI() throws Exception {

		try
		{

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_LocationRequireAPI() throws Exception {

		try
		{

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_LocationRequireAPI() throws Exception {

		try
		{

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	PromoCode_LocationRequireAPI_Segment1
	public static ValidatableResponse PromoCode_LocationRequireAPI_Segment1() throws Exception {

		try
		{

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




	//	 playStore
	public static ValidatableResponse PlayStore_LocationRequireAPI() throws Exception {

		try
		{

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";


			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	 playStore_S1
	public static ValidatableResponse PlayStore_LocationRequireAPI_Segment1() throws Exception {

		try
		{

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";


			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_LocationRequireAPI_LTBC1() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_LTBC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Merchant_BC1
	public static ValidatableResponse Merchant_LocationRequireAPI_BC1() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Merchant_L1
	public static ValidatableResponse Merchant_LocationRequireAPI_L1() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L1);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


//	Merchant_L2
	public static ValidatableResponse Merchant_LocationRequireAPI_L2() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L2_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


//	Merchant_L3
	public static ValidatableResponse Merchant_LocationRequireAPI_L3() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L3_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_L3);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}

	
	
	//	Merchant_BC2
	public static ValidatableResponse Merchant_LocationRequireAPI_BC2() throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC2_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.locationEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			Random rand = new Random();


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_BC2);

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers,url);


			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}

		catch(Exception e)
		{
			String message="LocationRequireAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	// Segment 1
	public static ValidatableResponse GetPinDetailsAPI() throws Exception {

		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getPinDetailsEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			//		HashMap<String, String> req_body = new HashMap<>();
			//		req_body.put("otp", (String) data[0][0]);
			//		req_body.put("mobile_number", (String) data[0][1]);
			//		req_body.put("client_id", (String) data[0][2]);
			//		req_body.put("source_app", (String) data[0][3]);
			//
			//		JSONObject Myrequestbody = new JSONObject();
			//
			//		Myrequestbody.put("otp", req_body.get("otp"));
			//		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			//		Myrequestbody.put("client_id", req_body.get("client_id"));
			//		Myrequestbody.put("source_app", req_body.get("source_app"));
			//
			//		String req=String.valueOf(Myrequestbody);
			//		ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;
		}
		catch (Exception e) {
			String message="GetPinDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	Merchant
	public static ValidatableResponse Merchant_GetPinDetailsAPI() throws Exception {

		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getPinDetailsEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			//		HashMap<String, String> req_body = new HashMap<>();
			//		req_body.put("otp", (String) data[0][0]);
			//		req_body.put("mobile_number", (String) data[0][1]);
			//		req_body.put("client_id", (String) data[0][2]);
			//		req_body.put("source_app", (String) data[0][3]);
			//
			//		JSONObject Myrequestbody = new JSONObject();
			//
			//		Myrequestbody.put("otp", req_body.get("otp"));
			//		Myrequestbody.put("mobile_number", req_body.get("mobile_number"));
			//		Myrequestbody.put("client_id", req_body.get("client_id"));
			//		Myrequestbody.put("source_app", req_body.get("source_app"));
			//
			//		String req=String.valueOf(Myrequestbody);
			//		ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;
		}
		catch (Exception e) {
			String message="GetPinDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PromoCode
	public static ValidatableResponse PromoCode_GetPinDetailsAPI() throws Exception {

		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getPinDetailsEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;
		}
		catch (Exception e) {
			String message="GetPinDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}


	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_GetPinDetailsAPI_Segment1() throws Exception {

		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getPinDetailsEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;
		}
		catch (Exception e) {
			String message="GetPinDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PlayStore
	public static ValidatableResponse PlayStore_GetPinDetailsAPI() throws Exception {

		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getPinDetailsEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;
		}
		catch (Exception e) {
			String message="GetPinDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_GetPinDetailsAPI_Segment1() throws Exception {

		try {
			Random rand = new Random();
			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.getPinDetailsEndPoint);

			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token",
					"eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("header", header);


			ValidatableResponse response = Utilities.getMethodWithHeaderAPI(headers, url);

			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;
		}
		catch (Exception e) {
			String message="GetPinDetailsAPI";
			ExtentReporter.extentLoggerFail(message+" - Failed");	
		}
		return null;

	}



	//	Segment 1
	public static ValidatableResponse SendOtpForPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sentOtpForPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			//		req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			//		Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SendOtpForPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_SendOtpForPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sentOtpForPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);

			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);


			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("action", (String) data[0][0]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SendOtpForPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	PromoCode
	public static ValidatableResponse PromoCode_SendOtpForPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sentOtpForPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			//		req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			//		Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SendOtpForPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_SendOtpForPinAPI_Segment1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sentOtpForPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			//		req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			//		Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SendOtpForPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




	//	PlayStore
	public static ValidatableResponse PlayStore_SendOtpForPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sentOtpForPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			//		req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			//		Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SendOtpForPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	PlayStore_S1
	public static ValidatableResponse PlayStore_SendOtpForPinAPI_Segment1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.sentOtpForPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			//		req_body.put("encrypted_name", (String) data[0][1]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			//		Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SendOtpForPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	// Segment 1
	public static ValidatableResponse SetResetPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.setResetPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			req_body.put("otp", (String) data[0][1]);
			req_body.put("pin_hash", (String) data[0][2]);
			req_body.put("salt", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("pin_hash", req_body.get("pin_hash"));
			Myrequestbody.put("salt", req_body.get("salt"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SetResetPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Merchant
	public static ValidatableResponse Merchant_SetResetPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_Merchant_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.setResetPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_MerchantQRCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			req_body.put("otp", (String) data[0][1]);
			req_body.put("pin_hash", (String) data[0][2]);
			req_body.put("salt", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("pin_hash", req_body.get("pin_hash"));
			Myrequestbody.put("salt", req_body.get("salt"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch(Exception e)
		{
			String message="SetResetPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	PromoCode
	public static ValidatableResponse PromoCode_SetResetPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.setResetPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			req_body.put("otp", (String) data[0][1]);
			req_body.put("pin_hash", (String) data[0][2]);
			req_body.put("salt", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("pin_hash", req_body.get("pin_hash"));
			Myrequestbody.put("salt", req_body.get("salt"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SetResetPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




	//	PromoCode_Segment1
	public static ValidatableResponse PromoCode_SetResetPinAPI_Segment1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.setResetPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PromoCode_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_PromoCode_S1();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			req_body.put("otp", (String) data[0][1]);
			req_body.put("pin_hash", (String) data[0][2]);
			req_body.put("salt", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("pin_hash", req_body.get("pin_hash"));
			Myrequestbody.put("salt", req_body.get("salt"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_promocode_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");


			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;

		}
		catch(Exception e)
		{
			String message="SetResetPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	PlayStore
	public static ValidatableResponse PlayStore_SetResetPinAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.setResetPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			req_body.put("otp", (String) data[0][1]);
			req_body.put("pin_hash", (String) data[0][2]);
			req_body.put("salt", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("pin_hash", req_body.get("pin_hash"));
			Myrequestbody.put("salt", req_body.get("salt"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SetResetPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




	//	PlayStore_S1
	public static ValidatableResponse PlayStore_SetResetPinAPI_Segment1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.userGatewayURL.concat(RingPay_Endpoints.setResetPinEndPoint);
			logger.info("Url :" + url);
			ExtentReporter.extentLogger("url", url);


			//			ValidatableResponse userTokenResponse = com.business.RingPay_PlayStore_Journey_Segment1.RegisterUser_UserAuthenticate.userToken_Positive();
			//
			//			String user_token = userTokenResponse.extract().body().jsonPath().get("data.user_token");
			//			logger.info("UserToken: " + user_token);
			//			ExtentReporter.extentLogger("UserToken: ",user_token);



			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			// System.out.println((String) data[0][3]);
			req_body.put("action", (String) data[0][0]);
			req_body.put("otp", (String) data[0][1]);
			req_body.put("pin_hash", (String) data[0][2]);
			req_body.put("salt", (String) data[0][3]);

			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("action", req_body.get("action"));
			Myrequestbody.put("otp", req_body.get("otp"));
			Myrequestbody.put("pin_hash", req_body.get("pin_hash"));
			Myrequestbody.put("salt", req_body.get("salt"));


			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);


			HashMap<String, Object> headers = new HashMap<>();
			headers.put("Authorization", "Bearer " + user_token_playstore_S1);
			headers.put("x-request-id", rand.nextInt(1001));
			headers.put("X-Client-App", "android");
			headers.put("X-Client-Version", "1.0.1");
			headers.put("X-Client-OS-Type", "android");
			headers.put("X-Client-OS-Version", 28);
			headers.put("x-login-token","eyJhbGciOiJSUzI1NiIsIng");
			headers.put("x-login-nonce", "97D6AEE40256321930BD5DBC2316493F81A0783D");
			headers.put("x-login-timestamp", "1634728700217");



			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="SetResetPinAPI";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Ring Policy
	public static ValidatableResponse RingPolicyAPI(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="Segment1API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Ring Policy_LTBC1
	public static ValidatableResponse RingPolicyAPI_LTBC1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_LTBC1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="RingPolicyAPI_LTBC1";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}



	//	Ring Policy_BC1
	public static ValidatableResponse RingPolicyAPI_BC1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="RingPolicyAPI_BC1";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


	//	Ring Policy_L1
	public static ValidatableResponse RingPolicyAPI_L1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="RingPolicyAPI_L1";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}


//	Ring Policy_L2
	public static ValidatableResponse RingPolicyAPI_L2(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L2_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="RingPolicyAPI_L2";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}

	
//	Ring Policy_L3
	public static ValidatableResponse RingPolicyAPI_L3(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_L3_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);

			return response;

		}
		catch(Exception e)
		{
			String message="RingPolicyAPI_L3";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}
	

	//	Ring Policy_BC2
	public static ValidatableResponse RingPolicyAPI_BC2(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_RingPolicy_BC2_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="RingPolicyAPI_BC2";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




	//	RingPolicyAPI_PromoCode_S1
	public static ValidatableResponse RingPolicyAPI_PromoCode_S1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PromoCode_S1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="Segment1API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}






	//	Ring Policy_Playstore_S1
	public static ValidatableResponse RingPolicyAPI_PlayStore_S1(Object[][] data) throws Exception {

		try
		{

			String filePath = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\Datasheet\\RingPayAPI_TestData_PlayStore_S1_stage.xlsx";

			String url = RingPay_BaseURL.testingServiceURL.concat(RingPay_Endpoints.dummyCibilEndPoint);
			logger.info("Url :" + url);

			ExtentReporter.extentLogger("url", url);

			Random rand = new Random();

			HashMap<String, String> req_body = new HashMap<>();
			req_body.put("user_reference_number", (String) data[0][0]);
			req_body.put("score", (String) data[0][1]);
			req_body.put("encrypted_name", (String) data[0][2]);
			req_body.put("cibil_user_name", (String) data[0][3]);
			req_body.put("is_only_thin_cibil_loan_accounts", (String) data[0][4]);


			JSONObject Myrequestbody = new JSONObject();

			Myrequestbody.put("user_reference_number", req_body.get("user_reference_number"));
			Myrequestbody.put("score", req_body.get("score"));
			Myrequestbody.put("encrypted_name", req_body.get("encrypted_name"));
			Myrequestbody.put("cibil_user_name", req_body.get("cibil_user_name"));
			Myrequestbody.put("is_only_thin_cibil_loan_accounts", req_body.get("is_only_thin_cibil_loan_accounts"));

			String req=String.valueOf(Myrequestbody);
			ExtentReporter.extentLogger("req_body", "Request :"+req);

			HashMap<String, Object> headers = new HashMap<>();
			headers.put("client-id", "zx2789");

			String header=String.valueOf(headers);
			ExtentReporter.extentLogger("headers","Headers :"+ header);


			ValidatableResponse response = Utilities.postMethodAPI(headers, Myrequestbody, url);

			logger.info("Request :" + Myrequestbody);
			ExtentReporter.extentLogger("", "Request :" + Myrequestbody);
			String Resp = response.extract().body().asString();
			logger.info("Response Body= " + Resp);
			ExtentReporter.extentLogger("", "Response Body= " + Resp);


			return response;


		}
		catch(Exception e)
		{
			String message="Segment1API";
			ExtentReporter.extentLogger("",message);
			ExtentReporter.extentLoggerFail(e.getMessage());
			return null;
		}

	}




}