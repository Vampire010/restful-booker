package OnlyForQA;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class UpdateBooking {
	RequestSpecification _requestSpecification;
	Response _response;
	ValidatableResponse  _validatableResponse;

	@Test
	public void getBookingDetailsWithID()
	{
		// Base URL of the API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        // JSON payload for updating booking
        String jsonString = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";

     // Update 
        _validatableResponse = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/1")
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(jsonString)
                .when()
                .put()
                .then()
                .assertThat().statusCode(200)
                .body("firstname", equalTo("James"));
 
        System.out.println(_validatableResponse.extract().asPrettyString());
 
    } 
}

