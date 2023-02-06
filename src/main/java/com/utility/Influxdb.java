package com.utility;

import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;

import io.restassured.response.ValidatableResponse;

//	public class InfluxDB2Example {
//	  public static void main(final String[] args) {


import java.time.Instant;
import java.util.List;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.WriteApi;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.*;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxTable;

//		  public class InfluxResources {
public class Influxdb {
	private InfluxDBClient client;

	public static String passbyval(String testCaseName,int responsecode,long ResponseTime)
	{
		// You can generate an API token from the "API Tokens Tab" in the UI
		//		String token = "PECJIBNuuLMcc2kGab4VjduuxUMu2tzjpiEHo1n1QwuB-xjNag1eCqKy8jhpNhxv6kh_i8FvlioYm4Bac8mNzQ==";
		//		String bucket = "Ringpay";
		//		String org = "Collabera";

		//		  		VM-token
		String token = "oBhb1odC8ChqQP0rJi50tNZ7sHj34de7mhwHCeHmfhI0VUDCnxFoLZVP4iNNmKcoXihvYyGFebi_KutbHgdjOw==";
		String bucket = "API_Auto";
		String org = "Collabera_API";


		InfluxDBClient client = InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());

		//create point measurement for influx db or adding fields
		Point point = Point.measurement("APIMonitoring")
				.addTag("description", testCaseName)
				.addField("Response Code : ",  responsecode)
				.addField("Time", ResponseTime)
				;
		//writing the data to influx db
		WriteApiBlocking writeApi = client.getWriteApiBlocking();
		writeApi.writePoint(bucket, org, point);
		return org;
	}


	//		  	public static String time(String testCaseName,ValidatableResponse response,int responsecode)
	//		  	{
	//		  	long Time = response.extract().time();
	//			String TimeMS = String.valueOf(Time+" ms");
	//			System.out.println("responseTime :"+TimeMS);
	//			
	//			String value=Influxdb.passbyval("RegisterUser_Mock_User",responsecode, Time);
	//			return value;
	//		  
	//		  	}


}

