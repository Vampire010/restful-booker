package JsonManipulation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class dataParameterization {
	
	 @Test(dataProvider = "bookingData")
	    public void testCreateBooking(String firstName, String lastName, int totalCost) 
	 	{
	        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";

	        // JSON payload for creating a booking
	        String jsonPayload = "{\"firstname\": \"" + firstName + "\", " +
	                             "\"lastname\": \"" + lastName + "\", " +
	                             "\"totalprice\": " + totalCost + ", " +
	                             "\"depositpaid\": true, " +
	                             "\"bookingdates\": {\"checkin\": \"2024-06-01\", \"checkout\": \"2024-06-05\"}, " +
	                             "\"additionalneeds\": \"Breakfast\"}";

	        // Make POST request to create a booking
	        Response response = given()
	                                .contentType("application/json")
	                                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
	                                .body(jsonPayload)
	                            .when()
	                                .post("/booking");

	        // Assert response
	        response.then().statusCode(200);
	    }

	    @DataProvider(name = "bookingData")
	    public Object[][] bookingData() {
	        return new Object[][] {
	            {"John", "Doe", 200},
	            {"Alice", "Smith", 250},
	            // Add more test data as needed
	        };
	    }
	}