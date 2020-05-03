package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="51838"; //Hard coded - input for Get details for single employee
	
	public static Logger logger;
			
	@BeforeClass
	public void setup() {
		logger = logger.getLogger("EmployeeRestApi");
		PropertyConfigurator.configure("Log4j.properties"); //added logger
		//logger.setLevel(Level.DEBUG);
		logger.setLevel(Level.DEBUG);
		
	}
	

}
