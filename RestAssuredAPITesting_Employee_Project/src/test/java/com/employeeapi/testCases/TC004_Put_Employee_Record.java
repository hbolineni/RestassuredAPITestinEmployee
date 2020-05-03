package com.employeeapi.testCases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.base.TestBase;
import com.employeeapi.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC004_Put_Employee_Record extends TestBase{

	RequestSpecification httpRequest;
	Response response;
	JSONObject requestParams;

	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSal();
	String empAge = RestUtils.empAge();

	@BeforeClass
	void createEmployee() throws InterruptedException {
		
		logger.info("****************** Started TC004_Put_Employee_Record **********" );
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		httpRequest =RestAssured.given();
		
		//JSONObject is a class that represents a simple JSON. We can add key-value  pairs using the  put method
		//{"name":"john123x","salary":"123","age":"23"}
		requestParams =new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		//Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type","application/Json");
		
		//Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT,"/update"+empID);
		
		Thread.sleep(5000);
		
	}

	@Test
	void checkResponseBody() {
		
		String responseBody=response.getBody().asString();
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge),true);
			
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
		logger.info("*********** Finished TC004_Put_Employee_Record ************");
		
	}	
	
	
	
	
}
