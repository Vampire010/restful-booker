package OnlyForQA;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.testng.annotations.Test;

public class CreateBooking 
{
	RequestSpecification _requestSpecification;
    Response _response;
    ValidatableResponse _validatableResponse;
    
	String jsonString ="{\r\n"
			+ "    \"firstname\" : \"Jim\",\r\n"
			+ "    \"lastname\" : \"Brown\",\r\n"
			+ "    \"totalprice\" : 111,\r\n"
			+ "    \"depositpaid\" : true,\r\n"
			+ "    \"bookingdates\" : {\r\n"
			+ "        \"checkin\" : \"2018-01-01\",\r\n"
			+ "        \"checkout\" : \"2019-01-01\"\r\n"
			+ "    },\r\n"
			+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
			+ "}";
	
	@Test
	public void createBooking()
	{
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";		
		//create a _requestSpecification
		_requestSpecification = RestAssured.given();
		
		_requestSpecification.contentType(ContentType.JSON);
		_requestSpecification.body(jsonString);
		_response = _requestSpecification.post();
		
		_validatableResponse = _response.then();
		_validatableResponse.statusCode(200);
		_validatableResponse.statusLine("HTTP/1.1 200 OK");
	}
}
