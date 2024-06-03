package JsonManipulation;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class getsingleEmployeeData 
{
	
	@Test
	public void passBodyAsMultiplepayload()
	{
		 // Define base URI and path
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        String basePath = "/booking/1"; // Assuming you are updating booking with ID 1

        // Create JSON object representing request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("firstname", "James");
        requestBody.put("lastname", "Brown");
        requestBody.put("totalprice", 111);
        requestBody.put("depositpaid", true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2018-01-01");
        bookingDates.put("checkout", "2019-01-01");

        requestBody.put("bookingdates", bookingDates);
        requestBody.put("additionalneeds", "Breakfast");

        // Send PUT request and validate response
        given()
            .contentType(ContentType.JSON)
            .header("Accept", "application/json")
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .body(requestBody.toString())
        .when()
            .put(basePath)
        .then()
            .statusCode(200)
            .body("firstname", equalTo("James"))
            .body("lastname", equalTo("Brown"))
            .body("totalprice", equalTo(111))
            .body("depositpaid", equalTo(true))
            .body("bookingdates.checkin", equalTo("2018-01-01"))
            .body("bookingdates.checkout", equalTo("2019-01-01"))
            .body("additionalneeds", equalTo("Breakfast"));
	}

}
