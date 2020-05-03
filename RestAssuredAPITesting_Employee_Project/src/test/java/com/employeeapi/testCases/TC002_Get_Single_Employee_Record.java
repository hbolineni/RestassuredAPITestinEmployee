package com.employeeapi.testCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC002_Get_Single_Employee_Record extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger.info("***************Started TC002_Get_Single_Employee_Record **********" );
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees/" + empID);
		
		Thread.sleep(3000);
		
	}
	
	@Test
	public void checkReposeBody() {
		logger.info("********* Checking Reponse Body ************");
		String responseBody = response.getBody().asString();
		logger.info("Response Body==> " + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);
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
	void checkContentLength() {
		logger.info("************ checking content length **********");
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is ==>" + contentLength);
		
		if(Integer.parseInt(contentLength)<100){
				logger.warn("Content Length is less that 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength)<1500);
	}
			
	@Test
	void checkCookies() {
		logger.info("************** Checking Cookies ***********");
		String cookie = response.getCookie("PHPSESSID");
			
	}
	
	@AfterClass
	void tearDown() {
		logger.info("*********** Finished TC002_Get_Single_Employee_Record ************");
		
	}	
		
}
