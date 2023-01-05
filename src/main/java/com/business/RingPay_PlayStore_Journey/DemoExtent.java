package com.business.RingPay_PlayStore_Journey;

import com.utility.ExtentReporter;

public class DemoExtent {
	private com.business.RingPay_PlayStore_Journey.RegisterUser_Mock_User mockuser;

	public void ggshs() throws Exception {
		ExtentReporter.HeaderChildNode("PlayStore");
		
		mockuser=new com.business.RingPay_PlayStore_Journey.RegisterUser_Mock_User();
		mockuser.mock_User_Positive();
		System.out.println("userDetailsAPI,Validation is Done");
		

		
	}
	
}
