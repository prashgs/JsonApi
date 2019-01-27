package JsonApi;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class Planets 
{
	public Planets(String url)
	{
		setUrl(url);
	}
	
	private String url;
	
	public String getUrl() 
	{
		return url;
	}

	public void setUrl( String Url ) 
	{
		this.url = Url;
	}
	
	private Response response;
	
	public Response getResponse() 
	{
		return response;
	}

	public void setResponse(Response response) 
	{
		this.response = response;
	}
	
	private int httpStatusCode;
	
	public int getHttpStatusCode() 
	{
		return httpStatusCode;
	}

	public void setHttpStatusCode(int statusCode) 
	{
		this.httpStatusCode = statusCode;
	}
	
	public Planets DoGetRequest()
	{
		try 
		{
			RestAssured.defaultParser = Parser.JSON;
			Response response = RestAssured.get(getUrl());
			setHttpStatusCode(response.getStatusCode());
			setResponse(response);
		}
		catch(Exception e) 
		{
			setResponse(null);
		}
		return this;
	}

}
