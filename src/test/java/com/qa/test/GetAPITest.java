package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	public String apiUrl;
	public String serviceUrl;
	public String actualURL;
	public TestBase testBase;
	public CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		apiUrl = prop.getProperty("apiURL");
		serviceUrl = prop.getProperty("serviceURL");
		
		actualURL = apiUrl+serviceUrl;

	}
	
	@Test
	public void getWithoutHeaderTest() throws ClientProtocolException, IOException {
	
		RestClient restClient = new RestClient();
		httpResponse = restClient.get(actualURL);
		
		//Status Code
	   	int statusCode =	httpResponse.getStatusLine().getStatusCode();
	   	System.out.println("Status code is : " +statusCode );
	   	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200 , "Status Code is not 200");
	   	
	   	//JSON String
	   	String responseString = EntityUtils.toString(httpResponse.getEntity() , "UTF-8");
	  	JSONObject responseJson = new JSONObject(responseString);
	   	System.out.println("Response is : " +responseJson);
	   	
	   	//Headers Data
	   	Header[] headersArray =  httpResponse.getAllHeaders();
	    HashMap<String , String> allHeader = new HashMap<String , String>();
	    
	    for(Header header : headersArray) {
	    	allHeader.put(header.getName(), header.getValue());
	    }
	   	System.out.println("Headers array : " +allHeader);

//	   	//single value - JSON Object
//		String pages = TestUtil.getValueByJPath(responseJson, "/pagination");
//		System.out.println("Value of per page attribute is : " +pages);
//		
//		//JSON Array 	
	   	
	}

	
	@Test
	public void getWithHeaderTest() throws ClientProtocolException, IOException {
	
		RestClient restClient = new RestClient();
		
		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Authorization", "Bearer e43ee9ec8a6fa6430dcc2b64f02c98989cb637c099725721813dbde784eeeb8e");
		headerMap.put("User-Agent", "PostmanRuntime/7.28.2");
//		headerMap.put(apiUrl, actualURL)
		
		
		httpResponse = restClient.get(actualURL , headerMap);
		
		//Status Code
	   	int statusCode =	httpResponse.getStatusLine().getStatusCode();
	   	System.out.println("Status code is : " +statusCode );
	   	Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200 , "Status Code is not 200");
	   	
	   	//JSON String
	   	String responseString = EntityUtils.toString(httpResponse.getEntity() , "UTF-8");
	  	JSONObject responseJson = new JSONObject(responseString);
	   	System.out.println("Response is : " +responseJson);
	   	
	   	//Headers Data
	   	Header[] headersArray =  httpResponse.getAllHeaders();
	    HashMap<String , String> allHeader = new HashMap<String , String>();
	    
	    for(Header header : headersArray) {
	    	allHeader.put(header.getName(), header.getValue());
	    }
	   	System.out.println("Headers array : " +allHeader);

		
	}
}
