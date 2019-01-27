package JsonApi.Tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.testng.Assert;
import org.testng.annotations.Test;

import JsonApi.Planets;
import io.restassured.response.Response;

public class JsonRequest {
	
	@Test
	public void TestOkResponseCode() throws MalformedURLException
	{
		URL url = new URL("https://swapi.co/api/planets/");
		Planets planets = new Planets(url.toString());
		int responseCode = planets.DoGetRequest().getHttpStatusCode();
		Assert.assertEquals(200, responseCode);
	}
	
	@Test
	public void TestValidResponse() throws MalformedURLException
	{
		URL url = new URL("https://swapi.co/api/planets/");
		Planets planets = new Planets(url.toString());
		Response response = planets.DoGetRequest().getResponse();	
		Assert.assertNotNull(response);
		Assert.assertTrue(!response.jsonPath().getList("results").isEmpty());
	}
	
	@Test
	public void TestErrorResponseCode() throws MalformedURLException
	{
		URL url = new URL("https://swapi.co/api/planet");
		Planets planets = new Planets(url.toString());
		int responseCode = planets.DoGetRequest().getHttpStatusCode();
		Assert.assertNotEquals(200, responseCode);
	}
	
	@Test
	public void TestValidResponseForId() throws MalformedURLException
	{
		int id=25;
		URL url = new URL("https://swapi.co/api/planets/"+id);
		Planets planets = new Planets(url.toString());
		Response response = planets.DoGetRequest().getResponse();	
		String planetName = response.jsonPath().getString("name");
		Assert.assertNotNull(planetName);
	}

	@Test
	public void TestInValidResponseForId() throws MalformedURLException
	{
		int id=65;
		URL url = new URL("https://swapi.co/api/planets/"+id);
		Planets planets = new Planets(url.toString());
		Response response = planets.DoGetRequest().getResponse();	
		int responseCode = planets.DoGetRequest().getHttpStatusCode();
		String planetName = response.jsonPath().getString("name");
		Assert.assertNotEquals(200, responseCode);
		Assert.assertNull(planetName);

	}

	
}
