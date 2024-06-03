package OnlyForQA;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

public class MultipleApiIntegrationTest {

	private static final String BASE_URL = "https://restful-booker.herokuapp.com/";
	int bookingId;
	RequestSpecification requestSpecification;
	Response response;
	ValidatableResponse validatableResponse;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    
    @Test(priority=0)
    public void testCreateBooking() {
        String requestBody = "{ \"firstname\": \"John\", \"lastname\": \"Doe\", \"totalprice\": 100,"
        		+ " \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2024-06-01\", \"checkout\": \"2024-06-02\" }, \"additionalneeds\": \"Breakfast\" }";
        response = RestAssured.given().contentType("application/json").body(requestBody).post("/booking");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Expected status code: 200");
        
        // Validate common JSON response values
        String responseBody = response.getBody().asString();
        System.out.println("testCreateBooking: "+responseBody);

        Assert.assertTrue(responseBody.contains("bookingid"));
        Assert.assertTrue(responseBody.contains("firstname"));
        Assert.assertTrue(responseBody.contains("lastname"));
        // Add more validations as per your requirements
        
     // Parse JSON response to extract booking ID
        JsonObject respBody = new Gson().fromJson(response.getBody().asString(), JsonObject.class);
        bookingId = respBody.get("bookingid").getAsInt();
        //System.out.println("bookingId: "+bookingId);
    }
    
    @Test(priority=1)
    public void testGetBookingDetails() {
        
    	
        response = RestAssured.get("/booking/" + bookingId);
        int statusCode = response.getStatusCode();
        
        Assert.assertEquals(statusCode, 200, "Expected status code: 200");
        
        // Validate common JSON response values
        String responseBody = response.getBody().asString();
        System.out.println("testGetBookingDetails: " + responseBody);

        /*Assert.assertTrue(responseBody.contains("bookingid"));
        Assert.assertTrue(responseBody.contains("firstname"));
        Assert.assertTrue(responseBody.contains("lastname"));*/
        // Add more validations as per your requirements
    }
    
    @Test(priority=2)
    public void updateUser() 
    {
    	String jsonString= "{\r\n"
    			+ "    \"firstname\" : \"Ram\",\r\n"
    			+ "    \"lastname\" : \"Govind\",\r\n"
    			+ "    \"totalprice\" : 111,\r\n"
    			+ "    \"depositpaid\" : true,\r\n"
    			+ "    \"bookingdates\" : {\r\n"
    			+ "        \"checkin\" : \"2018-01-01\",\r\n"
    			+ "        \"checkout\" : \"2019-01-01\"\r\n"
    			+ "    },\r\n"
    			+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
    			+ "}";
    	
    	Map<String,String> data =  new HashMap<String , String>();
		data.put("firstname", "Tata Nexon");
		data.put("totalprice", "928000");
		
    	RestAssured.given()             
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body(data)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/"+bookingId)
                .then() 
                .assertThat().statusCode(200);

 
       // System.out.println("updateBookingDetails :" + RestAssured.);
    }
    
    @Test(priority=3)
	public void DeleteBooking()
	{
		
		validatableResponse = given()
                .baseUri("https://restful-booker.herokuapp.com/booking/"+bookingId)
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .delete()
                .then()
                .assertThat().statusCode(201);     
	
	}

   
}
