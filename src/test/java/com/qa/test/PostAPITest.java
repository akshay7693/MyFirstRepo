package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.User;

public class PostAPITest extends TestBase {

	TestBase testBase ;
	String apiUrl;
	String serviceUrl;
	String url;
	RestClient restClient;
	CloseableHttpResponse httpResponse;
	
	
	
	@BeforeMethod
	public void setUp() {
		
		testBase = new TestBase();
		apiUrl = prop.getProperty("apiUrl");
		serviceUrl = prop.getProperty("serviceUrl");
		url = apiUrl + serviceUrl;
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
	
		restClient = new RestClient();
		HashMap<String , String> headerMap = new HashMap<String , String>();
		//headerMap.put("Authorization", "Bearer e43ee9ec8a6fa6430dcc2b64f02c98989cb637c099725721813dbde784eeeb8e");
		headerMap.put("User-Agent", "PostmanRuntime/7.28.2");
		headerMap.put("Control-Type", "application/json");
		
		//jackson api
		ObjectMapper mapper = new ObjectMapper();
		User user = new User("testpost" , "a@test.com" , "male" , "active");
		
		//object --> JSON
		mapper.writeValue(new File("C:\\Users\\akspo\\eclipse-workspace\\restapi\\src\\main\\java\\com\\qa\\data\\users.json"), user);
		
		//object to json in string
		
		String userJsonString = mapper.writeValueAsString(user);
		System.out.println(userJsonString);
		
		httpResponse = restClient.post(url, userJsonString, headerMap);
		
		//1. Status
		int status = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, TestBase.RESPONSE_STATUS_CODE_201, "Status in not 201");
		
		//2. JSON String
		String resposneString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject responseJson = new JSONObject(resposneString);
		System.out.println("Response from API is : " +responseJson);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
