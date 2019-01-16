package com.api.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
	
	//1.Get method without headers
	
	public  CloseableHttpResponse Get(String url) throws Exception, IOException
	{
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //get request
		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget);// hit the url
		return closablehttpresponse;
		
	}


	//2.Get method With headers

	public  CloseableHttpResponse Get(String url, HashMap<String, String> headersMap) throws Exception, IOException
	{
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); //get request
		for (Map.Entry<String, String> entry: headersMap.entrySet())
		{
			httpget.addHeader(entry.getKey(), entry.getValue());
			
		}

		CloseableHttpResponse closablehttpresponse = httpclient.execute(httpget);// hit the url
		return closablehttpresponse;

	}

	//3. Post method

	public CloseableHttpResponse post(String url,String entitystring, HashMap<String, String> headersMap) throws Exception
	{
		CloseableHttpClient httpclient= HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url); //http post request
        httppost.setEntity(new StringEntity(entitystring)); //payload


        //for headers
        for (Map.Entry<String, String> entry: headersMap.entrySet())
        {
            httppost.addHeader(entry.getKey(), entry.getValue());

        }
        CloseableHttpResponse closablehttpresponse = httpclient.execute(httppost);// hit the url
        return closablehttpresponse;
            
        }
		

	}
	
	
	

