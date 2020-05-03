package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_Delete_Employee_Record extends TestBase {

	@BeforeClass
	void deleteEmployee() throws InterruptedException {
		
		logger.info("****************** Started TC005_Delete_Employee_Record **********" );
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest =RestAssured.given();
		
		response = httpRequest.request(Method.GET,"/employees");
		
		//First get the JsonPath object instance from the response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		
		//Capture ID
		String empID=jsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE,"/delete/"+empID);
				
		Thread.sleep(5000);
		
	}

	@Test
	void checkResponseBody() {
		
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains("sucessfully! deleted records"),true);
		
		
		
	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode(); //Getting status code
		logger.info("********** Checking Status Code ************");
		Assert.assertEquals(statusCode, 200);
		
	}
	@Test 
	void checkReponseTime() {
		logger.info("********** Checking response code ***********");
		long responseTime = response.getTime();
		logger.info("Response Time is ==>" + responseTime );
		
		if (responseTime > 6000) {
			logger.warn("Response Time is greater than 6000");
			
			Assert.assertTrue(responseTime<6000);
			}
	}
	@Test
	void checkstatusLine() {
		logger.info("************* Checking Status Line **************");
		String statusLine = response.getStatusLine();
		logger.info("Status line is==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	@Test
	void checkContentType() {
		logger.info("********** Checking content type *********");
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>"+contentType);
		Assert.assertEquals(contentType,"text/html; charset=UTF-8");
	}

	@Test
	void checkserverType() {
		logger.info("************ Checking server type ************");
		String serverType = response.header("Server");
		logger.info("Server Type is ==>" + serverType);
		Assert.assertEquals(serverType, "nginx/1.14.1");
	}

	@Test
	void checkcontentEncoding() {
		logger.info("************ Checking content encoding ************");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is ==>" + contentEncoding);
		Assert.assertEquals(contentEncoding, "gzip");
	}

	@Test
	void checkCookies() {
		logger.info("************** Checking Cookies ***********");
		String cookie = response.getCookie("PHPSESSID");
			
	}

	@AfterClass
	void tearDown() {
		logger.info("*********** Finished TC005_Delete_Employee_Record ************");
		
	}	
	
}
